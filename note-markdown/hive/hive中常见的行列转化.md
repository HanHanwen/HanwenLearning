# 行列转换常用的函数

## Constructor Function

| Constructor Function | Operands                  | Description                                   |
| -------------------- | ------------------------- | --------------------------------------------- |
| map                  | (key1,value1,key2,value2) | Creates a map with the given key/value pairs. |



> 数据【处理前】

| uuid          | open_id     | union_id    |
| ------------- | ----------- | ----------- |
| 37553da0-555f | oavWRuDOu3D | oCwKwuBz48b |

```sql
select
     uuid
    ,key
    ,value
from
(
    select
         uuid
        ,map('open_id',open_id,'union_id',union_id) as mess
)a
--最终结果mess默认输出为key，value
```



> 数据【处理后】

```sql
{"open_id":"oavWRuDOu3D","union_id":"oCwKwuBz48b"}
```



## Built-in Aggregate Function(UDAF)

| Return Type | Name(Signature)   | Description                                                  |
| ----------- | ----------------- | ------------------------------------------------------------ |
| array       | collect_set(col)  | Returns a set of objects with duplicate elements eliminated. |
| array       | collect_list(col) | Returns a list of objects with duplicates.                   |



> 数据【处理前】

| uuid          | key      | value       |
| ------------- | -------- | ----------- |
| 37553da0-555f | union_id | oavWRuDOu3D |
| 37553da0-555f | open_id  | oCwKwuBz48b |
| 37553da0-555f | open_id  | oCwKwuBz23T |
| 37553da0-555f | open_id  | oCwKwuBz48b |

```sql
select
     uuid
    ,key
    ,collect_set(value) as value
group by 1,2
```

> 数据【处理后】

| uuid          | key      | value                         |
| ------------- | -------- | ----------------------------- |
| 37553da0-555f | union_id | oavWRuDOu3D                   |
| 37553da0-555f | open_id  | ["oCwKwuBz48b","oCwKwuBz23T"] |



```sql
select
     uuid
    ,key
    ,collect_list(value) as value
group by 1,2
```

> 数据【处理后】

| uuid          | key      | value                                       |
| ------------- | -------- | ------------------------------------------- |
| 37553da0-555f | union_id | ["oavWRuDOu3D"]                             |
| 37553da0-555f | open_id  | ["oCwKwuBz48b","oCwKwuBz23T","oCwKwuBz48b"] |





## String Functions

| Return Type        | Name(Signature)                          | Description                                                  |
| ------------------ | ---------------------------------------- | ------------------------------------------------------------ |
| string             | concat_ws(string SEP,string A,string B…) | Like concat(), but with custom separator SEP                 |
| string             | concat_ws(string SEP,array<string>)      |                                                              |
| map<string,string> | str_to_map(text[,delimiter1,delimiter2]) | Splits text into key-value pairs using two delimiters. Delimiter1 separates text into K-V pairs, and Delimiter2 splits each K-V pair. Default delimiters are ',' for delimiter1 and ':' for delimiter2. |

```sql
select
     uuid
    ,key
    ,concat_ws(',',collect_set(value)) as value
```

```sql
str_to_map(concat_ws(',',collect_set(concat_ws(':',key,value)))) as message1
```





## Built-in Operators

| Example            | Operators              | Description           |
| ------------------ | ---------------------- | --------------------- |
| A[B], A.identifier | bracket_op([]), dot(.) | element selector, dot |

> 该函数建议使用Map进行处理，针对其他方式当前暂未发现

```sql
select
     uuid
    ,str_to_map(concat_ws(',',collect_set(concat_ws(':', key,value))))['union_id'] as union_id
    ,str_to_map(concat_ws(',',collect_set(concat_ws(':', key,value))))['open_id'] as open_id
```





# hive宽表转高表





# hive高表转宽表

