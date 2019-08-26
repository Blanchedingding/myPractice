package exam.shixi.toutiaoshixi;

import java.util.*;

public class Mianshi {

    //用1/2/3/5元的钱找零钱N，问一共有多少种方法
    public static int lingqian(int n){
        int[] a = new int[]{1,2,3,5};
        int[][] dp = new int[a.length][n+1];//dp[i][j]表示i行代表的币种和小于其面值的币种有多少种方法组成j
        for(int i = 0; i <= n; i++){
            dp[0][i] = i % a[0] == 0? 1:0;//只用a[0]一种货币
        }
        for(int i = 0; i < a.length; i++){
            dp[i][0] = 1; //后面的货币全用自己的面值时也是一种解法
        }
        for(int i = 1; i < a.length; i++){
            for(int j = 1; j <= n; j++){
                int t = 0;
                for(int k = 0; k * a[i] <= j; k++){
                    t += dp[i - 1][j - k * a[i]];
                }
                dp[i][j] = t;
            }
        }
        return dp[a.length - 1][n];

    }

    // 找出无序数组中的最长连续序列的长度：
    // 例如数组[ 1 , 23 , 2 , 300 , 3 , 9 ,4 , 5 , 90 ]，
    // 最长连续序列为：1，2，3，4，5，因此返回长度为 5
    public static int changdu(int[] nums){
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for(int num : nums) set.add(num);
        for(int num : nums) {
            if (!set.contains(num-1)) {
                int val = num;
                while(set.remove(val++));
                max = Math.max(max, val-num-1);
            }
        }
        return max;
    }

    //给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    public static List<Integer> erchashu(Node root){
        if(root == null) return null;
        Queue<Node> list = new LinkedList<Node>();
        List<Integer> r = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        list.add(root);
        list.add(null);
        while(!list.isEmpty()){
            Node n = list.poll();
            if(n == null && !s.empty()){
                r.add(s.peek());
                s.clear();
                list.add(null);
            } else if(n != null){
                s.add(n.val);
                if(n.left != null){
                    list.add(n.left);
                }
                if(n.right != null){
                    list.add(n.right);
                }
            }
        }

        return r;
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        d.left = f;
        System.out.println(erchashu(a).toString());

        System.out.println(lingqian(5));
        System.out.println(changdu(new int[]{1 , 23 ,304, 2 ,299,301, 300 , 3 , 9 ,4 ,302, 303, 5 , 90}));
    }

    static class Node{
        int val;
        Node left;
        Node right;
        Node(int v){
            val = v;
        }
    }
}
