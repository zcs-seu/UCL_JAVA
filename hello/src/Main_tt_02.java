import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main_tt_02 {

    /**
     * @param args
     */
    public static int lowerBound(int[] nums,int l,int r,int v){
        while(l<r){
            int m = l + (r - l) / 2;
            if(nums[m] >= v) r = m;//因为是寻找下界，不考虑右边还有没有元素
            else if(nums[m] < v) l = m+1;
        }
        if (nums[l-1] == v) {
            return l;
        } else {
            return -1;
        }

    }

    public static int upperBound(int[] nums,int l,int r,int v){
        while(l<r){
            int m = l + (r - l) / 2;
            if(nums[m] <= v) l = m+1;
            else if(nums[m] > v) r = m;
        }
        return l;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] list = new int[n];
            for (int i=0;i<n;i++) {
                list[i] = scanner.nextInt();
            }
            Arrays.sort(list);
            int s = scanner.nextInt();
            int[] result = new int[s];
            for (int i=0;i<s;i++) {
                int l = scanner.nextInt();
                int r = scanner.nextInt();
                int k = scanner.nextInt();
                int start = lowerBound(list, l, r,k);
                int end = upperBound(list,l,r, k);
                if (start==-1||end==-1) {
                    result[i] =0;
                } else {
                    result[i] = end - start+1;
                }

            }
            for(int i=0;i<s;i++) {
                System.out.println(result[i]);
            }
        }
    }
}
