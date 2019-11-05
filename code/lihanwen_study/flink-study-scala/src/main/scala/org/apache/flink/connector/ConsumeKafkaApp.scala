package org.apache.flink.connector

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

object ConsumeKafkaApp {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    /**
      * Kafka Consumers and Fault Tolerance
      */
    env.enableCheckpointing(5000)//checkpoint every 5000 msecs，单位：毫秒
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)

    val properties = new Properties()
    val topic = "test"//从kafka群组中消费的topic

    properties.setProperty("bootstrap.servers", "localhost:9092")//逗号分割的Kafka broker列表
    properties.setProperty("zookeeper.connect", "localhost:2181")//逗号分割的Zookeeper服务器列表
    properties.setProperty("group.id", "mytest-group")//Kafka消费组id

    import org.apache.flink.api.scala._

    val myConsumer = new FlinkKafkaConsumer[String](topic,new SimpleStringSchema(),properties)


    /**
      * Topic discovery
      */
    val myConsumer02 = new FlinkKafkaConsumer[String](
      java.util.regex.Pattern.compile("test-topic-[0-9]"),
      new SimpleStringSchema(),
      properties
    )


    /**
      * Kafka Consumers Start Position Configuration
      */
    myConsumer02.setStartFromEarliest()
    //myConsumer.setStartFromLatest()

    //从指定的时间戳开始。 对于每个分区，其时间戳大于或等于指定时间戳的记录将用作开始位置。 如果分区的最新记录早于时间戳，则将仅从最新记录中读取分区。 在这种模式下，Kafka中已提交的偏移将被忽略，并且不会用作起始位置。
    //myConsumer.setStartFromTimestamp(0L)


    //If offsets could not be found for a partition, the auto.offset.reset setting in the properties will be used.
    //default behaviour
    myConsumer.setStartFromGroupOffsets()

    /*
    //针对不同分区设置不同的消费节点

    val specificStartOffsets = new util.HashMap[KafkaTopicPartition, java.lang.Long]()
    specificStartOffsets.put(new KafkaTopicPartition("myTopic", 0), 23L)
    specificStartOffsets.put(new KafkaTopicPartition("myTopic", 1), 31L)
    specificStartOffsets.put(new KafkaTopicPartition("myTopic", 2), 43L)

    myConsumer.setStartFromSpecificOffsets(specificStartOffsets)
    */

    val stream = env.addSource(myConsumer)


    stream.print()


    env.execute("ConsumeKafkaApp")

  }

}
