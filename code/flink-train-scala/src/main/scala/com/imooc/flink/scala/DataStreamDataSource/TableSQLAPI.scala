package com.imooc.flink.scala.DataStreamDataSource

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.types.Row

object TableSQLAPI {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    //获取table环境
    val tableEnv = TableEnvironment.getTableEnvironment(env)

    import org.apache.flink.api.scala._

    //获取文件路径
    val filepath = "file:///Users/lihanwen/Study/mygit/HanwenLearning/text/sales.csv"

    //生成DataSet
    val salesLog = env.readCsvFile[SalesLog](filepath,ignoreFirstLine=true)

    //salesLog.print()


    /**
      * Converts the given "DataSet" into a "Table"
      * *
      * * The field names of the "Table" are automatically derived from the type of the "DataSet"
      */
    val salesTable = tableEnv.fromDataSet(salesLog)


    tableEnv.registerTable("sales",salesTable)

    val table  = tableEnv.sqlQuery("select * from sales")


    tableEnv.toDataSet[Row](table).print()


  }

  case class SalesLog(transporter_id:String,user_id:String,sku_id:String,price:Double)

}
