import org.junit.Test;

/**  测试成员内部类被private修饰  */
public class testPrivateMemberInner {
    @Test
    public void testMemberInnerMethod () {
        //TODO 创建内部类对象，并执行show()
//     Outer2.Inner2 oi = new Outer2().new Inner2();//报错，Inner2已经被private了
        //3，测试被private的内部类的资源能否执行！
        new Outer2().test();
    }
}

class Outer2{
    private String str = "内部类调用成员变量";

    //2，如果想要访问private的内部类，可以访问外部类提供的对应方法
    public void test() {
        //访问内部类方法
        new Inner2().show();
    }

    //位置在类里方法外--成员内部类
    //1，内部类可以被private修饰，但是外界无法直接创建对象了！
    private class Inner2{
        public void show() {
            System.out.println("Inner2.show()");
            System.out.println(str);
        }
    }
}