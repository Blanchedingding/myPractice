package swordOffer;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;


public class ArraySearch {

    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public boolean Find(int target, int [][] array) {
        int rowNum = array.length;
        int colNum = array[0].length;
        for(int i = 0; i < colNum; i++){
            for(int j = 0; j < rowNum; j++){
                if(array[i][j] == target) {
                    return true;
                } else if(array[i][j] > target){
                    break;
                }
            }
        }
        return false;
    }

    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll(" ", "%20");
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> r = new ArrayList<Integer>();
        while(null != listNode){
            r.add(0, listNode.val);
            listNode = listNode.next;
        }
        return r;
    }

    public class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     *输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre.length > 0){
            TreeNode t = new TreeNode(pre[0]);
            if(pre.length > 1){
                int index = -1;
                for(int i = 0; i < in.length; i++){
                    if(in[i] == pre[0]) {
                        index = i;
                        break;
                    }
                }
                int[] pre1 = new int[index];
                System.arraycopy(pre, 1, pre1, 0, index);
                int[] in1 = new int[index];
                System.arraycopy(in, 0, in1, 0, index);
                t.left = reConstructBinaryTree(pre1, in1);

                int[] pre2 = new int[pre.length - 1 - index];
                System.arraycopy(pre, index + 1, pre2, 0, pre.length - 1 - index);
                int[] in2 = new int[in.length - index - 1];
                System.arraycopy(in, index + 1, in2, 0, in.length - 1 - index);
                t.right = reConstructBinaryTree(pre2, in2);
            }
            return t;
        } else {
            return null;
        }

    }

    public void print(TreeNode t){
        System.out.println(t.val);;
        if(t.left != null) print(t.left);
        if(t.right != null) print(t.right);
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  //用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push1(int node) {
        stack1.push(node);
    }

    public int pop1() {
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        int r = stack2.pop();
        while (!stack2.empty()){
            stack1.push(stack2.pop());
        }
        return r;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     */
    public int minNumberInRotateArray(int [] array) {
        if(array.length <= 0) return 0;
        int min = array[0];
        int index = array.length - 1;
        while(min >= array[index]){
            min = array[index];
            index --;
        }
        return min;
    }

    //输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
    public int NumberOf1(int n) {
        int r = 0;
        for(int i = 1; i <= 32; i++){
            if((n & 1 )== 1) r ++;
            n = n >> 1;
        }
        return r;
    }

    //给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方
    public double Power(double base, int exponent) {
        double r = base;
        if(exponent > 0){
            for(int i = 2; i <= exponent; i++){
                r *= base;
            }
        } else if (exponent == 0){
            r = 1;
        } else {
            r = 1;
            for(int i = 1; i <= 0- exponent; i++){
                r /= base;
            }
        }
        return r;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
     * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     */
    public void reOrderArray(int [] array) {
        int n = array.length;
        int index = -1;
        for(int i = 0; i < n; i++){
          if(array[i] % 2 == 1){
              index ++;
              int temp = array[i];
              if(index < i){
                  for(int j = i; j > index; j--){
                      array[j] = array[j - 1];
                  }
                  array[index] = temp;
              }
          }
        }
    }

    //输入一个链表，反转链表后，输出新链表的表头。
    public ListNode ReverseList(ListNode head) {
        if(head == null) return null;
        ListNode pre = head, temp;
        head = head.next;
        if(head != null){
            pre.next = null;
        }
        while(head != null){
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    // 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null ) return list2;
        if(list2 == null ) return list1;
        ListNode l = new ListNode(0);
        ListNode r = l;
        while(list1!= null && list2 != null){
            if(list1.val <= list2.val){
                l.next = list1;
                list1 = list1.next;
            } else {
                l.next = list2;
                list2 = list2.next;
            }
            l = l.next;
        }
        if(list1 != null){
            l.next = list1;
        } else if(list2 != null){
            l.next = list2;
        }
        return r.next;
    }

    //输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
    //！！！并不是说是不是子树，只要有子结构就可以
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1 == null || root2 == null) return false;
        if(root1.val == root2.val){
            boolean r1 = true, r2 = true;
            if(root2.left != null) {
                r1 = HasSubtree(root1.left, root2.left);
            }
            if(r1 && root2.right!= null){
                r2 = HasSubtree(root1.right, root2.right);
            }
            if(r1 && r2) return true;
        }
        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    //操作给定的二叉树，将其变换为源二叉树的镜像。
    //输入描述:
    //二叉树的镜像定义：源二叉树
    //    	    8
    //    	   /  \
    //    	  6   10
    //    	 / \  / \
    //    	5  7 9 11
    //    	镜像二叉树
    //    	    8
    //    	   /  \
    //    	  10   6
    //    	 / \  / \
    //    	11 9 7  5
    public void Mirror(TreeNode root) {
        if(root != null){
            if(root.left!= null && root.left != null){
                TreeNode t = root.left;
                root.left = root.right;
                root.right = t;
                Mirror(root.left);
                Mirror(root.right);
            } else if(root.left != null){
                root.right = root.left;
                root.left = null;
                Mirror(root.right);
            } else if (root.right != null){
                root.left = root.right;
                root.right = null;
                Mirror(root.left);
            }
        }
    }

    //输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，
    // 如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
    // 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> r = new ArrayList<Integer>();
        int row = matrix.length, col = matrix[0].length;
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};//右，下，左，上
        int count = 0,x=0,y=0,u=0;//x对应row，y对应col
        int left = 0, right = col - 1, top = 0, bottom = row - 1;
        while(count < row * col){
            r.add(matrix[x][y]);
            count++;
            if(x+direction[u][0] < top || x+direction[u][0] > bottom ||
                    y + direction[u][1] <left || y+direction[u][1] > right){
                if(u == 0) top++;
                else if(u == 1) right--;
                else if(u == 2) bottom --;
                else left++;
                u = (u+1)%4;
            }
            x += direction[u][0];
            y += direction[u][1];
            System.out.println("u=" + u + " x=" + x + " y=" + y + " left="+left + " right="+right+" top="+top+" bottom="+bottom);
        }
        return r;
    }

    //定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
    int min = Integer.MAX_VALUE;
    ArrayList<Integer> r = new ArrayList<Integer>();
    public void push(int node) {
        r.add(node);
        if(node < min) min = node;
    }

    public void pop() {
        int t = r.get(r.size() - 1);
        r.remove((int)r.size()-1);
        if(t == min){
            min = Integer.MAX_VALUE;
            for(int i = 0; i < r.size(); i++){
                if(r.get(i) < min) min = r.get(i);
            }
        }
    }

    public int top() {
        return r.get(r.size() - 1);
    }

    public int min() {
        return min;
    }



    //输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
    // 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
    // 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
    // （注意：这两个序列的长度是相等的）
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        int n = pushA.length;
        if(n == 0) return false;
        Stack<Integer> s = new Stack<>();
        int j = 0;
        for(int i = 0; i < n ; i++){
            s.push(pushA[i]);
            while(!s.empty() && s.peek() == popA[j]){
                s.pop();
                j++;
            }
        }
        return s.empty();
    }

    public boolean IsPopOrder2(int [] pushA,int [] popA) {
        int n = pushA.length;
        int minIndex = -1;
        Map<Integer,Integer> p = new HashMap<>();
        boolean[] b = new boolean[n];
        for(int i = 0; i < n ; i ++){
            p.put(pushA[i], i);
        }
        for(int i = 0; i < n ; i ++){
            if(p.get(popA[i]) == null) return false;
            int t = p.get(popA[i]);
            if(t < minIndex) {
              for(int j = t+1; j <= minIndex;j++){
                  if(b[j] == false) return false;
              }
            } else {
                minIndex = t - 1;
            }
            b[t] = true;
        }
        return true;
    }

    //从上往下打印出二叉树的每个节点，同层节点从左至右打印。
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> r = new ArrayList<Integer>();
        if(root == null) return r;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode t = q.poll();
            r.add(t.val);
            if(t.left != null) q.offer(t.left);
            if(t.right != null) q.offer(t.right);
        }
        return r;
    }

    //输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
    // 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同
    //主要是最后一个元素需要把前面分成左右两队，左边小于最后一个元素，右边都大于最后一个元素
    public boolean VerifySquenceOfBST(int [] sequence) {
        int n = sequence.length;
        if(n ==0)return false;
        int root = sequence[n- 1];
        int index = 0;
        while(index < n && sequence[index] < root) {
            index ++;
        }
        int bigger = index;
        while(bigger < n){
            if(sequence[bigger] < root) return false;
            bigger++;
        }
        index --;
        boolean r1=true, r2 = true;
        if(index > 0){
            int[] left = new int[index];
            System.arraycopy(sequence, 0,  left, 0, index);
            r1 = VerifySquenceOfBST(left);
        }
        if(n - index - 2 > 0){
            int[] right = new int[n - index - 2];
            System.arraycopy(sequence, 0,  right, 0, n-index-2);
            r2 = VerifySquenceOfBST(right);
        }
        return r1 && r2;
    }

    //输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
    // 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    // (注意: 在返回值的list中，数组长度大的数组靠前)
    //两点需要注意：
    //1. 添加的list是复制的，传递给right的也是复制的
    //2. 是从根到叶节点的一整条路径
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> r = new ArrayList<ArrayList<Integer>>();
        if(root != null){
            helper(r, root, target, 0, new ArrayList<Integer>());
        }
        Collections.sort(r, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o2.size() - o1.size();
            }
        });
        return r;
    }

    public void helper(ArrayList<ArrayList<Integer>> r, TreeNode root, int target, int sum, ArrayList<Integer> list){
        if(root == null) return;
        if(root.val + sum == target && root.left == null && root.right == null){
            ArrayList<Integer> a = new ArrayList<>(list);
            a.add(root.val);
            r.add(a);
        } else if(root.val + sum < target){
            list.add(root.val);
            ArrayList<Integer> b = new ArrayList<>(list);
            sum += root.val;
            helper(r, root.left, target, sum, list);
            helper(r, root.right, target, sum, b);
        }
    }

    //输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
    // 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
    /*
            1、复制每个节点，如：复制节点A得到A1，将A1插入节点A后面
            2、遍历链表，A1->random = A->random->next;
            3、将链表拆分成原链表和复制后的链表
        */
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null) return null;
        RandomListNode cur = pHead;
        while(cur != null){
            RandomListNode a = new RandomListNode(cur.label);
            a.next = cur.next;
            cur.next = a;
            cur = a.next;
        }
        cur = pHead;
        while(cur != null){
            if(cur.random != null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        RandomListNode r = pHead.next;
        RandomListNode t;
        cur = pHead;
        while(cur.next != null){
            t = cur.next;
            cur.next = t.next;
            cur = t;
        }
        return r;
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    //输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
    // 要求不能创建任何新的结点，只能调整树中结点指针的指向。

    // 记录子树链表的最后一个节点，终结点只可能为只含左子树的非叶节点与叶节点
    protected TreeNode leftLast = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null) return null;
        if(pRootOfTree.left == null && pRootOfTree.right == null){
            leftLast = pRootOfTree;
            return pRootOfTree;
        }
        TreeNode l = Convert(pRootOfTree.left);
        if(l != null){
            leftLast.right = pRootOfTree;
            pRootOfTree.left = leftLast;
        }
        leftLast = pRootOfTree;// 当根节点只含左子树时，则该根节点为最后一个节点
        TreeNode r = Convert(pRootOfTree.right);
        if(r != null){
            pRootOfTree.right = r;
            r.left = pRootOfTree;
        }
        return l == null ? pRootOfTree: l;
    }

    //输入一个字符串,按字典序打印出该字符串中字符的所有排列。
    // 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
    //输入描述:
    //输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
    public ArrayList<String> Permutation(String str) {
        char[] c = str.toCharArray();
        ArrayList<String> r =new ArrayList<>();
        if(c.length > 0){
            boolean[] b = new boolean[c.length];
            StringBuilder s = new StringBuilder("");
            helper(r, c, s, b);
        }
        return r;
    }

    //注意要去重
    public void helper(ArrayList<String> r, char[] c, StringBuilder s, boolean[] b){
        if(s.toString().length() == c.length) {
            if(r.contains(s.toString())) return;
            r.add(s.toString());
        }
        for(int i = 0; i < c.length; i++ ){
            if(!b[i]){
                s.append(c[i]);
                b[i] = true;
                helper(r, c, s, b);
                s.deleteCharAt(s.length() - 1);
                b[i] = false;
            }
        }
    }

    //求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
    //解题思路：
    //1.需利用逻辑与的短路特性实现递归终止。 2.当n==0时，(n>0)&&((sum+=Sum_Solution(n-1))>0)只执行前面的判断，为false，然后直接返回0；
    //3.当n>0时，执行sum+=Sum_Solution(n-1)，实现递归计算Sum_Solution(n)。
    public int Sum_Solution(int n) {
        int s = n;
        boolean ans = (s>0) && ((s += Sum_Solution(n - 1)) > 0);
        return s;
    }

    //把只包含质因子2、3和5的数称作丑数（Ugly Number）。
    // 例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。
    // 求按从小到大的顺序的第N个丑数。
    public int GetUglyNumber_Solution(int index) {
        if (index < 7)return index;
        int[] res = new int[index];
        res[0] = 1;
        int t2 = 0, t3 = 0, t5 = 0, i;
        for (i = 1; i < index; ++i) {
            res[i] = Math.min(res[t2] * 2, Math.min(res[t3] * 3, res[t5] * 5));
            if (res[i] == res[t2] * 2)t2++;
            if (res[i] == res[t3] * 3)t3++;
            if (res[i] == res[t5] * 5)t5++;
        }
        return res[index - 1];
    }

    //数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
    // 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
    // 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length == 1) return array[0];
        Arrays.sort(array);
        int n = array.length;
        for(int i = 0; i < n/2; i ++){
            if(i + n/2 < n && array[i] == array[i + n/2]){
                return array[i];
            }
        }
        return 0;
    }

    //输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k <= 0)
            return res;

        //merge sort
        mergeSort(input, 0, input.length - 1);
        for(int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    public void mergeSort(int[] input, int l, int r){
        if(l >= r) return;
        int m = (r + l) / 2;
        mergeSort(input, l ,m);
        mergeSort(input, m+1, r);
        merge(input, l, m, r);
    }

    public void merge(int[] input, int l, int m, int r){
        int n1 = m - l +1;
        int n2 = r - m;
        int[] a1 = new int[n1 +1];
        int[] a2 = new int[n2 + 1];
        for(int i = l; i <= m; i++){
            a1[i - l] = input[i];
        }
        a1[n1] = Integer.MAX_VALUE;
        for(int i = m + 1; i <= r; i++){
            a2[i - m - 1] = input[i];
        }
        a2[n2] = Integer.MAX_VALUE;
        int index1 =0, index2 = 0;
        for(int i = l; i <= r; i++){
            if(a1[index1] <= a2[index2]){
                input[i] = a1[index1];
                index1++;
            } else {
                input[i] = a2[index2];
                index2++;
            }
        }
    }

    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(input == null || input.length == 0 || k > input.length || k <= 0)
            return res;

        //利用快排
        int l = 0, r = input.length - 1;
        int index = partition(input, l ,r);

        while(index != k - 1) {
            if(index > k - 1)
                index = partition(input, l, index - 1);
            else
                index = partition(input, index + 1, r);
        }

        for(int i = 0; i < k; i++) {
            res.add(input[i]);
        }

        return res;
    }

    private int partition(int[] input, int l, int r) {
        int pivotPosition =(int)( l + Math.random() * (r - l + 1));
        int pivot = input[pivotPosition];

        swap(input, pivotPosition, r);
        int smallIndex = l - 1;
        for(int i = l; i <= r; i++) {
            if(input[i] <= pivot) {
                smallIndex++;
                if(i > smallIndex)
                    swap(input, smallIndex, i);
            }
        }
        return smallIndex;
    }

    private void swap(int[] input, int index1, int index2) {
        int temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }

    //HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:
    // 在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
    // 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
    // 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
    // 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
    public int FindGreatestSumOfSubArray(int[] array) {
        int n = array.length;
        if(n == 0) return 0;
        return helper(array, 0, array.length - 1);
    }

    public int helper(int[] array, int l, int r){
        if(l == r) return array[l];
        int m = (l +r) / 2;
        int leftMax = helper(array, l, m);
        int rightMax = helper(array, m +1, r);
        int midMax = Integer.MIN_VALUE;
        int max1 = Integer.MIN_VALUE,max2 = Integer.MIN_VALUE;
        int s1 = 0, s2 = 0;
        for(int i = m; i >= 0; i--){
            s1 += array[i];
            max1 = Math.max(max1, s1);
        }
        for(int j = m +1; j <= r; j++){
            s2 += array[j];
            max2 = Math.max(max2, s2);
        }
        midMax = Math.max(max1 +max2 , Math.max(max1, max2));
        return Math.max(midMax, Math.max(leftMax, rightMax));
    }

    //求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
    // 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
    // ACMer希望你们帮帮他,
    // 并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
    public int NumberOf1Between1AndN_Solution(int n) {
        int high = n / 10;
        int low  = 1;
        int count = 0;
        int temp = n;
        while(temp > 0){
            int curBit = temp % 10;
            if(curBit > 1){
                count += (high + 1) * low;
            } else if(curBit == 1){
                count += high * low + (n - n / low * low) + 1;
            } else {
                count += high * low;
            }
            temp /= 10;
            low *= 10;
            high /= 10;
        }
        return count;
    }

    //输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
    // 打印能拼接出的所有数字中最小的一个。
    // 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
    //关键：两两比较时，如果一个元素是另一个的前缀，那么递归的比较这个元素和另一个元素的后缀
    public String PrintMinNumber(int [] numbers) {
        int n = numbers.length;
        ArrayList<String> s = new ArrayList<>();
        for(int i = 0; i < n; i++){
            s.add(String.valueOf(numbers[i]));
        }
        Collections.sort(s, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int n1 = o1.length();
                int n2 = o2.length();
                for(int i = 0; i < Math.min(n1, n2); i++){
                    if(o1.charAt(i) < o2.charAt(i)){
                        return -1;
                    } else if(o1.charAt(i) > o2.charAt(i)){
                        return 1;
                    }
                }
                if(n1 < n2){
                    return compare(o1, o2.substring(n1));
                } else if(n1 > n2){
                    return compare(o1.substring(n2), o2);
                }
                return 0;
            }
        });
