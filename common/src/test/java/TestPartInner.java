import lombok.experimental.Accessors;
import org.junit.Test;

/**
 * 测试匿名内部类
 */
public class TestPartInner {
    @Test
    public void testPartInnerMethod() {
        new Hello() {// 匿名对象，本身接口不能new，这里new Hello()匿名对象，就相当于Hello接口的实现类
            // 匿名内部类
            @Override
            public void save() {
                System.out.println("save()..");
            }

            @Override
            public void update() {
                System.out.println("update()..");
            }
        }.update();// 触发指定的方法
        new Hello2() {//抽象类的匿名内部类
            @Override
            public void show() {
                System.out.println("Hello2");
            }
        }.show();
        new Animal() {//普通类的匿名内部类
            @Override
            public void eat() {
                System.out.println("Animal");
            }
        }.eat();
    }
}

//TODO 创建匿名对象+匿名内部类测试
class Animal{
    public void eat() {}
}

abstract class Hello2 {
    abstract public void show();
    public void delete() {  }
}

// 定义接口
interface Hello {
    void save();
    void update();
}