package leetcode.hard;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses {

    int longestValidParentheses(String s) {
        int n = s.length();
        if(n <= 1) return 0;
        char[] c = s.toCharArray();
        int curMax = 0;
        int[] longest = new int[n];
        for(int i=1; i < s.length(); i++){
            if(c[i] == ')' && i-longest[i-1]-1 >= 0 && c[i-longest[i-1]-1] == '('){
                longest[i] = longest[i-1] + 2 + ((i-longest[i-1]-2 >= 0)?longest[i-longest[i-1]-2]:0);
                curMax = Math.max(longest[i],curMax);
            }
        }
        return curMax;
    }

    public int longestValidParentheses2(String s) {
        int n = s.length();
        char[] c= s.toCharArray();
        Stack<Integer> stack = new Stack();
        for(int i = 0; i < n; i++){
            if(c[i] == '(') stack.push(i);
            else {
                if(!stack.empty() && c[stack.peek()] == '('){
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        if(stack.isEmpty()) return n;
        int a = n, b = 0;
        int max = 0;
        while(!stack.isEmpty()){
            b = stack.pop();
            max = Math.max(max, a - b - 1);
            a = b;
        }
        max = Math.max(max, a);
        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses l = new LongestValidParentheses();
        System.out.println(l.longestValidParentheses(")()())"));
    }
}
