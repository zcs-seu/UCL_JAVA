import java.util.*;
/**
 * Created by seu on 2017/9/6.
 */
public class Main_toutiao_nt_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = scanner.next();
            }
            System.out.println(getNumWeightK(n, k, strs));
        }
    }
    public static int getNumWeightK(int n, int K, String[] strs) {
        int count = 0;
        ArrayList<String> strings = new ArrayList<String>();
        //生成全排列，存储到strings中，k表示当前要添加的是第几个元素（0-n-1）
        permutation(strings, strs, 0);
        for (int i = 0; i < strings.size(); i++) {
            int weightTemp = getWeight(strings.get(i));
            if (weightTemp == K) {
                count++;
            }
        }
        return count;
    }

    //求一个字符的权重
    public static int getWeight(String s) {
        int weight = 0;
        int len = s.length();
        for(int i=0;i<s.length();i++){
            if(s.substring(0, i).equals(s.substring(len - i, len)) && s.substring(0, len-i).equals(s.substring(i, len))){
                //通过起始点和移动的i步长，相当于将整个弧形划分成了两段：
                // 原来（len-i,len）移动到了(0, i)
                // 原来（0，len-i）移动到了（i,len）
                weight++;
            }
        }
        return weight;
    }

    // 全排列
    public static void permutation(ArrayList<String> strings, String[] strs, int k) {
        if (k == strs.length) {
            String temp = ""; //一开始用StringBuffer类来保存，就超时了。所以尽量不要用StringBuffer
            for (int i = 0; i < strs.length; i++) {
                temp += strs[i];
            }
            strings.add(temp);
        } else{
            for (int i = k; i < strs.length; i++) {
                swap(strs, i, k);
                permutation(strings, strs, k + 1);
                swap(strs, i, k);
            }
        }
    }

    public static void swap(String[] strs, int i, int j) {
        if (i != j) {
            String t = strs[i];
            strs[i] = strs[j];
            strs[j] = t;
        }
    }
}