package normal.study.fanxing;

import org.mortbay.log.Log;

import java.util.ArrayList;
import java.util.List;

public class FanXing {
    public static void main1(String[] args){
        List<String> arrayList = new ArrayList<String>();

        arrayList.add("abc");
        //arrayList.add(100);

        for (int i=0; i < arrayList.size(); i++){
            String item = (String) arrayList.get(i);

            //java.lang.Integer cannot be cast to java.lang.String
            Log.debug("泛型测试","item=" + item);
        }
    }


    public static void main(String[] args){
        List<String> stringList = new ArrayList<String>();
        List<Integer> integerList = new ArrayList<Integer>();

        Class classStringList = stringList.getClass();
        Class classIntegerList = integerList.getClass();

        if(classStringList.equals(classIntegerList)){
            System.out.println("classStringList 类型:" + classStringList);
            System.out.println("classIntegerList 类型:" + classIntegerList);

            System.out.println("泛型测试：类型相同");

        }

    }
}
