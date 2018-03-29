import java.util.*;
public class Main_sg_nt_01{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int N=sc.nextInt();//问题数
            //answer中key表示回答者的id，value为由该回答者回答的问题的提问者的id构成的HashSet
            HashMap<Integer,HashSet<Integer>> answer=new HashMap<Integer,HashSet<Integer>>();
            Queue<Integer> cheatQueue=new LinkedList<Integer>();
            for(int i=0;i<N;i++){
                int from=sc.nextInt();//提问者id
                int n=sc.nextInt();//回答者数目
                for(int j=0;j<n;j++){
                    int to=sc.nextInt();
                    //注意自己回答自己的问题不算作弊，因此不用考虑
                    if(to!=from){
                        if(!answer.containsKey(to)){
                            answer.put(to,new HashSet<Integer>());
                        }
                        answer.get(to).add(from);
                        if(answer.containsKey(from) && answer.get(from).contains(to)){
                            cheatQueue.offer(from);
                            cheatQueue.offer(to);
                        }
                    }
                }
            }
            //利用TreeSet剔除重复的作弊者id，并使其从小到大排列
            //在此过程中，将由多个（>2）作弊者回答过提问的提问者也加入作弊者队列考虑
            TreeSet<Integer> cheatSet=new TreeSet<Integer>();
            HashSet<Integer> cheatAnswer=new HashSet<Integer>();
            while(!cheatQueue.isEmpty()){
                int cheat=cheatQueue.poll();
                if(!cheatSet.contains(cheat)){
                    cheatSet.add(cheat);
                    if(answer.containsKey(cheat)){
                        for(Integer from:answer.get(cheat)){
                            if(cheatAnswer.contains(from)){
                                cheatSet.add(from);
                            }else{
                                cheatAnswer.add(from);
                            }
                        }
                    }
                }
            }
            //打印作弊者数目
            System.out.println(cheatSet.size());
            if(!cheatSet.isEmpty()){
                //打印作弊者id
                Iterator<Integer> iter=cheatSet.iterator();
                System.out.print(iter.next());
                while(iter.hasNext()){
                    System.out.print(" "+iter.next());
                }
                System.out.println();
            }
        }
    }
}