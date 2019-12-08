package org.apache.flink.connector.StateAndFaultTolerance

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object ExampleCountWindowAverage extends App {

  val env = StreamExecutionEnvironment.getExecutionEnvironment

  import org.apache.flink.api.scala._
  env.fromCollection(List(
    (1L, 3L),
    (1L, 5L),
    (1L, 7L),
    (1L, 4L),
    (1L, 2L)
  )).keyBy(_._1)
      .flatMap(new CountWindowAverage)
      .print()

  env.execute("ExampleManagedState")

}
