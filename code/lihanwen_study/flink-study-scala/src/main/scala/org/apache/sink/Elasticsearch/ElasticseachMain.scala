package org.apache.sink.Elasticsearch

import java.util

import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests

object ElasticseachMain {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //获取数据
    val input: DataStream[String] = env.socketTextStream("localhost",9999)


    val httpHosts = new util.ArrayList[HttpHost]()

    //本地elasticsearch端口
    httpHosts.add(new HttpHost("localhost",9200))


    val esSinkBuilder = new ElasticsearchSink.Builder[String](
      httpHosts,
      new ElasticsearchSinkFunction[String]{
        override def process(element: String, ctx: RuntimeContext, indexer: RequestIndexer): Unit = ???


        def createIndexRequest(element: String): IndexRequest = {
          val json = new java.util.HashMap[String, String]
          json.put("data", element)

          return Requests.indexRequest()
            .index("my-index")
            .`type`("my-type")
            .source(json)

        }
      }
    )

    //设置每个批量请求要缓冲的最大操作数
    esSinkBuilder.setBulkFlushMaxActions(1)

    //设置REST客户端工厂以进行自定义客户端配置
    //    esSinkBuilder.setRestClientFactory(
    //      restClientBuilder -> {
    //        restClientBuilder.setDefaultHeaders(...)
    //        restClientBuilder.setMaxRetryTimeoutMillis(...)
    //        restClientBuilder.setPathPrefix(...)
    //        restClientBuilder.setHttpClientConfigCallback(...)
    //      }
    //    )


    input.addSink(esSinkBuilder.build)


    env.execute("Sink_To_Elasticsearch")

  }

}
