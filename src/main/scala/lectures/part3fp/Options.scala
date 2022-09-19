package lectures.part3fp

import java.util.Random

object Options extends App {

  /*
    An option is a wrapper for a value that might be present or not
    Option is an abstract class with two subclasses:
      Some - wraps a concrete value
      None - singleton for absent values

    Option was invented to deal with unsafe APIs

    Use Option to stay away from null exceptions
      - avoid runtime crashes due to null exceptions
      - avoid endless amount of null-related assertions

    A functional way of dealing with absence of a value
      - map, flatMap, filter
      - orElse
      - other: fold, collect, toList
   */

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption) // to get value myFirstOption.head
  println(noOption)

  // unsafe APIs
  def unsafeMethod(): String = null
  //val result = Some(null) // Wrong!!!

  val result = Option(unsafeMethod()) // never do null checks, Option type will do that for us
  println(result)

  // usage of Options in chained methods
  def backupMethod(): String = " a valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // if you design unsafe APIs return Option, so that the user/caller of the api knows what to expect
  def betterUnsafeMethod() : Option[String] = None
  def betterBackupMethod() : Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // unsafe - do not use this (can throw null pointer exception)

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x>10)) // if does not satisfy the condition, turn it into None
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for - comprehensions

  /*
    Exercises
   */


  // TODO: check imperative code on Daniels github page
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String):Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h,p)))
  val connectionStatus = connection.map(c => c.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)


  /*
    Some other ways of setting up the connections
  */

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)

}
