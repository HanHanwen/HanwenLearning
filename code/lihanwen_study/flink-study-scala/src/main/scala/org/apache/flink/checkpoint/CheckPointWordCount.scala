package org.apache.flink.checkpoint

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object CheckPointWordCount {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.streaming.api.scala._

    env.socketTextStream("localhost",9999)
        .flatMap(_.toLowerCase.split("\\W+"))
        .filter(_.nonEmpty)
        .map((_,1))
        .keyBy(0)
        .sum(1)
        .print()



    env.execute("CheckPoint-WordCount")


  }

}
