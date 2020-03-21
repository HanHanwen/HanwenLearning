> 官网：https://cwiki.apache.org/confluence/display/Hive/Configuration+Properties

# 参数查看

```sql
--set + 参数
set hive.default.fileformat;
```



# 参数类型

## 固定型参数

> 此类参数，正常情况下一次设置后，后续基本上无需二次更新



```sql
set hive.default.fileformat=orc;
```

> 作用：设置后续所有创建的表为orc格式，即后续所有表为***列存储***格式；支持presto查询



```sql
set hive.exec.dynamic.partition=true;

set hive.exec.dynamic.partition.mode=nonstrict;
```



```sql
set hive.auto.convert.join=true;
```

> 作用：设置关联在map端进行，主要适用于大表与小表的关联



```sql
set hive.exec.parallel=true;
```

> 作用：设置任务并行运行，主要适用于union all情况



## 可变型参数

```sql
set mapred.max.split.size=30000000;
--一个map输入的最大大小，单位为b

set mapred.min.split.size.per.node=30000000;
--同一个节点的数据块形成切片时，切片大小的最小值；不得超过map的大小，建议与map大小一致

set mapred.min.split.size.per.rack=30000000;
--同一个机架的数据块形成切片时，切片大小的最小值；不得超过map的大小，建议与map大小一致

set hive.exec.reducers.bytes.per.reducer=30000000;
--每个reduce任务处理的数据量
```



hadoop 0.20开始通过如下公式定义map的输入长度

```sql
Math.max(minSize, Math.min(maxSize, blockSize))
--minSize:mapred.min.split.size
--maxSize:mapred.max.split.size

--简而言之，hive的map数由输入文件大小与定义map大小进行控制
```



reduce数控制

```sql
--计算reducer数的公式很简单N=min(参数2，总输入数据量/参数1)
--参数1:
hive.exec.reducers.bytes.per.reducer
--参数2:hive内置设置reduce最大数量
hive.exec.reducers.max
```



什么情况下会只有一个reduce？

```tex
1.数据量小于hive.exec.reducers.bytes.per.reducer

2.没有group by汇总，如使用distinct

3.用了全局排序order by

4.笛卡尔积
```





举例：

```shell
hadoop fs -du -h /bd/bi_dw_table/
141.7 M   425.1 M  /bd/bi_dw_table/dt=2020-03-20
```

```sql
set mapred.max.split.size=2000000;
set mapred.min.split.size.per.node=2000000;
set mapred.min.split.size.per.rack=2000000;
set hive.exec.reducers.bytes.per.reducer=2000000;

drop table if exists temp.temp_lhw_test;
create table temp.temp_lhw_test
  as
select
     device_id
    ,channel_code as channel
    ,row_number() over(partition by device_id order by client_unixtime asc) as rnk
from bd.bi_dw_table
where dt='2020-03-20'
;


--Hadoop job information for Stage-1: number of mappers: 73; number of reducers: 75

--141.7M/(2000000b/1024/1024)=74
```

