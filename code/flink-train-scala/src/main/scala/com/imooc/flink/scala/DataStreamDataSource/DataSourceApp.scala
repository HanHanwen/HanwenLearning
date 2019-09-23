package com.imooc.flink.scala.DataStreamDataSource

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._

object DataSourceApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    //GetSocketData(env)
    //GetNonParallelSourceFunction(env)
    //GetParallelSourceFunction(env)
    GetRichParallelSourceFunction(env)

    env.execute("DataStream")
  }


  /**
    * get rich parallel data
    */
  def GetRichParallelSourceFunction(env: StreamExecutionEnvironment): Unit ={
    val data = env.addSource(new CustomRichParallelSourceFunction()).setParallelism(2)

    data.print().setParallelism(1)
  }


  /**
    * get parallel data
    */
  def GetParallelSourceFunction(env: StreamExecutionEnvironment): Unit ={
    val data = env.addSource(new CustomParallelSourceFunction()).setParallelism(2)

    data.print().setParallelism(1)
  }


  /**
    * get non parallel data
    */
  def GetNonParallelSourceFunction(env: StreamExecutionEnvironment): Unit ={
    val data = env.addSource(new CustomNonParallelSourceFunction()).setParallelism(1)

    data.print().setParallelism(1)
  }


  /**
    * get socket data
    * @param env
    */
  def GetSocketData(env: StreamExecutionEnvironment): Unit ={
    val data = env.socketTextStream("localhost",9999)

    data.print().setParallelism(1)

  }

}
