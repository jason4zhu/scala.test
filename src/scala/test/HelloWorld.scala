package scala.test

/**
 * Created by jasonzhu on 30/1/15.
 */



object HelloWorld {
  def main(args: Array[String]) {
    println("Hey~~~~")
    System.out.println("123")

    //play with java class
    JavaClass.sayHi()
    val jc = new JavaClass
    jc.sayHiInPrivate()
  }
}
