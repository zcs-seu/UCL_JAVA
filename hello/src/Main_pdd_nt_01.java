/**
 * Created by zhang_cs on 2017/9/3.
 */
import java.util.*;
public class Main_pdd_nt_01{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int[] nums = new int[n];
            for(int i = 0;i < n;i++){
                nums[i] = sc.nextInt();
            }
            System.out.println(genMax(nums));
        }
    }

    private static long genMax(int[] nums) {
        //主要思路：进行一次遍历查找最大、次大、第三大的数和最小、次小的数
        if(nums==null){
            return 0;
        }else if(nums.length==1){
            return nums[0];
        }else if(nums.length==2){
            return Math.max(nums[0]*nums[1],Math.max(nums[0],nums[1]));
        }
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for(int num:nums){
            if(num > max1){
                max3=max2;
                max2=max1;
                max1=num;
            }else if(num > max2){
                max3 = max2;
                max2 = num;
            }else if(num > max3){
                max3 = num;
            }
            if(num < min1){
                min2 = min1;
                min1 = num;
            }else if(num < min2){
                min2 = num;
            }
        }
        return Math.max((long)max1*max2*max3,(long)max1*min1*min2);
    }
}
