package com.imooc.flink.scala.BasicWC

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time


//使用scala开发flink的刘处理数据
object StreamingWCScalaAPP {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val text = env.socketTextStream("localhost",9999)

    import org.apache.flink.api.scala._

//    text.flatMap(_.toLowerCase.split(",")).filter(_.nonEmpty)
//        .map((_,1))
//        .keyBy(0)
//        .timeWindow(Time.seconds(5))
//        .sum(1)
//        .print()
//        .setParallelism(1)

    text.flatMap(_.toLowerCase().split(","))
        .filter(_.nonEmpty)
        .map(x => WC(x,1))
        //.keyBy("word")
        .keyBy(_.word)
        .timeWindow(Time.seconds(2))
        .sum("count")
        .print()
        .setParallelism(1)

    env.execute("FlinkStreamingApp")
  }

  case class WC(word:String ,count:Int)

}
