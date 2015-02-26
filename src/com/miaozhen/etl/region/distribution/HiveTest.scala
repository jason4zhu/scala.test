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
 * Launch-command: spark-submit --class "com.miaozhen.etl.region.distribution.HiveTest" --master yarn-cluster --num-executors 16 --driver-memory 8g --executor-memory 4g --executor-cores 4 scala.test.jar
 */
object HiveTest {

  def main(args: Array[String]): Unit = {

    // -- configuration --
    val hconf = new Configuration()
    val conf = new SparkConf().setAppName("HiveTest_[ZHUDI]")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val hqlContext = new org.apache.spark.sql.hive.HiveContext(sc)





    println("JUDKING_HEADER: HiveTest")

    //--SUCCEED--
//    hqlContext.sql("CREATE TEMPORARY FUNCTION ip_to_region as 'com.miaozhen.diablo.mzhiveudf.IpToRegionFunction'")
//    hqlContext.sql("CREATE TEMPORARY FUNCTION row_to_map AS 'com.miaozhen.diablo.mzhiveudf.RowToMap'")
//    hqlContext.sql("set ca-meta=ALL")


    //--SUCCEED--
//    hqlContext.sql("show databases").collect().foreach(println)


    /**
     * --FAIL--
     * 应该是因为UDF返回的是java.util.Map，然后现在的Spark还不能正常处理
     */
//    hqlContext.sql(
//      """
//        FROM logbase_db.logbase_2
//        SELECT row_to_map(doc) AS row,dateid
//        WHERE dateid >= 20140318 AND dateid <=20140318
//        limit 10
//      """.stripMargin).collect().foreach(println)



    //--SUCCEED--
//    hqlContext.sql("use logbase_db")
//    hqlContext.sql("set ca-meta=ALL")
//    hqlContext.sql("select * from logbase_test limit 10").collect().foreach(println)




    //--SUCCEED--
////    hqlContext.sql("CREATE TEMPORARY FUNCTION ip_to_region as 'com.miaozhen.etl.region.distribution.IpToRegionFunction'")
//    hqlContext.sql("CREATE TEMPORARY FUNCTION ip_to_region as 'com.miaozhen.diablo.mzhiveudf.IpToRegionFunction'")
//    hqlContext.sql(
//      """
//        FROM logbase_db.zhudi_ip_test
//        SELECT ip_to_region(ip, "/tong/data/resource/dicmanager/IPlib-Region-000000000000000000000008-top100-top100-top100-merge-20141024182445")
//      """.stripMargin).collect().foreach(println)


    //--FAIL-- | 只要有java.util.Map, 必挂
//    hqlContext.sql("CREATE TEMPORARY FUNCTION row_to_map as 'com.miaozhen.etl.region.distribution.RowToMap'")
//    hqlContext.sql(
//      """
//        FROM (
//        FROM logbase_db.zhudi_ip_test
//          SELECT row_to_map(ip) as row
//        ) tmp
//          SELECT row
//      """.stripMargin).collect().foreach(println)


  }
}


