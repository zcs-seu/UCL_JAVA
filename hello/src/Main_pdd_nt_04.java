/**
 * Created by zhang_cs on 2017/9/3.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_pdd_nt_04 {
    private static class Node{
        int x;
        int y;
        int key;
        int step;
        // 其中，（x,y）构成节点坐标，
        // key表示目前的锁状态（共10位，第i位为1表示已经获得第i个锁）
        // step表示目前走到该节点所用的步数
        public Node(int x,int y,int key,int step){
            this.x=x;
            this.y=y;
            this.key=key;
            this.step=step;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            //init
            int n = sc.nextInt();
            int m = sc.nextInt();
            char[][] map=new char[n][m];
            for(int i=0;i<n;i++){
                map[i]=sc.next().toCharArray();
            }
            //找起点
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(map[i][j]=='2'){
                        Queue<Node> queue = new LinkedList<Node>();
                        queue.offer(new Node(i,j,0,0));
                        System.out.println(getBFSSteps(i,j,n,m,map));
                    }
                }
            }
        }
    }

    private static int getBFSSteps(int i, int j, int n, int m, char[][] map) {
        boolean[][][] isVisited=new boolean[n][m][1025];
        int[][] next={
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };
        //BFS搜索
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(i,j,0,0));
        while(!queue.isEmpty()){
            Node curNode=queue.poll();
            for(int s=0;s<4;s++){
                int x=curNode.x+next[s][0];
                int y=curNode.y+next[s][1];
                int key=curNode.key;
                if(x<0 ||x>=n || y<0 || y>=m || map[x][y]=='0'){
                    continue;
                }else if(map[x][y]=='3'){
                    return curNode.step+1;
                }else if(Character.isLowerCase(map[x][y])){
                    key=key|(1<<(map[x][y]-'a'));
                }else if(Character.isUpperCase(map[x][y]) && (key&(1<<(map[x][y]-'a')))==0){
                    continue;
                }
                if(!isVisited[x][y][key]){
                    isVisited[x][y][key]=true;
                    queue.offer(new Node(x,y,key,curNode.step+1));
                }
            }
        }
        return -1;
    }
}
