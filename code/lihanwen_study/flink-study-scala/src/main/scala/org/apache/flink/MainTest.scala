package org.apache.flink

import java.text.SimpleDateFormat

import scala.util.parsing.json.JSON

object MainTest {

  def main(args: Array[String]): Unit = {
    val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
    val date = System.currentTimeMillis()

    val time = simpleDateFormat.format(date).concat("+0800")

    println(time)

    println(JSON.formatted(time))

  }
}
