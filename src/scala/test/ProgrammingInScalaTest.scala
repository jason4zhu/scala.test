package scala.test

import java.io.{IOException, FileNotFoundException, FileReader, File}
import java.net.MalformedURLException
import java.util.regex.Matcher
import javax.print.DocFlavor.URL

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import Element.elem

/**
 * Created by jasonzhu on 11/2/15.
 */
object ProgrammingInScalaTest {
  def main(args: Array[String]) {
    var m = Map("1"->"2", "3"->"4")
    m += ("5"->"6")
    println(m)

    var ss = "Abc"
    println(ss.exists(_.isUpper))

    m.foreach((kv:(String, String)) => println("key=["+kv._1+"], val=["+kv._2+"]"))
    m.foreach(kv => println("key=["+kv._1+"], val=["+kv._2+"]"))
    m.foreach(println)
    for(kv <- m)  {
      println(kv)
    }

    val g = new Array[String](3)
    g(0) = "123"
    g(1) = "456"
    g(2) = "789"    // same as `g.update(2, "789")`
    for(i <- 0 to 2)  {  // same as `0.to(2)`
      println(g(i))   // same as `g.apply(i)`
    }

    val g2 = Array("abc", "def")  //same as `Array.apply("abc", "def")`

    val l1 = List(1,2,3)
    val l2 = List(4,5,6)
    println(l1 ::: l2)

    /**
     * If a method is used in operator notation, such as a * b,
     * the method is invoked on the left operand, as in a.*(b)
     * unless the method name ends in a colon. If the method name
     * ends in a colon, the method is invoked on the right operand.
     * Therefore, in 0 :: l1, the :: method is invoked on l1,
     * passing in 0, like this: l1.::(0).
     */
    println(0 :: l1) // `::` sounds like "cons"

    println(Nil.::(1))
    println(1 :: Nil)

    println(l2.count(intt => intt > 4))
    println(l2.exists(intt => intt == 4))
    println(l2.forall(intt => intt > 3))
    println(l2.filter(intt => intt > 4))
    println(l2.map(intt => intt+1))
    println(l2.mkString(", "))
    println(l2.reverse)
    println(l2.sortWith((i1, i2) => i1 > i2))


    var pair = (9, "jason")
    println(pair._1)


    var jset = Set("a", "b", "b")
    jset += "c"
    println(jset)
    println(jset.contains("c"))


    var jmap = Map(1->"a", 2->"b")
    jmap += (3->"c")
    println(jmap)
    println(jmap(3))

    var jmap1 = Map()

    def formatArgs1(args: Array[String]) = args.mkString("\n")
    def formatArgs2(args: Array[String]):String = args.mkString("\n")


    assert(formatArgs1(Array("1","2","3")) == "1\n2\n3")


    for(line <- Source.fromFile("/Users/jasonzhu/Downloads/rcode.txt").getLines())  {
      println(line)
    }

    var num = 5
    println(num.max(7))

    val lines = List("ab", "a", "bcd", "ef")
    val longestLine = lines.reduceLeft((a, b) => if(a.length > b.length) a else b)
    println(longestLine)


    new ChecksumAccumulator

    println(ChecksumAccumulator.calculate("asd111"))

    println(
      """|I
        |love
        |u
      """.stripMargin)


    updateRecordByName('favoriteAlbum, "OK Computer")



    println(2.+(3))




    println("helloworld" indexOf ("o", 5))  // This syntax is called `infix operator notation`, the same as invoking "helloworld".indexOf("o", 5).

    //`prefix operator notation`
    //The only identifiers that can be used as prefix operators are +, -, !, and ~
    println(-7)  // same as `println(7.unary_-)`

    //`postfix operator notation`
    //Postfix operators are methods that take no arguments, when they are invoked without a dot or parentheses.
    println(7 toLong)

    /**
     * scala's == calls on equals method on the left operhand, which is not reference quality in java.
     *
     * Scala provides a facility for comparing reference equality, as well, under the name eq. However,
     * eq and its opposite, ne, only apply to objects that directly map to Java objects.
     */
    println(List(1) eq List(1))
    println(List(1) == List(1))




    println(new Rational(1, 6) + (new Rational(1, 6)))

    println(1 < -2) // can't be written like `1<-2` coz '<-' is taken as as a single identifier.


    Thread.`yield`()  // It has to be written between back ticks coz that 'yield' is a reserved word in Scala.

    println(new Rational(1, 6)+1)

    //`implicit conversion`
    implicit def intToRational(x: Int) = new Rational(x)

    println(1+new Rational(1, 6))

    val filename = if (!args.isEmpty) args(0) else "default.txt"
    println(filename)

    def greet() = println("hi")
    println(greet() == ()) //Unit is represented as ()


    // `generator expression`
    for(file <- (new File(".")).listFiles()
          if file.isFile
          if file.getName.endsWith(".md"))  {
      println(file)
    }

    def fileLines(file: File) = scala.io.Source.fromFile(file).getLines().toList
    def grep(pattern: String) =
      for(
        file <- (new File(".")).listFiles();
        if file.getName.endsWith(".md");    // ; will not be infered between perentheses, thus we have to write it explicitly.
        line <- fileLines(file);
        lineTrimmed = line.trim;          // `Mid-stream variable bindings`
        if lineTrimmed.matches(pattern)
      ) println(file + ": " + lineTrimmed)
    grep(".*tutorial.*")

    def files =
      for {
        file <- (new File(".")).listFiles();
        if file.isFile
      } yield file        //Syntax: `for clauses yield body`
    for(file <- files) println(file)



    for(i <- 1 to 4)  {
      println(i)
    }
    for(i <- 1 until 4) {
      println(i)
    }


//    val half =
//      if (11 % 2 == 0)
//        11 / 2
//      else
//        throw new RuntimeException("must be even number.")


    try {
      val f = new FileReader("not_exist.txt")

    }
    catch {
      case ex: FileNotFoundException => println(ex)
      case ex: IOException => println(ex)
    }
    finally {
      println("#In Finally Clause.")
    }


    def urlFor(path: String) =
      try {
        new URL(path)
      } catch {
        case ex: MalformedURLException => new URL("http://www.baidu.com")
      }

    def ff(): Int = try {return 1} finally {return 2} //finally clause override the return value in try block
    println(ff())

    def gg(): Int = try {1} finally {2}
    println(gg())   //It's confusing when comparing to function ff().
                    // Thus it’s usually best to avoid returning values from finally clauses.


    val friend = "egg" match {
      case "salt" => "pepper"
      case "egg" => "bacon"
      case _ => "huh?"
    }
    println(friend)


    import scala.util.control.Breaks._
    breakable {
      while (true) {
        println("just loop once.")
        break
      }
    }


    val aaa = 1;
    {
      val aaa = 2;
      {
        println(aaa)
      }
    }


    processFile("README.md")


    val arr1 = Array[Int](1,2,3,4,5)
    for(num <- arr1.drop(2))  {
      println(num)
    }

    //`function literal`
    var increase = (x:Int) => x+1
    println(increase(1))


    val someNumbers = List(-11, 0, 23, 52)
//    someNumbers.foreach((x: Int) => println(x))
//    someNumbers.foreach((x) => println(x))    //`Target typing`
//    someNumbers.foreach(x => println(x))
//    someNumbers.foreach(println _)  //`Partially applied functions`
    someNumbers.foreach(println)


//    println(someNumbers.filter((x: Int) => x > 0))
    println(someNumbers.filter(_ > 0))


    val f = (_:Int) + (_:Int)
    println(f(5,10))    //Multiple underscores mean multiple pa- rameters, not reuse of a single parameter repeatedly.





    def sum(a: Int, b: Int , c: Int): Int = a+b+c
    val f2 = sum _          //`Partially applied functions`   //GRAMMAR REFERENCE: http://stackoverflow.com/questions/5009411/two-ways-of-defining-functions-in-scala-what-is-the-difference
    println(f2(1,2,3))
    val f3 = sum(1, _:Int, 3)
    println(f3(1))


    //`Closure`
    var more = 2
    val addMore = (x:Int) => x+more
    println(addMore(1))
    more = 3
    println(addMore(1))

    var sumup = 0
    someNumbers.foreach(sumup += _)
    println(sumup)

    def makeIncreaser(more: Int) = (x:Int) => x + more
    val inc1 = makeIncreaser(1)
    val inc9999 = makeIncreaser(9999)
    println(inc1(1))
    println(inc9999(1))


    //`repeated parameters`
    def echo(aggs : String*) = for (agg <- aggs) println(agg)   // String* is the same as Array[String]
    echo("a", "b", "c")
    val arr = Array("x", "y", "z")
    val lst = List("m", "n", "o")
    echo(arr: _*)     //This notation tells the compiler to pass each element of arr as its own argu- ment to echo, rather than all of it as a single argument.
    echo(lst: _*)


    //`named arguments`
    def speed(distance: Float, time: Float): Float = distance/time
    println(speed(100, 11))
    println(speed(time = 11, distance = 100))


    //`default parameter values`
    def printTime(out: java.io.PrintStream = Console.out) = out.println("time= "+System.currentTimeMillis())
    printTime()
    printTime(Console.err)

    def printTime2(out: java.io.PrintStream = Console.out, divisor: Int = 1) = out.println("time= "+System.currentTimeMillis()/divisor)
    printTime2()
    printTime2(out = Console.err)
    printTime2(divisor = 1000)



    def filesMatching(matcher: String => Boolean) = {
      def filesHere = (new java.io.File(".")).listFiles

      for(file <- filesHere; if matcher(file.getName))
        yield file
    }
    def filesEnding(query: String) =
      filesMatching(_.endsWith(query))   // same as "(fileName) => fileName.endsWith(query)"
    def filesContaining(query: String) =
      filesMatching(_.contains(query))
    def filesRegex(query: String) =
      filesMatching(_.matches(query))


    //`currying`
    def curriedSum(x: Int)(y: Int) = x+y
    println(curriedSum(2)(3))
    println(curriedSum(2)_)
    def curriedSumDecomposed(x: Int) = (y: Int) => x+y
    println(curriedSum(2)(3))


    /**
     * One way in which you can make the client code look a bit more like a built-in control structure
     * is to use curly braces instead of parentheses to surround the argument list. In any method
     * invocation in Scala in which you’re passing in exactly one argument, you can opt to use curly
     * braces to surround the argument instead of parentheses.
     */
    println("helloworld")
    println { "helloworld" }


    //`loan pattern`
    def withPrintWriter(file: File, op: java.io.PrintWriter => Unit) = {
      val writer = new java.io.PrintWriter(file)
      try {
        op(writer)
      } finally {
        writer.close()
      }
    }

    withPrintWriter(new File("loan_pattern.out"), _.println(new java.util.Date))

    // Refactor withPrintWriter with currying, thereby being able to apply curly braces on the new function
    def withPrintWriter2(file: File)(op: java.io.PrintWriter => Unit) = {
      val writer = new java.io.PrintWriter(file)
      try {
        op(writer)
      } finally {
        writer.close()
      }
    }

    val file2 = new File("loan_pattern.out")
    withPrintWriter2(file2) {
      _.println(new java.util.Date)
    }


    //`by-name parameter`
    //--original awkward version
    var assertionsEnabled = true
    def MyAssert(predicate: () => Boolean) = {
      if(assertionsEnabled && !predicate())
        throw new AssertionError()
    }
    MyAssert(()=>5>3)
    //--by-name parameter version
    def byNameAssert(predicate: => Boolean) = {
      if(assertionsEnabled && !predicate)
        throw new AssertionError()
    }
    byNameAssert(5>3)
    //--by-value parameter version
    def boolAssert(predicate: Boolean) = {
      if (assertionsEnabled && !predicate)
        throw new AssertionError
    }
    boolAssert(5>3)

    /**
     * ⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆
     * by-name参数，先传入函数，后evaluate；by-value参数，先evaluate，后传入函数。
     *
     * The difference between the two approaches, therefore, is that if assertions are disabled,
     * you’ll see any side effects that the expression inside the parentheses may have in boolAssert,
     * but not in byNameAssert. For example, if assertions are disabled, attempting to assert on
     * “x / 0 == 0” will yield an exception in boolAssert’s case
     */

    assertionsEnabled = false
//    boolAssert(2/0 == 0)
    byNameAssert(2/0 == 0)


    for(ele <- (Array(1,2,3) ++ Array(4,5,6)))  {
      println(ele)
    }


    for((ele1, ele2) <- Array(1, 2) zip Array(3, 4, 5)) {
      println("("+ele1+", "+ele2+")")
    }


    println(elem(Array("hello")) beside elem(Array("world!!!")))




    println("abc".substring(2) == "c")
    println("abc".substring(2) equals "c")
    println("abc".substring(2) eq "c")  //`reference equality`



    //Type Nothing is at the very bottom of Scala’s class hierarchy; it is a sub- type of every other type.
    //one use of Nothing is that it signals abnormal termination.
    def error(msg: String): Nothing = throw new RuntimeException(msg)



    //`trait`
    trait Philosophical {
      def philosophize(): Unit =  {
        println("I consume memory, therefore I am!")
      }
    }

    //If you wish to mix a trait into a class that explicitly extends a superclass, you use extends to indicate the superclass and with to mix in the trait.
    class Animal
    trait HasLegs

    class Frog extends Animal with Philosophical with HasLegs {  //`mix in trait`
      override def toString = "green"
    }

    val frog = new Frog
    frog.philosophize()

    //traits can enrich a thin interface, making it into a rich interface.
    class Point(val x: Int, val y: Int)
    trait Rectangular {
      def topLeft: Point
      def bottomRight: Point

      def left = topLeft.x
      def right = bottomRight.x
      def width = right-left

    }

    abstract class Component extends Rectangular  {

    }

    class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {

    }

    val rect = new Rectangle(new Point(1,1), new Point(10, 10))
    println(rect.width)

    //Rational extends trait Ordered
    val half = new Rational(1, 2)
    val third = new Rational(1, 3)
    println(half < third)

    abstract class IntQueue {
      def get(): Int
      def put(x: Int)
    }

    class BasicIntQueue extends IntQueue  {
      private val buf = new ArrayBuffer[Int]

      override def get(): Int = buf.remove(0)

      override def put(x: Int): Unit = {buf += x}
    }

    // The Doubling trait has two funny things going on. The first is that it declares a superclass, IntQueue.
    // This declaration means that the trait can only be mixed into a class that also extends IntQueue.
    // Thus, you can mix Doubling into BasicIntQueue, but not into Rational.
    trait Doubling extends IntQueue  {
      abstract override def put(x: Int) {super.put(2 * x)}    //This arrangement is frequently needed with traits that implement `stackable modifications`. To tell the compiler you are doing this on purpose, you must mark such methods as abstract override.
    }

    class MyQueue extends BasicIntQueue with Doubling
//    val myQueue = new MyQueue
    val myQueue = new BasicIntQueue with Doubling // simpler way
    myQueue.put(10)
    println(myQueue.get)

    trait Incrementing extends IntQueue {
      abstract override def put(x: Int)  {super.put(x + 1)}
    }

    trait FilteringNonNegative extends IntQueue {
      abstract override def put(x: Int): Unit = {
        if(x >= 0) super.put(x)
      }
    }

    // The order of mixins is significant.
    // traits further to the right take effect first.（最后边的先执行，也即，FilteringNonNegative之后才会执行Incrementing）
    // As hinted previously, the answer is `linearization`. When you instantiate a class with new, Scala takes the class and all of its inherited classes and traits and puts them in a single, linear order. Then, whenever you call super inside one of those classes, the invoked method is the next one up the chain. If all of the methods but the last call super, the net result is stackable behavior.
    val queue = new BasicIntQueue with Incrementing with FilteringNonNegative
    queue.put(-1); queue.put(0); queue.put(1)
    println(queue.get)
    println(queue.get)



    //Scala provides a package named _root_ that is outside any package a user can write. Put another way, every top-level package you can write is treated as a member of package _root_.
    import _root_.java.lang.String


    //import Pattern and Matcher from java.util.regex package, meanwhile, rename Pattern to Ptn
    import java.util.regex.{Pattern => Ptn, Matcher}

    //This imports all members of java.util.regex except Pattern. A clause of the form “<original-name> => _” excludes <original-name> from the names that are imported. In a sense, renaming something to ‘_’ means hiding it altogether.
    import java.util.regex.{Pattern => _, _}


    //`implicit imports`
    //the following three import clauses had been added to the top of every source file with extension “.scala”
    import java.lang._ // everything in the java.lang package
    import scala._     // everything in the scala package
    import Predef._    // everything in the Predef object


    //`access modifiers`
    class Outer {

      class Inner {
        private def f() {
          println("f")
        }

        class InnerMost {
          f() // OK }
        }
      }
//      (new Inner).f() // error: f is not accessible

    }

    class Super {
      protected def f() { println("f") }
    }
    class Sub extends Super {
      f()
    }
    class Other {
//      (new Super).f()  // error: f is not accessible
    }

    //`scope of modifier`
    //Access modifiers in Scala can be augmented with qualifiers. A modifier of the form private[X] or protected[X] means that access is private or protected “up to” X, where X designates some enclosing package, class or singleton object.
//    package bobsrockets
//    package navigation {
//      private[bobsrockets] class Navigator {
//        protected[navigation] def useStarChart() {}
//        class LegOfJourney {
//          private[Navigator] val distance = 100
//        }
//        private[this] var speed = 200
//      }
//    }
//    package launch {
//      import navigation._
//      object Vehicle {
//        private[launch] val guide = new Navigator
//      }
//    }





  }
  //END




  def max(x: Int, y: Int): Int = {
    if (x > y) return x
    else y
  }

  def max2(x:Int, y:Int) = if(x > y) x else y



  def ttt = 1   // same as "def ttt():Int = 1"


  //Symbol literals in scala
  /**
   * In Java terms, symbols are interned strings. This means, for example,
   * that equality comparison works correctly: 'abcd == 'abcd will return true,
   * while "abcd" == "abcd" might not (depending on JVM's whims).
   *
   * @param r
   * @param value
   */
  def updateRecordByName(r:Symbol, value:Any): Unit = {
    println("the Symbol literal's name is "+r.name)
  }


  def processFile(filename: String): Unit = {
    //`local function`
    //notice that 'filename' need not to be specified
//    def justForDemon(line: String): Unit =  {
//      println(filename+": "+line.trim)
//    }

    //`function literal`
    val justForDemon = (line: String) => println(filename+": "+line.trim)

    val source = Source.fromFile(filename)
    for(line <- source.getLines())  {
      justForDemon(line)
    }
  }




}






class ChecksumAccumulator {
  private var sum = 0

