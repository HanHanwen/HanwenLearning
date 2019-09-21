package com.imooc.flink.scala.DataStreamDataSource.Connection

import java.sql.{Connection, DriverManager, PreparedStatement}

import com.imooc.flink.scala.DataStreamDataSource.Entity.Student
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}

class SinkToMysql extends RichSinkFunction[Student]{

  var ps: PreparedStatement = null
  var ct: Connection = null



  //open() 方法中建立连接，这样不用每次 invoke 的时候都要建立连接和释放连接
  override def open(parameters: Configuration): Unit = {
    super.open(parameters)

    val connection = getConnection()

    val sql : String = "insert into Student2(id, name, age) values(?, ?, ?);"

    ps = connection.prepareStatement(sql)
  }

  override def close(): Unit = {
    super.close()

    //关闭和释放资源
    if(ct != null) ct.close()
    if(ps != null) ps.close()
  }

  //每条数据的插入都要调用一次 invoke() 方法
  override def invoke(value: Student, context: SinkFunction.Context[_]): Unit = {
    super.invoke(value, context)

    ps.setInt(1,value.id)
    ps.setString(2,value.name)
    ps.setInt(3,value.age)

    ps.executeUpdate()
  }


  def getConnection():Connection = {
    var con: Connection = null

    try {
      Class.forName("com.mysql.jdbc.Driver")
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF" +
        "-8", "root", "root")
    } catch {
      case e: Exception =>

        println("-----------mysql get connection has exception , msg = " + e.getMessage)
    }

    return con
  }
}