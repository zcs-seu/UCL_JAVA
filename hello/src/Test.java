/**
 * Created by seu on 2017/9/6.
 */
public class Test {
    static class Base{
        public static int id=1;
    }
    static class Child extends Base{
        public int id;
    }
    public static void main(String[] args){
        System.out.println(new Child().id);
        if(new Child() instanceof Base){
            System.out.println("ok");
        }
    }
}
