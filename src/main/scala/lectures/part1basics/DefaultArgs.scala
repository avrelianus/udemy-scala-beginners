package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App{

  def trFactorial(n: Int, acc: Int = 1): BigInt = {
      if (n <= 1) acc
      else trFactorial(n - 1, n * acc)
  }

  val fact10 = trFactorial(10)
  val fact11 = trFactorial(10,2)

  // default parameters need to come after passed parameters
  def savePicture(format: String = "jpg", wd: Int = 800, he: Int = 600): Unit = println("saving picture")
  //savePicture(800,600)
  savePicture(wd=900)
  savePicture(he=900, wd=1600, format = "bmp")

  /*
  1. pass in every leading argument
  2. name the arguments
   */
}
