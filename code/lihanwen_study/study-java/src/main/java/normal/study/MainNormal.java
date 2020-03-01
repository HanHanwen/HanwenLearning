package normal.study;

import java.util.*;

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


    public static void main2(String[] args){
        String s = "pwdlw";

        System.out.println(s.substring(1,2));
    }


    public static void main(String[] args){
        String fields  = "1,2,3";

        List<String> fieldsArray = Arrays.asList(fields.split(","));

        String str = "struct<";
        for(String field: fieldsArray){
            str = str + field + ":string";

            if(fieldsArray.size() - 1 == fieldsArray.indexOf(field)){
                str = str + ">";
            }else {
                str = str + ",";
            }

        }

        System.out.println(fieldsArray);
        System.out.println(str);
    }
}
