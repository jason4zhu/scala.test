package com.miaozhen.etl.region.distribution

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * Created by jasonzhu on 3/2/15.
 */
object SimpleSparkSQL {
  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setAppName("ETL_Region_Distribution_[ZHUDI]")
//    val sc = new SparkContext(conf)
//
//
//    // sc is an existing SparkContext.
//    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
//    // createSchemaRDD is used to implicitly convert an RDD to a SchemaRDD.
//    import sqlContext.createSchemaRDD
//
//    // Define the schema using a case class.
//    // Note: Case classes in Scala 2.10 can support only up to 22 fields. To work around this limit,
//    // you can use custom classes that implement the Product interface.
//    case class Person(name: String, age: Int)
//    val people = sc.textFile("examples/src/main/resources/people.txt").map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt))
//    people.registerTempTable("people")
//
//    // SQL statements can be run by using the sql methods provided by sqlContext.
//    val teenagers = sqlContext.sql("SELECT name FROM people WHERE age >= 13 AND age <= 19")
//
//    // The results of SQL queries are SchemaRDDs and support all the normal RDD operations.
//    // The columns of a row in the result can be accessed by ordinal.
//    teenagers.map(t => "Name: " + t(0)).collect().foreach(println)


  }


}
