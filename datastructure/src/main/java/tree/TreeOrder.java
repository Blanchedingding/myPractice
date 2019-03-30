package tree;

import java.util.Stack;

public class TreeOrder {

    /**
     *      1
     *    /  \
     *   2    3
     *  /     /\
     * 4     6  7
     *  \   /
     *   5 8
     */
    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        Node h = new Node(8);
        a.left = b;
        a.right = c;
        b.left = d;
        d.right = e;
        f.left = h;
        c.left = f;
        c.right = g;
//        preOrder(a);
        inOrder(a);
//        postOrder(a);
    }

    //非递归实现前序遍历
    public static void preOrder(Node node){
        Stack<Node> s = new Stack<>();
        while(node != null || !s.empty()){
            while(node != null){
                System.out.println(node.val);
                s.push(node);
                node = node.left;
            }
            if(!s.empty()){
                node = s.pop();
                node = node.right;
            }
        }
    }

    //非递归实现中序遍历
    public static void inOrder(Node node){
        Stack<Node> s = new Stack<>();
        while(node != null || !s.empty()){
            while(node != null){
                s.push(node);
                node = node.left;
            }
            if(!s.empty()){
                node = s.pop();
                System.out.println(node.val);
                node = node.right;
            }
        }
    }

    //非递归实现后序遍历
    public static void postOrder(Node node){
        Stack<Node> s = new Stack<>();
        while(node != null || !s.empty()){
            while(node != null){
                s.push(node);
                s.push(null);
                node = node.left;
            }
            while(!s.empty() && s.peek() != null){
                Node t = s.pop();
                System.out.println(t.val);
            }
            if(!s.empty()){
                s.pop();//把null pop出来
                node = s.peek();//查看peek节点的右边
                node = node.right;
            }
        }
    }

    public static class Node{
        int val;
        Node left;
        Node right;
        public Node(int v){
            val = v;
        }
    }

}
