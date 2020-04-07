package normal.study.fanxing;

/**
 * @author lihanwen
 * @param <T> 泛型类
 * 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * 在实例化泛型类时，必须指定T的具体类型
 */
public class Generic<T> {
    /**
     * key这个成员变量的类型为T，T的类型由外部指定
     */
    private T key;

    /**
     * 泛型构造方法形参key的类型也为T，T的类型由外部指定
     * @param key 参数
     */
    public Generic(T key){
        this.key = key;
    }

    /**
     * 泛型方法getKey的返回值类型为T，T的类型由外部指定
     * @return key
     */
    public T getKey(){
        return key;
    }


    public <T> T GenericMethod(Class<T> tclass) throws IllegalAccessException, InstantiationException {
        T instance = tclass.newInstance();
        return instance;
    }

}
