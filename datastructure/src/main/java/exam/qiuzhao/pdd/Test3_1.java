package exam.qiuzhao.pdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Test3_1 {

    static HashMap<Character, ArrayList<String>> mapping = new HashMap<>();

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        HashMap<Integer, HashSet<Integer>> mapping = new HashMap<>();
        HashSet<Integer> todo = new HashSet<>();
        for(int i = 1; i <= N; i++){
            HashSet<Integer> set = new HashSet<>();
            mapping.put(i, set);
            todo.add(i);
        }

        int[] time = new int[N+1];
        for(int i = 1; i <= N; i++){
            int tempTime = in.nextInt();
            time[i] = tempTime;
        }

        for(int i = 0; i < M; i++){
            int from = in.nextInt();
            int to = in.nextInt();
            mapping.get(to).add(from);
        }

        ArrayList<Integer> result = new ArrayList<>();

        while(todo.size() != 0){
            ArrayList<Integer> thisTurnToDo = new ArrayList<>();
            for(int i = 1; i <= N; i++){
                if(mapping.get(i).isEmpty() && todo.contains(i) ){
                    thisTurnToDo.add(i);
                }
            }
            int minThing = 0;
            int minTime = Integer.MAX_VALUE;
            for(Integer thing : thisTurnToDo) {
                if(time[thing] < minTime){
                    minThing = thing;
                    minTime = time[thing];
                }
            }

            result.add(minThing);

            for(int i = 1; i <= N; i++){
                mapping.get(i).remove(minThing);
            }

            todo.remove(minThing);
        }

        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i));
            if(i != result.size() - 1){
                System.out.print(" ");
            }
        }
    }
}
