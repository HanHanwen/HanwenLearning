package cn.hhanwen.java.common;

public class JavaTransform {
    public static void main(String[] args){

        String t = "a||b||c||d";

        /**
         * \\会转义成反斜杠，反斜杠本身就是转义符，所有就成了“\|”，在进行转义就是|，所以\\|实际上是“|”
         */

        String[] temp = t.split("\\|\\|");

        System.out.println(temp.length);

    }
}
