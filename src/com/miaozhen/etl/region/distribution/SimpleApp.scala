package com.miaozhen.etl.region.distribution

import org.apache.hadoop.io.{Text, IntWritable, SequenceFile}
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * Created by jasonzhu on 27/1/15.
 */


/**
 * Launch-command: scala -classpath /home/hadoop/scala.test.jar com.miaozhen.etl.region.distribution.SimpleApp
 */
object SimpleApp {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logFile = "/user/hadoop/zhudi/poem" // Should be some file on your system
    val logData = sc.textFile(logFile)   //.cache()





    //--Spark does provide two limited types of shared variables for two common usage patterns: broadcast variables and accumulators.--
//    val broadcastVar = sc.broadcast(Array(1,2,3))
//    println(broadcastVar.value)
//
//    val accum = sc.accumulator(0, "myAccumulator")
//    sc.parallelize(Array(1,2,3,4)).foreach(x => accum += x)
//    println(accum.value)



//    logData.map(line => (line, 1)).reduceByKey((a, b) => a+b).collect



//    val a = sc.parallelize(List("dog", "salmon", "salmon", "rat", "elephant"), 3)
//    val b = a.keyBy(_.length)
//    val c = sc.parallelize(List("dog","cat","gnu","salmon","rabbit","turkey","wolf","bear","bee"), 3)
//    val d = c.keyBy(_.length)
//    b.join(d).collect



//    val a = sc.parallelize(List("dog","cat","gnu","salmon","rabbit","turkey","wolf","bear","bee"), 3)
//    val b = sc.parallelize(List(1,1,2,2,2,1,2,2,2), 3)
//    val c = b.zip(a)
//    val d = c.combineByKey(List(_), (x:List[String], y:String) => y :: x, (x:List[String], y:List[String]) => x ::: y)
//    d.collect



//    logData.mapPartitions(lines => lines.map(_.split(" ").size))



//    val data = Array(1,2,3,4,5)
//    val distData = sc.parallelize(data)    //`Parallelized Collections`
//    println(distData.reduce((a, b) => a+b))



    //--wordcount--
    logData.flatMap(line => line.split(" ")).map(word =>
      (word.replaceAll("[^a-zA-Z0-9]", ""),1)).reduceByKey((a,b) => a+b).sortByKey()




    //--calculate line count with specific keyword in a file--
//    val numAs = logData.filter(line => line.contains("a")).count()
//    val numBs = logData.filter(line => line.contains("b")).count()
//    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
