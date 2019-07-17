package com.imooc.flink.java.course02;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


public class BatchWCJavaAPP {
    public static void main(String[] args) throws Exception{

        String input = "file:///Users/lihanwen/Study/test_file/flink/hello.txt";

        //set up the batch execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //read
        DataSource<String> text = env.readTextFile(input);

        //transform
        text.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
            //输入一个string，转为<string,integer>
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {

                String[] tokens = s.toLowerCase().split("\t");
                //通过"\t"作为分隔符，获取文本信息

                for (String token : tokens){
                    if (token.length()>0){
                        //字符串长度大于0时
                        collector.collect(new Tuple2<String, Integer>(token,1));
                        //每一个字符串，赋值为1
                    }
                }
            }
        }).groupBy(0).sum(1).print();//key-value
    }
}
