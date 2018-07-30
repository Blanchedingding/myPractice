package tree;

/**
 * 一棵高度为h的二叉搜索树，search/insert/delete/predecessor/successor/minimum/maximum操作的时间复杂度均为O(h)
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {

    ////////////////////node节点类/////////////////////////////////////////////
    public class BSNode<T extends Comparable<T>>{
        T key;
        BSNode left;
        BSNode right;
        BSNode parent;

        public BSNode(T key){
            this.key = key;
        }

        public T getKey(){
            return key;
        }

        @Override
        public String toString(){
            return this.key + "";
        }
    }
    ///////////////////////////////////////////////////////////////////////////

    BSNode root;

    public BinarySearchTree(){
        root = null;
    }

    //插入关键字
    public void insert(T z){
        BSNode<T> temp = this.root;
        BSNode<T> tempParent = null;
        while(temp != null){
            tempParent = temp;
            if(temp.getKey().compareTo(z) < 0){
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        BSNode<T> znode = new BSNode<T>(z);
        if(tempParent == null){
            this.root = znode;
        } else{
            znode.parent = tempParent;
            if(tempParent.getKey().compareTo(z) < 0){
                tempParent.right = znode;
            } else {
                tempParent.left = znode;
            }
        }
    }

    //搜索关键字
    public BSNode<T> search(T key){
        return search(this.root, key);
    }

    private BSNode<T> search(BSNode<T> temp, T key){
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

    ///////////找节点的后继/////////////////////////////
    public BSNode<T> successor(BSNode<T> x){
        if(x.right != null) {
            return minimum(x.right);
        }
        BSNode<T> y = x.parent;
        while(y != null && x == y.right){
            x = y;
            y = y.parent;
        }
        return y;
    }

    //找最小的关键字
    public T minimum(){
        BSNode<T> min = minimum(this.root);
        if(min == null){
            return null;
        } else {
            return min.getKey();
        }
    }

    private BSNode<T> minimum(BSNode<T> temp){
        if(temp == null) return null;
        while(temp.left != null){
            temp = temp.left;
        }
        return temp;
    }
    /////////////////////////////////////////////////////////
    //删除
    public void remove(T key){
        BSNode<T> z = search(this.root, key);
        if(z != null){
            remove(this.root, z);
            if(z != null){
                z = null;
            }
        }
    }

    private void remove(BSNode<T> temp, BSNode<T> z){
        if(z.left == null) {
            transplant(z, z.right);
        } else if(z.right == null){
            transplant(z, z.left);
        } else {
            BSNode<T> y = minimum(z.right);
            if(y.parent != z){
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }

    //将一棵以v为根的子树替换一棵以u为根的子树
    private void transplant(BSNode<T> u, BSNode<T> v){
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
    ///////////////////////////////////////////////////////////

    //销毁树
    public void clear(){
        destroy(this.root);
        this.root = null;
    }

    private void destroy(BSNode<T> node){
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

    //打印
    public void print(){
        if(this.root != null){
            print(this.root, (T) this.root.key, 0);
        }
    }

    private void print(BSNode<T> node, T key, int direction){
        if(node != null){
            if(direction == 0){
                System.out.printf("%s is root\n", node.toString());
            } else {
                System.out.printf("%s is %s's %s child\n", node.key, key, direction==1?"right" : "left");
            }
            print(node.left, node.key, -1);
            print(node.right, node.key, 1);
        }
    }

    ////////////////找节点的前驱//////////////////////////////
    public BSNode<T> predecessor(BSNode<T> x){
        if(x.left != null) {
            return maximum(x.left);
        }
        BSNode<T> y = x.parent;
        while(y != null && x == y.left){
            x = y;
            y = y.parent;
        }
        return y;
    }

    //找最大的关键字
    public T maximum(){
        BSNode<T> max = maximum(this.root);
        if(max == null){
            return null;
        } else {
            return max.getKey();
        }
    }

    private BSNode<T> maximum(BSNode<T> temp){
        if(temp == null) return null;
        while(temp.right != null){
            temp = temp.right;
        }
        return temp;
    }
    ////////////////////////////////////////////////////////////


    //前序遍历
    public void preOrder(){
        preOrder(this.root);
    }

    private void preOrder(BSNode<T> t){
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

    private void inOrder(BSNode<T> t){
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

    private void postOrder(BSNode<T> t){
        if(t != null){
            postOrder(t.left);
            postOrder(t.right);
            System.out.print(t.toString() + " ");
        }
    }

    //main方法
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        int arr[] = {1,5,4,3,2,6};
        for(int i = 0; i < arr.length; i++){
            tree.insert(arr[i]);
        }
        System.out.println("\n== 前序遍历: ");
        tree.preOrder();
        System.out.println("\n== 中序遍历: ");
        tree.inOrder();
        System.out.println("\n== 后序遍历: ");
        tree.postOrder();

        System.out.println("\n===最小值：" + tree.minimum());
        System.out.println("\n===最大值：" + tree.maximum());
        System.out.println("\n===详细信息：");
        tree.print();

        System.out.print("\n== 删除节点: "+ arr[3]);
        tree.remove(arr[3]);

        System.out.print("\n== 中序遍历: ");
        tree.inOrder();

        // 销毁二叉树
        tree.clear();
    }

}
