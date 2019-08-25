package com.imooc.flink.scala.DataStreamDataSource

import org.apache.flink.streaming.api.scala._

object DataSourceApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //GetSocketData(env)
    GetNonParallelSourceFunction(env)

    env.execute("DataStream")
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
