package org.apache.scala

object ApplyExample {
  def main(args: Array[String]): Unit = {
    var at = ApplyTest()

    at()

    println(at.getClass)

    var ap = ApplyTest

    println(ap.getClass)

    var ob1 = ObjectTest

    println(ob1.getClass)
  }
}
