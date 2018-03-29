import java.util.*;
public class Main_01{
    public static void main(String[] args){
        int[] nums={0,1,3,7,15,31,63,127,255,511,1023,2047,4095,8191,16383,32767,65535,131071,262143,524287,1048575,2097151,4194303,8388607,
        16777215,33554431,67108863,134217727,268435455,536870911,1073741823,2147483647};
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            System.out.println("Case "+(i+1)+": "+helper(nums,a,b));
        }
    }

    public static int helper(int[] nums,int a,int b){
        int min=0;
        while(min<31&&a>nums[min]){
            min++;
        }
        int max=30;
        while(max>=0&&b<nums[max]){
            max--;
        }
        if(max>=min){
            return nums[max];
        }
        return nums[max]+1+helper(nums,a-(nums[max]+1),b-(nums[max]+1));
    }
}