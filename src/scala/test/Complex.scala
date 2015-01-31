package scala.test

/**
 * Created by jasonzhu on 30/1/15.
 */
class Complex(real: Double, imaginary: Double) {
  //--version 1--
//  def re() = real
//  def im() = imaginary

  //--version 2--
  def re = real
  def im = imaginary



  override def toString() =
    "" + re + (if(im<0) "" else "+") + im + "i"

}

object ComplexNumbers {
  def main(args: Array[String]): Unit = {
    val c = new Complex(1.2, 3.4)

    //--version 1--
//    println("imaginary part: "+c.im())

    //--version 2--
    println("imaginary part: "+c.im)


    println(c.toString())
  }
}
