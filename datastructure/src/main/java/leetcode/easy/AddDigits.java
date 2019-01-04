package leetcode.easy;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *
 * Example:
 *
 * Input: 38
 * Output: 2
 * Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
 *              Since 2 has only one digit, return it.
 */
public class AddDigits {
    /**
     * Digit Root数字根：
     * 数字根（Digital Root）就是把一个自然数的各位数字相加，再将所得数的各位数字相加，
     * 直到所得数为一位数字为止。而这个一位数便是原来数字的数字根。例如: 198的数字根为9（1+9+8=18，1+8=9）。
     *
     * 性质：
     * - 任何数加9的数字根还是它本身
     * - 9乘任何数字的数字根都是9
     *
     * -  a的数根 = ( a - 1) % 9 + 1 （考虑到9的数根）
     * - 两数之和的数字根等于这两个数的数字根的和的数字根
     * -  两数之积的数字根等于这两个数的数字根的积的数字根
     * -  一个数字的n次幂的数字根等于这个数字的数字根的n次幂的和数字根
     */
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
