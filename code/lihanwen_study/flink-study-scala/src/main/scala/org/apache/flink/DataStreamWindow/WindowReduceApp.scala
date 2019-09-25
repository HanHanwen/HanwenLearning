package org.apache.flink.DataStreamWindow

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object WindowReduceApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    /**
      * 此处为了实现ReduceFunction，故scoket传入元素需为数值型
      * ReduceFunction
      *
      * val input: DataStream[(String, Long)] = ...
      *
      * input
      * .keyBy(<key selector>)
      * .window(<window assigner>)
      * .reduce { (v1, v2) => (v1._1, v1._2 + v2._2) }
      */

    val data = env.socketTextStream("localhost",9999)

    data.flatMap(_.split(","))
      .map(x => (1,x.toInt))//传入数值：1 2 3 4 5转化为（1，1）（1，2）（1，3）（1，4）（1，5）
      .keyBy(0)//因为key都是1，所以所有的元素都分配到一个task执行
      .timeWindowAll(Time.seconds(5))
      .reduce{(v1,v2) => {
        println(v1 + "..." + v1)

        (v1._1 , v1._2 + v2._2)
      }}
      .print()


    env.execute("WindowReduceApp")


  }

}
