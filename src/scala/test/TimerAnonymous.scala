package scala.test

/**
 * Created by jasonzhu on 30/1/15.
 */
object TimerAnonymous {
  def oncePerSec(callback: ()=>Unit): Unit =  {
    while(true) {
      callback()
      Thread sleep 1000
    }
  }

  def main(args: Array[String]): Unit = {
    oncePerSec(() => {println("time flies again.."); println("111")})
  }
}
