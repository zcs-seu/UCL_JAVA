import java.util.*;

/**
 * 木棒拼图：
 * 有一个由很多木棒构成的集合，每个木棒有对应的长度，请问能否用集合中的这些木棒以某个顺序首尾相连构成一个面积大于 0 的简单多边形且所有木棒都要用上，
 * 简单多边形即不会自交的多边形。初始集合是空的，有两种操作，要么给集合添加一个长度为 L 的木棒，要么删去集合中已经有的某个木棒。每次操作结束后你
 * 都需要告知是否能用集合中的这些木棒构成一个简单多边形。

    输入描述:
        每组测试用例仅包含一组数据，每组数据第一行为一个正整数 n 表示操作的数量(1 ≤ n ≤ 50000) ， 接下来有n行，每行第一个整数为操作类型 i (i ∈ {1,2})，第二个整数为一个长度 L(1 ≤ L ≤ 1,000,000,000)。如果 i=1 代表在集合内插入一个长度为 L 的木棒，如果 i=2 代表删去在集合内的一根长度为 L 的木棒。输入数据保证删除时集合中必定存在长度为 L 的木棒，且任意操作后集合都是非空的。
    输出描述:
        对于每一次操作结束有一次输出，如果集合内的木棒可以构成简单多边形，输出 "Yes" ，否则输出 "No"。

    输入例子1:
         5
         1 1
         1 1
         1 1
         2 1
         1 2

    输出例子1:
         No
         No
         Yes
         No
         No

    解题思路： 设所有边（及木棒）总长度为 Tlen，当前最长的一条边长度为max_len，则仅当 Tlen - max_len > max_len 时，
    才能组成面积大于0 的简单多边形

 * Created by seu on 2017/9/6.
 */
public class Main_toutiao_nt_02 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            long[][] data=new long[n][2];
            for(int i=0;i<n;i++){
                data[i][0]=sc.nextLong();
                data[i][1]=sc.nextLong();
            }
                dealWith(data);
        }
    }
     
    public static void dealWith(long[][] data){
        List<Long> sticks=new ArrayList<Long>();
        for(int i=0;i<data.length;i++){
            if(data[i][0]==1)
                sticks.add(data[i][1]);
            else
                sticks.remove(data[i][1]);
            judge(sticks);
        }
    }
     
    public static void judge(List<Long> sticks){
        if(sticks.size()<3)
            System.out.println("No");
        else{
            Collections.sort(sticks);
            long sum=0;
            int i=0;
            for(i=0;i<sticks.size()-1;i++){
                sum+=sticks.get(i);
            }
            if(sum>sticks.get(i))
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}