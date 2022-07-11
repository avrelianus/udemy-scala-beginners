package lectures.part2oop // everything defined in this file will become part of this here package

import playground.{AnotherImportClass, ImportClass => impClass} // Imports both classes from a package
// aliasing ImportClass => impClass
//import playground._ // imports everything from a package. Best practice, only use _ if you really need it

// aliasing
import java.util.Date
import java.sql.{Date => sqlDate}

object PackagingAndImports extends App {

  // What is package?
  // A package is basically just a bunch of definitions grouped under the same name in 99 percent of the
  // time. This matches directory structure.

  // Package memebers are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJvm", 2018)

  // Import package
  // If you are not in the proper package, you need to import the package
  val importedClass = new impClass // needs import

  // If you do not want to import, then you can use fully qualified name for the classes and other constructs
  val fullyQualifiedVal = new playground.ImportClass

  // Packages are ordered hierarchically, and the hierarchy is given by this dot notation,
  // so the part to OP package, for example, is a sub package of the lectures package and this maps the folder
  // structure in the file system


  // SCALA specific code organizing structure, which is called a package object
  // "But we might need to have some kind of universal constants or universal methods that reside outside.
  // classes so we don't need to resort to classes to access them." Package objects were created for this purpose.
  // Package objects can only be one per package.

  sayHello
  println(SPEED_OF_LIGHT)


  // IMPORTS
  val anotherImport = new AnotherImportClass

  // 1. use FQ name
  val date = new Date
  val sqldate = new java.sql.Date(2018,5,4)
  // 2. aliasing
  val sqldate1 = new sqlDate(2019,5,4)


  // DEFAULT IMPORTS
  // java.lang -> String, Object, Exception
  // scala -> Int, Nothing, Function
}
