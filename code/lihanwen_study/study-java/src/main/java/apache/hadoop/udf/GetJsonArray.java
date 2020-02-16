package apache.hadoop.udf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.Map;


/**
 * date:        2019-08-05
 * comment:     获取json字符串中的key
 */

public class GetJsonArray extends UDF {

    /**
     * {"store_id":"11677293","userAction":"{\"cateid1\":\"4407786\",\"cateid2\":\"4596270\",\"catename1\":\"肉类家禽\",\"catename2\":\"品质猪肉\",\"store_id\":\"11677293\"}","select":"0","industry":"1"}
     *
     *
     * 此处可能会涉及java转译
     *
     */

    public String evaluate(String[] args){
        String s = args[0];

        /**
         * 去掉非空和非标准json格式的数据
         */
        if (isJSONValid(s) && s.length()>0){
            return JSON.toJSONString(getKeys(JSON.parseObject(args[0])));
        }else {
            return null;
        }

    }


    public Map<String,Object> getKeys(JSONObject json) {

        Map<String,Object> keys = Maps.newHashMap();
        json.keySet().forEach(key ->{
            try {
                JSONObject json2 = JSON.parseObject(json.getString(key));


                Map<String,Object> lists2 = getKeys(json2);

                keys.put(key,lists2);

            } catch (Exception e) {
                keys.put(key,"");
            }
        });
        return keys;
    }

    /**
     * 判断是否为标准json格式
     * @param test
     * @return
     */
    public static boolean isJSONValid(String test) {
        try {
            JSONObject.parseObject(test);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

}