  // 1. method parameters in Scala are vals
  // 2. Another way to express such methods is to leave off the result type and the equals sign,
  //    and enclose the body of the method in curly braces. In this form, the method looks like a procedure,
  //    a method that is executed only for its side effects.
  def add(b: Byte) {sum += b}

  def checksum(): Int = ~(sum & 0xFF) + 1
}

object ChecksumAccumulator  {
  private var cache = Map[String, Int]()

  def calculate(s: String): Int =   {
    if(cache.contains(s)) {
      cache(s)
    }
    else  {
      val acc = new ChecksumAccumulator
      for(c <- s) {
        acc.add(c.toByte)
      }
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }
  }
}

//object FallWinterSpringSummer extends App {
//  for(season <- List("fall", "winter", "spring")) {
//    println(season+": "+ChecksumAccumulator.calculate(season))
//  }
//}

/**
 * The identifiers n and d in the parentheses after the class name, Rational, are called class parameters.
 *
 * @param n
 * @param d
 */
class Rational(n:Int, d:Int) extends Ordered[Rational]   {
  require(d != 0)
  //`parametric fields`
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n/g
  val denom: Int = d/g
  println("Created "+numer+"/"+denom)

  // define auxiliary constructor
  /**
   * In Scala, every auxiliary constructor must invoke another constructor of the same class as its first action.
   * In other words, the first statement in every auxiliary constructor in every Scala class will have the form “this(...)”.
   * The invoked constructor is either the primary constructor (as in the Rational example), or another auxiliary
   * constructor that comes textually before the calling constructor. The net effect of this rule is that every
   * constructor invocation in Scala will end up eventually calling the primary constructor of the class. The primary
   * constructor is thus the single point of entry of a class.
   *
   * @param n
   */
  def this(n:Int) {
    this(n, 1)
  }

