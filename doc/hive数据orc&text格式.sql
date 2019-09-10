set hive.default.fileformat=orc;
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.exec.max.dynamic.partitions=100000;
set hive.exec.max.dynamic.partitions.pernode=100000;
set hive.auto.convert.join = false;
set mapred.max.split.size=10000000;
set mapred.min.split.size.per.node=10000000;
set mapred.min.split.size.per.rack=10000000;
set hive.exec.reducers.bytes.per.reducer=180000000;
set hive.exec.parallel=true;

-- map join
-- set hive.auto.convert.join=true;
set hive.mapjoin.smalltable.filesize=25000000;

-- 是否将多个mapjoin合并为一个
set hive.auto.convert.join.noconditionaltask=true;  

-- 多个mapjoin转换为1个时，所有小表的文件大小总和的最大值。
set hive.auto.convert.join.noconditionaltask.size=894435328;  

-- 将动态分区字段全局排序
set hive.optimize.sort.dynamic.partition=false;

-- ？ Map Join任务HashMap中key对应value数量 
set hive.smbjoin.cache.rows=10000;

-- 新创建的表/分区是否自动计算统计数据
set hive.stats.autogather=true;
set hive.stats.fetch.column.stats=true;
set hive.compute.query.using.stats=true;

-- 是否开启自动使用索引
set hive.optimize.index.filter=true;


-- 将只有SELECT, FILTER, LIMIT转化为FETCH, 减少等待时间
set hive.fetch.task.conversion=more;
set hive.fetch.task.conversion.threshold=1073741824;

-- 在只有map的作业结束时合并小文件，默认开启true；
set hive.merge.mapfiles=true;

-- 正常的map-reduce job后，是否启动merge job来合并reduce端输出的结果，建议开启
set hive.merge.mapredfiles=false;

-- 作业结束时合并文件的大小，默认256MB；
set hive.merge.size.per.task=256000000;

-- 在作业输出文件小于该值时，起一个额外的map/reduce作业将小文件合并为大文件，小文件的基本阈值，设置大点可以减少小文件个数，需要mapfiles和mapredfiles为true，默认值是16MB
set hive.merge.smallfiles.avgsize=16000000;

   drop table if exists bi_temp.temp_dw_dj_log_rn_init_lhw;
 create table           bi_temp.temp_dw_dj_log_rn_init_lhw
           as
       select
              lower(l.event_id                                                                  )  event_id                    --  事件ID
             ,lower(l.session_id                                                                )  session_id                  --  会话编码
             ,lower(l.ref_page_name                                                             )  ref_page_name               --  上一跳页面名称
             ,lower(l.page_name                                                                 )  page_name                   --  页面名称
             ,regexp_replace(lower(l.device_id),'rn_','')                                          device_id                   --  APP设备号
             ,lower('rn'                                                                        )  app_name                    --  app 名称
             ,trim(lower(l.channel_name                                                        ))  channel_code                --  渠道代码
         from ods_dj.ods_dj_log_rn      l
         from ods_dj.ods_dj_log_rn      l
        where 1=1
          and l.create_dt='2019-08-26'
             ;



