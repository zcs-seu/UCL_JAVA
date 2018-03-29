import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * 求集合并集——给你两个集合，要求{A} + {B}。 注：同一个集合中不会有两个相同的元素。
 *      输入描述:
            每组输入数据分为三行,第一行有两个数字n,m(0 ≤ n,m ≤ 10000)，分别表示集合A和集合B的元素个数。后两行分别表示集合A和集合B。每个元素为不超过int范围的整数,每个元素之间有个空格隔开。

        输出描述:
            针对每组数据输出一行数据，表示合并后的集合，要求从小到大输出，每个元素之间有一个空格隔开,行末无空格。

        输入例子1:
            3 3
            1 3 5
            2 4 6

        输出例子1:
            1 2 3 4 5 6
 * Created by seu on 2017/9/6.
 */
public class Main_jd_nt_04 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            n+=sc.nextInt();
            TreeSet<Integer> set = new TreeSet<>();
            for(int i=0;i<n;i++){
                set.add(sc.nextInt());
            }

            Iterator<Integer> iter=set.iterator();
            while(iter.hasNext()){
                System.out.print(iter.next());
                if(iter.hasNext()){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
