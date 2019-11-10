package org.apache.scala

class ApplyTest{
  def apply(): Unit= {
    println("This is a class, apply()...")
  }
}


object ApplyTest{
  def apply(): ApplyTest = {//此处返回类型一定要为ApplyTest才能调用该类下的方法
    println("This is an object, apply()...")

    new ApplyTest()
  }
}

