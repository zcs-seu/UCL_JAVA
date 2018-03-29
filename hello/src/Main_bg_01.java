import java.util.Scanner;
import java.util.Stack;

/**
 * Created by seu on 2017/9/4.
 */
public class Main_bg_01 {
/*
输入示例：
2
A 2 2 100.5
B 4 3 180.5
3 2 1
其中第一行表示一共有几种房型，设为整形n；
接下来的n行三个数分别表示每种房型能够容纳的成人数、能够容纳的儿童数、单价
最后一行的三个数分别表示客户中的成人数、儿童数和住几晚
*/
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int N = sc.nextInt();//房型数
            int[] adults = new int[N];//房型可住成人数
            int[] children = new int[N];//房型可住儿童数
            double[] prices = new double[N];//房型单价
            for(int i=0;i<N;i++){
                adults[i]=sc.nextInt();
                children[i]=sc.nextInt();
                prices[i]=sc.nextDouble();
            }
            int X=sc.nextInt();//客户中有几人是成人
            int Y=sc.nextInt();//客户中有几人是儿童
            int num=sc.nextInt();//客户一共住几晚
            int[] res=new int[N];//中间数组，用于进行深度优先搜索时使用
            int[] destChoices=new int[N];//目标房型选择数目数组，若destChoices[i]=t，表示需要第i型房t间
            double[] minPrice=new double[1];//用于存最低价，注意这里的最低价表示num晚总的最低价
            minPrice[0]=Integer.MAX_VALUE;//初始化最低价为整形最大值

            //求最佳选择
            getMinPriceChoiceWithNum(adults,children,prices,res,minPrice,destChoices,X,Y,num,0,0,0.0);

            //输出各房型选择的数量
            for(int r:destChoices){
                System.out.print(r+"\t");
            }

            //输出num晚总的房价
            System.out.println(minPrice[0]);
        }
    }

    /**
     * 求num晚的最佳选择
     * @param adults :每种房型可以入住的成人数
     * @param children :每种房型可以入住的儿童数
     * @param prices :每种房型单价
     * @param res :中间数组，用于进行深度优先搜索时使用
     * @param minPrice :用于存最低价，注意这里的最低价表示num晚总的最低价
     * @param destChoices :目标房型选择数目数组，若destChoices[i]=t，表示需要第i型房t间
     * @param x :客户中的成人数
     * @param y :客户中的儿童数
     * @param num :客户住几晚
     * @param xn :目前这种选择可以容纳多少成人
     * @param yn :目前这种选择可以容纳多少儿童
     * @param cur :目前这种选择总金额
     */
    private static void getMinPriceChoiceWithNum(int[] adults, int[] children, double[] prices, int[] res,double[] minPrice,int[] destChoices,int x, int y,int num,int xn,int yn,double cur) {
        getMinPriceChoice(adults,children,prices,res,minPrice,destChoices,x,y,xn,yn,cur);
        minPrice[0]*=num;
    }


    /**
     * 求1晚的最佳选择
     * @param adults :每种房型可以入住的成人数
     * @param children :每种房型可以入住的儿童数
     * @param prices :每种房型单价
     * @param res :中间数组，用于进行深度优先搜索时使用
     * @param minPrice :用于存最低价，注意这里的最低价表示num晚总的最低价
     * @param destChoices :目标房型选择数目数组，若destChoices[i]=t，表示需要第i型房t间
     * @param x :客户中的成人数
     * @param y :客户中的儿童数
     * @param xn :目前这种选择可以容纳多少成人
     * @param yn :目前这种选择可以容纳多少儿童
     * @param cur :目前这种选择总金额
     */
    private static void getMinPriceChoice(int[] adults, int[] children, double[] prices, int[] res,double[] minPrice,int[] destChoices,int x, int y,int xn,int yn,double cur) {
        if(xn>=x && yn>=y){
            if(cur<minPrice[0]){
                minPrice[0]=cur;
                copyArr(res,destChoices);
            }
            return;
        }
        for(int i=0;i<res.length;i++){
            res[i]+=1;
            getMinPriceChoice(adults,children,prices,res,minPrice,destChoices,x,y,xn+adults[i],yn+children[i],cur+prices[i]);
            res[i]-=1;
        }
    }


    /**
     * 拷贝数组元素
     * @param fromArr 源数组
     * @param toArr 目的数组
     */
    private static void copyArr(int[] fromArr,int[] toArr){
        if(fromArr==null || toArr==null || toArr.length < fromArr.length){
            return;
        }
        for(int i=0;i<fromArr.length;i++){
            toArr[i]=fromArr[i];
        }
    }
}