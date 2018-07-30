package tree;

/**
 *
 * 1. 根节点是黑色
 * 2. 叶节点为NIL，是黑色
 * 3. 红色节点的两个子节点都是黑色
 * 4. 对每个节点，从该节点到其所有后代叶节点的简单路径上，均包含相同数目的黑色节点
 */
public class RBTree <T extends Comparable<T>>{

    private RBTNode<T> root;    // 根结点
    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    //节点
    public class RBTNode<T extends Comparable<T>>{
        boolean color;        // 颜色
        T key;                // 关键字(键值)
        RBTNode<T> left;    // 左孩子
        RBTNode<T> right;    // 右孩子
        RBTNode<T> parent;    // 父结点

        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey(){
            return this.key;
        }

        public String toString(){
            return ""+key+(this.color==RED?"(R)":"(B)");
        }
    }

    public RBTree(){
        this.root = null;
    }

    //////////////////////////// 基本操作 ///////////////////////////////////////////
    public RBTNode<T> parentOf(RBTNode<T> node){
        return node != null? node.parent : null;
    }

    public boolean colorOf(RBTNode<T> node){
        return node != null? node.color : BLACK;
    }

    public boolean isRed(RBTNode<T> node){
        return (node!=null)&&(node.color==RED);
    }

    public boolean isBlack(RBTNode<T> node){
        return !isRed(node);
    }

    public void setBlack(RBTNode<T> node){
        if(node != null){
            node.color = BLACK;
        }
    }

    public void setRed(RBTNode<T> node){
        if(node != null){
            node.color = RED;
        }
    }

    public void setParent(RBTNode<T> node, RBTNode<T> parent){
        if(node != null){
            node.parent = parent;
        }
    }

    public void setColor(RBTNode<T> node, boolean color){
        if(node != null){
            node.color = color;
        }
    }

    ///////////////////////////////// 遍历操作 ///////////////////////////////////////////////
    //前序遍历
    public void preOrder(){
        preOrder(this.root);
    }

    private void preOrder(RBTNode<T> t){
        if(t != null){
            System.out.print(t.toString() + " ");
            preOrder(t.left);
            preOrder(t.right);
        }
    }

    //中序遍历
    public void inOrder(){
        inOrder(this.root);
    }

    private void inOrder(RBTNode<T> t){
        if(t != null){
            inOrder(t.left);
            System.out.print(t.toString()  + " ");
            inOrder(t.right);
        }
    }

    //后序遍历
    public void postOrder(){
        postOrder(this.root);
    }

    private void postOrder(RBTNode<T> t){
        if(t != null){
            postOrder(t.left);
            postOrder(t.right);
            System.out.print(t.toString() + " ");
        }
    }

    ///////////找节点的后继/////////////////////////////

    public RBTNode<T> successor(RBTNode<T> x){
        if(x.right != null) {
            return minimum(x.right);
        }
        RBTNode<T> y = x.parent;
        while(y != null && x == y.right){
            x = y;
            y = y.parent;
        }
        return y;
    }

    //找最小的关键字
    public T minimum(){
        RBTNode<T> min = minimum(this.root);
        if(min == null){
            return null;
        } else {
            return min.getKey();
        }
    }

    private RBTNode<T> minimum(RBTNode<T> temp){
        if(temp == null) return null;
        while(temp.left != null){
            temp = temp.left;
        }
        return temp;
    }

    ////////////////找节点的前驱//////////////////////////////

    public RBTNode<T> predecessor(RBTNode<T> x){
        if(x.left != null) {
            return maximum(x.left);
        }
        RBTNode<T> y = x.parent;
        while(y != null && x == y.left){
            x = y;
            y = y.parent;
        }
        return y;
    }

    //找最大的关键字
    public T maximum(){
        RBTNode<T> max = maximum(this.root);
        if(max == null){
            return null;
        } else {
            return max.getKey();
        }
    }

    private RBTNode<T> maximum(RBTNode<T> temp){
        if(temp == null) return null;
        while(temp.right != null){
            temp = temp.right;
        }
        return temp;
    }

    ////////////////////////// 搜索关键字 ///////////////////////////////////
    //搜索
    public RBTNode<T> search(T key){
        return search(this.root, key);
    }

    private RBTNode<T> search(RBTNode<T> temp, T key){
        if(temp == null) {
            return null;
        }
        if(temp.getKey().compareTo(key) < 0){
            return search(temp.right, key);
        } else if(temp.getKey().compareTo(key) > 0){
            return search(temp.left, key);
        } else {
            return temp;
        }
    }

