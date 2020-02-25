package normal.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainNormal {

    public static void main1(String[] args){
        List<String> list = new ArrayList<>();

        list.add("a");

        Map<String, List<String>> map = new HashMap<>();

        map.put("1",list);

        if(map.containsKey("1")){
            list = map.get("1");

            list.add("b");

            map.put("1",list);
        }

        System.out.println(list.size());

        System.out.println(list.get(0));

        System.out.println(map.get("1"));
    }


    public static void main(String[] args){
        String s = "pwdlw";

        System.out.println(s.substring(1,2));
    }
}
