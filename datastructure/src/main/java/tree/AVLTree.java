package tree;

/**
 * 1.本身首先是一棵二叉搜索树。
 * 2.带有平衡条件：每个结点的左右子树的高度之差的绝对值（平衡因子）最多为1。
 * node节点增加height，不需要parent
 * 插入操作只需要执行O(1)次旋转
 */
public class AVLTree <T extends Comparable<T>>{

    private AVLTreeNode<T> root;    // 根结点

    public AVLTree(){
        this.root = null;
    }

    // AVL树的节点(内部类)
    class AVLTreeNode<T extends Comparable<T>> {
        T key;                // 关键字(键值)
        // !!!!高度!!!!树的高度为最大层次。即空的二叉树的高度是0，非空树的高度等于它的最大层次(根的层次为1，根的子节点为第2层，依次类推)
        int height;
        AVLTreeNode<T> left;    // 左孩子
        AVLTreeNode<T> right;    // 右孩子

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    /*
    * 获取树的高度
    */
    private int height(AVLTreeNode<T> tree) {
        if (tree != null)
            return tree.height;
        return 0;
    }

    public int height() {
        return height(this.root);
    }

    /*
    * 比较两个值的大小
    */
    private int max(int a, int b) {
        return a>b ? a : b;
    }

    ///////////////////////////////// 遍历操作 ///////////////////////////////////////////////
    //前序遍历
    public void preOrder(){
        preOrder(this.root);
    }

    private void preOrder(AVLTreeNode<T> t){
        if(t != null){
            System.out.print(t.key + " ");
            preOrder(t.left);
            preOrder(t.right);
        }
    }

    //中序遍历
    public void inOrder(){
        inOrder(this.root);
    }

    private void inOrder(AVLTreeNode<T> t){
        if(t != null){
            inOrder(t.left);
            System.out.print(t.key  + " ");
            inOrder(t.right);
        }
    }

    //后序遍历
    public void postOrder(){
        postOrder(this.root);
    }

    private void postOrder(AVLTreeNode<T> t){
        if(t != null){
            postOrder(t.left);
            postOrder(t.right);
            System.out.print(t.key + " ");
        }
    }

    ////////////////////////// 搜索关键字、 找最大和最小的关键字///////////////////////////////////
    //搜索
    public AVLTreeNode<T> search(T key){
        return search(this.root, key);
    }

    private AVLTreeNode<T> search(AVLTreeNode<T> temp, T key){
        if(temp == null) {
            return null;
        }
        if(temp.key.compareTo(key) < 0){
            return search(temp.right, key);
        } else if(temp.key.compareTo(key) > 0){
            return search(temp.left, key);
        } else {
            return temp;
        }
    }

    //找最小的关键字
    public T minimum(){
        AVLTreeNode<T> min = minimum(this.root);
        if(min == null){
            return null;
        } else {
            return min.key;
        }
    }

    private AVLTreeNode<T> minimum(AVLTreeNode<T> temp){
        if(temp == null) return null;
        while(temp.left != null){
            temp = temp.left;
        }
        return temp;
    }

    //找最大的关键字
    public T maximum(){
        AVLTreeNode<T> max = maximum(this.root);
        if(max == null){
            return null;
        } else {
            return max.key;
        }
    }

    private AVLTreeNode<T> maximum(AVLTreeNode<T> temp){
        if(temp == null) return null;
        while(temp.right != null){
            temp = temp.right;
        }
        return temp;
    }

    ///////////////////////// 销毁树 //////////////////////////////////
    public void clear(){
        destroy(this.root);
        this.root = null;
    }

    private void destroy(AVLTreeNode<T> node){
        if(node == null) {
            return;
        }
        if(node.left != null){
            destroy(node.left);
        }
        if(node.right != null){
            destroy(node.right);
        }
        node = null;
    }

    ///////////////////// 打印 ////////////////////////////////////////////
    public void print(){
        if(this.root != null){
            print(this.root, (T) this.root.key, 0);
        }
    }

    private void print(AVLTreeNode<T> node, T key, int direction){
        if(node != null){
            if(direction == 0){
                System.out.printf("%s is root\n", node.key);
            } else {
                System.out.printf("%s is %s's %s child\n", node.key, key, direction==1?"right" : "left");
            }
            print(node.left, node.key, -1);
            print(node.right, node.key, 1);
        }
    }

