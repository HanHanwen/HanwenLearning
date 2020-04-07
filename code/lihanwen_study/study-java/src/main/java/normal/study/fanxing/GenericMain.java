package normal.study.fanxing;

import com.esotericsoftware.minlog.Log;
import normal.study.fanxing.Generic;

/**
 * @author lihanwen
 */
public class GenericMain {
    public static void main1(String[] args){
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> integerGeneric = new Generic<>(1234);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> stringGeneric = new Generic<>("abc");

        System.out.println(integerGeneric.getKey());
        System.out.println(stringGeneric.getKey());

    }

    public static void main2(String[] args){
        Generic generic0 = new Generic("abc");
        Generic generic1 = new Generic(123);
        Generic generic2 = new Generic(55.64);
        Generic generic3 = new Generic(false);

        System.out.println(generic0.getKey());
        System.out.println(generic1.getKey());
        System.out.println(generic2.getKey());
        System.out.println(generic3.getKey());

    }

    public static void main3(String[] args){
        Generic<Integer> integerGeneric = new Generic<>(123);
        Generic<Number> numberGeneric = new Generic<>(456);

        showKeyValue(numberGeneric);
        showKeyValue(integerGeneric);

    }



    public static void showKeyValue(Generic<?> obj){
        System.out.println("泛型测试：" +obj.getKey());
    }
}
