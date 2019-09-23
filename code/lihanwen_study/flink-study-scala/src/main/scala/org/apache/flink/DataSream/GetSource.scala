package org.apache.flink.DataSream

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object GetSource {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    GetFromSocket(env)

    env.execute("get-from-scoket")

  }

  def GetFromSocket(env : StreamExecutionEnvironment): Unit ={
    val data = env.socketTextStream("localhost",9999)

    data.print()


  }

}
