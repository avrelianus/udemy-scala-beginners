package lectures.exercises

trait MyPredicate[-T] {
  def test(value : T) : Boolean
}

trait MyTransformer[-A, B] {
  def transform(value: A) : B
}

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

  def map[B](transformer: MyTransformer[A,B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]) : MyList[A]

  // concatenation
  def ++[B >: A](list: MyList[B]) : MyList[B]
}

object Empty extends MyList[Nothing] {
  def head(): Nothing = throw new NoSuchElementException()
  def tail(): MyList[Nothing] = throw new NoSuchElementException()
  def isEmpty(): Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements(): String = ""

  def map[B](transformer: MyTransformer[Nothing,B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]) : MyList[Nothing] = Empty

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

  def map[B](transformer: MyTransformer[A,B]): MyList[B] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }

  //def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def filter(predicate: MyPredicate[A]) : MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def ++[B >: A](list: MyList[B]) : MyList[B] = new Cons(h, t ++ list)

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
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

  println(listOfInts.map(new MyTransformer[Int,Int] {
    override def transform(value: Int): Int = value * 2
  }).toString)

  println(list.filter(new MyPredicate[Int] {
    override def test(value: Int): Boolean = value % 2 == 0
  }).toString)

  println((list ++ aNewListOfInts).toString)
  println(list.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(value: Int): MyList[Int] = new Cons(value, new Cons[Int](value + 1, Empty))
  }))
}


