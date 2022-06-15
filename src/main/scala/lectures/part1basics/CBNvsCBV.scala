package lectures.part1basics

object CBNvsCBV extends App{

  /*
  So in the by value call, the exact value of this expression is computed before the function evaluates.
  in the same value is used in the function definition.
  Same as in other languages
   */
  def calledByValue(x: Long): Unit = {
    println("by value: " + x) // x replaced by some value 12334567
    println("by value: " + x) // x replaced by some value 12334567
  }
  /*
  In call by name, System.nanoTime() is passed literally to the func, body
  and called when necessary. Func is evaluated every time
   */
  def calledByName(x: => Long): Unit = {
    println("by name: " + x) // x replaced by System.nanoTime()
    println("by name: " + x) // x replaced by System.nanoTime()
  }

  calledByValue((System.nanoTime()))
  calledByName((System.nanoTime())) // extremely usefully in lazy streams

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println((x))

  //printFirst(infinite(), 34) // infinite loop because it evaluates to value which is again infinite()
  printFirst(34, infinite()) // infinite() is called by name, and it is not used (does not evaluate) so the printFirst func prints 34

}
