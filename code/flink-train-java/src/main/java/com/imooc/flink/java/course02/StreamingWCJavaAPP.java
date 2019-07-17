package com.imooc.flink.java.course02;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;


/**
 * 使用Java API来开发flink的实时处理应用程序
 *
 * wordcount统计的数据来源为socket
 *
 * 代码参考来源为：https://ci.apache.org/projects/flink/flink-docs-release-1.7/dev/api_concepts.html
 * Basic API Concepts
 */
public class StreamingWCJavaAPP {

    public static void main(String[] args) throws Exception {

        //step1:获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //step2:读取数据
        //DataStreamSource<String> text = env.socketTextStream("localhost",9999);
        /**
         * 通过传参来实现端口的使用
         */

        int port = 0;

        try {
            ParameterTool tool = ParameterTool.fromArgs(args);
            port = tool.getInt("port");
        } catch (Exception e){
            System.out.println("获取端口信息失败，使用默认端口值：9999");
            port = 9999;
        }

        DataStreamSource<String> text = env.socketTextStream("localhost",port);

        //监控接口：nc -l 9999


//        //step3-1:transform
//        /**
//         * Anonymous classes
//         */
//        text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
//            @Override
//            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
//                String[] tokens = value.toLowerCase().split(",");
//
//                for(String token: tokens){
//                    if(token.length()>0){
//                        out.collect(new Tuple2<String, Integer>(token, 1));
//                    }
//
//                }
//            }
//        })
//                .keyBy(0)
//                .timeWindow(Time.seconds(5))
//                .sum(1)
//                .print();


//        //step3-2:transform
//        text.flatMap(new FlatMapFunction<String, WC>() {
//
//            @Override
//            public void flatMap(String value, Collector<WC> out) throws Exception {
//                String[] tokens = value.toLowerCase().split(",");
//
//                for (String token : tokens){
//                    if (token.length()>0){
//                        out.collect(new WC(token.trim(),1));
//                    }
//                }
//            }
//        })


        //step3-3:transform
        /**
         * Specifying Transformation Functions
         */
        text.flatMap(new MyMapFunction())
                /**
                 * Define keys using Field Expressions
                 */
                //.keyBy("word")
                /**
                 * Define keys using Key Selector Functions
                 */
                .keyBy(new KeySelector<WC, String>() {
                    @Override
                    public String getKey(WC value) throws Exception {
                        return value.word;
                    }
                })
                .timeWindow(Time.seconds(3))
                .sum("count")
                .print();


        //step4:启动
        env.execute("flink-java-stream");
    }

    public static class WC{
        public String word;
        public int count;

        public WC() {
        }

        public WC(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "WC{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

    /**
     * Implementing an interface
     * The most basic way is to implement one of the provided interfaces
     */
    static class MyMapFunction implements FlatMapFunction<String, WC> {

        @Override
        public void flatMap(String value, Collector<WC> out) throws Exception {

            String[] tokens = value.toLowerCase().split(",");

            for (String token : tokens){
                if (token.length()>0){
                    out.collect(new WC(token.trim(),1));
                }
            }

        }
    }

}
