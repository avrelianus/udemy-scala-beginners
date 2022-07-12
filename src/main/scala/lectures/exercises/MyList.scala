package lectures.exercises

abstract class MyList[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */
  def head(): A
  def tail(): MyList[A]
  def isEmpty(): Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements(): String
  override def toString: String = "[" + printElements() + "]"

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: Function1[A, MyList[B]]): MyList[B]
  def filter(predicate: A => Boolean) : MyList[A]

  // concatenation
  def ++[B >: A](list: MyList[B]) : MyList[B]
}

object Empty extends MyList[Nothing] {
  def head(): Nothing = throw new NoSuchElementException()
  def tail(): MyList[Nothing] = throw new NoSuchElementException()
  def isEmpty(): Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements(): String = ""

  // Higher order functions, receive functions as parameters or return functions
  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean) : MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]) : MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head(): A = h
  def tail(): MyList[A] = t
  def isEmpty(): Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements(): String = {
    if(t.isEmpty()) "" + h
    else h + " " + t.printElements()
  }

  def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer(h), t.map(transformer))
  }

  //def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def filter(predicate: A => Boolean) : MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def ++[B >: A](list: MyList[B]) : MyList[B] = new Cons(h, t ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
}


object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head())
  list.add(4)
  println(list.head())
  println(list.isEmpty())
  println(list.toString)

  val listOfInts: MyList[Int] = new Cons[Int](2, new Cons[Int](4, new Cons[Int](6, Empty)))
  val aNewListOfInts = new Cons(1, new Cons(4, new Cons(9, Empty)))
  val listOfStrings: MyList[String] = new Cons[String]("hello", new Cons[String]("world!", Empty))

  println(listOfInts)
  println(listOfStrings)

  println(listOfInts.map(new Function1[Int,Int] {
    override def apply(value: Int): Int = value * 2
  }).toString)

  println(list.filter(new Function1[Int, Boolean] {
    override def apply(value: Int): Boolean = value % 2 == 0
  }).toString)

  println((list ++ aNewListOfInts).toString)
  println(list.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(value: Int): MyList[Int] = new Cons(value, new Cons[Int](value + 1, Empty))
  }))
}


