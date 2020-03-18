#调用事例：
#启动：./quick_job.sh start flink
#      第二个参数选填，若是不填写，则一次性启动所有任务
#关闭：./quick_job.sh stop flink
#      第二个参数选填，若是不填写，则一次性关闭所有任务


# 设置日志路径
logPath="/Users/lihanwen/Study/log"



#创建本地启动命令函数
function StartJob()
{
  case $2 in
    mysql)
      #启动mysql服务
      /usr/local/opt/mysql@5.5/bin/mysql.server start > $logPath/mysql_start.log
    ;;
    flink)
      #启动flink服务
      /Users/lihanwen/Software/flink-1.6.4/bin/start-cluster.sh > $logPath/flink_start.log
    ;;
    zookeeper)
      #启动zookeeper
      /usr/local/Cellar/kafka/2.1.1/bin/zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties > $logPath/zookeeper_start.log &
    ;;
    kafka)
      #启动kafka
      /usr/local/Cellar/kafka/2.1.1/bin/kafka-server-start /usr/local/etc/kafka/server.properties > $logPath/kafka_start.log &
    ;;
  esac
}



#创建本地关闭命令函数
function StopJob()
{
  case $2 in
    mysql)
      /usr/local/opt/mysql@5.5/bin/mysql.server stop > $logPath/mysql_stop.log
    ;;
    flink)
      /Users/lihanwen/Software/flink-1.6.4/bin/stop-cluster.sh > $logPath/flink_stop.log
    ;;
    zookeeper)
      /usr/local/Cellar/kafka/2.1.1/bin/zookeeper-server-stop /usr/local/etc/kafka/zookeeper.properties > $logPath/zookeeper_stop.log &
    ;;
    kafka)
      /usr/local/Cellar/kafka/2.1.1/bin/kafka-server-stop /usr/local/etc/kafka/server.properties > $logPath/kafka_stop.log &
    ;;
  esac
}



main()
{
  #输入第一个参数不为空
  if [[ $1 ]]; then

    # 第一个参数为start|stop才进入解析
    if [[ ($1 = "start" || $1 = "stop") ]]; then
        case $1 in
          "start")
            StartJob $@
          ;;
          "stop")
            StopJob $@
          ;;
        esac
    else
      echo "请输入有效参数"
    fi

  else
    echo "请输入参数"
  fi
}

main $@