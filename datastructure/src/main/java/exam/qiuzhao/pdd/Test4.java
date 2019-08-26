package exam.qiuzhao.pdd;

import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<M> list = new ArrayList<>();
        int[] L = new int[N + 1];
        int[] W = new int[N + 1];
        for(int i = 1; i <= N; i++){
            L[i] = sc.nextInt();
        }
        for(int i = 1; i <= N; i++){
            W[i] = sc.nextInt();
        }
        for(int i = 1; i <= N; i++){
            list.add(new M(L[i], W[i]));
        }
        Collections.sort(list, new Comparator<M>() {
            @Override
            public int compare(M o1, M o2) {
                if(o1.L != o2.L){
                    return o1.L - o2.L;
                } else {
                    return o1.W - o2.W;
                }
            }
        });
        int[] height = new int[N + 1];
        int[] weight = new int[N + 1];
        weight[0] = 0;
        height[0] = 0;
        int result = 0;
        for(int i = 1; i <= N; i++){
            M m = list.get(i - 1);
            int maxHeight = 1;
            int minWeight = m.W;
            for(int j = 1; j < i; j ++){
                if(list.get(j - 1).L < m.L && weight[j] <= m.W * 7 && maxHeight <= height[j] + 1){
                    if(maxHeight < height[j] + 1){
                        maxHeight = height[j] + 1;
                        minWeight = weight[j] + m.W;
                    } else {
                        minWeight = Math.min(minWeight, weight[j] + m.W);
                    }
                }
            }
            height[i] = maxHeight;
            weight[i] = minWeight;
            System.out.println("i=" + i + " height=" + height[i] + " weight=" + weight[i]);
            result = Math.max(result, height[i]);
        }
        System.out.println(result);
    }
}

class M{
    int L;
    int W;
    public M(int L, int W){
        this.L = L;
        this.W = W;
    }
}