import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by seu on 2017/9/8.
 */
public class Main_jd_02 {
    static interface IPerson{

    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            if(n==1){
                System.out.println(1);
            }
            //a^b=c^d，先考虑a=1,b=1的情况
            int res=0;
            res+=n*n;
            for(int i=2;i<=n;i++){
                for(int j=1;j<=n;j++){
                    int cur=helper(i,j,n);
                    res+=cur*cur;
                }
            }
            System.out.println(res);
        }
    }
    public static int helper(int a,int b,int n){
        int num=0;
        int i=1;
        int x=(int)Math.pow(a,b);
        double cur=x;
        while(cur>=2.0){
            cur=sqrtn(x,i);
            if(cur==(int)cur){
                if(cur<=n&&i<=n)
                    num++;
            }
            i++;
        }
        return num;
    }
    public static double sqrtn(int x,int n){
        return Math.pow(x, 1.0/n);
    }
}
