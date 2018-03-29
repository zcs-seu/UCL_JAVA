import java.util.Scanner;

/**
 * Created by seu on 2017/9/5.
 */
public class Main_cb_01 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            double[] x=new double[n];
            double[] y=new double[n];
            double[] vx=new double[n];
            double[] vy=new double[n];
            for(int i=0;i<n;i++){
                x[i]=sc.nextDouble();
                y[i]=sc.nextDouble();
                vx[i]=sc.nextDouble();
                vy[i]=sc.nextDouble();
            }
            double res=Double.MAX_VALUE;
            double t=0.0;
            for(int i=0;i<n;i++){
                for(int j=1;j<n;j++){
                    double a=Math.pow(vx[j]-vx[i],2)+Math.pow(vy[j]-vy[i],2);
                    double b=2*((x[j]-x[i])*(vx[j]-vx[i])+(y[j]-y[i])*(vy[j]-vy[i]));
                    double c=Math.pow(x[j]-x[i],2)+Math.pow(y[j]-y[i],2);
                    double cur=(4*a*c-b*b)/(4*a);
                    if(cur<res){
                        res=cur;
                    }
                }
            }
            System.out.println(res);
        }
    }
}
/*
2
0 0 1 0
2 0 -1 0

4

 */
