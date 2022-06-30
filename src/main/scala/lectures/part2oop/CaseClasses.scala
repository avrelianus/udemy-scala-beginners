package lectures.part2oop

object CaseClasses extends App{

  /*
    For classes we need to implement boiler plate code like
    equals, hashCode, toString etc.
    CaseClasses implement this boiler plate code and companion object.
    They represent lightweight class
   */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  //    Adding val to parameter is redundant for case class because alla params are fields
  val jim = new Person("Jim", 34)
  println(jim.name)
  println(jim.age)

  // 2. sensible toString
  // syntactic sugar println(jim) jim as object gives same result as jim.toString
  // println(instance) = println(instance.toString)
  println(jim.toString) // Person("jim", 34)

  // 3. equals and hashCode implemented out of the box
  //    Usefull in collections
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // true, basic class would give false

  // 4. Case Classes have handy copy method
  val jim3 = jim.copy(age = 45) // copy creates new instance of this case class, accepts params. It copies fields also
  println(jim3) // Person("jim", 45)

  // 5. Case Classes have companion objects
  //    Compiler auto. creates companion object for CC
  val thePerson = Person
  val mary = Person("Mary", 23)
  println(mary)

  // 6. CCs are serializable
  //    Useful in distributed systems, so that instances of CCs can be sent throughout the network, or in between JVMs
  //    Very important for Akka

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  // case object exists, act as case class but is a object
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
}
