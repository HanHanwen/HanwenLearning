package com.imooc.flink.scala.DataSetDataSource

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala._

import scala.collection.mutable.ListBuffer


object DataSetTransformationApp {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    //mapFunction(env)

    //filterFunction(env)

    //mapPartition(env)

    //firstFunction(env)

    //flatMapFunction(env)

    joinFunction(env)

  }


  def joinFunction(env: ExecutionEnvironment): Unit ={
    val info1 = ListBuffer[(Int,String)]()  //第一个数据集：   编号   姓名

    info1.append((1,"zhao"))
    info1.append((2,"qian"))
    info1.append((3,"sun"))
    info1.append((4,"li"))

    val info2 = ListBuffer[(Int,String)]()  //第二个数据集：   编号   城市

    info2.append((1,"上海"))
    info2.append((2,"北京"))
    info2.append((3,"成都"))
    info2.append((5,"杭州"))

    val data1 = env.fromCollection(info1)
    val data2 = env.fromCollection(info2)

    data1.join(data2).where(0).equalTo(0).apply((first,second)=>{
      (first._1,first._2,second._2)   //取第一个数据集的编号+姓名，取第二个数据集的城市
    }).print()
  }


  def flatMapFunction(env: ExecutionEnvironment): Unit ={
    val info = ListBuffer[String]()

    info.append("hadoop,spark")
    info.append("hadoop,flink")
    info.append("hadoop,hive")

    val data = env.fromCollection(info)

    //data.flatMap(_.split(",")).print()
    data.flatMap(_.split(",")).map((_,1)).groupBy(0).sum(1).print()
  }


  def firstFunction(env: ExecutionEnvironment): Unit ={
    val info = ListBuffer[(Int,String)]()

    info.append((1,"Hadoop"))
    info.append((1,"Flink"))
    info.append((1,"Spark"))
    info.append((2,"Hive"))
    info.append((3,"Java"))
    info.append((4,"Presto"))

    val data = env.fromCollection(info)

    //data.first(3).print()//展示前3个
    //data.groupBy(0).first(2).print()//分组以后展示当前分组的前2个
    data.groupBy(0).sortGroup(1,Order.DESCENDING).first(1).print()
  }



  def mapPartition(env: ExecutionEnvironment): Unit ={
    /**
      * 假设dataSource中有100个元素，将这部分数据写入数据库；最低级的方法是每一个元素调用一次连接
      */

    val students = new ListBuffer[String]

    for(i<- 1 to 100){
      students.append("student" + i)
    }

    /**
    env.fromCollection(students)
      .map(x => {
        //每一个元素存储入数据库，则每一次均调用一次连接
        val connection = DBUtils.getConnection()
        println(connection + "^^……^^")

        //TODO... 保存数据到DB
        DBUtils.returnConnection(connection)
      }).print()

      */

    /**
      * 设置并行处理
      * 当多个元素时，若是一个元素插入数据库就调用一次连接，则数据库连接并行度太低，很快将会被打满
      * 故设置将数据进行分区，多个元素整合入一个分区，则再次写入数据库时，提高数据库并行度，降低连接数
      */
    env.fromCollection(students).setParallelism(5)
      .mapPartition(x => {
        val connection = DBUtils.getConnection()
        println(connection + "^^……^^")

        //TODO... 保存数据到DB
        DBUtils.returnConnection(connection)

        x
      }).print()

  }


  /**
    * 过滤
    * @param env
    */
  def filterFunction(env: ExecutionEnvironment): Unit ={
    //val data = env.fromCollection(List(1,2,3,4,5,6,7,8,9,10))
    //data.map(_ + 1).filter(_ > 5).print()

    env.fromCollection(List(1,2,3,4,5,6,7,8,9,10))
      .map(_ + 1)
      .filter(_ > 5)
      .print()
  }

  /**
    * map处理
    * @param env
    */
  def mapFunction(env: ExecutionEnvironment): Unit ={
    val data = env.fromCollection(List(1,2,3,4,5,6,7,8,9,10))

    //data.map((x:Int) => x+1 ).print()
    //data.map( (x) => x+1 ).print()
    //data.map( x => x+1 ).print()

    data.map(_ + 1).print()

  }
}
