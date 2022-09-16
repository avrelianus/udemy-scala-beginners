package lectures.part3fp

object TuplesAndObjects extends App{

  // finite ordered "lists"

  // ##### TUPLE
  // diff ways of instantiating a tuple
  val tuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val tuple1 = Tuple2(2, "hello, Scala")
  val tuple2 = (2, "hello, Scala")

  // Tuple1 ... Tuple22 elements is a max tuple
  println(tuple._1)
  println(tuple2._2)
  println(tuple.copy(_2 = "goodbye Java"))
  println(tuple.swap) // ("Hello, Scala", 2)


  // ##### MAP - associate keys with values (Dictionary in Python)
  val aMap: Map[String, Int] = Map() // construction of a Map()

  val phonebook = Map(("Jim", 555), ("Boris", 129)).withDefaultValue(-1)
  println(phonebook)
  val phonebook_2 = Map("Jelena" -> 88, "Mia" -> 938) // "Key" -> 2  is syntactic sugar for ("Key", 2)
  println(phonebook_2)

  // Map operations
  println(phonebook.contains("Jim"))
  println(phonebook("Mary")) // without defining a default value, it throws key not found error

  // add a pairing
  val newPairing = "Mary" -> 100
  val newPhoneBook = phonebook + newPairing

  // functionals on map
  // map, flatMap, filter. All of these take a pairing (val1, val2)
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.view.filterKeys(key => key.startsWith("J")).toMap)
  // mapValues
  println(phonebook.view.mapValues(value => "0245" + value).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Dan", 123)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) // groupBy returns a map


  /*
    EXERCISES
   */

  val aNewMap = Map(("Jim", 123), ("JIM", 900))
  println(aNewMap)
  println(aNewMap.groupBy(t => t._1.charAt(0)))

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
      network + (person -> Set())
  }

  def removeFriend(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    if (network.contains(person))
      network.removed(person)
    else
      network
  }

  def friend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
      val friendsA = network(personA)
      val friendsB = network(personB)

      network + (personA -> (friendsA + personB)) + (personB -> (friendsB + personA))
  }

  def unfriend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)

    network + (personA -> (friendsA - personB)) + (personB -> (friendsB - personA))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val net = add(add(empty, "Bob"), "Mary")
  println(net)
  println(friend(net, "Bob", "Mary"))
  println(unfriend(net, "Bob", "Mary"))
  println(remove(friend(net, "Bob", "Mary"), "Bob"))

  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)


  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(pair => pair._2.isEmpty)
}
