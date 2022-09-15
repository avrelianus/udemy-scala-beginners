package lectures.part3fp

object Sequences extends App{

  /* Seq
    Sequences are a very general interface for data structures that have a well-defined order and they
    can be indexed sequences, have a lot of operations supported out of the box.

    Supports various operations:
    - apply, iterator, length, reverse for indexing and iterating
    - concatenation, appending, prepending
    - map, grouping, searching, slicing etc

   */
  val aSequence = Seq(1,2,4,3) // declared type Seq[Int]
  // Seq has apply method that constructs subclasses of sequence. In this case aSequence is of type
  // List()
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // 3
  println(aSequence ++ Seq(5,7,6))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10  // until
  aRange.foreach(println)

  (1 to 10).foreach(x => print("Hello"))
  println()

  /* List
    - immutable
    - extends LinearSeq
    - head, tail, isEmpty: O(1), most other ops are O(n)
    - two subtypes Nil and ::
   */
  val aList = List(1,2,3,4)
  val prepend = 42 :: aList // :: and +: preprend
  val append = aList :+ 99
  val appAndprep = 54 +: aList :+ 54
  println(prepend)
  println(append)
  println(appAndprep)

  val apples5 = List.fill(5)("apples")
  println(apples5)
  println(aList.mkString("-")) // concat all values and places - in between

  /* Arrays equivalent of Java arr
    mutable
    indexing is fast
    can be constructed with predef length
   */
  val numbers = Array(1,2,3,4)
  val treeElementsInts = Array.ofDim[Int](3)
  val treeElementsStr = Array.ofDim[String](4)
  println(treeElementsInts) // some crap
  treeElementsInts.foreach(println) // prints default values 0 because of Int declaration (for primitives)
  treeElementsStr.foreach(println)  // prints null as default values (for reference types)

  // mutation of array
  numbers(2) = 0 // this calls numbers.update(2,0) (syntactic sugar)
  numbers.foreach(println)


  // Arr and Seq
  val numberSeq: Seq[Int] = numbers // implicit conversion from Array to Wrapped array
  println(numberSeq) // WrappedArray

  /*  Vectors

  The default implementation for immutable sequences
    - effectively constant indexed read and write: O(log32(n))
    - fast element addition: append/prepend
    - implemented as a fixed-branched trie(branch factor 32)
    - good performance for large sizes
    - offers same functionality with other sequences
   */
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // performance of Vectors vs Lists goes here


}
