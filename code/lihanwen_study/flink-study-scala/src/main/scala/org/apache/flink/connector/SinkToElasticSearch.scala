package org.apache.flink.connector

import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests

object SinkToElasticSearch {
  def main(args: Array[String]): Unit = {

    /**
curl -X PUT "http://localhost:9200/my-index" -H 'Content-Type:application/json' -d'
{
  "mappings":{
    "my-type":{
      "properties": {
        "desc": {
          "type": "text"
        }
      }
    }
  }
}'
      */

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val input = env.socketTextStream("localhost",9999)


    val httpHosts = new java.util.ArrayList[HttpHost]()
    httpHosts.add(new HttpHost("127.0.0.1",9200, "http"))

    val esSinkBuilder = new ElasticsearchSink.Builder[String](
      httpHosts,
      new ElasticsearchSinkFunction[String] {
        def createIndexRequest(element: String): IndexRequest = {
          val json = new java.util.HashMap[String, String]
          json.put("desc", element)

          return Requests.indexRequest()
            .index("my-index")
            .`type`("my-type")
            .source(json)
        }

        override def process(element: String, ctx: RuntimeContext, indexer: RequestIndexer): Unit = {
          indexer.add(createIndexRequest(element))
        }
      }
    )


    //设置每个批量请求要缓冲的最大操作数
    //esSinkBuilder.setBulkFlushMaxActions(1)

    input.print()

    input.addSink(esSinkBuilder.build)

    env.execute("SinkToElasticSearch")


  }

}