    ///////////////////////////// 旋转 ////////////////////////////////////////////
    /**
     * 如果在AVL树中进行插入或删除节点后，可能导致AVL树失去平衡。
     * 这种失去平衡的可以概括为4种姿态：LL(左左)，LR(左右)，RR(右右)和RL(右左)。
     */
    //LL不平衡，只需向右旋转一次
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> x){
        AVLTreeNode<T> y = x.left;
        x.left = y.right;
        y.right = x;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    //RR不平衡，只需向左旋转一次
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> x){
        AVLTreeNode<T> y = x.right;
        x.right = y.left;
        y.left = x;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    //LR不平衡，先对x.left左旋，再对x右旋
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> x){
        x.left = rightRightRotation(x.left);
        return leftLeftRotation(x);
    }

    //RL不平衡，先对x.right右旋，再对x左旋
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> x){
        x.right = leftLeftRotation(x.right);
        return rightRightRotation(x);
    }

    ////////////////////// 插入 /////////////////////////////////////
    public void insert(T key){
        this.root = insert(this.root, key);
    }

    private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key){
        if(tree == null){
            tree = new AVLTreeNode<T>(key, null, null);
        } else {
            // 应该将key插入到"tree的右子树"的情况
            if(tree.key.compareTo(key) < 0){
                tree.right = insert(tree.right, key);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if(height(tree.right) - height(tree.left) >= 2){
                    if(key.compareTo(tree.right.key) < 0){
                        tree = rightLeftRotation(tree);
                    } else {
                        tree = rightRightRotation(tree);
                    }
                }
            } else if(tree.key.compareTo(key) > 0){ // 应该将key插入到"tree的左子树"的情况
                tree.left = insert(tree.left, key);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if(height(tree.left) - height(tree.right) >= 2){
                    if(key.compareTo(tree.left.key) < 0){
                        tree = leftLeftRotation(tree);
                    } else {
                        tree = leftRightRotation(tree);
                    }
                }
            } else {
                System.out.println("添加失败：不允许添加相同的节点！");
            }
        }
        tree.height = max( height(tree.left), height(tree.right)) + 1;
        return tree;
    }

    //////////////////////// 删除 ////////////////////////////////////
    public void remove(T key){
        AVLTreeNode<T> z;
        if((z = search(key)) != null){
            this.root =  remove(this.root, z);
        }
    }

    private AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z){
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (tree==null || z==null) return null;

        // 待删除的节点在"tree的右子树"中
        if(tree.key.compareTo(z.key) < 0){
            tree.right = remove(tree.right, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.left) - height(tree.right) >= 2) {
                AVLTreeNode<T> l =  tree.left;
                if (height(l.right) > height(l.left)){
                    tree = leftRightRotation(tree);
                } else {
                    tree = leftLeftRotation(tree);
                }
            }
        } else if(tree.key.compareTo(z.key) > 0){ // 待删除的节点在"tree的左子树"中
            tree.left = remove(tree.left, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.right) - height(tree.left) >= 2) {
                AVLTreeNode<T> l =  tree.right;
                if (height(l.left) > height(l.right)){
                    tree = rightLeftRotation(tree);
                } else {
                    tree = rightRightRotation(tree);
                }
            }
        } else { // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((tree.left!=null) && (tree.right!=null)) {
                if (height(tree.left) > height(tree.right)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                    AVLTreeNode<T> max = maximum(tree.left);
                    tree.key = max.key;
                    tree.left = remove(tree.left, max);
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    AVLTreeNode<T> min = minimum(tree.right);
                    tree.key = min.key;
                    tree.right = remove(tree.right, min);
                }
            } else {
                AVLTreeNode<T> tmp = tree;
                tree = (tree.left!=null) ? tree.left : tree.right;
                tmp = null;
            }
        }
        if(tree!= null) tree.height = max( height(tree.left), height(tree.right)) + 1;
        return tree;
    }

    ///////////////////// 测试 /////////////////////////////////////

    public static void main(String[] args) {
        int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
        int i;
        AVLTree<Integer> tree = new AVLTree<Integer>();

        System.out.printf("== 依次添加: ");
        for(i=0; i<arr.length; i++) {
            System.out.printf("%d ", arr[i]);
            tree.insert(arr[i]);
        }

        System.out.printf("\n== 前序遍历: ");
        tree.preOrder();

        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();

        System.out.printf("\n== 后序遍历: ");
        tree.postOrder();
        System.out.printf("\n");

        System.out.printf("== 高度: %d\n", tree.height());
        System.out.printf("== 最小值: %d\n", tree.minimum());
        System.out.printf("== 最大值: %d\n", tree.maximum());
        System.out.printf("== 树的详细信息: \n");
        tree.print();

        i = 8;
        System.out.printf("\n== 删除根节点: %d", i);
        tree.remove(i);

        System.out.printf("\n== 高度: %d", tree.height());
        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();
        System.out.printf("\n== 树的详细信息: \n");
        tree.print();

        // 销毁二叉树
        tree.clear();
    }



}

