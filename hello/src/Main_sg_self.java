/**
 * Created by seu on 2017/9/8.
 */
import java.util.*;
public class Main_sg_self{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            double[] nums=new double[n];
            for(int i=0;i<n;i++){
                nums[i]=sc.nextDouble();
            }
            //暴力求解
            double maxDist=-1.0;
            for(int i=0;i<n;i++){
                //找到对端点
                double dest=(nums[i]+180)%360;
                //思想：只与对面点两边的第一个点的进行比较
                int left=Arrays.binarySearch(nums,dest);
                int right=-(left+1);
                if(left>=0&&left<n) {
                    double  curDist=Math.abs(nums[left]-nums[i]);
                    if(curDist>180.0)
                        curDist=360.0-curDist;
                    maxDist=Math.max(maxDist,curDist);
                }
                if(right>=0&&right<n) {
                    double  curDist=Math.abs(nums[right]-nums[i]);
                    if(curDist>180.0)
                        curDist=360.0-curDist;
                    maxDist=Math.max(maxDist,curDist);
                }

            }
            System.out.printf("%.8f",maxDist);
        }
    }
    public static int binarySearch(double dest,double[] nums) {
        int left=0;
        int right=nums.length;
        int mid=(left+right)/2;
        while(left<right) {
            if(nums[mid]==dest)
                return mid;
            else if(nums[mid]>dest) {
                right=mid-1;
            }
            else {
                left=mid+1;
            }
            mid=(left+right)/2;
        }
        return left;
    }

}