package lectures.part2oop

object OOBasics extends App{

  val person = new Person("Alex", 25)
  println(person.age)

  person.greet("Zlatan")


  val writer1 = new Writer("Mark", "Morton", 1950)
  val writer2 = new Writer("Richard", "Tolkien", 1980)

  val novel1 = new Novel("Lamb of God", 1980, writer1)
  val novel2 = new Novel("Lord of the Rings", 2000, writer2)

  println(writer1.fullName())
  println(writer2.fullName())

  println(novel1.authorAge())
  println(novel2.authorAge())

  println(novel1.isWrittenBy(writer2))
  println(novel2.isWrittenBy(writer2))

  val novel3 = novel2.copy(2022)
  println(novel3.yearOfRelease)
  println(novel3.authorAge())


  val counter = new Counter(7)
  val counterby1 = counter.inc()
  val counterMinus1 = counter.dec()

  val counterDecBy9 = counter.dec(9)
  val counterIncBy9 = counter.inc(10)

  println(counter.value)
  println(counter.inc().inc().inc().inc().value)
  println(counterby1.value)
  println(counterMinus1.value)
  println(counterDecBy9.value)
  println(counterIncBy9.value)
}


// class definitions can sit on top level code
// class parameters are not class fields. To make them fields either add val or var before the name, or
// assign value from parameters to fields inside a class
class Person(name: String, val age: Int) { // constructor
  // body
  // Can do anything that you can do in block expression, expressions, func, val var etc

  // fields
  val x = 2

  println("Some expression inside a class. Number converted to string: " + 19)

  // this referring to the parameter name
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading methods, with diff signatures.
  def greet(): Unit = println(s"Hi, I am $name")
  def greet(x: Int): Unit = println(x)

  // overloading constructors. Calling another constructor primary or auxiliary ones.
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}


class Writer(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  def fullName(): String = firstName + " " + lastName
}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {

  // Age of author at the year of release
  def authorAge(): Int = yearOfRelease - author.yearOfBirth

  def isWrittenBy(author: Writer): Boolean = this.author.fullName() == author.fullName()

  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

/*
Counter class
  - receives an int
  - has method returns current count
  - method to inc and dec (give new counter) => new Counter
  - overload inc/dec to receive and amount => new Counter
 */


class Counter(val value: Int) {
  // Immutability, when you need to modify an instance, you need to create a new one (func. programming)
  def inc(): Counter = new Counter(value + 1)
  def inc(incrementBy: Int): Counter = {
    if (incrementBy <= 0) this
    else inc.inc(incrementBy-1) // wtf?
  }
  def dec(): Counter = new Counter(value - 1)
  def dec(decrementBy: Int): Counter = new Counter(value - decrementBy)
}