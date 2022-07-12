package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements. Work with functions as we would with values
  // PROBLEM: Scala works on top of JVM (object oriented prog)
  // simulate func. prog. by using classes and instances of classes. In order to use methods as functions
  // first you need to instantiate a class instance and call a method or you can use anonymous classes for this.
  // This can be mitigated with apply function

  val doubler = new MyFunction[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }

  println(doubler(2)) // scala supports these functions (doubler) out of the box
  // function types = Function1[A,B] // by default supported in scala
  // goes from Function1 .. Function22

  val stringToIntConvert = new Function1[String, Int] {
    override def apply(string: String) : Int = string.toInt
  }

  println(stringToIntConvert("3"))

  // Function2[A,B,R]
  val adder = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  // Function2[A,B,R] with syntactic sugar
  val adderSyntacticSugar :((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  println(adder(10, 20))


  // Function type Function2[A,B,R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS (INSTANCES OF FUNCTION1..23 CLASSES)


  /*
    Exercises

    1. a func which takes 2 strings and concat them
    2. transform the MyPredicate and MyTransformer into function types
    3. def a func which takes an int and returns another func which takes an int and returns an int
      - what's the type of the func
      - how to do it
   */

  val concat : ((String, String) => String) = new Function2[String, String, String]{
    override def apply(v1:String, v2:String) = v1 + v2
  }

  println(concat("Mia", "Maja"))

  val higherOrderFunc = new Function1[Int,  Int => Int] {
    override def apply(x: Int): Function1[Int,  Int] = new Function1[Int, Int] {
      override def apply(y: Int) : Int = x + y
    }
  }

  val supperAdder = higherOrderFunc(10)
  println(supperAdder(10))
  println(higherOrderFunc(10)(10)) // curried function
  /*
    This special function is called a curried function, curried functions have the property
    that they can be called with multiple parameter lists just by their mere definition.
   */
}



trait MyFunction[A,B] {
  def apply(elem: A) : B
}