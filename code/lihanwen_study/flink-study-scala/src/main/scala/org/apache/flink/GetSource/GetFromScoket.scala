package org.apache.flink.GetSource

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

class GetFromScoket {
  def getFromSocket(env: StreamExecutionEnvironment): Unit ={

    env.socketTextStream("localhost", 9999)


  }

}
