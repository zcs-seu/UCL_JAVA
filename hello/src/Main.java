import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int m=sc.nextInt();
            double cur1=n>=2?1.0*n*(n-1):0.0;
            double cur2=m>=2?1.0*m*(m-1):0.0;
            double cur3=n>=1?(m>=1?2.0*m*n:0.0):0.0;
            double sum=cur1+cur2+cur3;
            double res=helper(n,m);
            double cur=cur2/sum;
            for(int i=0;i<10;i++){
                res+=cur;
                cur*=cur;
            }
            System.out.println(String.format("%.1f", res));
        }
    }
    private static double helper(int n,int m){
        if(n<=0){
            return 0.0;
        }
        double cur1=n>=2?1.0*n*(n-1):0.0;
        double cur2=m>=2?1.0*m*(m-1):0.0;
        double cur3=n>=1?(m>=1?2.0*m*n:0.0):0.0;
        double sum=cur1+cur2+cur3;
        double res1=cur1/sum*(1.0+helper(n-2,m+2));
        double res3=cur3/sum*(1.0+helper(n-1,m+1));
        double res=res1+res3;
        return res;
    }
}