package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }

  // Looks like this instantiates abstract class Animal but that is not the case
  // New anonymous class was instantiated instead AnonymousClasses$$anon$1
  // Works for both abstract classes and non abstract classes
  // Needs to implement all abstract methods, fields if creating anon. class from abstract one
  val funny: Animal = new Animal {
    override def eat: Unit = println("ahahahahah")
  }
  /* Equivalent with ->
    class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("ahahahahah")
    }
   */

  println(funny.getClass) // lectures.part2oop.AnonymousClasses$$anon$1

  class Person(name: String) {
    def sayHi = println(s"Hi, my name is $name!")
  }

  // Anonymous class needs to conform with the parameters call of the original class
  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hello! My name is Jim!")
  }
}
