package normal.study.fanxing;

import java.util.Random;

/**
 * 传入泛型实参时：
 * 定义一个生产器实现这个接口，虽然我们只创建了一个泛型接口Generator<T>
 * 但是我们可以为T传入无数个实参，形成无数种类型的Generator接口
 * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
 * @author lihanwen
 */
public class FruitGenrtator implements Generator<String> {

    private String[] fruits = new String[]{"Apple","Banana","Pear"};

    @Override
    public String next() {
        Random random = new Random();
        return fruits[random.nextInt(3)];
    }
}
