import java.util.*;
/**
 * Created by seu on 2017/9/8.
 */
public class Main_sg_nt_02 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int m=sc.nextInt();
            if(n<1 || m<1){
                System.out.println(0);
                continue;
            }
            int[][] help=new int[n][m];
            for(int i=0;i<n;i++){
                Arrays.fill(help[i],1);
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    int value=sc.nextInt();
                    for(int k=0;k<j;k++){
                        help[i][k]*=value;
                    }
                    for(int k=j+1;k<m;k++){
                        help[i][k]*=value;
                    }
                    for(int k=0;k<i;k++){
                        help[k][j]*=value;
                    }
                    for(int k=i+1;k<n;k++){
                        help[k][j]*=value;
                    }
                }
            }
            //Ñ°ÕÒ×î´óÖµ
            int res=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    res=Math.max(res,help[i][j]);
                }
            }
            System.out.println(res);
        }
    }
}
