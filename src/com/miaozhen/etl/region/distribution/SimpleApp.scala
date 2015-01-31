package com.miaozhen.etl.region.distribution

import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by jasonzhu on 27/1/15.
 */


/**
 * Launch-command: scala -classpath /home/hadoop/scala.test.jar com.miaozhen.etl.region.distribution.SimpleApp
 */
object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "/user/hadoop/zhudi/poem" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
