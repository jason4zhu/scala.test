package com.miaozhen.etl.region.distribution

import com.miaozhen.dm.sdk.inputformat.mr.DMCombineFileInputFormat
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileStatus, Path, FileSystem}
import org.apache.hadoop.io.{Text, LongWritable}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql._


/**
 * Created by jasonzhu on 31/1/15.
 */




/**
 * Launch-command: spark-submit --class "com.miaozhen.etl.region.distribution.EtlRegionDist" --master yarn-cluster --num-executors 16 --driver-memory 8g --executor-memory 4g --executor-cores 4 scala.test.jar 20141205
 */
object EtlRegionDist {

  /**
   * 传入HDFS文件夹路径，返回目录下所包含文件
   *
   * @param hconf
   * @param dir
   * @return
   */
  def getFilePaths(hconf: Configuration, dir: String): List[Path] =  {
    val fs = FileSystem.get(hconf)
    val path = new Path(dir)

    var res = List[Path]()
    fs.listStatus(path).foreach(fss => {
      res :+= fss.getPath
    })

    return res
  }

  def main(args: Array[String]): Unit = {
    if(args.length != 1)  {
      throw new IllegalArgumentException("Date(yyyyMMdd) should be specified as argument")
    }

    // -- configuration --
    val hconf = new Configuration()
    val conf = new SparkConf().setAppName("ETL_Region_Distribution_[ZHUDI]")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)


    // -- Read multiple HDFS files into a merged HadoopFileRDD --
    var curdate = args(0)
    println("CURDATE=["+curdate+"]")

    var hadoopFileSeqs = Seq[org.apache.spark.rdd.RDD[(org.apache.hadoop.io.LongWritable, org.apache.hadoop.io.Text)]]()
    getFilePaths(hconf, "/tong/data/output/dailyMerger/"+curdate).foreach(path => {
      val strPath = path.toString
      if(strPath contains "campaign")  {
        val hadoopFile = sc.newAPIHadoopFile(
          strPath,
          classOf[DMCombineFileInputFormat],
          classOf[LongWritable],
          classOf[Text],
          hconf)
        hadoopFileSeqs :+= hadoopFile
      }
    })
    val hadoopFiles = sc.union(hadoopFileSeqs)

    // -- Create SchemaRDD, mapping to data in HDFS files--
    val schema = StructType("uuid ip plt".split(" ").map(fieldName => StructField(fieldName, StringType, true)))
    val rowRDD = hadoopFiles.map(line =>
          line._2.toString.split("\\^") flatMap {
            field => {
              var pair = field.split("=", 2)
              if(pair.length == 2)
                Some(pair(0) -> pair(1))
              else
                None
            }
          } toMap
        ).map(kvs => Row(kvs("uuid"), kvs("ip"), kvs("plt").trim))
    val schemaRDD = sqlContext.applySchema(rowRDD, schema)
    schemaRDD.registerTempTable("etllog")


    val ipToRegion = new IpToRegionFunction
    sqlContext.registerFunction("ipToRegion", (x:String) => ipToRegion.evaluate(x, "/tong/data/resource/dicmanager/IPlib-Region-000000000000000000000008-top100-top100-top100-merge-20141024182445"))

    println("JUDKING_HEADER: Distrubution of Region.")

    var result = sqlContext.sql("SELECT ipToRegion(ip), count(distinct(uuid)) from etllog where plt='0' group by ipToRegion(ip)")
    println("DEBUG_STRING=["+result.toDebugString+"]")
    result.collect().foreach(println)


  }
}


