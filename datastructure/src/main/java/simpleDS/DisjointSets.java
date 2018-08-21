package simpleDS;

/**
 * 并查集（Disjoint set或者Union-find set）是一种树型的数据结构，常用于处理一些不相交集合（Disjoint Sets）的合并及查询问题。
 *
 * 有一个联合-查找算法（union-find algorithm）定义了两个操作用于此数据结构：
 *
 * Find：确定元素属于哪一个子集。它可以被用来确定两个元素是否属于同一子集。
 * Union：将两个子集合并成同一个集合。
 *
 * 为了避免树的深度过大，可以每次让深度大的树做为新根，这样减少树深度增加速率。
 * 那么就需要记住当前根的深度，而由于我们只采用了一个数组，所以，可以让根的值为负值，代表深度。
 * 这样，根节点的值为-1，表示深度为-1。
 *
 * 路径压缩:
 * 执行一次Find后，让该路径上，所有节点直接指向根，而不需要指向父亲，这样下一次查找的时候能够快速找到根，节约时间。
 *
 */
public class DisjointSets {

    class DisjointSetsNode {
        private String element;//元素
        private int rank;//存储秩(最大可能的秩)或者父节点所在位置
    }

    private DisjointSetsNode[] s;//存储并查集每一个元素

    public DisjointSets(String[] t) {
        s = new DisjointSetsNode[t.length];
        for (int i = 0; i < t.length; i++) {
            s[i] = new DisjointSetsNode();
            s[i].rank = -1;
            s[i].element = t[i];
        }
    }

    /////////////////////// 查找 ///////////////////////////
    /**
     * 查找传入元素的代表元素
     *
     * @param element 需要查找的元素
     * @return 返回代表元素  返回为null代表没有该元素
     */
    public String find(String element) {
        int index = findIndex(element);
        if (index == -1) {
            return null;
        }
        return find(index).element;
    }

    /**
     * 查找下标对应元素所在树的根(使用路径压缩)：
     * 如果查找的元素rank为负的说明是根节点，rank表示其到叶子的最长路径长度；
     * 如果正的，rank表示其父节点的index，沿着父节点往上找到根节点后将其父节点直接设为根节点
     * ！！！！这里并不改变根节点的rank，所以这个rank是指该节点高度的一个上界
     *
     * @param x 需要查找的下标
     * @return
     */
    public DisjointSetsNode find(int x) {
        if (s[x].rank < 0) {
            return s[x];
        } else {
            DisjointSetsNode root = find(s[x].rank);
            s[x].rank = findIndex(root.element);
            return root;
        }
    }

    /**
     * 查找该元素的下标
     *
     * @param element
     * @return
     */
    private int findIndex(String element) {
        for (int i = 0; i < s.length; i++) {
            if (s[i].element.equals(element)) {
                return i;
            } else {
                continue;
            }
        }
        return -1;
    }

    //////////////////////////// 合并 //////////////////////////////////////////
    public void union(String ele1, String ele2) {
        DisjointSetsNode root1 = find(findIndex(ele1));//ele1的代表元素(即树的根)
        DisjointSetsNode root2 = find(findIndex(ele2));//ele2的代表元素(即树的根)
        if (root1 == null || root2 == null) {
            System.out.println("有元素不存在,并操作失败……");
            return;
        }
        if (root1.element.equals(root2.element)) {
            return;
        }
        if (root1.rank < root2.rank) {//root1的秩大于root2的秩（此处因为根节点的rank为负值，所以是小于号）  代表set1高于set2
            root2.rank = findIndex(root1.element);
        } else if (root1.rank == root2.rank) {//root1的秩与root2的秩相等  秩+1
            root1.rank--;
            root2.rank = findIndex(root1.element);
        } else {//root1的秩小于root2的秩  代表set1低于set2
            root1.rank = findIndex(root2.element);
        }
    }


    public static void main(String[] args) {
        String[] is = new String[20];
        for (int i = 0; i < is.length; i++) {
            is[i] = i + "0";
        }
        System.out.println("操作前的数组==========");
        for (int i = 0; i < is.length; i++) {
            System.out.print(is[i] + " ");
        }
        System.out.println();
        DisjointSets disjSets = new DisjointSets(is);
        System.out.println("并查集内的元素=====");
        for (int i = 0; i < is.length; i++) {
            System.out.print(disjSets.s[i].element + "===" + disjSets.s[i].rank + "    ");
        }
        System.out.println();
        System.out.println("并操作之前查找:" + disjSets.find("170"));
        disjSets.union("10", "20");
        disjSets.union("30", "40");
        disjSets.union("10", "30");
        disjSets.union("50", "60");
        disjSets.union("70", "80");
        disjSets.union("50", "80");
        disjSets.union("60", "10");
        disjSets.union("110", "120");
        disjSets.union("130", "140");
        disjSets.union("110", "130");
        disjSets.union("150", "160");
        disjSets.union("170", "180");
        disjSets.union("150", "180");
        disjSets.union("160", "110");
        disjSets.union("10", "110");
        System.out.println("并操作之后查找:" + disjSets.find("170"));
        System.out.println("并查集内的元素=====");
        for (int i = 0; i < is.length; i++) {
            System.out.print(disjSets.s[i].element + "===" + disjSets.s[i].rank + "    ");
        }
        System.out.println();
        disjSets.find("180");
        System.out.println("路径压缩查找之后查找:" + disjSets.find("170"));
        System.out.println("并查集内的元素=====");
        for (int i = 0; i < is.length; i++) {
            System.out.print(disjSets.s[i].element + "===" + disjSets.s[i].rank + "    ");
        }
        System.out.println();

    }

}
