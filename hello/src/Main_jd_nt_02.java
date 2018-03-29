import java.util.Scanner;

/**
 * 求进制均值，以不可化简的分数表示
 * 输入描述:
        输入中有多组测试数据，每组测试数据为一个整数A(1 ≤ A ≤ 5000).

        输出描述:
            对每组测试数据，在单独的行中以X/Y的形式输出结果。

        输入例子1:
            5
            3

        输出例子1:
            7/3
            2/1

    思路：分子等于2-A-1进制数位之和，分母等于n-2
 * Created by seu on 2017/9/6.
 */
public class Main_jd_nt_02 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            System.out.println(helper(n));
        }
    }
    public static String helper(int n){
        int res=0;
        for(int i=2;i<n;i++){
            res+=getSumDigits(n,i);
        }
        int comm=gcd(res,n-2);
        return res/comm+"/"+(n-2)/comm;
    }
    public static int gcd(int a,int b){
        while(a%b!=0){
            int c=a%b;
            a=b;
            b=c;
        }
        return b;
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
