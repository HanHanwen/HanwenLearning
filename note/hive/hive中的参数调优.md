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

set mapred.min.split.size.per.node=30000000;

set mapred.min.split.size.per.rack=30000000;

set hive.exec.reducers.bytes.per.reducer=30000000;
```

