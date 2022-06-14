package lectures.part1basics

object Expressions extends App{

  val x = 1 + 2 // Expression. It is evaluated to a value
  println(x)

  print(2 + 3 * 3) // Math expression
  // operators in scala
  // + - * / & | << >> >>> (right shift with zero extension)

  println(1 == x) // relational expression
  // relational operators in scala
  // == != || &&

  var aVariable = 2 // assignment op.
  aVariable += 3 // increment op. Also -> -= *= /=
  println(aVariable)

  // Instructions(statements) vs Expressions
  /*
  Instruction is something you tell computer to do, change var, print to console
  Expression is something that has a value. Expressions produce a value
  Instructions are executed, expressions are evaluated
  */

  // if expression
  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3
  println(aConditionValue)
  println(if(aCondition) 5 else 3) // produces value


  // while loop
  var i = 0
  val aWhile = while (i < 10) {
    println(i)
    i+=1
  } // NEVER WRITE THIS AGAIN, loops in scala are discouraged
  // Scala forces everything to be an expression
  // Everything in scala is an expression
  // Only definitions of a val or a class or a package are not expression

  val aWeirdValue = (aVariable=3) // Unit === void (meaning do not return anything meaningful
  println(aWeirdValue) // value of Unit is ()
  println("While is a sideeffect expression that returns a Unit.")
  println(aWhile)
  // Side effects in Scala are expressions returning Unit
  // Side effects are println(), whiles, reassigning


  // Code block
  // Code block is an expression. Value of the block it is the last expression
  // Code block can have its own definition of values and variables.
  // Everything definied inside the code block stays visible within code block
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // aAnotherValue = z + 1 // not visible outside of the block

  // Exercises
  // 1. diff between "hello" vs println("hello")
  // 2.

  val someValue = {
    2 < 3
  }
  println(someValue)

  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }
  println(someOtherValue)

}
