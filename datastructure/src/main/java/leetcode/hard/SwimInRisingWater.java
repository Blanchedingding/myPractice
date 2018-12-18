package leetcode.hard;

import java.util.*;

/**
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 *
 * Now rain starts to fall. At time t, the depth of the water everywhere is t.
 * You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
 * You can swim infinite distance in zero time.
 * Of course, you must stay within the boundaries of the grid during your swim.
 *
 * You start at the top left square (0, 0).
 * What is the least time until you can reach the bottom right square (N-1, N-1)?
 *
 * Example 1:
 * Input: [[0,2],[1,3]]
 * Output: 3
 *
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 *
 * Example 2:
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 *
 * Explanation:
 *  0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 *
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 * Note:
 *
 * 2 <= N <= 50.
 * grid[i][j] is a permutation of [0, ..., N*N - 1].
 */
public class SwimInRisingWater {

    class Node {
        public int val;
        public int x;
        public int y;
        Node(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        Comparator<Node> cmp = new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        };
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        PriorityQueue<Node> q = new PriorityQueue<Node>(n * m, cmp);
        Node node = new Node(grid[0][0], 0, 0);
        q.offer(node);
        visited[0][0] = 1;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.x == n - 1 && cur.y == m - 1) {
                return cur.val;
            }
            for(int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if(isInGrid(nx, ny, n, m) && visited[nx][ny] == 0) {
                    grid[nx][ny] = Math.max(grid[cur.x][cur.y], grid[nx][ny]);
                    q.offer(new Node(grid[nx][ny], nx, ny));
                    visited[nx][ny] = 1;
                }
            }
        }
        return 0;
    }

    public boolean isInGrid(int x, int y, int n, int m) {
        if(x >= 0 && x < n && y >= 0 && y < m) return true;
        return false;
    }

    public static void main(String[] args) {
        SwimInRisingWater s = new  SwimInRisingWater();
        int[][] g = new int[][]{
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}
        };
        int[][] g2 = new int[][]{
                {10,12,4,6},
                {9,11,3,5},
                {1,7,13,8},
                {2,0,15,14}
        };
        int[][] g3 = new int[][]{
                {3,2},
                {0,1}
        };
        System.out.println(s.swimInWater(g));
    }
}
