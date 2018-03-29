import java.util.Scanner;

/**
 * 求幸运数
 *
 * 定义：二进制表示数位之和等于十进制表示的数位之和
 *      输入描述:
            每组数据输入一个数n(n<=100000)

        输出描述:
            每组数据输出一行，小于等于n的幸运数个数。

        输入例子1:
            21

        输出例子1:
            3
 * Created by seu on 2017/9/6.
 */
public class Main_jd_nt_03{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            System.out.println(helper(n));
        }
    }
    public static int helper(int n){
        int res=0;
        for(int i=1;i<=n;i++){
            if(getSumDigits(i,10)==getSumDigits(i,2)){
                res++;
            }
        }
        return res;
    }
    public static int getSumDigits(int num,int base){
        int res=0;
        while(num!=0){
            res+=(num%base);
            num/=base;
        }
        return res;
    }
}
