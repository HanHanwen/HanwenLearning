package com.imooc.flink.scala.DataSetDataSource

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration
import org.apache.flink.api.common.accumulators.LongCounter
import org.apache.flink.core.fs.FileSystem.WriteMode

object DataSetRichMapFunctionApp {
  def main(args: Array[String]): Unit = {
    /**
      * flink计数器开发
      *
      * step1:定义计数器
      * step2:注册计数器
      * step3:获取计数器
      */
    val env = ExecutionEnvironment.getExecutionEnvironment

    val data = env.fromElements("hadoop","hive","spark","flink","ck")

    //    data.map(new RichMapFunction[String, Long](){
    //      var counter = 0l
    //
    //      override def map(value: String): Long = {
    //        counter = counter + 1
    //
    //        println("counter:" + counter)
    //
    //        counter
    //      }
    //    }).setParallelism(3).print()

    val info = data.map(new RichMapFunction[String,String]() {

      //step1:定义计数器
      var counter = new LongCounter()

      override def open(parameters: Configuration): Unit = {
        //step2:注册计数器

        getRuntimeContext.addAccumulator("ele-counter-scala",counter)

      }

      override def map(value: String): String = {
        counter.add(1)

        value
      }
    })

    val filepath = "/Users/lihanwen/Study/mygit/HanwenLearning/text/sink/sink_txt"

    info.writeAsText(filepath,WriteMode.OVERWRITE).setParallelism(3)

    val JobResult = env.execute("CounterApp")

    //step3:获取计数器
    val num = JobResult.getAccumulatorResult[Long]("ele-counter-scala")

    println("num:    " + num)

  }

}
