package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App{

  class Person(val name: String, val favoriteMovie: String, val age : Int = 0){

    def likes(movie: String): Boolean = movie == favoriteMovie

    def learns(subject: String): String = s"${this.name} learns $subject!"

    def learnsScala(): String = this.learns("Scala")

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(nickname: String): Person = new Person(s"${this.name} ($nickname)", this.favoriteMovie)

    def unary_! : String = s"$name, what the heck"

    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)

    def isAlive: Boolean = true

    // must be with ( ), otherwise compiler will complain
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def apply(times: Int) = s"${this.name} watched ${this.favoriteMovie} $times times!"
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



  /*
    ### EXERCISES ###

    1. Overload the + operator
        mary + "the rockstar" => new person "Mary (the rockstar)"
    2. Add an age to the Person class
       Add unary + op => new person with the age + 1
       +mary => with the age incrementer
    3. Add a "learns" method in the Person class => Mary learns (arg) Scala"
       Add a learnsScala method, calls learns method with "Scala"
       Use it in postfix notation
    4. Overload apply method to recivieve a num and return a String
       mary.apply(2) => "mary watched Inception 2 times"
   */

  println("EXERCISES")

  val new_mary = mary + "the rockstar"
  println(new_mary.name)

  val inc_mary = +new_mary
  println(inc_mary.age)

  println(inc_mary learns "guitar")
  println(inc_mary learnsScala)
  println(inc_mary(2))
}
