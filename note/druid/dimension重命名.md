# 方法：

## 代码

```json
"dataSchema": {
  "dataSource": "druid_topic_dw_dj_user_flow_test",
  "parser": {
    "type": "string",
    "parseSpec": {
      "format": "json",
      "dimensionsSpec": {
        "dimensions": ["city_id","city_name","app_version"],
        "dimensionsExclusions": []
      },
      "timestampSpec": {
        "format": "yyyy-MM-dd HH:mm:ss.SSSZ",
        "column": "dw_insert_time"
      }
    }
  },
  "transformSpec": {
    "transforms": [
      { "type": "expression", "name": "city_id", "expression": "city_id_select" },
      { "type": "expression", "name": "city_name", "expression": "city_name_select" }
    ]
  }
}
```

## 介绍

根据官网介绍：

> https://druid.apache.org/docs/latest/ingestion/index.html#dataschema

> The `dimensionsSpec` is located in `dataSchema` → `parser` → `parseSpec` → `dimensionsSpec` and is responsible for configuring [dimensions](https://druid.apache.org/docs/latest/ingestion/index.html#dimensions).

同时：

> The name of the dimension. This will be used as the field name to read from input records, as well as the column name stored in generated segments.
>
> Note that you can use a [`transformSpec`](https://druid.apache.org/docs/latest/ingestion/index.html#transformspec) if you want to rename columns during ingestion time.

> The `transformSpec` is located in `dataSchema` → `transformSpec` and is responsible for transforming and filtering records during ingestion time. It is optional.

得出结论：

> 需在dataSchema中新增与parser同级的transformSpec来处理字段重命名，且查看之后得出transformSpec语法如下：

```json
{
  "type": "expression",//固定
  "name": "<output name>",//输出字段名
  "expression": "<expr>"//输入字段，且接受进行函数转化
}
```

> The `expression` is a [Druid query expression](https://druid.apache.org/docs/latest/misc/math-expr.html).