  //override def toString(): String = {n+"/"+d}
  override def toString = numer+"/"+denom

  def +(that: Rational): Rational = new Rational(numer*that.denom + that.numer*denom, denom*that.denom)

  //override
  def +(i: Int): Rational = new Rational(numer+i*denom, denom)

  def lessThan(that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational): Rational = if(this.lessThan(that)) that else this

  // calculate the greatest common divisor
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  override def compare(that: Rational): Int = {
    (this.numer*that.denom) - (that.numer*this.denom)
  }
}






//Chapter_10
abstract class Element {
  /**
   * 下面说下统一访问原则(uniform access principle)[which says that client code should not be affected by a decision to implement an attribute as a field or method]
   * 对于无参数方法的方法形式 obj.width 又像是在直接引用 obj 对象的 width 属性，这种统一性就叫做统一访问原则，就是说客代码不应由‘属性’ 是通过字段实现还是方法实现而受影响。例如前面的 def width: Int 可以写成 val width: Int，然而 obj.width 访问形式不变。
   * 由于 Java 中没有统一访问原则，所以关于是 string.length()，而不 string.length；是 array.length，而不是 array.length() 的问题会突然间让人很迷惑。有了统一访问原则的 Scala，以及结合 length 方法是无副作用的，就会直接写成 string.length 和 array.length，而犯不着为此犹豫不决。
   */
  def contents: Array[String]   //`parameterless methods` | By contrast, meth- ods defined with empty parentheses, such as def height(): Int, are called empty-paren methods.
  def height: Int = contents.length
  def width: Int = if(height == 0) 0 else contents(0).length


