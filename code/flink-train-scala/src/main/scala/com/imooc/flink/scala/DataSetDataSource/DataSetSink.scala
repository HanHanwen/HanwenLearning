package com.imooc.flink.scala.DataSetDataSource

import org.apache.flink.api.scala._
import org.apache.flink.core.fs.FileSystem.WriteMode

object DataSetSink {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val data = 1 to 9

    val source = env.fromCollection(data)

    val filepath = "/Users/lihanwen/Study/mygit/HanwenLearning/text/sink/sink_txt"

    //覆盖重写
    source.writeAsText(filepath,WriteMode.OVERWRITE)

    //source.writeAsText(filepath,WriteMode.OVERWRITE).setParallelism(2)
    //设置并行度为2时，则将输出文件写成2部分

    env.execute("sink")
  }

}
