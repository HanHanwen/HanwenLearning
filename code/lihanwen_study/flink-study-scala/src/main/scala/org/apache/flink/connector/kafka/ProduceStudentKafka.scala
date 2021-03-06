package org.apache.flink.connector.kafka

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer

object ProduceStudentKafka {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    var stream = env.socketTextStream("localhost",9999)

    val brokerlist = "localhost:9092"
    val topic = "student"

    val myProducer = new FlinkKafkaProducer[String](
      brokerlist,
      topic,
      new SimpleStringSchema()
    )


    myProducer.setWriteTimestampToKafka(true)

    stream.addSink(myProducer)

    env.execute("ProduceToKafka")


  }

}