//        System.out.println(s);
        StringBuilder r = new StringBuilder();
        for(String y : s){
           r.append(y);
        }
        return r.toString();
    }

    //在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
    // 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
    //注意：数据量过大，必须在每一步计算结果都取模
    public int InversePairs(int [] array) {
        int n = array.length;
        if(n == 0 || n == 1) return 0;
        return countInversion(array, 0, n-1);
    }

    public int countInversion(int[] array, int l, int r){
        if(l >= r) return 0;
        int count = 0, m = (l+r)/2;
        count += countInversion(array, l, m);
        count %= 1000000007;
        count += countInversion(array, m+1, r);
        count %= 1000000007;
        count += mergeCount(array, l, m, r);
        return count % 1000000007;
    }

    public int mergeCount(int[] array, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] b1 = new int[n1+1];
        for(int i = 0; i < n1; i++){
            b1[i] = array[i + l];
        }
        b1[n1] = Integer.MAX_VALUE;

        int[] b2 = new int[n2+1];
        for(int i = 0; i < n2; i++){
            b2[i] = array[i + m + 1];
        }
        b2[n2] = Integer.MAX_VALUE;

        int p1 = 0, p2 = 0;
        int count = 0;
        for(int i = l; i <= r; i++){
            if(b1[p1] <= b2[p2]){
                array[i] = b1[p1];
                p1++;
            } else {
                if(p1 < n1){
                    count += n1 - p1;
                    count %= 1000000007;
                }
                array[i] = b2[p2];
                p2 ++;
            }
        }
        return count;
    }

    //在一个字符串(0<=字符串长度<=10000，全部由字母组成)中
    // 找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
    public int FirstNotRepeatingChar(String str) {
        int n = str.length();
        if(n == 0) return -1;
        int[] v = new int[52];
        for(int i = 0; i < n; i ++){
            if(str.charAt(i) >= 'a'){
                v[str.charAt(i) - 'a' + 26] ++;
            } else {
                v[str.charAt(i) - 'A'] ++;
            }
        }
        for(int i = 0; i < n; i++){
            if(str.charAt(i) >= 'a' && v[str.charAt(i) - 'a' + 26] == 1){
                return i;
            } else if(str.charAt(i) < 'a' && str.charAt(i) >= 'A' && v[str.charAt(i) - 'A'] == 1){
                return i;
            }
        }
        return -1;
    }

    //输入两个链表，找出它们的第一个公共结点。
    //找出2个链表的长度，然后让长的先走两个链表的长度差，然后再一起走
    //（因为2个链表用公共的尾部）
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int len1 = findListLenth(pHead1);
        int len2 = findListLenth(pHead2);
        if(len1 > len2){
            pHead1 = walkStep(pHead1,len1 - len2);
        }else{
            pHead2 = walkStep(pHead2,len2 - len1);
        }
        while(pHead1 != null){
            if(pHead1 == pHead2) return pHead1;
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return null;
    }


    int findListLenth(ListNode pHead1){
        if(pHead1 == null) return 0;
        int sum = 1;
        while((pHead1 = pHead1.next) != null) sum++;
        return sum;
    }

    ListNode walkStep(ListNode pHead1, int step){
        while(step > 0){
            pHead1 = pHead1.next;
            step--;
        }
        return pHead1;
    }

    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        ListNode r= null;
        ListNode l1 = pHead1, l2 = pHead2;
        while(l2 != null){
            if(l1 == l2) return l1;
            else if(l1 == null){
                l2 = l2.next;
                l1 = pHead1;
            } else{
                l1 = l1.next;
            }
        }
        return r;
    }

    //统计一个数字在排序数组中出现的次数
    ////因为data中都是整数，所以可以稍微变一下，不是搜索k的两个位置，而是搜索k-0.5和k+0.5
    ////这两个数应该插入的位置，然后相减即可。
    public int GetNumberOfK(int [] array , int k) {
        return biSearch(array, k + 0.5) - biSearch(array, k - 0.5);
    }

    int biSearch(int[] data, double num){
        int s = 0, e = data.length -1;
        while(s <= e){
            int mid = (e - s)/2 + s;
            if(data[mid] < num)
                s = mid + 1;
            else if(data[mid] > num)
                e = mid - 1;
        }
        return s;
    }

    public int GetNumberOfK2(int [] array , int k) {
        Arrays.sort(array);
        int c = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == k){
                while(i < array.length && array[i] == k){
                    c ++;
                    i++;
                }
                return c;
            }
        }
        return 0;
    }

    //输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
    int max = 0;
    public int TreeDepth(TreeNode root) {
        depth(root, 0);
        return max;
    }

    public void depth(TreeNode root, int n){
        if(root == null) {
            max = Math.max(max, n);
            return;
        }
        depth(root.left, n+1);
        depth(root.right, n+1);
    }

    //输入一棵二叉树，判断该二叉树是否是平衡二叉树。
    public boolean IsBalanced_Solution(TreeNode root) {
        return getDepthAndJudgeBlance(root) != -1;
    }

    public int getDepthAndJudgeBlance(TreeNode node){
        if(node == null) {
            return 0;
        }
        int leftDepth = getDepthAndJudgeBlance(node.left);
        if(leftDepth == -1) return -1;
        int rightDepth = getDepthAndJudgeBlance(node.right);
        if(rightDepth == -1) return -1;
        return Math.abs(leftDepth - rightDepth) > 1? -1: 1+ Math.max(leftDepth, rightDepth);
    }

    //一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
    //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
    //
    //可以用位运算实现，如果将所有所有数字相异或，则最后的结果肯定是那两个只出现一次的数字异或
    // 的结果，所以根据异或的结果1所在的最低位，把数字分成两半，每一半里都还有只出现一次的数据和成对出现的数据
    // 这样继续对每一半相异或则可以分别求出两个只出现一次的数字
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int n = array.length;
        int temp = 0;
        for(int k: array){
            temp ^= k;
        }
        if(temp == 0) return;
        int index = 0;
        while((temp&1)==0){
            temp=temp>>1;
            ++index;
        }
        num1[0] = 0;
        num2[0] = 0;
        for(int i=0;i<n;i++) {
            if(IsBit(array[i],index))
                num1[0] ^= array[i];
            else
                num2[0] ^= array[i];
        }
    }

    boolean IsBit(int num,int index) {
        num=num>>index;
        return ((num & 1) != 0);
    }

    public void FindNumsAppearOnce2(int[] array, int num1[], int num2[]) {
        int n = array.length;
        Map<Integer, Integer> p = new HashMap<>();
        for(int i = 0; i < n; i++){
           if(p.get(array[i]) == null){
               p.put(array[i], 1);
           } else {
               int j = p.get(array[i]);
               p.put(array[i], j+1 );
           }
        }
        System.out.println(p);
        num1[0] = Integer.MAX_VALUE;
        num2[0] = Integer.MAX_VALUE;
        for(Integer key: p.keySet()){
            System.out.println(key + "  " + p.get(key));
            if(p.get(key) == 1 && num1[0] == Integer.MAX_VALUE){
                num1[0] = key;
            }else if(p.get(key) == 1){
                num2[0] = key;
            }
        }
    }

    //小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
    // 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
    // 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
    // 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
    //输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer> > r = new ArrayList<ArrayList<Integer> >();
        ArrayList<Integer> a = new ArrayList<Integer>();
        int c = 0;
        for(int i = 1; i <= sum / 2 + 1; i++){
            a.add(i);
            c += i;
//            System.out.println("i=" + i +" sum=" + c);
            if(c == sum){
                if(a.size() > 1){
                    r.add(new ArrayList<>(a));
                }
                c -= a.get(0);
                a.remove(0);
            } else if(c > sum){
                while(c > sum){
                    c -= a.get(0);
                    a.remove(0);
                }
                if(c == sum){
                    r.add(new ArrayList<>(a));
                    c -= a.get(0);
                    a.remove(0);
                }
            }
        }
        return r;
    }

    //输入一个递增排序的数组和一个数字S，
    // 在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
    //对应每个测试案例，输出两个数，小的先输出
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> r = new ArrayList<Integer>();
        int high = array.length - 1;
        int low = 0;
        while(low < high){
            if(array[low] + array[high] == sum){
                //实际上找到的第一组就是乘积最小的！！！
                r.add(array[low]);
                r.add(array[high]);
                break;
            } else if(array[low] + array[high] > sum){
                high --;
            } else {
                low ++;
            }
        }
        return r;
    }

    //汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
    // 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
    // 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
    public String LeftRotateString(String str,int n) {
        int length = str.length();
        if(length <= 0) return "";
        int leftNum = n % length;
        return str.substring(leftNum) + str.substring(0, leftNum);
    }

    //牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
    // 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
    // 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
    // 正确的句子应该是“I am a student.”。
    // Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
    public String ReverseSentence(String str) {
        String[] s = str.split(" ");
        if(s.length <= 0) return str;
        StringBuilder r = new StringBuilder();
        for(int i = s.length - 1; i >0; i--){
            r.append(s[i] + " ");
        }
        r.append(s[0]);
        return r.toString();
    }

    //LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
    // 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
    // “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,
    // 他想了想,决定大\小王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
    // 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
    // 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
    // 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
    public boolean isContinuous(int [] numbers) {
        int n = numbers.length;
        if(n < 5) return false;
        Arrays.sort(numbers);
        int s = 0, pre = Integer.MIN_VALUE;
        for(int i = 0; i < n ;i ++){
            if(numbers[i] == 0) s++;
            else if(pre == Integer.MIN_VALUE){
                pre = numbers[i];
            }
            else {
                if(pre == numbers[i]) return false;
                s -= numbers[i] - pre - 1;
                pre = numbers[i];
            }
        }
        return s >= 0;
    }

    //有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
    // 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,
    // 从他的下一个小朋友开始,继续0...m-1报数....这样下去....
    // 直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
    // 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
    public int LastRemaining_Solution(int n, int m) {
        if(n==0)
            return -1;
        if(n==1)
            return 0;
        else
            return (LastRemaining_Solution(n-1,m)+m)%n;
    }

    public int LastRemaining_Solution2(int n, int m) {
        boolean[] v = new boolean[n];
        int s = n, t = 0, c = 0;
        while(s > 1){
           if(t == m-1){
               v[c] = true;
               s --;
               c = (c+1)%n;
               while(v[c]) c = (c+1)%n;
               t = 0;
           } else {
               t ++;
               c = (c+1)%n;
               while(v[c]) c = (c+1)%n;
           }
        }
        for(int i = 0; i < n; i++){
            if(!v[i]) return i;
        }
        return -1;
    }

    //写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
    public int Add(int num1,int num2) {
        while (num2!=0) {
            int temp = num1^num2;
            num2 = (num1&num2)<<1;
            num1 = temp;
        }
        return num1;
    }

    //将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
    // 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
    //输入描述:
    //输入一个字符串,包括数字字母符号,可以为空
    //输出描述:
    //如果是合法的数值表达则返回该数字，否则返回0
    public int StrToInt(String str) {
        int n = str.length();
        if(n <= 0) return 0;
        int r = 0;
        boolean minus = false;
        for(int i = 0; i < n; i++){
            char p = str.charAt(i);
            if(i == 0){
                if(p == '-'){
                    minus = true;
                } else if(p == '+'){

                } else if(p >= '0' && p <= '9' ) {
                    r = r * 10 + (p - '0');
                } else {
                    return 0;
                }
            } else {
                if(p >= '0' && p <= '9' ){
                    r = r * 10 + (p - '0');
                } else {
                    return 0;
                }
            }
        }
        if(minus){
            r = 0 - r;
        }
        return r;
    }

    //在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
    // 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
    // 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if(length <= 0) return false;
        Set<Integer> s = new HashSet<>();
        for(int n: numbers){
            if(s.contains(n)) {
                duplication[0] = n;
                return true;
            } else {
                s.add(n);
            }
        }
        return false;
    }

    //给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
    // 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] r = new int[n];
        int[] t = new int[n];
        t[0] = 1;
        for(int i = 1; i < n; i++){
            t[i] = t[i-1] * A[i - 1];
        }
        int p = 1;
        for(int i = n-1; i >= 0; i--){
            r[i] = t[i] * p;
            p *= A[i];
        }
        return r;
    }

    public int[] multiply2(int[] A) {
        int n = A.length;
        int[] r = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n-1] = 1;
        for(int i = 0; i < n - 1; i++){
            left[i + 1] = left[i] * A[i];
            right[n - 2 - i] = right[n - i -1] * A[n - 1 - i];
        }
        for(int i = 0; i < n ; i++){
            r[i] = left[i] * right[i];
        }
        return r;
    }

    //请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
    // 而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
    // 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return matchHelper(str,0, pattern, 0);
    }

    public boolean matchHelper(char[] str, int index1, char[] pattern, int index2){
        if(index1 == str.length && index2 == pattern.length) return true;
        if(index1 != str.length && index2 == pattern.length) return false;
        if(index2 + 1 < pattern.length && pattern[index2+1] == '*'){
            if((index1 < str.length && str[index1] == pattern[index2])
                    || (index1 < str.length &&pattern[index2] == '.')){
                return matchHelper(str, index1, pattern, index2 + 2)
                        || matchHelper(str, index1 + 1, pattern, index2);
            } else {
                return matchHelper(str, index1, pattern, index2 +2);
            }
        } else {
            if((index1 < str.length && str[index1] == pattern[index2])
                    || (index1 < str.length && pattern[index2] == '.')){
                return matchHelper(str, index1+1, pattern, index2+1);
            } else {
                return false;
            }
        }
    }

    //请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
    // 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
    // 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
    public boolean isNumeric(char[] str) {
        String string = String.valueOf(str);
        return string.matches("[+-]?[0-9]*(\\.[0-9]+)?([eE][+-]?[0-9]+)?");
//        "[+-]?[0-9]*(.[0-9]*)?([eE][+-]?[0-9]+)?"
    }

    public boolean isNumeric2(char[] str) {
        int n = str.length;
        boolean p=false, e = false, sign = false;
        for(int i = 0; i < n; i++){
            char c = str[i];
            if(c == '+' || c == '-'){
                if(sign && str[i - 1] != 'e' && str[i - 1] != 'E')return false;
                if(i != 0  && sign == false && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
                sign = true;
            } else if(c == '.'){// e后面不能接小数点，小数点不能出现两次
                if(e || p) return false;
                p = true;
            } else if(c == 'e' || c == 'E'){
                if(i == n - 1) return false;
                if(e == true) return false;
                e = true;
            }
            else if(c > '9' || c < '0'){
                return false;
            }
        }
        return true;
    }

    //请实现一个函数用来找出字符流中第一个只出现一次的字符。
    // 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
    // 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
    //如果当前字符流没有存在出现一次的字符，返回#字符。

    //Insert one char from stringstream
    String s="";
    char[] hash= new char[256];
    void Insert(char ch) {
        s+=ch;
        hash[ch]++;
    }
    //return the first appearence once char in current stringstream
    char FirstAppearingOnce() {
        int size=s.length();
        for(int i=0;i<size;++i) {
            if(hash[s.charAt(i)]==1)
                return s.charAt(i);
        }
        return '#';
    }

    //给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
    //找到环的节点数n，再一个指针先走n步，另一个指针跟着，两指针相遇即为入口节点
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode meetingNode=meetingNode(pHead);
        if(meetingNode==null)
            return null;
//      得到环中的节点个数
        int nodesInLoop=1;
        ListNode p1=meetingNode;
        while(p1.next!=meetingNode){
            p1=p1.next;
            ++nodesInLoop;
        }
//      移动p1
        p1=pHead;
        for(int i=0;i<nodesInLoop;i++){
            p1=p1.next;
        }
//      移动p1，p2
        ListNode p2=pHead;
        while(p1!=p2){
            p1=p1.next;
            p2=p2.next;
        }
        return p1;
    }

    //找到一快一满指针相遇处的节点，相遇的节点一定是在环中
    public static ListNode meetingNode(ListNode head) {
        if(head==null) return null;
        ListNode slow = head.next;
        if(slow==null) return null;

        ListNode fast = slow.next;
        while (slow != null && fast != null) {
            if(slow==fast){
                return fast;
            }
            slow=slow.next;
            fast=fast.next;
            if(fast!=slow){
                fast=fast.next;
            }
        }
        return null;
    }

    //在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
    // 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode cur = pHead, pre = null, next = null;
        while(cur != null ){
            next = cur.next;
            boolean repeat = false;
            while(next != null && cur.val == next.val){
                repeat = true;
                next = next.next;
            }
            if(repeat){
                if(pre == null){
                    pHead = next;
                } else {
                    pre.next = next;
                }
            } else {
                pre = cur;
            }
            cur = next;
        }
        return pHead;
    }


    //给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
    // 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if(pNode == null) return null;
        if(pNode.right != null){
            TreeLinkNode t = pNode.right;
            while(t.left != null){
                t = t.left;
            }
             return t;
        } else {
            TreeLinkNode p = pNode.next;
            while(p!= null && p.right == pNode){
                pNode = p;
                p = pNode.next;
            }
            return p;
        }
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    //请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
    //结构和对应数值都要相同才是镜像的
    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null) return true;
        if(pRoot.left == null && pRoot.right == null) return true;
        if(pRoot.left == null || pRoot.right == null) return false;
        return isSymmetricalHelper(pRoot.left, pRoot.right);
    }

    boolean isSymmetricalHelper(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return isSymmetricalHelper(left.right, right.left) && isSymmetricalHelper(left.left, right.right);
    }

    //请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
    // 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > r = new ArrayList<ArrayList<Integer> >();
        if (pRoot == null) return r;
        Queue q = new LinkedList();
        q.offer(pRoot);
        q.offer(null);
        ArrayList<Integer> a = new ArrayList<Integer>();
        boolean isFromLeft = true;
        while(!q.isEmpty()){
            Object o = q.poll();
            if(o == null){
                //要先把当前字符串加入list中
                r.add(new ArrayList<>(a));
                //如果后面没有了，就直接break
                if(q.isEmpty()) break;
                q.offer(null);
                a.clear();
                if(isFromLeft){
                    isFromLeft = false;
                } else {
                    isFromLeft = true;
                }
            } else {
                TreeNode t = (TreeNode)o;
                if(isFromLeft){
                    a.add(t.val);
                } else {
                    a.add(0, t.val);
                }
                if(t.left != null) q.offer(t.left);
                if(t.right != null) q.offer(t.right);
            }
        }
        return r;
    }

    //从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
    ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > r = new ArrayList<ArrayList<Integer> >();
        if (pRoot == null) return r;
        Queue q = new LinkedList();
        q.offer(pRoot);
        q.offer(null);
        ArrayList<Integer> a = new ArrayList<Integer>();
        while(!q.isEmpty()){
            Object o = q.poll();
            if(o == null){
                //要先把当前字符串加入list中
                r.add(new ArrayList<>(a));
                //如果后面没有了，就直接break
                if(q.isEmpty()) break;
                q.offer(null);
                a.clear();
            } else {
                TreeNode t = (TreeNode)o;
                a.add(t.val);
                if(t.left != null) q.offer(t.left);
                if(t.right != null) q.offer(t.right);
            }
        }
        return r;
    }

    //请实现两个函数，分别用来序列化和反序列化二叉树
    String Serialize(TreeNode root) {
        StringBuilder s = new StringBuilder();
        preSerialize(root,s);
        return s.toString();
    }

    void preSerialize(TreeNode node, StringBuilder s){
        if(node == null) {
            s.append("#,");
            return;
        } else {
            s.append(node.val + ",");
            preSerialize(node.left, s);
            preSerialize(node.right, s);
        }
    }

    TreeNode Deserialize(String str) {
        return DeserializeHelper(str.split(","));
    }

    int index = -1;
    TreeNode DeserializeHelper(String[] p) {
        index ++;
        TreeNode t = null;
        if(index < p.length && !"#".equals(p[index])){
            t = new TreeNode(Integer.valueOf(p[index]));
            t.left = DeserializeHelper(p);
            t.right = DeserializeHelper(p);
        }
        return t;
    }

    //给定一棵二叉搜索树，请找出其中的第k小的结点。
    // 例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
    ArrayList<Integer> sb = new ArrayList<>();
    TreeNode KthNode(TreeNode pRoot, int k) {
        if(pRoot == null) return null;
        TreeNode t1 = KthNode(pRoot.left, k);
        if(t1 != null) return t1;
        sb.add(pRoot.val);
        if(sb.size() == k) return pRoot;
        TreeNode t2 = KthNode(pRoot.right, k);
        if(t2 != null) return t2;
        return null;
    }

    //如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
    // 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
    // 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
    ArrayList<Integer> a = new ArrayList<>();
    public void Insert(Integer num) {
        a.add(num);
        Collections.sort(a);
    }

    public Double GetMedian() {
        if(a.size() % 2 == 1){
            return new Double(a.get(a.size() /2));
        } else {
            double d1 = a.get(a.size()/2 -1);
            double d2 = a.get(a.size() /2);
            double d3 = (d1+d2)/2;
            return new Double(d3);
        }
    }

    //给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
    // 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
    // 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
    // {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        int n = num.length;
        ArrayList<Integer> r = new ArrayList<Integer>();
        if(n < size || size == 0) return r;
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            while(!dq.isEmpty() && num[dq.peekLast()] <= num[i]){
                dq.pollLast();
            }
            while(!dq.isEmpty() && dq.peekFirst() <= i - size){
                dq.pollFirst();
            }
            dq.offerLast(i);
            if(i >= size - 1){
                r.add(num[dq.peekFirst()]);
            }
        }
        return r;
    }


    public ArrayList<Integer> maxInWindows2(int [] num, int size) {
        int n = num.length;
        ArrayList<Integer> r = new ArrayList<Integer>();
        if(n < size || size == 0) return r;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < size; i++){
            if(num[i] > max){
                max = num[i];
            }
        }
        r.add(max);
        for(int i = size; i < n; i++){
            if(num[i - size] == max){
                if(num[i] > max){
                    max = num[i];
                    r.add(max);
                } else {
                    max = Integer.MIN_VALUE;
                    for(int j = i - size + 1; j <= i; j++){
                        if(num[j] > max){
                            max = num[j];
                        }
                    }
                    r.add(max);
                }
            } else {
                if(num[i] > max){
                    max = num[i];
                }
                r.add(max);
            }
        }
        return r;
    }

    //请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
    // 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
    // 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
    // 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
    // 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        boolean[] visited = new boolean[matrix.length];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(hasPathHelper(matrix, rows, cols, i, j, str, 0, visited))
                    return true;
            }
        }
        return false;
    }

    public boolean hasPathHelper(char[] matrix, int rows, int cols, int i, int j, char[] str, int index, boolean[] visited){
        int k = i * cols + j;
        if(i < 0 || i >= rows || j < 0 || j >= cols || matrix[k] != str[index] || visited[k] == true) {
            return false;
        }
        if(index == str.length - 1) return true;
        visited[k] = true;
        if(hasPathHelper(matrix, rows, cols, i + 1, j, str, index + 1, visited) ||
                hasPathHelper(matrix, rows, cols, i, j + 1, str, index + 1, visited) ||
                hasPathHelper(matrix, rows, cols, i - 1, j, str, index + 1, visited) ||
                hasPathHelper(matrix, rows, cols, i, j - 1, str, index + 1, visited)){
            return true;
        }
        visited[k] = false;
        return false;
    }

    //地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
    // 但是不能进入行坐标和列坐标的数位之和大于k的格子。
    // 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
    // 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        return movingCountHelper(threshold, rows, cols, visited, 0, 0);
    }

    public int movingCountHelper(int threshold, int rows, int cols, boolean[][] visited, int i, int j) {
        if(i < 0 || i >= rows || j < 0 || j >= cols
                || visited[i][j] == true || countBitSum(i) + countBitSum(j) > threshold){
            return 0;
        }
        visited[i][j] = true;
        return 1 + movingCountHelper(threshold, rows, cols, visited, i + 1, j)
                + movingCountHelper(threshold, rows, cols, visited, i - 1, j)
                + movingCountHelper(threshold, rows, cols, visited, i, j + 1)
                + movingCountHelper(threshold, rows, cols, visited, i, j - 1);
    }

    public int countBitSum(int i){
        int sum = 0;
        while(i > 0){
            sum += i % 10;
            i /= 10;
        }
        return sum;
    }




    public static void main(String[] args) {
        ArraySearch a = new ArraySearch();
        System.out.println(a.maxInWindows(new int[]{2,3,4,2,6,2,5,1}, 3));
//        System.out.println(a.isNumeric(new char[]{'-','1','E'}));
//        System.out.println(a.match(new char[]{}, new char[]{}));
//        System.out.println(a.match(new char[]{'a','a','a'}, new char[]{'a','b','*','a'}));
//        Arrays.stream(a.multiply(new int[]{1,2,3})).forEach(System.out::println);
//        System.out.println(a.StrToInt("+2147483647"));
//        System.out.println(a.StrToInt("    1a33"));
//        System.out.println(a.LastRemaining_Solution(5,3));
//        System.out.println(a.isContinuous(new int[]{1,3,2,4,5}));
//        System.out.println(a.LeftRotateString("abcXYZdef",3));
//        System.out.println(a.FindNumbersWithSum(new int[]{1,2,5,8,11}, 10));
//        System.out.println(a.FindContinuousSequence(100));
//        a.FindNumsAppearOnce(new int[]{4,6,2,2,3,3,8,8,9,9,1000000,1000000}, new int[]{0}, new int[]{0});
//        System.out.println((int)'a' + " " + (int)'A');
//        System.out.println(a.FirstNotRepeatingChar("aberkidwdaebb"));
//        System.out.println(a.InversePairs(new int[]{2,3,8,6,1}));
//        System.out.println(a.PrintMinNumber(new int[]{3334,3,3333332}));
//        System.out.println(a.NumberOf1Between1AndN_Solution(13));
//        System.out.println(a.MoreThanHalfNum_Solution(new int[]{1}));
//        System.out.println(a.GetUglyNumber_Solution(10));
//        ArrayList<String> s = a.Permutation("aa");
//        s.stream().forEach(System.out::println);
//        System.out.println(a.VerifySquenceOfBST(new int[]{4,8,6,12,16,14,10}));
//        System.out.println(a.VerifySquenceOfBST(new int[]{4,6,12,8,16,14,10}));
//        System.out.println(a.IsPopOrder(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2}));
//        System.out.println(a.IsPopOrder(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));
//        System.out.println(a.IsPopOrder(new int[]{1}, new int[]{2}));
    }


}
