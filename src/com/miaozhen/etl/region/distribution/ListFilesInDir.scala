package com.miaozhen.etl.region.distribution

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileStatus, Path, FileSystem}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by jasonzhu on 31/1/15.
 */


/**
 * 由于spark兼具spark、scala、hadoop三类包，所以需要用spark命令执行此main方法
 * Launch-command: spark-submit --class "com.miaozhen.etl.region.distribution.ListFilesInDir" scala.test.jar
 */
object ListFilesInDir {

  def main(args: Array[String]): Unit =   {
    val hconf = new Configuration
    val fs = FileSystem.get(hconf)
    val path = new Path("/tong/data/output/dailyMerger/20140318")
    fs.listStatus(path).foreach(fss => {
      println(fss.getPath.toString)
    })
  }
}