- Output[event_id, session_id, ref_page_name, page_name, device_id, app_name, channel_code
] => [lower:varchar, lower_0:varchar, lower_1:varchar, lower_2:varchar, regexp_replace:varchar, lower_3:varchar(2), trim:varchar
] Cost: {rows: 1000 (68.36kB), cpu: ?, memory: 0.00, network: 60000.00
} event_id := lower session_id := lower_0 ref_page_name := lower_1 page_name := lower_2 device_id := regexp_replace app_name := lower_3 channel_code := trim - Project[] => [lower:varchar, lower_0:varchar, lower_1:varchar, lower_2:varchar, regexp_replace:varchar, lower_3:varchar(2), trim:varchar
] Cost: {rows: 1000 (68.36kB), cpu: ?, memory: 0.00, network: 60000.00
} lower := "lower"("event_id") lower_0 := "lower"("session_id") lower_1 := "lower"("ref_page_name") lower_2 := "lower"("page_name") regexp_replace := "regexp_replace"("lower"("device_id"), CAST('rn_' AS joniregexp), '') lower_3 := 'rn' trim := "trim"("lower"("channel_name")) - LocalExchange[ROUND_ROBIN
] () => event_id:varchar, session_id:varchar, ref_page_name:varchar, channel_name:varchar, device_id:varchar, page_name:varchar Cost: {rows: 1000 (58.59kB), cpu: ?, memory: 0.00, network: 60000.00
} - Limit[
  1000
] => [event_id:varchar, session_id:varchar, ref_page_name:varchar, channel_name:varchar, device_id:varchar, page_name:varchar
] Cost: {rows: 1000 (58.59kB), cpu: ?, memory: 0.00, network: 60000.00
} - LocalExchange[SINGLE
] () => event_id:varchar, session_id:varchar, ref_page_name:varchar, channel_name:varchar, device_id:varchar, page_name:varchar Cost: {rows: 1000 (58.59kB), cpu: ?, memory: 0.00, network: 60000.00
} - RemoteExchange[GATHER
] => event_id:varchar, session_id:varchar, ref_page_name:varchar, channel_name:varchar, device_id:varchar, page_name:varchar Cost: {rows: 1000 (58.59kB), cpu: ?, memory: 0.00, network: 60000.00
} - LimitPartial[
  1000
] => [event_id:varchar, session_id:varchar, ref_page_name:varchar, channel_name:varchar, device_id:varchar, page_name:varchar
] Cost: {rows: 1000 (58.59kB), cpu: ?, memory: 0.00, network: 0.00
} - TableScan[dw_hive:ods_dj:ods_dj_log_rn, originalConstraint = ("create_dt" = CAST('2019-08-26' AS varchar))
] => [event_id:varchar, session_id:varchar, ref_page_name:varchar, channel_name:varchar, device_id:varchar, page_name:varchar
] Cost: {rows: ? (?), cpu: ?, memory: 0.00, network: 0.00
} LAYOUT: ods_dj.ods_dj_log_rn channel_name := HiveColumnHandle{name=channel_name, hiveType=string, hiveColumnIndex=14, columnType=REGULAR, comment=渠道代码对应的中文名
} event_id := HiveColumnHandle{name=event_id, hiveType=string, hiveColumnIndex=0, columnType=REGULAR, comment=事件ID
} device_id := HiveColumnHandle{name=device_id, hiveType=string, hiveColumnIndex=15, columnType=REGULAR, comment=APP设备号
} ref_page_name := HiveColumnHandle{name=ref_page_name, hiveType=string, hiveColumnIndex=2, columnType=REGULAR, comment=上一跳页面名
} page_name := HiveColumnHandle{name=page_name, hiveType=string, hiveColumnIndex=22, columnType=REGULAR, comment=页面名称
} session_id := HiveColumnHandle{name=session_id, hiveType=string, hiveColumnIndex=1, columnType=REGULAR, comment=会话编码
} HiveColumnHandle{name=create_dt, hiveType=string, hiveColumnIndex=-1, columnType=PARTITION_KEY
} : : [
  [
    2019-08-26
  ]
]







set hive.default.fileformat=orc;
   drop table if exists bi_temp.temp_lhw_test_1 ;
 create table           bi_temp.temp_lhw_test_1
           as
       select
              l.device_id      -- pk 设备号
             ,l.platform_type  -- pk 平台类型 ios android h5 rn                
             ,l.channel_code  
         from bi_temp.temp_dw_dj_log_device_extend_day_union l
        where device_id='99001085129189-2047dac70692'
             ;


hive查询：有问题；orc没有问题
select
    concat(trim(channel_code),'///'),*
from bi_temp.temp_lhw_test_1


set hive.default.fileformat=TextFile;
   drop table if exists bi_temp.temp_lhw_test_11 ;
 create table           bi_temp.temp_lhw_test_11
           as
       select
              l.device_id      -- pk 设备号
             ,l.platform_type  -- pk 平台类型 ios android h5 rn                
             ,l.channel_code  
         from bi_temp.temp_dw_dj_log_device_extend_day_union l
        where device_id='99001085129189-2047dac70692'
             ;

             前置依赖仅有1条数据，但此处有2条记录

select
    concat(trim(channel_code),'///'),*
from bi_temp.temp_lhw_test_11



