import java.util.*;

/**
 * Created by seu on 2017/10/30.
 */
public class Solution_0950_03 {
    private static class NewInteger{
        int val;
        int row;
        int col;
        public NewInteger(int val,int row,int col){
            this.val=val;
            this.row=row;
            this.col=col;
        }
    }
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
        ArrayList<Integer> list = new ArrayList<>();
        if (arrays == null || arrays.length == 0 || arrays[0].length == 0) {
            return list;
        }
        //·ÖÖÎ·¨
        PriorityQueue<NewInteger> minHeap=new PriorityQueue<>(arrays.length, new Comparator<NewInteger>() {
            @Override
            public int compare(NewInteger o1, NewInteger o2) {
                return o1.val-o2.val;
            }
        });
        for(int i=0;i<arrays.length;i++){
            minHeap.offer(new NewInteger(arrays[i][0],i,0));
        }
        while (!minHeap.isEmpty()){
            NewInteger min=minHeap.poll();
            if(min.col+1<arrays[min.row].length){
                minHeap.offer(new NewInteger(arrays[min.row][min.col+1],min.row,min.col+1));
            }
            list.add(min.val);
        }
        return list;
    }
}
