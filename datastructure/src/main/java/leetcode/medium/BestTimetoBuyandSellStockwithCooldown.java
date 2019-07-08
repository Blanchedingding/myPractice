package leetcode.medium;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 *
 * Example:
 * Input: [1,2,3,0,2]
 * Output: 3
 *
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimetoBuyandSellStockwithCooldown {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n <= 0) return 0;
        int[] buy = new int[n];
        buy[0] = - prices[0];
        int[] sell = new int[n];
        sell[0] = 0;
        int[] cool = new int[n];
        cool[0] = 0;
        for(int i = 1; i < n; i++){
            buy[i] = Math.max(buy[i - 1], cool[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            cool[i] = Math.max(cool[i - 1], Math.max(buy[i - 1], sell[i - 1]));
        }
        return sell[n - 1];
    }

    public static void main(String[] args) {
        BestTimetoBuyandSellStockwithCooldown b = new BestTimetoBuyandSellStockwithCooldown();
        System.out.println(b.maxProfit(new int[]{1,2,3,0,2}));
    }
}
