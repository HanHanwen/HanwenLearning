package org.apache.flink.DataStreamWindow



import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector


object WindowProcessApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val data = env.socketTextStream("localhost",9999)


    import org.apache.flink.api.scala._

    data.flatMap(_.split(","))
        .map(x => (1,x))
        .keyBy(0)
        .timeWindow(Time.minutes(5))
//        .process(new ProcessWindowFunction() {
//          override def process(key: Tuple, context: Context, elements: (Integer, Integer),
//                               out: Collector[Object]): Unit = {
//
//          }
//        }[(Integer, Integer), Object, Tuple, TimeWindow])


    env.execute("WindowProcessApp")

  }


}