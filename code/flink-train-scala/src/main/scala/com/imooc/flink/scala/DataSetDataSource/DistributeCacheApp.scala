package com.imooc.flink.scala.DataSetDataSource

import org.apache.commons.io.FileUtils
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration

object DistributeCacheApp {
  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    val filePath = "file:///Users/lihanwen/Study/mygit/HanwenLearning/text/hello.txt"
    val filename = "DistributeCache"

    //step1:注册一个本地/hdfs文件
    env.registerCachedFile(filePath,filename)

    val data = env.fromElements("hadoop","scala","flink","hive")

    data.map(new RichMapFunction[String,String](){

      //step2:使用open方法获取分布式缓存内容
      override def open(parameters: Configuration): Unit = {

        val DCFile = getRuntimeContext.getDistributedCache.getFile(filename)

        val lines = FileUtils.readLines(DCFile)//java

        /**
          * 此时会出现java类型与scala类型的冲突
          */
        import scala.collection.JavaConverters._
        for(ele <- lines.asScala){//scala
          println("DistributeCache: " + ele)
        }

      }

      override def map(value: String): String = {
        value
      }
    }).print()


  }

}
