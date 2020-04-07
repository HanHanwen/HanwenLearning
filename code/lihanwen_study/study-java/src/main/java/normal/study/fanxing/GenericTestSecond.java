package normal.study.fanxing;

public class GenericTestSecond {
    static class Fruit{
        @Override
        public String toString() {
            return "fruit";
        }
    }

    static class Apple extends Fruit{
        @Override
        public String toString() {
            return "Apple";
        }
    }

    static class Person{
        @Override
        public String toString() {
            return "Person";
        }
    }

    /**
     * 此为泛型类
     * @param <T>
     */
    static class GenerateTestFruit<T>{
        public void show1(T t){
            System.out.println(t.toString());
        }

        /**
         * 在泛型类中声明一个泛型方法，使用泛型 E ，这种泛型 E 可以为任意类型，可以与类型 T 相同，也可以不同
         * 由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型
         * @param e
         * @param <E>
         */
        public <E> void show2(E e){
            System.out.println(e.toString());
        }

        /**
         * 在泛型类中声明一个泛型方法，使用泛型 T ，注意此时的 T 是一种全新的类型，可以与泛型类中声明的 T 不是同一种类型
         * @param t
         * @param <T>
         */
        public <T> void show3(T t){
            System.out.println(t.toString());
        }
    }


    public static void main1(String[] args){
        Apple apple = new Apple();
        Person person = new Person();
        System.out.println(apple.toString());
        System.out.println(person.toString());

        GenerateTestFruit<Fruit> fruitGenerateTestFruit = new GenerateTestFruit<>();
        //apple是Fruit的子类，所以这里可以访问
        fruitGenerateTestFruit.show1(apple);
        //此处编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
        //fruitGenerateTestFruit.show1(person);

        //使用这两个办法都可以
        fruitGenerateTestFruit.show2(apple);
        fruitGenerateTestFruit.show2(person);

        //使用这两个办法也都可以
        fruitGenerateTestFruit.show3(apple);
        fruitGenerateTestFruit.show3(person);

    }

    public static void main(String[] args){
        printMsg("111",222,"aaa",55.67);
    }

    public static  <T> void printMsg(T... args){
        for(T t : args){
            System.out.println(t);
        }
    }

}
