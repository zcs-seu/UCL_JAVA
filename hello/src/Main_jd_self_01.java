import java.util.Scanner;

/**
 * Created by seu on 2017/9/9.
 */
public class Main_jd_self_01 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();//数列的个数
        for(int i=0;i<N;i++){
            int n=sc.nextInt();//元素个数
            int nums4=0;//能被4整除的元素个数
            int numsx=0;//既不能被2也不能被4整除的元素个数
            for(int j=0;j<n;j++){
                int cur=sc.nextInt();
                if(cur%4==0){
                    nums4++;
                }else if(cur%2!=0){
                    numsx++;
                }
            }
            //如果能被4整除的个数大于等于既不能被4也不能被2整除的元素个数则可以
            if(nums4>=numsx){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
    }
}
