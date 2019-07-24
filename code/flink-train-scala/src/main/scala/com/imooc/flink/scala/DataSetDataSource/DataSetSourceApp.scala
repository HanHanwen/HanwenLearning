package com.imooc.flink.scala.DataSetDataSource

import org.apache.flink.api.scala.ExecutionEnvironment

object DataSetSourceApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    /**
      * 集合的方式
      */
    //fromCollection(env)

    /**
      * 读取本地文件
      */
    readText(env)

  }

  import org.apache.flink.api.scala._

  def fromCollection(env: ExecutionEnvironment): Unit ={
    val data = 1 to 10

    env.fromCollection(data).print()
  }

  def readText(env: ExecutionEnvironment): Unit ={
    //val filePath = "file:///Users/lihanwen/Study/git_myself/HanwenLearning/text/hello.txt"

    val filePath2 = "file:///Users/lihanwen/Study/git_myself/HanwenLearning/text/"

    env.readTextFile(filePath2).print()

  }

}