    ///////////////////////// 销毁树 //////////////////////////////////
    public void clear(){
        destroy(this.root);
        this.root = null;
    }

    private void destroy(RBTNode<T> node){
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

    private void print(RBTNode<T> node, T key, int direction){
        if(node != null){
            if(direction == 0){
                System.out.printf("%s is root\n", node.toString());
            } else {
                System.out.printf("%2d(%s) is %2d's %6s child\n", node.key, isRed(node)?"R":"B", key, direction==1?"right" : "left");
            }
            print(node.left, node.key, -1);
            print(node.right, node.key, 1);
        }
    }

    ///////////////////// 旋转操作 ////////////////////////////////////////////
    //左旋
    private void leftRotate(RBTNode<T> x){
        RBTNode<T> y = x.right;
        x.right = y.left;
        if(y.left != null){
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            this.root = y;
        } else {
            if(x.parent.left == x){
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;
    }

    //右旋
    private void rightRotate(RBTNode<T> x){
        RBTNode<T> y = x.left;
        x.left = y.right;
        if(y.right != null){
            y.right.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            this.root = y;
        } else {
            if(x.parent.right == x){
                x.parent.right = y;
            } else {
                x.parent.left = y;
            }
        }
        y.right = x;
        x.parent = y;
    }

    ///////////////////// 插入操作 ////////////////////////////////////////////
    public void insert(T key){
        RBTNode<T> node = new RBTNode<T>(key, BLACK,null, null, null);
        if(node != null){
            insert(node);
        }
    }

    public void insert(RBTNode<T> node){
        RBTNode<T> y = null;
        RBTNode<T> x = this.root;
        while(x != null){
            y = x;
            if(node.key.compareTo(x.key) < 0){
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if(y != null){
            if(node.key.compareTo(y.key) < 0){
                y.left = node;
            } else {
                y.right = node;
            }
        } else {
            this.root = node;
        }
        node.color = RED;
        insertFixUp(node);
    }

    public void insertFixUp(RBTNode<T> node){
        RBTNode<T> parent, gparent;

        // 若“父节点存在，并且父节点的颜色是红色”
        while((parent = parentOf(node))!=null && isRed(parent)){
            gparent = parentOf(parent);

            //若“父节点”是“祖父节点的左孩子”
            if(parent == gparent.left){
                // Case 1条件：叔叔节点是红色,把父节点和叔叔都变成黑色，祖父变成红色，
                // 再接着看祖父跟他祖父的爸爸是不是连着的两个红色
                RBTNode<T> uncle = gparent.right;
                if(uncle != null && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                } else {
                    // Case 2条件：叔叔是黑色，且当前节点是右孩子
                    //把两个连续红色旋转一下，变成和祖父在一条直线上，变成case3的情况
                    if(parent.right == node){
                        RBTNode<T> tmp;
                        leftRotate(parent);
                        tmp = parent;
                        parent = node;
                        node = tmp;
                    }
                    // Case 3条件：叔叔是黑色，且当前节点是左孩子。
                    //将上面一个红色节点（父节点）变成黑色，祖父节点变成红色，
                    // 再把父节点旋转上去，祖父节点转下来
                    setBlack(parent);
                    setRed(gparent);
                    rightRotate(gparent);
                    //父节点为黑色，循环终止
                }

            } else {
                //若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                RBTNode<T> uncle = gparent.left;
                if(uncle != null && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                } else {
                    // Case 2条件：叔叔是黑色，且当前节点是左孩子
                    if(parent.left == node){
                        RBTNode<T> tmp;
                        rightRotate(parent);
                        tmp = parent;
                        parent = node;
                        node = tmp;
                    }
                    // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                    setBlack(parent);
                    setRed(gparent);
                    leftRotate(gparent);
                    //父节点为黑色，循环终止
                }
            }
        }
        setBlack(this.root);
    }

    ///////////////////// 删除操作 ////////////////////////////////////////////

    //删除
    public void remove(T key){
        RBTNode<T> z = search(this.root, key);
        if(z != null){
            remove(z);
            if(z != null){
                z = null;
            }
        }
    }

    private void remove(RBTNode<T> node){
        RBTNode<T> child, parent; //用parent代替node，用child代替parent
        boolean originalColor; //parent的颜色
        parent = node;
        originalColor = parent.color;

        if (node.left == null) {
            child = node.right;
            transplant(node, node.right);
        } else if(node.right == null){
            child = node.left;
            transplant(node, node.left);
        } else {
            // 被删除节点的"左右孩子都不为空"的情况。
            parent = minimum(node.right);
            originalColor = parent.color;
            child = parent.right;
            //node的后继'parent'不是他的右孩子，需要将node的右子树做调整
            //调整成以'parent'为根的子树，过程中会以'parent'的右孩子'child'代替'parent'
            if(parent.parent != node){
                transplant(parent, parent.right);
                parent.right = node.right;
                parent.right.parent = parent;
            }
            transplant(node, parent);
            parent.left = node.left;
            parent.left.parent = parent;
            //node被parent替代,颜色不变，只需要考虑parent的颜色是否为黑；
            // parent被child代替，child相当于多了一层黑；若child本身为红只需要将child变黑；否则需要一层层看
            parent.color = node.color;
        }
        if(originalColor == BLACK){
            removeFixUp(child);
        }
    }

    //调整冲突
    private void removeFixUp(RBTNode<T> node){
        RBTNode<T> brother;
        RBTNode<T> parent = parentOf(node);

        //node == null要，因为书中假设叶节点为黑色，但是代码实现叶节点直接为null了
        while(node != this.root && (isBlack(node) || node == null)){
            if(parent.left == node){
                brother = parent.right;
                // Case 1: x的兄弟w是红色的，此时x的父节点一定是黑色
                if(isRed(brother)){
                    setBlack(brother);
                    setRed(parent);
                    leftRotate(parent);
                    brother = parent.right;
                }
                // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                if((brother.left == null || isBlack(brother.left))
                        && (brother.right == null || isBlack(brother.right))){
                    setRed(brother);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色
                    if((brother.right == null || isBlack(brother.right))){
                        setBlack(brother.left);
                        setRed(brother);
                        rightRotate(brother);
                        brother = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色
                    setColor(brother, parent.color);
                    setBlack(parent);
                    setBlack(brother.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }

            } else {
                brother = parent.left;
                // Case 1: x的兄弟w是红色的，此时x的父节点一定是黑色
                if(isRed(brother)){
                    setBlack(brother);
                    setRed(parent);
                    rightRotate(parent);
                    brother = parent.left;
                }
                // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                if((brother.left == null || isBlack(brother.left))
                        && (brother.right == null || isBlack(brother.right))){
                    setRed(brother);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    // Case 3: x的兄弟w是黑色的，并且w的左孩子不存在或为黑色
                    if((brother.left == null || isBlack(brother.left))){
                        setBlack(brother.right);
                        setRed(brother);
                        leftRotate(brother);
                        brother = parent.left;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的左孩子是红色的
                    setColor(brother, colorOf(parent));
                    setBlack(parent);
                    setBlack(brother.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }
        if(node != null){
            setBlack(node);
        }
    }


    //将一棵以v为根的子树替换一棵以u为根的子树
    private void transplant(RBTNode<T> u, RBTNode<T> v){
        if(u.parent == null){
            this.root = v;
        } else if(u == u.parent.left){
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if(v != null) {
            v.parent = u.parent;
        }
    }


    ////////////////////////// 测试 ////////////////////////////////////

    //main方法
    public static void main(String[] args) {

        int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
//        int a[] = {8,7,6,4,3,2,1};
        boolean mDebugInsert = false;    // "插入"动作的检测开关(false，关闭；true，打开)
        boolean mDebugDelete = false;    // "删除"动作的检测开关(false，关闭；true，打开)

        int i, ilen = a.length;
        RBTree<Integer> tree=new RBTree<Integer>();

        System.out.printf("== 原始数据: ");
        for(i=0; i<ilen; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        for(i=0; i<ilen; i++) {
            tree.insert(a[i]);
            // 设置mDebugInsert=true,测试"添加函数"
            if (mDebugInsert) {
                System.out.printf("== 添加节点: %d\n", a[i]);
                System.out.printf("== 树的详细信息: \n");
                tree.print();
                System.out.printf("\n");
            }
        }

        System.out.printf("== 前序遍历: ");
        tree.preOrder();

        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();

        System.out.printf("\n== 后序遍历: ");
        tree.postOrder();
        System.out.printf("\n");

        System.out.printf("== 最小值: %s\n", tree.minimum());
        System.out.printf("== 最大值: %s\n", tree.maximum());
        System.out.printf("== 树的详细信息: \n");
        tree.print();
        System.out.printf("\n");

        // 设置mDebugDelete=true,测试"删除函数"
        if (mDebugDelete) {
            for(i=0; i<ilen; i++) {
                tree.remove(a[i]);
                System.out.printf("== 删除节点: %d\n", a[i]);
                System.out.printf("== 树的详细信息: \n");
                tree.print();
                System.out.printf("\n");
            }
        }

        // 销毁二叉树
        tree.clear();
    }

}
