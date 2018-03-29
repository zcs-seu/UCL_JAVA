import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by seu on 2017/9/20.
 */
public class Main_temp {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String str=sc.next();
            System.out.println(helper(str));
        }

    }

    private static int helper(String str) {
        int t=0;
        if(str.length()==1){
            char h=str.charAt(0);
            if(h>='4' && h<'7'){
                return 1;
            }
            if(h>='7'){
                return 2;
            }
        }
        int res=0;
        String next=str.substring(1,str.length());
        int h=str.charAt(0);
        if(h<'4'){
            return count(next.length());
        }else if(h<'7'){
            return (count(next.length())+helper(next))%1000000007;
        }else{
            return (2*count(next.length())+helper(next))%1000000007;
        }
    }
    public static int count(int n){
        int res=0;
        for(int i=1;i<=n;i++){
            res=(int)(res+Math.pow(2,i))%1000000007;
        }
        return res;
    }
}
