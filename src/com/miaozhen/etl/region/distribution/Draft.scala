package com.miaozhen.etl.region.distribution

/**
 * Created by jasonzhu on 8/2/15.
 */
object Draft {
  def main(args: Array[String]) {
    //    val res = sc.textFile("/user/hadoop/zhudi/etlsample.txt").map(
    //          _.split("\\^") map {
    //            field =>
    //              var pair = field.split('=')
    //              (pair(0) -> pair(1))
    //          } toMap
    //    ).collect()


    //    import sqlContext.createSchemaRDD
    //    case class ETL(uuid: String, ip: String, plt: Int)
    //    val res = sc.textFile("/user/hadoop/zhudi/etlsample.txt").map(
    //      _.split("\\^") map {
    //        field =>
    //          var pair = field.split('=')
    //          (pair(0) -> pair(1))
    //      } toMap
    //    ).map(kvs => ETL(kvs("uuid"), kvs("ip"), kvs("plt").trim.toInt))
    //    res.registerTempTable("etllog")


    //    val rcode = ipToRegion.evaluate("27.214.13.244", "/tong/data/resource/dicmanager/IPlib-Region-000000000000000000000008-top100-top100-top100-merge-20141024182445")
    //    println(rcode)



    //    for(r <- res) {
    //      println(r("uuid")+"\t"+r("ip")+"\t"+ipToRegion.evaluate(r("ip"), "/tong/data/resource/dicmanager/IPlib-Region-000000000000000000000008-top100-top100-top100-merge-20141024182445"))
    //    }

  }
}
