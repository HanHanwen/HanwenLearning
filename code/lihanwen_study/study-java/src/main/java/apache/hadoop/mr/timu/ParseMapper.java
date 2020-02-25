package apache.hadoop.mr.timu;

import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseMapper extends Mapper<String, String, String ,List<String>> {

    /**
     * 输入信息：
     * id       point       feature
     * 1        1           f1
     * 2        1           f2
     * 3        2           f3
     * 4        1           f4
     * 5        2           f5
     *
     *
     *
     * 经过Map端处理后：
     * point        list<feature>
     * 1            f1,f2,f4
     * 2            f3,f5
     */

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    @Override
    protected void map(String key, String value, Context context) throws IOException, InterruptedException {
        Map<String, List<String>> map = new HashMap<>();



    }


    public Map<String, List<String>> MakeMapper(Map<String, List<String>> map, String key, String value){

        if(map.containsKey(key)){
            //判断是否包含该key值，若是包含，则将该key值对应的value新增元素
            List<String> list = new ArrayList<>();

            //该key所对应的value
            list = map.get(key);

            //将该key对应的value追加至list
            list.add(value);

            map.put(key, list);
        }else{
            //若是不包含该key，则将该key-value追加至map
            List<String> list = new ArrayList<>();

            list.add(value);

            map.put(key, list);
        }

        return map;
    }
}
