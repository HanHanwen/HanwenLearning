package com.imooc.flink.scala.DataSetDataSource

import scala.util.Random

object DBUtils {
  def getConnection(): String ={
    new Random().nextInt(10) + ""
  }

  def returnConnection(connection: String): Unit ={

  }

}
