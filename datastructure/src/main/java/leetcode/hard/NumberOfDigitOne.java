package leetcode.hard;

/**
 * Given an integer n,
 * count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 *
 * Example:
 * Input: 13
 * Output: 6
 * Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class NumberOfDigitOne {

    /**
     * intuitive: 每10个数, 有一个个位是1, 每100个数, 有10个十位是1, 每1000个数, 有100个百位是1.
     * 做一个循环, 每次计算单个位上1得总个数(个位,十位, 百位).
     *
     * 例子:
     *
     * 以算百位上1为例子: 假设百位上是0, 1, 和 >=2 三种情况:
     *
     * case 1: n=3141092, a= 31410, b=92. 计算百位上1的个数应该为 3141 *100 次.
     *
     * case 2: n=3141192, a= 31411, b=92. 计算百位上1的个数应该为 3141 *100 + (92+1) 次.
     *
     * case 3: n=3141592, a= 31415, b=92. 计算百位上1的个数应该为 (3141+1) *100 次.
     */
    public int countDigitOne(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10) {
            long a = n / m, b = n % m;
            ones += (a + 8) / 10 * m;
            if(a % 10 == 1) ones += b + 1;
        }
        return ones;
    }

    public static void main(String[] args) {
        NumberOfDigitOne n = new NumberOfDigitOne();
        System.out.println(n.countDigitOne(13));
    }
}
