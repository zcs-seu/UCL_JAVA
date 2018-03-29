import java.util.*;

/**
 * 最大映射
    有 n 个字符串，每个字符串都是由 A-J 的大写字符构成。现在你将每个字符映射为一个 0-9 的数字，不同字符映射为不同的数字。这样每个字符串就可以看做一个整数，唯一的要求是这些整数必须是正整数且它们的字符串不能有前导零。现在问你怎样映射字符才能使得这些字符串表示的整数之和最大？

        输入描述:
            每组测试用例仅包含一组数据，每组数据第一行为一个正整数 n ， 接下来有 n 行，每行一个长度不超过 12 且仅包含大写字母 A-J 的字符串。 n 不大于 50，且至少存在一个字符不是任何字符串的首字母。

        输出描述:
            输出一个数，表示最大和是多少。
        示例1
            输入
                 2
                 ABC
                 BCA
            输出
                1875

 解题思路：
     给字符串中的每个字符一个权重，比如：字符串 ABC ，则 A 的权重为 100 ， B 的权重为 10 ， C 的权重为 1 ，其实就是按照它们所在位置设定权重的。
 这样的话，经过多个字符串，每个字符就会得到多个权值，然后把每个字符串的权值加起来，最后按权值对每个字符从大到小排序。
     例如：  ABC 和 BCA 两个字符串：
             ABC ： A=100 ， B=10 ， C=1
             BCA ： B=100 ， C=10 ， A=1
        累加： A=100+1=101 ， B=10+100=110 ， C=1+10=11 ，按权值从大到小排序后得 B ： 110 ， A ： 101 ， C ： 11 ，
        可以认为有 110 个 B ，有 101 个 A ，有 11 个 C ，那么映射 B 为 9 ， A 为 8 ， C 为 7 ，则最后结果为 9*110+8*101+7*11=1875.

 * Created by seu on 2017/9/6.
 */
public class Main_toutiao_nt_01 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = scanner.next();
            }
            System.out.println(maxSum(n, strs));
        }
    }
    public static long maxSum(int n, String[] strs) {
        long sum = 0; // 返回的最大和
        HashMap<Character, Long> map = new HashMap<Character, Long>();
        ArrayList<Character> headList = new ArrayList<Character>();
        for (int i = 0; i < n; i++) {
            setWeight(strs[i], map, headList);
        }
        // 按照各个字符的权重进行排序
        ArrayList<Map.Entry<Character, Long>> list = new ArrayList<Map.Entry<Character, Long>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Long>>() {
            @Override
            public int compare(Map.Entry<Character, Long> arg0, Map.Entry<Character, Long> arg1) {
                // TODO Auto-generated method stub
                return arg0.getValue() > arg1.getValue() ? -1 : 1; // 根据字符的权重进行从大到小排序
            }
        });
        int number = 9;// 最大的数字
        // 除去头元素为0的情况 办法：将权重最小的且不是头元素的第一个字符放置在映射值为0的位置
        if (list.size() == 10) {
            for (int i = 9; i >= 0; i--) {
                if (!headList.contains(list.get(i).getKey())) {// 满足权重最小的且不是头元素的第一个字符
                    list.remove(i); // 去除就相当于放置在映射值为0的位置
                    break;
                }
            }
        }
        for (Map.Entry<Character, Long> entry : list) {
            sum += entry.getValue() * number;
            number--;
        }
        return sum;
    }

    // 设置每个字符串中每个字符的权重，并保存首字符
    public static void setWeight(String string, HashMap<Character, Long> map, ArrayList<Character> headList) {
        char[] cs = string.toCharArray();
        long init = 1;
        for (int i = cs.length - 1; i >= 0; i--) {
            if (map.containsKey(cs[i])) {
                map.put(cs[i], map.get(cs[i]) + init);
            } else {
                map.put(cs[i], init);
            }
            init *= 10;
        }
        headList.add(cs[0]);
    }
}