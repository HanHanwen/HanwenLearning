package com.imooc.flink.scala.DataStreamDataSource

import java.{lang, util}

import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.scala._

object DataStreamTransformationApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //filterFunction(env)
    //unionFunction(env)

    splitFunction(env)


    env.execute("DataStreamTransformation")
  }

  def splitFunction(env: StreamExecutionEnvironment): Unit ={

    val data = env.addSource(new CustomNonParallelSourceFunction())

    val splits = data.split(new OutputSelector[Long] {
      override def select(value: Long): lang.Iterable[String] = {

        val list = new util.ArrayList[String]()

        if(value%2 == 0){
          list.add("偶数")

        }else{
          list.add("奇数")
        }
        list
      }
    })

    splits.select("偶数").print().setParallelism(1)

  }


  def unionFunction(env: StreamExecutionEnvironment): Unit ={
    val data1 = env.addSource(new CustomNonParallelSourceFunction())
    val data2 = env.addSource(new CustomNonParallelSourceFunction())

    data1.union(data2).print().setParallelism(1)
  }


  def filterFunction(env : StreamExecutionEnvironment): Unit ={
    val data = env.addSource(new CustomNonParallelSourceFunction())

    data.map( x => {
      println("receive: " + x)//打印该算子内容
      x
    }).filter(_%2 == 0).print()
  }

}
