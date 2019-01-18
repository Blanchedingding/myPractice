package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 一群人，每个人用（h,k）表示,h是身高，k是这个人前面的高度大于等于他的人数，根据这些重构这个队列
 * Suppose you have a random list of people standing in a queue.
 * Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person
 * who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 *
 * Note: The number of people is less than 1,100.
 *
 * Example
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionbyHeight {

    public int[][] reconstructQueue(int[][] people) {
        int pn = people.length;
        int[][] r = new int[pn][2];
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] > o2[0]){
                    return -1;
                } else if(o1[0] < o2[0]){
                    return 1;
                } else if(o1[1] < o2[1]) {
                    return -1;
                } else if(o1[1] > o2[1]){
                    return 1;
                } else {
                    return 0;
                }
            }
        });
//        Arrays.stream(people).forEach(e -> System.out.println(Arrays.toString(e)));
        List<Integer> t = new ArrayList<>();
        for(int i = 0; i < pn; i++){
            t.add(people[i][1], i);
//            System.out.println(t.toString());
        }
        int a = 0;
        for(int e: t){
            r[a] = people[e];
            a++;
        }
        return r;
    }

    public static void main(String[] args) {
        QueueReconstructionbyHeight q = new QueueReconstructionbyHeight();
        int[][] i = q.reconstructQueue(new int[][] {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}});
        Arrays.stream(i).forEach(e -> System.out.println(Arrays.toString(e)));
    }
}
