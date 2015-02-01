package com.miaozhen.etl.region.distribution

import com.miaozhen.dm.sdk.inputformat.mr.DMCombineFileInputFormat
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileStatus, Path, FileSystem}
import org.apache.hadoop.io.{Text, LongWritable}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by jasonzhu on 31/1/15.
 */


/**
 * Launch-command: spark-submit --class "com.miaozhen.etl.region.distribution.EtlLineCount" --master yarn-cluster --num-executors 3 scala.test.jar
 */
object EtlLineCount {

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
    // -- configuration --
    val hconf = new Configuration()
    val conf = new SparkConf().setAppName("ETL_Line_Count_[ZHUDI]")
    val sc = new SparkContext(conf)


        // -- Read multiple HDFS files into a merged HadoopFileRDD --
        var hadoopFileSeqs = Seq[org.apache.spark.rdd.RDD[(org.apache.hadoop.io.LongWritable, org.apache.hadoop.io.Text)]]()
        getFilePaths(hconf, "/tong/data/output/dailyMerger/20140318").foreach(path => {
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

        // -- Computation --
        val lineCounts = hadoopFiles.map(line => (1L)).reduce((a, b) => (a+b))
        println("LineCounts=[%s]".format(lineCounts))
  }
}
