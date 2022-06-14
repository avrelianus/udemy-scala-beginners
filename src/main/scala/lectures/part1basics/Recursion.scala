package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App{

  def factorial(n: Int): Int = {
    if(n==0) 1
    else {
      println("Computing factorial of " + n + ". I need to do factorial of " + (n - 1))
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)
      result
    }
  }

  println(factorial(10))
  // each call of a recursive function uses a stack frame
  // stack frame has limited memory
  // println(factorial(5000))
  // generates stack overflow. It happens when recursive depth is too big

  def tailFactorial(n: Int): BigInt = {
    @tailrec // annotates that function must be tail recursive. If func is not tc, compiler will complain
    def aFactorialHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else aFactorialHelper(x-1, x*accumulator) // calling function last in recursion preserves stack frame aka stack frame is reused

    aFactorialHelper(n, 1)
  }
  println(tailFactorial(10))
  println(tailFactorial(5000))

  // when you need loops, use _tail_ recursion

  /*
    1.  Concatenate a string n times
    2.  IsPrime function tail recursive
    3.  Fibonacci function, tail recursive
   */
}
