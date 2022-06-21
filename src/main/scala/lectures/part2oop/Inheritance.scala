package lectures.part2oop

object Inheritance extends App {
  /*
  Access modifier in scala:
    1. private - visible in this class
    2. protected - visible in this class and subclasses
    3. no modifier == public - completely visible
   */

  // scala has single class inheritance. Extending only one class at a time
  class Animal { // Superclass
    val creatureType = "wild"
    def eat = println("Animal, nom nom")
    // private - visible in this class
    // protected - visible in this classes and subclasses
  }
  // subclass Cat of superclass Animal
  // inherits all the non private fields and methods.
  class Cat extends Animal {
    def crunch: Unit = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  // JVM calls first constructor of the superclass and after that of the subclass
  // Superclass constructor needs to be guaranteed to run.
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name) // or Person(name, age) depending which constructor you want to call

  // overriding
  /*
  class Dog extends Animal {
    // override works for methods as for val-s and var-s
    override val creatureType = "domestic"
    override def eat = println("Dog crunch, crunch")
  }
  */

  // class Dog with overriding class parameter in constructor
  class Dog(override val creatureType: String) extends Animal {
    // override works for methods as for val-s and var-s
    override def eat = println("Dog crunch, crunch")
  }

  val dog = new Dog("k9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("k9")
  unknownAnimal.eat


  // super -> used when you want to reference field or a methods from a superclass
  class Bird extends Animal {
    override def eat = {
      super.eat
    }
  }

  val bird = new Bird
  bird.eat


  // preventing overrides
  // 1. keyword final - prevents subclasses from overriding the method
  // 2. final on class - prevents entire class from being inherited
  // 3. seal the class - extend classes in this file but prevent extension in other files
}
