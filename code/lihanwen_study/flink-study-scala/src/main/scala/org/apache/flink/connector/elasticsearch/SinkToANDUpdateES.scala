package org.apache.flink.connector.elasticsearch

import java.text.SimpleDateFormat
import java.util.Properties

import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.connector.entity.Student
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer



object SinkToANDUpdateES {
  /**
    * 写入数据到es，并进行更新
    */
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    /**
      * 从kafka读取数据
      */
    val properties = new Properties()
    val topic = "student"//从kafka群组中消费的topic

    properties.setProperty("bootstrap.servers", "localhost:9092")//逗号分割的Kafka broker列表
    properties.setProperty("zookeeper.connect", "localhost:2181")//逗号分割的Zookeeper服务器列表
    properties.setProperty("group.id", topic.concat("-group"))//Kafka消费组id

    import org.apache.flink.api.scala._

    val myConsumer = new FlinkKafkaConsumer[String](topic,new SimpleStringSchema(),properties)

    val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
    val date = System.currentTimeMillis()

    //设置插入es的时间的格式
    val time = simpleDateFormat.format(date).concat("+0800")

    var student = new Student()

    myConsumer.setStartFromGroupOffsets()

    //从kafka获取数据
    val stream = env.addSource[String](myConsumer)

    val json1 = stream.toString

    println(json1.getClass)

    val json = JSON.parseObject(json1)

    student.name = json.getString("name")

    println(student.name)

    stream.print()


    env.execute("SinkToAndUpdateES")

  }

}
