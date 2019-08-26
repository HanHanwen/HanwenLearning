package com.imooc.flink.scala.DataStreamDataSource

import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}

class CustomRichParallelSourceFunction extends RichParallelSourceFunction[Long]{
  var isRunning = true

  var counter = 1L

  override def run(ctx: SourceFunction.SourceContext[Long]): Unit = {
    while (isRunning){
      ctx.collect(counter)
      counter += 1

      Thread.sleep(1000)
    }
  }

  override def cancel(): Unit = {
    isRunning = false
  }
}
