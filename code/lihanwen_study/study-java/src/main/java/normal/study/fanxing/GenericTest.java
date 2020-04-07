package normal.study.fanxing;

public class GenericTest {

    /**
     * 此为泛型类
     * @param <T>
     */
    public class Generic<T>{
        private T key;

        public Generic(T key) {
            this.key = key;
        }

        /**
         * 该方法虽然使用了泛型，但是并不是一个泛型方法
         * 只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型
         * 所以在这个方法中才可以继续使用 T 这个泛型
         * @return key
         */
        public T getKey() {
            return key;
        }
    }

    /**
     * 这才是一个真正的泛型方法
     * 首先在public与返回值之间的 <T> 必不可少，这表明这是一个泛型方法，而且声明了一个泛型 T
     * 这个 T 可以出现在这个泛型方法的任意位置
     * 泛型的数量也可以为多个：
     * public <T,K> K showKeyName(Generic<T> Container){…}
     * @param generic
     * @param <T>
     * @return
     */
    public <T> T showKeyName(Generic<T> generic){
        System.out.println(generic.getKey());
        T test = generic.getKey();

        return test;
    }

    /**
     * 这并不是一个泛型方法，仅是一个普通方法，只是使用了Generic<Number>这个泛型类做形参而已
     * @param obj
     */
    public void showKeyName1(Generic<Number> obj){
        System.out.println(obj.getKey());
    }

    /**
     * 这也不是一个泛型方法，只是一个普通方法，只不过是使用了泛系通配符
     * @param obj
     */
    public void showKeyName2(Generic<?> obj){
        System.out.println(obj.getKey());
    }

    /**
     * 这个方法是有问题的，编译器会提示"Cannot resolve symbol 'E'"
     * 虽然我们声明了<T>，也表明了这是一个可以处理泛型的类型的泛型方法
     * 但是只声明了泛型类型 T ，并未声明泛型类型 E ，因此编译器并不知道该如何处理 E 这个类型
     */
//    public <T> T showkeyName3(Generic<E> eGeneric){
//        …
//    }

    public static void main(String[] args){

    }


}
