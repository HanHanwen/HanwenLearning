package org.apache.flink.DataStreamWindow

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object WindowProcessApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val data = env.socketTextStream("localhost",9999)

    data.flatMap(_.split(","))
        .keyBy(1)
        .timeWindow(Time.seconds(2))




    env.execute("WindowProcessApp")
  }

}
