package leetcode.medium;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Example 1:
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet=new HashSet<>(wordList);
        Set<String> reached=new HashSet<>();
        reached.add(beginWord);
        wordSet.remove(beginWord);
        int level=1;
        while(!reached.isEmpty()) {
            Set<String> reachedNext=new HashSet<>();
            for(String s:reached) {
                for(int i=0;i<s.length();i++) {
                    char[] c=s.toCharArray();
                    for(char j='a';j<='z';j++) {
                        c[i]=j;
                        String newS=new String(c);
                        if(wordSet.remove(newS)) {
                            reachedNext.add(newS);
                            if(endWord.equals(newS)) return level+1;
                        }
                    }
                }
            }
            reached=reachedNext;
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder w = new WordLadder();
        System.out.println(w.ladderLength("hot", "dog", Arrays.asList("hot","dog")));
//        System.out.println(w.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
    }
}
