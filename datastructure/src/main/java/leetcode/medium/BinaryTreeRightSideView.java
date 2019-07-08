package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if(root == null) return r;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        int pre = -1;
        while(!q.isEmpty()){
            TreeNode t = q.poll();
            if(t == null){
                r.add(pre);
                if(q.isEmpty())break;
                q.offer(null);
            } else {
                pre = t.val;
                if(t.left != null){
                    q.offer(t.left);
                }
                if(t.right != null){
                    q.offer(t.right);
                }
            }
        }
        return r;
    }
}
