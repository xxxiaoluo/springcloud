/**  测试成员内部类被static修饰  */
public class testStaticMemberInner {
    public static void main(String[] args) {
        // 创建内部类对象测试show()
//     Outer3.Inner3 oi = new Outer3().new Inner3();//报错，原因是Inner3是静态的内部类
        Outer3.Inner3 oi = new Outer3.Inner3();//Outer3.Inner3通过类名.调用类中的静态资源
        oi.show();
        Outer3.Inner3.show2();//调用静态内部类里的静态方法
    }
}

class Outer3{
    //1，内部类被static修饰--随着类的加载而加载，会造成内存资源浪费，并不常用！
    static class Inner3{
        public void show() {
            System.out.println("Inner3.show()");
        }

        static public void show2() {
            System.out.println("Inner3.show2()");
        }
    }
}
