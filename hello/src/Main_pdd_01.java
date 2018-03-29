import java.util.*;

/**
 * Created by seu on 2017/8/26.
 */

public class Main_pdd_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //init
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] nums=new int[m];
        int[] powers=new int[n];
        int[] hungry=new int[n];

        for(int i=0;i<m;i++){
            nums[i]=sc.nextInt();
        }
        Arrays.sort(nums);

        for(int i=0;i<n;i++){
            powers[i]=sc.nextInt();
            powers[i]=-powers[i];
            hungry[i]=sc.nextInt();
        }

        ArrayList<Integer> sugarList=new ArrayList<Integer>();
        for(int i:nums){
            sugarList.add(i);
        }
        TreeMap<Integer, HashSet<Integer>> map = genHungryMap(n,powers);
        solvePropHungry(map,hungry,sugarList);
        print(hungry);
    }

    private static TreeMap<Integer, HashSet<Integer>> genHungryMap(int n, int[] powers) {
        TreeMap<Integer,HashSet<Integer>> map=new TreeMap<Integer,HashSet<Integer>>();
        for(int i=0;i<n;i++){
            HashSet<Integer> tmpset=new HashSet<Integer>();
            if(map.containsKey(powers[i])){
                tmpset=map.get(powers[i]);
            }
            tmpset.add(i);
            map.put(powers[i],tmpset);
        }
        return map;
    }

    private static void solvePropHungry(TreeMap<Integer, HashSet<Integer>> map, int[] hungry, ArrayList<Integer> sugarList) {
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int stren = (int)entry.getKey();
            HashSet<Integer> set= (HashSet<Integer>)entry.getValue();
            for(int p:set){
                int i=-1;
                int hung=hungry[p];
                while(i!=0){

                    for(i=0;i<sugarList.size();i++){
                        if(sugarList.get(i)>hung){
                            break;
                        }
                    }
                    if(i!=0){
                        hung-=sugarList.get(i-1);
                        sugarList.remove(i-1);
                    }
                }
                hungry[p]=hung;
            }
        }
    }

    private static void print(int[] hungry) {
        for(int tmp:hungry){
            System.out.println(tmp);
        }
    }
}