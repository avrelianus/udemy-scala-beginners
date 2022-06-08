package lectures.part1basics

object ValuesVariablesTypes extends App{

  val x: Int = 42
  println(x)

  // val is immutable, no reassignment possible
  // Func. programming prefers val
  //x = 32 // throws an error

  // types of val are optional, compiler can infer types
  val y = 32


  // String val declaration and init.
  val aString: String = "Hello! This is a string"; // semicolons are not necessary, only in case of multiple expressions in one line (bad code)
  val aStr1: String = "First"; val aStr2 = "Second";

  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 32 // compiler complains if number is too big
  val aLong: Long = 2323242387473L // add L in order to specify number is of type Long
  val aFloat: Float = 2.0F // add F|f in order to specify number is of type float
  val aDouble: Double = 56666.0 // No need for letter

  // CONCEPT OF VARIABLES

  var aVariables: Int = 4
  // can be reassigned, they are mutable
  aVariables = 99 // in func. prog. variables are known as side effects
  /*
  Examples of side effects are, for example, changing a variable like here.
  We're printing something to the console or displaying something on screen.
  Programs without side effects are easier to understand, as we will see in this course, because there
  are no variables and logic to keep track of.
   */
}
