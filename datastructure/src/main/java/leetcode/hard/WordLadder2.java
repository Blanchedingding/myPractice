package leetcode.hard;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 * Note:
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 *
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder2 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if(!wordList.contains(endWord)) return result;
        Queue<List<String>> q = new LinkedList<>();
        q.offer(Arrays.asList(beginWord));
        while (! q.isEmpty()){
            List<List<String>> h = new LinkedList<>();
            Set<String> l = new HashSet<>();
            while(! q.isEmpty()){
                List<String> t = q.poll();
                for(String s:wordList){
                    if(canTrans(t.get(t.size()-1),s)){
                        l.add(s);
                        List<String> p = new ArrayList<>(t);
                        p.add(s);
                        h.add(p);
                        if(s.equals(endWord)) {
                            result.add(p);
                        }
                    }
                }
            }
            for(String k:l){
                wordList.remove(k);
            }
            if(result.size() > 0){
                break;
            }
            for(List<String> w: h){
                q.offer(w);
            }
        }
        return result;
    }

    public boolean canTrans(String a, String b){
        int n = a.length();
        int differentNum = 0;
        for(int i = 0; i < n; i++){
            if(a.charAt(i) != b.charAt(i)){
                differentNum ++;
            }
        }
        return differentNum == 1;
    }

    public static void main(String[] args) {
        WordLadder2 w = new WordLadder2();
        System.out.println(w.findLadders("hit", "cog", new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"))));
    }
}
