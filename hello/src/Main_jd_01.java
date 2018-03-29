import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by seu on 2017/9/8.
 */
public class Main_jd_01 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String str=sc.next();
            if(str.length()<1){
                System.out.println(0);
                continue;
            }
            char[] strArr=str.toCharArray();
            ArrayList<Integer> curList=new ArrayList<>();
            int leftNum=0;
            int rightNum=0;
            for(char ch:strArr){
                if(ch=='('){
                    leftNum++;
                }else{
                    curList.add(leftNum-rightNum);
                    rightNum++;
                }
            }
            int res=1;
            for(int num:curList){
                res*=num;
            }
            System.out.println(res);
        }
    }
}
