package com.imooc.flink.scala.BasicWC

import org.apache.flink.api.scala.ExecutionEnvironment

object BatchWCScalaAPP {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val text = env.readTextFile("file:///Users/lihanwen/Study/test_file/flink/hello.txt")

    //text.print()

    //引入隐式转化
    import org.apache.flink.api.scala._

    text.flatMap(_.toLowerCase().split("\t").filter(_.nonEmpty))
      .map((_,1))
      .groupBy(0)
      .sum(1)
      .print()


  }
}
