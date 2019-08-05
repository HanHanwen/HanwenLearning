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
      * 读取本地.txt文件
      */
    //readText(env)

    /**
      * 读取本地.csv文件
      */
    csvFile(env)

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

  def csvFile(env: ExecutionEnvironment): Unit ={
    val filePath = "/Users/lihanwen/Study/git_myself/HanwenLearning/text/people.csv"

    /**
      * 取出所有列
      */
    //env.readCsvFile[(String, Int, String)](filePath, ignoreFirstLine=true).print()//默认取出所有列

    /**
      * 取出指定列
      */
    //env.readCsvFile[(String,String)](filePath,ignoreFirstLine = true,includedFields = Array(0,2)).print()

    /**
      * 通过class来获取指定的列
      */
    case class MyCsvClass(name: String,age: Int)

    env.readCsvFile[MyCsvClass](filePath,ignoreFirstLine = true,includedFields = Array(0,1)).print()


  }

}
