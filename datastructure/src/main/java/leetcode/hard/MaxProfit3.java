package leetcode.hard;

import java.util.Arrays;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Example 2:
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class MaxProfit3 {

    // f[k, ii] represents the max profit up until prices[ii] (Note: NOT ending with prices[ii]) using at most k transactions.
    // f[k, ii] = max(f[k, ii-1], prices[ii] - prices[jj] + f[k-1, jj]) { jj in range of [0, ii-1] }
    //          = max(f[k, ii-1], prices[ii] + max(f[k-1, jj] - prices[jj]))
    // f[0, ii] = 0; 0 times transation makes 0 profit
    // f[k, 0] = 0; if there is only one price data point you can't make any money no matter how many times you can trade
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if(n <= 1) return 0;
        int[][] dp = new int[3][n];
        for(int j = 1; j <= 2; j ++){
            int temp = dp[j - 1][0] - prices[0];
            for(int i = 1; i < n; i++){
                temp = Math.max(temp, dp[j - 1][i] - prices[i]);
                dp[j][i] = Math.max(dp[j][i - 1], temp + prices[i]);
            }
        }
        return dp[2][n - 1];
    }

    public int maxProfit(int[] prices) {
        // these four variables represent your profit after executing corresponding transaction
        // in the beginning, your profit is 0.
        // when you buy a stock ,the profit will be deducted of the price of stock.
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int curPrice : prices) {
            if (secondSell < secondBuy + curPrice) secondSell = secondBuy + curPrice; // the max profit after you sell the second stock
            if (secondBuy < firstSell - curPrice) secondBuy = firstSell - curPrice; // the max profit after you buy the second stock
            if (firstSell < firstBuy + curPrice) firstSell = firstBuy + curPrice; // the max profit after you sell it
            if (firstBuy < curPrice) firstBuy = -curPrice; // the max profit after you buy first stock
        }

        return secondSell; // secondSell will be the max profit after passing the prices
    }

    public static void main(String[] args) {
        MaxProfit3 m = new MaxProfit3();
        int[] p = new int[]{3,3,5,0,0,3,1,4};
        System.out.println(m.maxProfit2(p));
    }
}
