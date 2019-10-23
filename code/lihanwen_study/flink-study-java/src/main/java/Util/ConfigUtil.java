package Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil {
    private Map<String, Properties> map = new HashMap();

    public String get(String section, String key){
        if (map.containsKey(section)){
            return get(section).getProperty(key);
        }

        return null;

    }

    private Properties get(String section) {

        return map.containsKey(section) ? map.get(section) : null;

    }
}
