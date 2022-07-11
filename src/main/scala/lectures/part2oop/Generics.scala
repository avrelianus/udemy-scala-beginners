package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // Defining generic type by [A], A is type parameter
    def add[B >: A](element: B) : MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }
  class MyMap[Key, Value] {
    // You can have as many of the type parameters as you like
  }
  trait newTrait[A] {
    // traits can have type parameter
  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods

  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal
  /*
    if  Cat extends Animal, does a list of cat also extend list of Animal
    There are three possible answers to this
    1. Yes list of cat extends Animal -> covariance
        list[Cat] extends list[Animal]
    2. No, List of cats and list of animals are 2 diff things -> Invariance
    3. Contravariance, counter intuitive basically subclass -> superclass
       Opposite to covariance
  */

  // 1. covariance
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList : CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? Hard question? Solution -> return list of Animals (check MyList)

  // 2. invariance
  class InvariantList[A]
  val invarantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // InvariantList[Cat] throws an error

  // 3. contravariance
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal] // wtf


  /* Bounded types
    Bounded types allow you to use your generic classes only for certain types that are either a subclass
    of a different type or a super class of a different type.

    operators:
      <: only subclasses of specified class
      >: only superclasses of specified class

   */
  class Cage[A <: Animal] (animal: A)// type A accepts only type parameters that are subclass of animal

  val cage = new Cage(new Dog)
  /*
  class Car
  val newCage = new Cage(new Car) // this will fail
  */

  /*
    1. Generic trait MyPredicate test[-T] => Boolean
    2. Generic trait MyTransformer[-A, B] with method transform(A) => B
    3. MyList
      - map(transformer) => MyList
      - filter(predicate) = MyList
      - flatMap(transformer from A to MyList[B]) => MyList[B]

    class EvenPredicate with MyPredicate[Int]
    class StringToIntTransformer with MyTransformer[String, Int]
   */


}
