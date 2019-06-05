package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParentheses {

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        helper(1, 0, "(", n);
        return result;
    }

    public void helper(int leftNum, int rightNum, String cur, int n){
        if(leftNum == n && rightNum == n){
            result.add(cur);
            return;
        } else {
            if(leftNum < n){
                helper(leftNum + 1, rightNum, cur+"(", n);
            }
            if(rightNum < leftNum){
                helper(leftNum, rightNum +1, cur+")", n);
            }
        }
    }

    public static void main(String[] args) {
        GenerateParentheses g = new GenerateParentheses();
        System.out.println(g.generateParenthesis(3));
    }
}
