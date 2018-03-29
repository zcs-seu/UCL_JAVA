import java.util.*;
/**
 * 思路说明 1.将数组分为5部分，x1,m,x2,n,x3；
 * 2.m为第一个想要判断的项，n为第二个判断项，x1为m前所有项的集合,x2为m和n之间所有项的集合,x3为n后*所有项的集合，其中x1,x2和x3均有可能是空集，后面有处理；
 * 3.要想能看到对方，情况分为两种，m>=max(x2)&&n>=max(x2)||m>max(x1,x3)&&n>max(x1,x3); 其他情况是这两种情况的子集，不必考虑；
 *
 * @author Levi_Lin
 *
 */
public class Main_jd_nt_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int result = 0;// 建立变量，准备存入结果；
            int n = sc.nextInt();
            int[] hill = new int[n];
            for (int i = 0; i < n; i++)
                hill[i] = sc.nextInt();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int x1 = getMax(hill, 0, i);
                    int x2 = getMax(hill, i + 1, j);
                    int x3 = getMax(hill, j + 1, n);
                    if ((hill[i] >= x2 && hill[j] >= x2) || (hill[i] >= getMax(x1, x3) && hill[j] >= getMax(x1, x3)))
                        result++;
                }
            }
            System.out.println(result);
        }
    }

    /**
     * 求两数最大值，和下面的函数重载：
     *
     * @return 两者最大的值
     */
    public static int getMax(int a, int b) {
        return a >= b ? a : b;
    }

    /**
     * 说明：left>=right时说明该集合为空集，返回-1，说明之间没有屏障，可以任意穿越该集合观看
     *
     * @param arr 需要找出较大值的数组
     * @param left数组角标左边界（包含）
     * @param right数组角标右边界（不包含）
     * @return返回较大值；
     */
    public static int getMax(int[] arr, int left, int right) {
        if (left >= right)
            return -1;
        int max = arr[left];
        for (int i = left + 1; i < right; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }
}