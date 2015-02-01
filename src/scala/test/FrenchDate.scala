package scala.test

/**
 * Created by jasonzhu on 30/1/15.
 */

import java.text.DateFormat._
import java.util.{Locale}

object FrenchDate {
  def main(args: Array[String]): Unit = {
    val now = new java.util.Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println(df format now)
  }
}
