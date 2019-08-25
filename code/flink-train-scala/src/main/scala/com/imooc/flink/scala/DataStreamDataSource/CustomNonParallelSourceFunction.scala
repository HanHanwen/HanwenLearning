package com.imooc.flink.scala.DataStreamDataSource

import org.apache.flink.streaming.api.functions.source.SourceFunction

/**
  * 单并行度，无法设置多并行度
  */
class CustomNonParallelSourceFunction extends SourceFunction[Long]{
  var counter = 1l

  var isRunning = true

  override def run(ctx: SourceFunction.SourceContext[Long]): Unit = {
    while (isRunning){

      ctx.collect(counter)
      counter += 1
      Thread.sleep(1000) //1s运行一次

    }

  }

  override def cancel(): Unit = {
    isRunning = false
  }
}
