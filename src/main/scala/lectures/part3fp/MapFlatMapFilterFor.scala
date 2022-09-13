package lectures.part3fp

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)
  list.foreach(println)

  // map
  println(list.map(x => x * 2))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(x => x % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List("a", "b", "c", "d")
  val colors = List("black", "white")

  // iterations
  println(numbers.flatMap(n => chars.flatMap(c => colors.map(col => "" + c + n + col))))

  // foreach - receives a func that returns Unit, similar to map
  list.foreach(println)

  // syntax overload
  list.map { x =>
    x * 2
  }

  // for-comprehensions -> replacement for the flatMap, Map combination. Prefered in practice compared to flatMap, Map
  // combo
  val forCombinations = for {
    n <- numbers if n % 2 == 0 // replaced with filter
    c <- chars
    color <- colors
  } yield  "" + c + n + "-" + color
  println(forCombinations)


  for { // same as  numbers.foreach(println)
    n <- numbers
  } println(n)

  /*
    Exercises
      1. MyList supports for comprehensions
         map(f: A => B) => MyList[B]
         filter(f: A => Boolean) => MyList[A]
         flatMap(f: A => MyList[B]) => MyList[B]
      2. A small collection of at most ONE element - Maybe[+T]
          - map, flatMap, filter
   */
}