  def above(that:Element): Element = {
    elem(this.contents ++ that.contents)
  }

  def beside(that:Element): Element = {
    //--imperative way of implementing
    //    val contents = new Array[String](this.contents.length)
    //    for(i <- 0 until this.contents.length)  {
    //      contents(i) = this.contents(i) + that.contents(i)
    //    }
    //    new ArrayElement2(contents)

    //--more concise way to implement
    elem(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1+line2
    )
  }


  override def toString = contents mkString "\n"
}


object Element  {
  private class ArrayElement(conts: Array[String]) extends Element  {
    val contents: Array[String] = conts   //Another difference is that in Scala, fields and methods belong to the same namespace. This makes it possible for a field to override a parameterless method.

    //--cannot define the following method and field simultaneously--
    //  private var f = 0
    //  def f: Int = 1

    final val ARRAY_NAME: String = "ARRAY_ELEMENT"  //cannot be overriden
  }


  //`parametric field` | Note that now the contents parameter is prefixed by val. This is a shorthand that defines at the same time a parameter and field with the same name.
  private class ArrayElement2(val contents: Array[String]) extends Element


  private class LineElement(s: String) extends ArrayElement2(Array(s))  {
    override def width = s.length
    override def height = 1     //`override` keyword can prevent from the Fragile Base Class Problem [http://en.wikipedia.org/wiki/Fragile_base_class]
  }


  private class UniformElement(
                        ch: Char,
                        override val width: Int,
                        override val height: Int
                        ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }



  def elem(contents: Array[String]): Element =  {
    new ArrayElement2(contents)
  }

  def elem(chr: Char, width: Int, height: Int): Element =  {
    new UniformElement(chr, width, height)
  }

  def elem(line:String): Element = {
    new LineElement(line)
  }
}





