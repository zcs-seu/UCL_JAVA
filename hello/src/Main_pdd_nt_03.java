/**
 * Created by zhang_cs on 2017/9/3.
 */

import java.util.Scanner;

public class Main_pdd_nt_03 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int[] h=new int[n];
            for(int i=0;i<n;i++){
                h[i]=sc.nextInt();
            }
            int m=sc.nextInt();
            int[] w=new int[m];
            for(int i=0;i<m;i++){
                w[i]=sc.nextInt();
            }
            System.out.println(getMax(h,w));
        }
    }

    private static int getMax(int[] h,int[] w) {
        //利用快速排序将h和w均做从小到大排序
        fastSort(h,0,h.length-1);
        fastSort(w,0,w.length-1);
        int j=0;
        int res=0;
        // i为糖果重量下标，如果当前糖果能够满足当前小朋友需要，糖果下标后移；
        // 如果不能满足当前小朋友需要，那么更不能满足下一个小朋友的需要，也要后移
        // 而小朋友下标及计数只在能够满足时后移
        for(int i=0;i<w.length;i++){
            if(j<h.length && w[i]>=h[j]){
                j++;
                res++;
            }
        }
        return res;
    }

    private static void fastSort(int[] nums,int begin,int end) {
        if(begin>=end){
            return;
        }
        int index=partition(nums,begin,end);
        fastSort(nums,begin,index-1);
        fastSort(nums,index+1,end);
    }

    private static int partition(int[] nums, int begin, int end) {
        int key = nums[begin];
        while(begin<end){
            while(nums[end]>=key && begin<end){
                end--;
            }
            nums[begin]=nums[end];
            while(nums[begin]<=key && begin<end){
                begin++;
            }
            nums[end]=nums[begin];
        }
        nums[begin]=key;
        return begin;
    }
}
