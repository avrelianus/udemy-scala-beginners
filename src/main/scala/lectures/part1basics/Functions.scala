package lectures.part1basics

object Functions extends App {

  /*
  function syntax
  def _name_ (_param_name : _type_, ... more args): _return_type = {
    _body_of_the_func....
    ...
    ..
    .
  }
   */

  def aFunction(a: String, b:Int): String =
    a + " " + b // single line does not need brackets

  // Compiler can figure out return type of the funcs, so return type does not need to be specified
  def aFunction1(a: String, b:Int) = {
    a + " " + b // multiline needs brackets
  }

  println(aFunction("hello", 3))

  // functions are expressions too

  // parameterless func
  def aParameterlessfun(): Int =
    54

  println(aParameterlessfun())
  println(aParameterlessfun) // parameterless func can be called without parentheses. Doesn't work in scala 3

  // Return type of recursive func needs to be specified, because compiler cannot figure out the return type
  // Specify return types of func for explicitness
  def aRepeatedFunc(aString: String, n: Int): String = {
    if (n==1) aString
    else aString + aRepeatedFunc(aString, n-1) // recursive func
  }
  println(aRepeatedFunc("hello", 5))
  // WHEN YOU WOULD NEED LOOPS IN IMPERATIVE LANG., IN FUNC. LANG. YOU USE RECURSION

  // Can define Unit as return type in order to perform side effects only like in imperative language
  def aFunctionWithSideEffect(aString: String): Unit = println(aString)

  // CODE BLOCKS allow you to define auxiliary functions
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b:Int): Int = a + b // auxiliary functions

    aSmallerFunction(n, n-1)
  }

  /*
  TASKS
  1. A greeting func. (name, age) => "Hi, my name is $name, and I am $age years old"
  2. Factorial func. 1 * 2 * 3 .. n
  3. A fibonnaci func
  4. Func. which tests if number is prime
   */

  // 1. Greeting
  def aGreetingFunc(name: String, age: Int): Unit =
    println(s"Hi, my name is $name, and I am $age years old.")

  aGreetingFunc("Stipo", 15)

  // 2. Factorial
  def fact(n: Int): Int = {
    if(n==0) 1
    else n * fact(n-1)
  }
  println(fact(0))
  println(fact(1))
  println(fact(2))
  println(fact(3))
  println(fact(4))

  // 3. fibonacci
  def fib(n: Int): Int = {
    if (n <= 2) 1
    else fib(n-1) + fib(n-2)
  }
  println(fib(5))

  // 4. prime
  def prime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <=1 ) true
      else n % t != 0 && isPrimeUntil(t-1)
    isPrimeUntil(n/2)
  }
}
