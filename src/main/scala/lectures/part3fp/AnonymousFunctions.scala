package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA) with syntactic sugar
  val dobuler = (x: Int) => x * 2
  val dobuler1 : Int => Int = (x: Int) => x * 2
  val dobuler2 : Int => Int = x => x * 2


  // multiple params in a LAMBDA
  val adder = (a:Int, b: Int) => a + b
  val adder1 : ((Int, Int) => Int) = (a:Int, b: Int) => a + b

  // no params
  val justDoSomething = () => 3
  val justDoSomething1 : () => Int = () => 3

  println(justDoSomething)
  println(justDoSomething()) // LAMBDAS without params must be called with ()

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MORE syntactic sugar
  // Please note that the underscore notation is extremely contextual.
  // If you miss the type, the compiler won't know what each underscore means.
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /*
    1. MyList replace all functionx calls with lambdas
    2. ReWrite the special adder (curried) as an anonymous function
   */

  val specialAdder = (x: Int) => (y: Int) => x + y
  println(specialAdder(3)(4))
}
