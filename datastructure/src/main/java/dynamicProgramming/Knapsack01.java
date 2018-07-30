package dynamicProgramming;

/**
 * 01背包问题
 * n个商品，i个商品价值vi，重wi
 * 背包最多能装K商品，价值最大为多少
 */
public class Knapsack01 {

    public int knapsack(int[] v, int[] w, int K){
        int n = v.length - 1;//商品个数
        int[][] r = new int[n + 1][K + 1];

        for(int i = 1; i <= n; i++){
           for(int j = 1; j <= K; j++){
               if(j >= w[i]){
                   r[i][j] = Math.max(r[i-1][j], r[i-1][j-w[i]] + v[i]);
               } else {
                   r[i][j] = r[i-1][j];
               }
           }
        }
        printMatrix(r);
        return r[n][K];
    }

    private void printMatrix(int[][] r){
        for(int i = 0; i < r.length; i++){
            for(int j = 0; j < r[0].length; j++){
                System.out.print(r[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //从下标为1开始
        int[] v = new int[]{0,2,4,5,1,8};
        int[] w = new int[]{0,5,3,7,5,10};
        Knapsack01 k = new Knapsack01();
        System.out.println(k.knapsack(v, w, 10));

    }
}
