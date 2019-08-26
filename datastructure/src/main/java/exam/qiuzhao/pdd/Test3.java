package exam.qiuzhao.pdd;

import java.util.*;

public class Test3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<T> tasks = new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 1; i <= N; i++){
            tasks.add(new T(i, sc.nextInt()));
            map.put(i, new HashSet<>());
        }
        for(int i = 0; i < M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            map.get(b).add(a);
        }
        Collections.sort(tasks, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if(o1.time != o2.time){
                    return o1.time - o2.time;
                } else {
                    return o1.index - o2.index;
                }
            }
        });
        int handled = 0;
        int[] result = new int[N];
        while(handled < N){
            int indexToMove = Integer.MAX_VALUE;
            int visitTaskIndex = -1;
            for(int i = 0; i < N; i++){
                T task = tasks.get(i);
                if( ! task.visited && map.get(task.index).size() == 0 && task.index < indexToMove){
                    indexToMove= task.index;
                    visitTaskIndex = i;
                    task.visited = true;
                }
            }
            result[handled] = indexToMove;
            handled++;
            tasks.get(visitTaskIndex).visited = true;
            for(int i = 1; i <= N; i++){
                map.get(i).remove(indexToMove);
            }
        }

        for(int i = 0; i < N - 1; i ++){
            System.out.print(result[i] + " ");
        }
        System.out.print(result[N - 1]);
    }

}

class T{
    int index;
    int time;
    boolean visited = false;
    public T(int index, int time){
        this.index = index;
        this.time = time;
    }
}
