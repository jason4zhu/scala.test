package scala.test

/**
 * Created by jasonzhu on 4/2/15.
 */
object OptionTest {

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

  def toInt(in:String):Option[Int] =  {
    try {
      Some(Integer.parseInt(in.trim))
    } catch {
      case e:NumberFormatException => None
    }
  }


  def main(args: Array[String]): Unit = {
    //----sec 1
    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

    println("show(capitals.get( \"Japan\")) : " +
      show(capitals.get( "Japan")) )
    println("show(capitals.get( \"India\")) : " +
      show(capitals.get( "India")) )


    //----sec 2
    val a:Option[Int] = Some(5)
    val b:Option[Int] = None

    println("a.getOrElse(0): " + a.getOrElse(0) )
    println("b.getOrElse(10): " + b.getOrElse(10) )

    //----sec 3
    val a2:Option[Int] = Some(5)
    val b2:Option[Int] = None

    println("a.isEmpty: " + a2.isEmpty )
    println("b.isEmpty: " + b2.isEmpty )


    //----sec 4
    toInt("122we23") match {
      case Some(i) => println(i)
      case None => println("Not a number in String format.")
    }

    //----sec 5
    val bag = List("1", "2", "foo", "3", "bar")
    val sum = bag.flatMap(toInt).sum
    println(sum)
  }
}
