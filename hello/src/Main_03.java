import java.util.Scanner;
/**
 * Created by seu on 2017/8/26.
 */


public class Main_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line=sc.nextLine();
        String[] strs=line.split(" ");
        int n=strs.length;
        int[] nums=new int[n];
        for(int i=0;i<n;i++){
            nums[i]=Integer.parseInt(strs[i]);
        }
        System.out.println(helper(nums,n));
    }
    private static int helper(int[] nums,int n){
        int curMax=0;
        int maxSoFar=Integer.MIN_VALUE;
        for(int num:nums){
            curMax=Math.max(curMax+num,num);
            maxSoFar=Math.max(maxSoFar,curMax);
        }
        return maxSoFar;
    }
}
