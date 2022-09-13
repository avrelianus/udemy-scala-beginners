package lectures.part2oop

object Enums extends App {
  /*
  TODO: check Scala 2 Enums
  Only Scala 3

  enum Permission {
    case READ, WRITE, EXECUTE, NONE

    def openDocument(): Unit =
      if (this == READ) println("opening document")
      else println("reading not allowed")
  }

  val somePermission: Permissions = Permission.READ
   */

  object Day extends Enumeration {
    type Day = Value
    val MON, TUE, WED, THU, FRI, SAT, SUN = Value
  }

  println(Day.values)

  Day.values foreach println


  val aStringLower = "hello"
  val aStringUpper = "DowN"
}
