package com.imooc.flink.scala.DataStreamDataSource

import com.imooc.flink.scala.DataStreamDataSource.Connection.SinkToMysql
import com.imooc.flink.scala.DataStreamDataSource.Entity.Student
import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.util.Collector


object DataStreamSinkToMysql {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val source = env.socketTextStream("localhost",8888)

    import org.apache.flink.api.scala._


    val studentSream = source.flatMap(new FlatMapFunction[String,Student] {
      override def flatMap(value: String, out: Collector[Student]): Unit = {
        val student : Student = new Student()

        val list = value.split(",").toList

        student.id = list.apply(0).toInt
        student.name = list.apply(1)
        student.age = list.apply(2).toInt

        println(student.name)

        return student
      }
    })


    studentSream.addSink(new SinkToMysql())


    env.execute("SinkToMysql")

  }

}
