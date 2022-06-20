package lectures.part2oop

object Objects extends App{

  // class level functionality (functionality not dependant on a class instance)
  // SCALA does not have class level functionality, no concept of static
  // object can have static like functionality. Can contain values and variables and finally method definitions
  // Objects can be defined in similar fashion to classes except that objects cannot receive parameters
  // Often have factory methods in singleton object
  // Scala companions can access each other's private members
  object Person { // type + its only instance
  // "static/class" - level functionality
    val N_EYES = 2
    def canFly(): Boolean = false

    // factory methods, sole purpose to build new Person instance by given params
    // Usually named as apply instead of some name
    def apply(mother: Person, father: Person): Person = new Person("Bob")
  }
  class Person(val name: String = "No name") {
    // instance level functionality
  }
  // COMPANIONS (Person) object + class of same name and they reside in same scope

  println(Person.N_EYES)
  println(Person.canFly())

  // Scala object is a SINGLETON OBJECT
  val mary = Person
  val john = Person
  println(mary == john) // true because same singleton object

  val mary1 = new Person("Mary")
  val john1 = new Person("John")
  println(mary1 == john1) // false because diff instance objects

  // writing objects and classes with a same name and in a same scope

  val bobbie = Person(mary1, john1) // Person.apply(..), widely used in practice

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
  // method needs to have exact definition scala converted to java app which requires main method
  // for running program or extends App
}
