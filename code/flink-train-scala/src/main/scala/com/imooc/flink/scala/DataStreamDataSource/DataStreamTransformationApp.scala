package com.imooc.flink.scala.DataStreamDataSource

import org.apache.flink.streaming.api.scala._

object DataStreamTransformationApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    filterFunction(env)


    env.execute("DataStreamTransformation")
  }

  def filterFunction(env : StreamExecutionEnvironment): Unit ={
    val data = env.addSource(new CustomNonParallelSourceFunction())

    data.map( x => {
      println("receive: " + x)//打印该算子内容
      x
    }).filter(_%2 == 0).print()
  }

}
