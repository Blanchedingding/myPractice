package leetcode.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Strings A and B are K-similar (for some non-negative integer K)
 * if we can swap the positions of two letters in A exactly K times
 * so that the resulting string equals B.
 *
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 *
 * Example 1:
 * Input: A = "ab", B = "ba"
 * Output: 1
 *
 * Example 2:
 * Input: A = "abc", B = "bca"
 * Output: 2
 *
 * Example 3:
 * Input: A = "abac", B = "baca"
 * Output: 2
 *
 * Example 4:
 * Input: A = "aabc", B = "abca"
 * Output: 2
 *
 * Note:
 * 1 <= A.length == B.length <= 20
 * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
 */
public class KSimilarStrings {

    public int kSimilarity(String A, String B) {
        if (A.equals(B)) return 0;
        Queue<String> q= new LinkedList<>();
        Set<String> vis= new HashSet<>();
        q.add(A);
        int res = 0;
        while(!q.isEmpty()){
            res ++;
            int sz=q.size();
            for (; sz>0; sz--){
                String s= q.poll();
                int i=0;
                while (s.charAt(i)==B.charAt(i)) i++;
                for (int j=i+1; j<s.length(); j++){
                    if (s.charAt(j)==B.charAt(j) || s.charAt(j)!=B.charAt(i) ) continue;
                    String temp= swap(s, i, j);
                    if (temp.equals(B)) return res;
                    if (vis.add(temp)) q.add(temp);
                }
            }
        }
        return res;
    }

    public String swap(String s, int i, int j){
        char[] ca=s.toCharArray();
        char temp=ca[i];
        ca[i]=ca[j];
        ca[j]=temp;
        return new String(ca);
    }

    public static void main(String[] args) {
        KSimilarStrings k = new KSimilarStrings();
        System.out.println(k.kSimilarity("aabc", "abca"));
    }
}
