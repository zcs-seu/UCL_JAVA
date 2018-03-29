import java.util.*;
public class Main_tt_01 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int m=sc.nextInt();
            int c=sc.nextInt();
            int[] dp=new int[c+1];
            Arrays.fill(dp,-1);
            HashSet<Integer> set=new HashSet<Integer>();
            ArrayList<Integer> curList=new ArrayList<Integer>();
            for(int i=0;i<n;i++){
                int num=sc.nextInt();
                for(int j=0;j<num;j++){
                    int cur=sc.nextInt();
                    if(dp[cur]!=-1&&i-dp[cur]<m){
                        set.add(cur);
                    }
                    if(i==0){
                        curList.add(cur);
                    }
                    dp[cur]=i;
                }
            }
            for(int cur:curList){
                if(dp[cur]!=-1&&n-dp[cur]<m){
                    set.add(cur);
                }
            }
            System.out.println(set.size());
        }
    }
/*
5 2 3
3 1 2 3
0
2 2 3
1 2
1 3
*/
}