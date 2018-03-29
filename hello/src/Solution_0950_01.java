import java.util.*;
/**
 * Created by seu on 2017/10/30.
 */
public class Solution_0950_01 {
    public static void main(String[] args){
        int[] nums={1,100,2,3,9,8,7,5,6,100,200,200,150,150};
        mergeSortKWay(nums,5);
    }
    public static void mergeSortKWay(int[] nums,int k){
        int simLen=nums.length/k;
        int dis=nums.length-simLen*k;
        int[][] arrays=new int[k][];
        for(int i=0;i<k;i++){
            arrays[i]=new int[simLen+(dis>0?1:0)];
            dis--;
        }
        int idx=0;
        for(int i=0;i<k;i++){
            for(int j=0;j<arrays[i].length;j++){
                arrays[i][j]=nums[idx++];
            }
            Arrays.sort(arrays[i]);
        }
        List<Integer>resList=mergekSortedArrays(arrays);
        for(int i=0;i<nums.length;i++){
            nums[i]=resList.get(i);
        }
        for(int num:nums){
            System.out.print(num+" ");
        }
    }
    private static  List<Integer> mergekSortedArrays(int[][] arrays) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (arrays == null || arrays.length == 0 || arrays[0].length == 0) {
            return list;
        }

        int[] temp = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            temp = mergeTwoArrays(temp, arrays[i]);
        }
        for (int num : temp) {
            list.add(num);
        }
        return list;
    }

    private static int[] mergeTwoArrays(int[] A, int[] B) {
        if (A.length == 0) {
            return B;
        }else if(B.length==0){
            return A;
        }
        int[] temp = new int[A.length + B.length];
        int index = 0, i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                temp[index++] = A[i++];
            } else {
                temp[index++] = B[j++];
            }
        }
        while (i < A.length) {
            temp[index++] = A[i++];
        }
        while (j < B.length) {
            temp[index++] = B[j++];
        }
        return temp;
    }
}
