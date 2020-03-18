package normal.study.suanfa;

import java.util.Stack;

/**
 * @author lihanwen
 */
public class StackJudge {
    public static void main(String[] args){

        //String str = "{[})]}";
        String str = "{[]()}";

        //判断字符串
        Boolean bool = judgeString(str);

        //输出结果
        System.out.println(bool);

    }

    public static Boolean judgeString(String str){

        Stack<String> stack = new Stack<>();
        /**
         * stack.pop        获取栈顶数据，并移除栈顶数据
         * stack.peek       获取栈顶数据，但不移除栈顶数据
         * stack.push       将数据压入栈顶
         * stack.isEmpty    判断栈是否为空
         */

        String[] arr = str.split("");

        for(int i=0; i< arr.length; i++){
            //获取判断字符
            String buttom = arr[i];
            if("(".equals(buttom) || "[".equals(buttom) || "{".equals(buttom)){
                //若是上述三个字符串，则将数据压入栈顶
                stack.push(buttom);
            }else {
                //查看栈顶对象
                String peek = stack.peek();
                //判断栈顶对象内容
                if("(".equals(peek)){
                    if(")".equals(buttom)){
                        stack.pop();
                    }else {
                        return false;
                    }
                }else if("[".equals(peek)){
                    if("]".equals(buttom)){
                        stack.pop();
                    }else {
                        return false;
                    }
                }else if("{".equals(peek)){
                    if("}".equals(buttom)){
                        stack.pop();
                    }else {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }

}
