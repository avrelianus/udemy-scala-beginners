package lectures.part2oop

object AbstractDataTypes extends App {

  // class that contain unimplemented, abstract fields and methods are abstract classes
  // abstract classes cannot be instantiated. They are meant to extended with subclasses
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }


  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }


  // traits - ultimate abstract types in scala
  // created by
  // Class can be extended by multiple traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded{
    override val creatureType: String = "croc"
    def eat: Unit = println("nomnom")
    def eat(animal: Animal): Unit = println(s"I am a croc and I am eating  ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  croc eat dog


  // Difference between traits and abstract classes
  // both abstract classes and traits can have both abstract(non-implemented) and non abstract members
  // 1. traits do not have constructor parameters
  // 2. extending multiple traits, only one class
  // 3. traits are behavior, abstract class is type of thing
  // traits describe what classes do, while abstract classes describe what they are ??
}
