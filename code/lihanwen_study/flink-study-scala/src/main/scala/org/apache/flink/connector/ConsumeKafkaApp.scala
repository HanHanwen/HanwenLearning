package org.apache.flink.connector

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

object ConsumeKafkaApp {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val properties = new Properties()
    val topic = "test"//从kafka群组中消费的topic

    properties.setProperty("bootstrap.servers", "localhost:9092")//逗号分割的Kafka broker列表
    properties.setProperty("zookeeper.connect", "localhost:2181")//逗号分割的Zookeeper服务器列表
    properties.setProperty("group.id", "mytest-group")//Kafka消费组id

    import org.apache.flink.api.scala._

    env.addSource(new FlinkKafkaConsumer[String](topic,new SimpleStringSchema(),properties)).print()


    env.execute("ConsumeKafkaApp")

  }

}
