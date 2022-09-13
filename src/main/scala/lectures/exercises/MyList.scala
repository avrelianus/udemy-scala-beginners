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

  def foreach(f: A => Unit) : Unit
  def sort(compare: (A,A) => Int) : MyList[A]
  def zipWith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
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

  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start

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

  def foreach(f: A => Unit) : Unit  = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }


  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  /*
    [1,2,3].fold(0)(+) =
    = [2,3].fold(1)(+) =
    = [3].fold(3)(+) =
    = [].fold(6)(+)
    = 6
   */
  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)
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
  val listOfStrings: MyList[String] = new Cons[String]("hello", new Cons[String]("world!", new Cons[String]("nah!", Empty)))

  println(listOfInts)
  println(listOfStrings)

  println(listOfInts.map(value => value * 2).toString)

  println(list.filter((value: Int) => value % 2 == 0).toString)

  println((list ++ aNewListOfInts).toString)
  println(list.flatMap((value: Int) => new Cons(value, new Cons[Int](value + 1, Empty))))

  listOfInts.foreach(println)
  println(listOfInts.sort((x,y) => y - x))
  println(listOfInts.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(listOfInts.fold(0)(_ + _))

  // for comprehensions
  val combinations = for {
    n <- listOfInts
    s <- listOfStrings
  } yield n + "-" + s

  println(combinations)
}


