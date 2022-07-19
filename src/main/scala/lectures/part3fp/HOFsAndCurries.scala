package lectures.part3fp

object HOFsAndCurries extends App {

  // HOF - higher order func. Returns a func, or takes as func as param
  val superFunction: (Int, (String,(Int => Boolean)) => Int) => (Int => Int) = null

  // examples of HOF are flatMap, map, filter (have func as a param)

  // func that applies a function n times over a value
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)) = nTimes(f, 2, f(x))
  // nTimes(f, n, x) = ... = nTimes(f, n-1, f(x)
  def nTimes(f: Int => Int, times: Int, x: Int) : Int = {
    if (times <= 0 ) x
    else nTimes(f, times - 1, f(x))
  }

  println(nTimes(a => a * 2, 3, 2))
  def plusOne : (Int => Int) = x => x + 1
  println(nTimes(plusOne, 10, 1))


  // ntb(f,n) = x => f(f(f..(x)))
  def nTimesBetter(f: Int => Int): (Int, Int) => Int = {
     def aFunct(times: Int, x: Int) : Int = {
       if (times <= 0 ) x
       else aFunct(times - 1, f(x))
    }

    aFunct
  }

  def temp = nTimesBetter(plusOne)
  println(temp(10, 1))
  println(nTimesBetter(plusOne)(10,1))

  def nTimesBetter(f: Int => Int, times: Int) : (Int => Int) = {
    if (times <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, times-1)(f(x))
  }

  println(nTimesBetter(plusOne, 10)(2))


  // curried functions
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y

  val add3 = superAdder(3)
  println(add3(6))
  println(superAdder(6)(3))

  // functions with multiple parameter list
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))


  /*
    Exercises

    1. Expand MyList
      - foreach method A => Unit
          [1,2,3].foreach(x => println(x))
      - sort ((A, A) => Int) => MyList
          [1,2,3].sort((x,y) => y - x) => [3,2,1]
      - zipWith(list, (A, A) => B) => MyList[B]
          [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = 4, 10, 18
      - fold
          [1,2,3].fold(0)(x + y) = 6
    2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
       fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int
    3. compose(f,g) => x => f(g(x))
       andThen(f,g) => x => g(f(x))
   */

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => x + y

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x,y) => f(x)(y)

  // FunctionX
  def compose[A,B,T](f: A => B, g: T => A) : T => B =
    x => f(g(x))

  def andThen[A,B,C](f: A => B, g: B => C) : A => C =
    x => g(f(x))


  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4,17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))
}
