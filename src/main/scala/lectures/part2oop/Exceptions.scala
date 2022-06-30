package lectures.part2oop

object Exceptions extends App {
  // Exceptions - JVM specific

  val x: String = null
  // println(x.length)
  // this ^^ will throw and exception

  // 1. Throwing Exceptions
  // val aWeirdValue: String = throw new NullPointerException() // throwing does not return Nothing, although it is valid
  // In order to make class (its instance) an exception class, it needs to inherit from Throwable
  // Throwable derivatives are Exception and Error
  // Exceptions denote something went wrong with the program
  // Errors denote something went wrong with the system (stackOverflow (JVM)

  // 2. How to catch Exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you")
    else 42

  val potentialFail = try{
    getInt(true) // returns Int
  } catch {
    case e: RuntimeException => println("caught a Runtime Exception") // returns Unit
  } finally {
    // code that will run no matter what
    // finally is optional, it does not influence type of try, catch, finally expression
    // Use finally only for side effects
    println("finally run")
  }

  // Like everything in scala, try, catch, finally is an expression
  // returns AnyVal because when combining Int and Unit parent is AnyVal

  // 3. How to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  //throw exception

  // Exercises

  // 1. Throws OOM -> outOfMemory
  // val array = Array.ofDim(Int.MaxValue)

  // 2. StackOverflow
  def inifinite: Int = 1 + inifinite
  // val noLimit = inifinite

  // 3. Pocket Calc

  class OveflowException extends Exception
  class UnderflowException extends Exception
  class MathCalcException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val res = x + y
      if (x > 0 && y > 0 && res < 0) throw new OveflowException
      else if (x < 0 && y < 0 && res > 0) throw new UnderflowException
      else res
    }

    def subtract(x: Int, y: Int): Int = {
      val res = x - y
      if (x > 0 && y < 0 && res < 0) throw new OveflowException
      else if (x < 0 && y > 0 && res > 0) throw new UnderflowException
      else res
    }

    def multiply(x: Int, y: Int): Int = {
      val res = x * y
      if (x > 0 && y > 0 && res < 0) throw new OveflowException
      else if (x < 0 && y < 0 && res < 0) throw new OveflowException
      else if (x > 0 && y < 0 && res > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && res > 0) throw new UnderflowException
      else res
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalcException
      else x / y
    }
  }

  println(PocketCalculator.divide(2,0))
}
