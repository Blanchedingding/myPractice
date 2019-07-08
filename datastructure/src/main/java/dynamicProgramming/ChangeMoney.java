package dynamicProgramming;

public class ChangeMoney {

    /**
     * 有1、2、3、5元可以找，问找n元有几种方案
     * @param n
     * @return
     */
    public int changemoney(int n){
        int[] moneyTypes = {1,2,3,5};
        int[][] dp = new int[moneyTypes.length][n + 1];

        for(int j = 0; j <dp.length; j++){
            dp[j][0] = 1;
        }

        for(int j = 0; j < n + 1; j++){
            dp[0][j] = (n % moneyTypes[0] == 0)? 1: 0;
        }

        for(int i = 1; i < moneyTypes.length; i++){
            for(int j = 1; j < n + 1; j++){
                dp[i][j] = dp[i - 1][j];
                for(int k = 1; k * moneyTypes[i] <= j; k ++){
                    dp[i][j] += dp[i - 1][j - k * moneyTypes[i]];
                }
            }
        }
        for(int i = 0; i < moneyTypes.length; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[moneyTypes.length - 1][n];
    }

    public static void main(String[] args) {
        ChangeMoney c = new ChangeMoney();
        System.out.println(c.changemoney(10));
    }
}
