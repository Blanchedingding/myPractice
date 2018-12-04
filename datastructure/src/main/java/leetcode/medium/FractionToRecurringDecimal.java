package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * Example 1:
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 *
 * Example 2:
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 *
 * Example 3:
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 */
public class FractionToRecurringDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();
        if(numerator == 0) return "0";
        // "+" or "-"
        result.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");

        long n = Math.abs((long)numerator);
        long d = Math.abs((long)denominator);
        long a = n / d;
        result.append(a);
        if(n % d == 0){
            return result.toString();
        }
        result.append(".");
        long b = (n - a * d) * 10;
        Map<Long, Integer> repeat = new HashMap<>();
        int position = result.toString().length();
        while(null == repeat.get(b)){
            a = b / d;
            result.append(a);
            if(b % d == 0){
                return result.toString();
            }
            repeat.put(b, position);
            position ++;
            b = (b - a * d) * 10;
        }
        result.insert(repeat.get(b), "(");
        result.append(")");
        return result.toString();
    }

    public static void main(String[] args) {
        FractionToRecurringDecimal f = new FractionToRecurringDecimal();
        System.out.println(f.fractionToDecimal(-1 ,-2147483648));
    }

}
