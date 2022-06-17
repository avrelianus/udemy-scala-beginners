package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App{

  class Person(val name: String, favoriteMovie: String){
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck"
    def isAlive: Boolean = true
    // must be with ( ), otherwise compiler will complain
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "inception")
  println(mary.likes("inception"))
  println(mary likes "inception") // infix notation, so much as natural language
  // infix notation only works with methods with single parameter (syntactic sugar)
  // _obj_name_   _method_name_   _param_name_

  // "OPERATORS" in Scala
  val tom = new Person("Tom", "Fight club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // Method names can be + - ! & etc chars
  // ALL OPERATORS ARE METHODS!!!

  // prefix notation (syntactic sugar)
  val x = -1 // equivalent to unary_-
  val y = 1.unary_-
  // unary_ prefix only works with few operators - + ! ~

  println(x)
  println(y)

  println(!mary)
  println(mary.unary_!)


  // postfix notation
  // Methods that do not have any parameters can be used in postfix notation
  // in scala 2 requires import scala.language.postfixOps
  println(mary.isAlive)
  println(mary isAlive)

  // Special method apply()
  // has special properties in scala
  println(mary.apply())
  println(mary()) // equivalent, compiler looks for definition of apply. Objects can be called like functions

}
