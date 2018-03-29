/**
 * Created by zhang_cs on 2017/9/3.
 */

import java.util.Scanner;

public class Main_pdd_nt_02 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str1 = sc.next();
            String str2 = sc.next();
            System.out.println(mutiplay(str1,str2));
        }
    }

    private static String mutiplay(String str1, String str2) {
        //由于计算乘法时一般采用从低位开始，故先取倒序
        str1 = new StringBuilder(str1).reverse().toString();
        str2 = new StringBuilder(str2).reverse().toString();
        //转数组，方便操作
        char[] numCharArr1 = str1.toCharArray();
        char[] numCharArr2 = str2.toCharArray();
        //定义计数数组
        int[] curArr = new int[numCharArr1.length + numCharArr2.length];
        for(int i=0;i<numCharArr1.length;i++){
            for(int j=0;j<numCharArr2.length;j++){
                curArr[i+j]+=(numCharArr1[i]-'0')*(numCharArr2[j]-'0');
            }
        }
        //构建结果字符串
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<curArr.length;i++){
            int d = curArr[i]%10;
            int c = curArr[i]/10;

            sb.insert(0,d);
            if(i<curArr.length-1)
                curArr[i+1]+=c;
        }
        //剔除前导0
        while(sb.length()>0 && sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }
        return sb.length()==0?"0":sb.toString();
    }

}
