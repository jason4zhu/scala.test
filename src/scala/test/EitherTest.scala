package scala.test

/**
 * Created by jasonzhu on 4/2/15.
 */

/**
 * Here's a quick comparison of the Option and Either approaches:
 * Either is just like Option.
 * Right is just like Some.
 * Left is just like None, except you can include content with it to describe the problem.
 */
object EitherTest {
  def divideXByY(x:Int, y:Int): Either[String, Int] =   {
    if (y == 0) Left("Cannot devide by 0.")
    else Right(x/y)
  }

  def main(args: Array[String]) {
    println(divideXByY(1,0))
    println(divideXByY(1,1))

    divideXByY(1, 0) match {
      case Left(s) => println("Answer: " + s)
      case Right(i) => println("Answer: " + i)
    }
  }
}
