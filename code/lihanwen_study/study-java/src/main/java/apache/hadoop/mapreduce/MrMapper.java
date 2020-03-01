package apache.hadoop.mapreduce;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author lihanwen
 * @date   2020-02-26
 */
public class MrMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
    final static Logger logger = LoggerFactory.getLogger(MrMapper.class);

    private static String fields;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);

        //获取外部传入需解析的字段
        fields = context.getConfiguration().get("InField");
    }


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        JSONObject lineJson = JSONObject.parseObject(line);

        //将外部传入的字段按逗号分割
        String[] filedsArray = fields.split(",");
        StringBuffer stringBuffer = new StringBuffer();

        for(String field: filedsArray){
            //String filedValue = JsonUtils
        }

    }
}
