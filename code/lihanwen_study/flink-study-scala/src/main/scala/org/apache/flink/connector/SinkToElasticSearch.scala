package org.apache.flink.connector

import java.util

import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink
import org.apache.http.HttpHost
import org.elasticsearch.client.Requests

object SinkToElasticSearch {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val input = env.socketTextStream("localhost",9999)


    val httpHosts = new util.ArrayList[HttpHost]()
    httpHosts.add(new HttpHost("127.0.0.1",9200, "http"))

    val esSinkBuilder = new ElasticsearchSink.Builder[String](
      httpHosts,
      new ElasticsearchSinkFunction[String]{

        override def process(element: String, ctx: RuntimeContext, indexer: RequestIndexer): Unit = {
          val json = new java.util.HashMap[String, String]
          json.put("data", element)

          println(json)

          return Requests.indexRequest()
            .index("my-index")
            .`type`("my-type")
            .source(json)

        }
      }
    )


    //设置每个批量请求要缓冲的最大操作数
    esSinkBuilder.setBulkFlushMaxActions(1)


    input.addSink(esSinkBuilder.build())

    env.execute("SinkToElasticSearch")


  }

}
