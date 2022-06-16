package lectures.part1basics

object StringOperations extends App{

  val str: String = "Hello, I am learning Scala!"

  // JAVA funcs for strings
  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)

  // Tests
  println(str.startsWith("Hello"))
  println(str.endsWith("mrs"))
  // Replacement functions
  println(str.replace(" ", "_"))
  println(str.toLowerCase())

  println(str.length)

  // scala specific string funcs
  val aNumberString = "54"
  val aNumber = aNumberString.toInt
  // +: append, :+ prepend operators
  println('a' +: aNumberString :+ 'z')

  println(str.reverse)
  // list like functions
  println(str.take(2))

  // String interpolations

  // S-interpolations
  //  - have property of evaluating complex expressions
  val name = "David"
  val age = 12
  val greeting = s"Hello , my name is $name and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"

  // F-interpolations (formatting interpolation)
  // For example, F interpolations F interpreters act as s interpreters in the sense that they can expand
  // values or complex expressions inside, but they can also receive printf like format's.

  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  // Raw-interpolations
  // prints characters literally
  println(raw"This is a newline symbol \n ")
  val escaped = "This is a newline \n symbol"
  println(raw"$escaped") // Injected variables get escaped

}
