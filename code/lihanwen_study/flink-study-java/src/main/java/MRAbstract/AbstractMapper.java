package MRAbstract;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public abstract class AbstractMapper<T extends AbstractEntity> extends Mapper<Object, Text, Object, T> {
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        super.map(key, value, context);
    }


    public abstract void getLogEntity(T t, JSONObject jsonObject);

}
