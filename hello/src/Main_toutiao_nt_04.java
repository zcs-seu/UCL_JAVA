import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *  或与加：
 *  有给定 x, k ，求满足 x + y = x | y 的第 k 小的正整数 y 。 | 是二进制的或(or)运算，例如 3 | 5 = 7。
    比如当 x=5，k=1时返回 2，因为5+1=6 不等于 5|1=5，而 5+2=7 等于 5 | 2 = 7。

        输入描述:
            每组测试用例仅包含一组数据，每组数据为两个正整数 x , k。 满足 0 < x , k ≤ 2,000,000,000。

        输出描述:
            输出一个数y。

        输入例子1:
            5 1

        输出例子1:
            2

    解题思路：如果要使得x+y=x|y，则必须要保证x中为1的位置上y不为1；然后将k的二进制表示映射进x中即可

 * Created by seu on 2017/9/6.
 */
public class Main_toutiao_nt_04 {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int x=sc.nextInt();
            int k=sc.nextInt();
            StringBuilder res=new StringBuilder();
            String k_bin=Integer.toString(k, 2);//转成二进制字符串
            int index=k_bin.length()-1;
            while(k!=0){
                if((x&1)==0){
                    res.append(k_bin.charAt(index--));
                    k/=2;
                }else{
                    res.append("0");
                }
                x>>>=1;
            }
            BigInteger num=new BigInteger(res.reverse().toString(), 2);
            System.out.println(num);
        }
    }
}