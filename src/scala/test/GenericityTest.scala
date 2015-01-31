package scala.test

/**
 * Created by jasonzhu on 31/1/15.
 */


class Reference[T]  {
  private var contents: T = _

  def set(value: T) { contents = value }
  def get: T = contents
}

object IntegerReference {
  def main(args: Array[String]): Unit = {
    val cell = new Reference[Int]
    cell.set(123)
    println(cell.get*2)
  }
}