select
    count(*)
from bi_temp.temp_dw_dj_log_device_extend_day_union
where device_id='99001085129189-2047dac70692'

记录数为1


前置依赖设置为text格式后重跑
   drop table if exists bi_temp.temp_dw_dj_log_device_extend_day_union_lhw ;
 create table           bi_temp.temp_dw_dj_log_device_extend_day_union_lhw
           as
       select
              log.device_id      -- pk 设备号
             ,log.platform_type  -- pk 平台类型 ios android h5 rn
             ,log.is_new_device         
             ,log.client_ip                       
             ,log.network 
             ,log.client_time                    
             ,log.request_time                      
             ,log.ext_par                  
             ,log.channel_code  
             ,log.lng_pos               
             ,log.lat_pos                                    
             ,log.agent                 
             ,log.agent_type            
             ,log.agent_ver                           
             ,log.test_tag 
             ,log.os                             
             ,a.os_version            
             ,log.app_name    
             ,a.app_version          
             ,log.model                 
             ,log.screen     
             ,log.brand
             ,log.imei
             ,log.idfa 
             ,log.mac_address 
             ,log.ad_id
             ,if(b.device_id is not null,1,0)  as is_home_page_bounce
             ,row_number() over (partition by log.device_id,log.platform_type order by log.client_time) rn
         from bi_temp.temp_dw_dj_log_device_extend_day_init   log 
    left join bi_temp.temp_dw_dj_log_device_extend_day_version a 
           on log.device_id=a.device_id
          and log.create_dt=a.create_dt
          and log.platform_type=a.platform_type
    left join bi_temp.temp_dw_dj_log_device_extend_day_home_lost b
           on log.device_id=b.device_id
          and log.create_dt=b.create_dt
          and log.platform_type=b.platform_type
             ;





set hive.default.fileformat=TextFile;
   drop table if exists bi_temp.temp_lhw_test_11_1 ;
 create table           bi_temp.temp_lhw_test_11_1
           as
       select
              l.device_id      -- pk 设备号
             ,l.platform_type  -- pk 平台类型 ios android h5 rn                
             ,l.channel_code  
         from bi_temp.temp_dw_dj_log_device_extend_day_union l
        where device_id='99001085129189-2047dac70692'
             ;

             OK




set hive.default.fileformat=TextFile;
   drop table if exists bi_temp.temp_lhw_test_text ;
 create table           bi_temp.temp_lhw_test_text
           as
       select
              '99001085129189-2047dac70692' as device_id      -- pk 设备号
             ,'rn' as platform_type  -- pk 平台类型 ios android h5 rn                
             ,'searchjdapp ' as channel_code
             ;

select
    length(channel_code),length(trim(channel_code)),concat(trim(channel_code),'///'),*
from bi_temp.temp_lhw_test_text
;


set hive.default.fileformat=orc;
   drop table if exists bi_temp.temp_lhw_test_orc ;
 create table           bi_temp.temp_lhw_test_orc
           as
       select
              '99001085129189-2047dac70692' as device_id      -- pk 设备号
             ,'rn' as platform_type  -- pk 平台类型 ios android h5 rn                
             ,'searchjdapp ' as channel_code
             ;


select
    length(channel_code),length(trim(channel_code)),concat(trim(channel_code),'///'),*
from bi_temp.temp_lhw_test_orc
;




drop table if exists bi_temp.temp_lhw_test;
create table bi_temp.temp_lhw_test
  as
select
    'text' as fileformat,device_id,concat(channel_code,'//') as channel_code,concat(trim(channel_code),'//')
from bi_temp.temp_dw_dj_log_device_extend_day_union_lhw    --text格式
where device_id='99001085129189-2047dac70692'

union all

select
    'orc' as fileformat,device_id,concat(channel_code,'//') as channel_code,concat(trim(channel_code),'//')
from bi_temp.temp_dw_dj_log_device_extend_day_union     --orc格式
where device_id='99001085129189-2047dac70692'
;


orc格式的数据表，在hive中运行存在问题



结果：
12      11      searchjdapp///  99001085129189-2047dac70692     rn      searchjdapp

自己写入数据时，无论是textFile还是orc格式运行均没有问题