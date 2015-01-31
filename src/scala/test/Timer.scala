package scala.test

/**
 * Created by jasonzhu on 30/1/15.
 */
object Timer {
  def oncePerSec(callback: ()=>Unit): Unit =  {
    while(true) {
      callback()
      Thread sleep 1000
    }
  }

  def timeFlies(): Unit = {
    println("time flies like an arrow...")
  }

  def main(args: Array[String]): Unit = {
    oncePerSec(timeFlies)
  }
}
