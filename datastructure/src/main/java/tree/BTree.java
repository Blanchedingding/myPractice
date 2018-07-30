package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 一棵m阶的B-Tree有如下特性：
 1. 每个节点最多有m个孩子。
 2. 除了根节点和叶子节点外，其它每个节点至少有Ceil(m/2)个孩子。
 3. 若根节点不是叶子节点，则至少有2个孩子
 4. 所有叶子节点都在同一层，且不包含其它关键字信息
 5. 每个非终端节点包含n个关键字信息（P0,P1,…Pn, k1,…kn）
 6. 关键字的个数n满足：ceil(m/2)-1 <= n <= m-1
 7. ki(i=1,…n)为关键字，且关键字升序排序。
 8. Pi(i=1,…n)为指向子树根节点的指针。P(i-1)指向的子树的所有节点关键字均小于ki，但都大于k(i-1)

 分裂时，将（关键字-1）一分为二，然后将中间的放到父节点中

 约定：
 1. 根节点始终在主存中，无需DISK-READ操作；根节点被改变，需要DISK-WRITE
 2. 任何被当做参数的节点在被传递之前，都要对它们做一次DISK-READ
 */
public class BTree{

    private final int M = 6; //表示这棵BTree是m阶，每个节点最多M棵子树，M-1个关键字
    private final int MIN_NUM = (int)Math.ceil(M/2.0);//每个节点至少MIN_NUM个子树，MIN_NUM - 1 个关键字
    private BTreeNode root;

    class BTreeNode {
        //指向父节点指针
        public BTreeNode parent;
        //本节点的关键字个数
        public int keyNum;
        //关键字数组,最多M-1个关键字，0号元素不用，最后一个作为备用,实际上只有1到M-1号元素合法
        public int[] keys = new int[M + 1];
        //子树指针，最多M棵子树，从0号元素开始，最后一个备用
        public BTreeNode[] childs = new BTreeNode[M + 1];
    }

    ////////////////////// 插入 ///////////////////////////////
    public void createBTree(int[] arr){
        for(int i = 0; i < arr.length ; i ++){
            add(arr[i]);
        }
    }

    public void add(int key){
        //根为空
        if(root == null){
            root = new BTreeNode();
            root.keys[++root.keyNum] = key;
            root.parent = null;
            return;
        }

        BTreeNode p = root;
        while(true){
            //插入排序
            int i;
            for(i = p.keyNum; i >= 1 ; i--){
                //找寻插入点
                if(key > p.keys[i]){
                    break;
                }
                if(key == p.keys[i]){
                    return;
                }
            }
            if(p.childs[i] == null){
                //插入到p的关键字列表中，插入点为i+1
                //可能引起p的关键字数为M,所以要调整
                for(int j = p.keyNum ; j >= (i + 1); j--){
                    p.keys[j + 1] = p.keys[j];
                }
                p.keys[i + 1] = key;
                p.keyNum++;
                break;
            }else{
                p = p.childs[i];
            }
        }

        //将key插入p之后，判断p节点是否符合BTree的条件
        //每个节点的关键字最多M-1个
        if(p.keyNum > M - 1){
            //将p节点分裂
            split(p);
        }
    }

    private void split(BTreeNode p){
        //分裂出来的左节点
        BTreeNode lt = new BTreeNode();
        BTreeNode gt = new BTreeNode();
        /*
        *   M = 3
        *   MIN_NUM = 2
        *   index = M 的值作为备用
        */
        System.arraycopy(p.keys,1,lt.keys,1,MIN_NUM - 1);
        System.arraycopy(p.childs,0,lt.childs,0,MIN_NUM);
        lt.keyNum = MIN_NUM - 1;

        System.arraycopy(p.keys,MIN_NUM + 1,gt.keys,1,M - MIN_NUM);
        System.arraycopy(p.childs,MIN_NUM,gt.childs,0,M - MIN_NUM + 1);
        gt.keyNum = M - MIN_NUM;

        //   分隔节点之后，需要更改父节点信息
        for(int i = 0 ; i <= lt.keyNum ; i ++){
            if(lt.childs[i] != null){
                lt.childs[i].parent = lt;
            }
        }
        for(int i = 0 ; i <= gt.keyNum ; i ++){
            if(gt.childs[i] != null){
                gt.childs[i].parent = gt;
            }
        }
        BTreeNode parent = p.parent;
        //root节点分裂
        if(parent == null){
            BTreeNode tmp = new BTreeNode();
            tmp.keys[++tmp.keyNum] = p.keys[MIN_NUM];
            tmp.parent = null;
            tmp.childs[tmp.keyNum - 1] = lt;
            tmp.childs[tmp.keyNum] = gt;
            lt.parent = tmp;
            gt.parent = tmp;
            root = tmp;
        }else{
            int i = 0;
            //插入排序
            for(i = parent.keyNum; i >= 1 ; i--){
                //找寻插入点
                if(p.keys[MIN_NUM] > parent.keys[i]){
                    break;
                }
            }
            //插入点为i+1,parent右边的集体往右挪一格
            for(int j = parent.keyNum ; j >= (i + 1); j--){
                parent.keys[j + 1] = parent.keys[j];
                parent.childs[j + 1] = parent.childs[j];
            }
            parent.keys[i + 1] = p.keys[MIN_NUM];
            parent.childs[i] = lt;
            parent.childs[i + 1] = gt;
            lt.parent = parent;
            gt.parent = parent;
            parent.keyNum++;
            //是否继续分裂
            if(parent.keyNum > M - 1){
                //将p节点分裂
                split(parent);
            }
        }

    }

    /////////////////////////////// 删除 ///////////////////////////
    /*
    *   删除节点i中的key
    *   1. 节点i不是最底层非终端节点
    *       将Ai子树上的最小值替换key
    *       问题变为2
    *   2. 节点i是最底层非终端节点
    *       2.1 节点i的关键字个数 >= MIN_NUM,直接删除
    *       2.2 节点i的关键字个数 = MIN_NUM - 1
    *           2.2.1 兄弟节点的关键字个数 >= MIN_NUM,将右兄弟最小值（左兄弟最大值）上移至父节点相应位置的数据，然后将父节点被替换的数据下移至被删除节点
    *           2.2.2 兄弟节点的关键字个数 = MIN_NUM - 1，合并（借助父节点）
    *
    */
    public  void delete(int key){
        //遍历BTree,查找key所在的节点
        BTreeNode p = root;
        int index = 0;

        label:
        while(p != null){
            for(int i = p.keyNum; i >= 1; i --){
                if(key == p.keys[i]){
                    index = i;
                    break label;//退出大循环！！！
                }else if(key > p.keys[i]){
                    p = p.childs[i];
                    continue label; //continue大循环！！！1
                }
            }
            p = p.childs[0];//key小于p最小的key，在p的最左边孩子里找
        }
        if(p == null){
            return ; //找不到这个key
        }
        //找到p,
        // p.child[0]不存在也就意味着p为叶节点
        if(p.childs[0] == null){
            delete(p,key);
        }else{
            //对于不是最底层的非终端节点,则将别比key小的最大值替换key
            BTreeNode q = p.childs[index - 1];
            while(q.childs[0] != null){
                q = q.childs[q.keyNum];
            }
            p.keys[index] = q.keys[q.keyNum];
            delete(q,q.keys[q.keyNum]);
        }
    }

    //从叶节点p中删除key
    public void delete(BTreeNode p,int key){
        if(p.keyNum >= MIN_NUM){
            //删除后节点关键字数满足MIN_KEY_NUM - 1,直接删除
            for(int i = 1; i <= p.keyNum; i ++){
                if(p.keys[i] > key){
                    p.keys[i - 1] = p.keys[i];
                }
            }
            p.keys[p.keyNum] = 0;
            p.keyNum--;
        }else{
            //删除后节点关键字数不能够满足MIN_KEY_NUM - 1,可能需要合并
            int index = 0;
            BTreeNode q = p.parent;
            if(q == null){
                //p是root，直接删除，因为root至少两棵子树，所以关键字个数没有限制
                for(int i = 1; i <= p.keyNum; i ++){
                    if(p.keys[i] > key){
                        p.keys[i - 1] = p.keys[i];
                    }
                }
                p.keys[p.keyNum] = 0;
                p.keyNum--;
                return;
            }
            //找到p是它父节点的第几个孩子
            for(int i = 0; i <= q.keyNum ; i ++){
                if(q.childs[i] == p){
                    index = i;
                    break;
                }
            }
            //查询右兄弟并且右兄弟的关键字个数大于MIN_KEY_NUM - 1,
            //将父节点中第一个大于key的值移动到删除节点
            //将右兄弟中的最小值替换父节点中的第一个大于key的值
            if(index < q.keyNum && q.childs[index + 1].keyNum >= MIN_NUM){
                //从p中删除关键字key,所有大于key的关键字向左挪一格
                //因为p是叶节点，不需要再考虑挪动p的孩子
                for(int i = 0 ; i <= p.keyNum ; i ++){
                    if(p.keys[i] > key){
                        p.keys[i - 1] = p.keys[i];
                    }
                }
                //将父节点中比p大一个的关键字拿下来放到p的最右边，补充p
                p.keys[p.keyNum] = q.keys[index + 1];
                //将右兄弟中的最小值替换父节点中的第一个大于key的值
                q.keys[index + 1] = q.childs[index + 1].keys[1];
                //将右兄弟的key集体向左移动一格，因为p的有兄弟也是叶节点，同样不需要考虑孩子
                for(int i = 2; i <= q.childs[index + 1].keyNum ; i ++){
                    q.childs[index + 1].keys[i - 1] = q.childs[index + 1].keys[i];
                }
                q.childs[index + 1].keys[q.childs[index + 1].keyNum] = 0;
                q.childs[index + 1].keyNum--;
                return;
            }
            //查询左兄弟并且左兄弟的关键字个数大于MIN_KEY_NUM - 1
            //将父节点中最后一个小于key的值移动到删除节点
            //将左兄弟中的最大值替换父节点中的最后一个小于key的值
            if(index > 0 && q.childs[index - 1].keyNum >= MIN_NUM){
                for(int i = p.keyNum ; i >= 1  ; i --){
                    if(p.keys[i] < key){
                        p.keys[i + 1] = p.keys[i];
                    }
                }
                p.keys[1] = q.keys[index];
                q.keys[index] = q.childs[index - 1].keys[q.childs[index - 1].keyNum];
                q.childs[index - 1].keys[q.childs[index - 1].keyNum] = 0;
                q.childs[index - 1].keyNum--;
                return;
            }

            //不符合上述条件，则需要有merge操作
            //删除key,然后和兄弟合并
            for(int i = 1 ; i <= p.keyNum; i++){ //这里应该是小于等于号，原来的错了
                if(p.keys[i] > key){
                    p.keys[i - 1] = p.keys[i];
                }
            }
            //将最后一个值赋值为0
            p.keys[p.keyNum] = 0;
            p.keyNum--;
            if(index < q.keyNum){
                //将节点与右节点结合
                merge(p,true);
            }else{
                //将节点与左节点结合
                merge(p,false);
            }

        }
    }

    //flag为true,将节点与右节点结合;否则，将节点与左节点结合
    public void merge(BTreeNode p,boolean flag){
        BTreeNode q = p.parent;
        int index = 0;
        //找到p是它父节点的第几个孩子
        for(int i = 0; i <= q.keyNum ; i++){
            if(q.childs[i] == p){
                index = i;
            }
        }

        if(flag){//将节点与右节点结合
            BTreeNode br = q.childs[index + 1];//找到右节点
            //将父节点中第一个大于p的值拿下来，放在p的最右边；父节点对应右边部分的key和child左移一格
            p.keys[++p.keyNum] = q.keys[index + 1];
            for(int i = index + 1; i <= q.keyNum; i++){
                q.keys[i] = q.keys[i + 1];
                q.childs[i] = q.childs[i + 1];
            }
            q.keyNum --;
            //兄弟节点合并到p中
            System.arraycopy(br.keys,1,p.keys,p.keyNum + 1,br.keyNum);
            System.arraycopy(br.childs,0,p.childs,p.keyNum,br.keyNum + 1);
            p.keyNum += br.keyNum;

            //父节点为根
            if(q == root){
                if(q.keyNum == 0){//若根的关键字个数为0，说明现在只有p一个有data的节点了，只需要把p当成root就好了
                    root = p;
                    p.parent = null;
                    for(int i = 0; i <= p.keyNum; i ++){
                        if(p.childs[i] != null){
                            p.childs[i].parent = root;
                        }
                    }
                }
            }else{ //由于拿了父节点q的一个关键字下来，所以要判断q的关键字个数
                if(q.keyNum < MIN_NUM - 1){
                    BTreeNode q1 = q.parent;
                    int index1 = 0;
                    //判断父节点是祖父节点的第几个孩子
                    for(int i = 0; i <= q1.keyNum ; i++){
                        if(q1.childs[i] == q){
                            index1 = i;
                        }
                    }
                    /////////////////觉得原来的算法不对//////////////////////
                    //查询右兄弟并且右兄弟的关键字个数大于MIN_KEY_NUM - 1,
                    //将父节点中第一个大于key的值到q的最右边关键字位置
                    //将右兄弟中的最小值替换父节点中的第一个大于key的值
                    if(index1 < q1.keyNum && q1.childs[index1 + 1].keyNum >= MIN_NUM){
                        System.out.println("+++++++++++++++++++++++++++++++");
                        //与delete部分有一些不同，这里q的keynum是增加1，因为已经删除了
                        q.keyNum++;
                        q.keys[q.keyNum] = q1.keys[index1 + 1];
                        //！！！！将右兄弟的最左边孩子移为q的最右边孩子
                        q.childs[q.keyNum] = q1.childs[index1 + 1].childs[0];
                        //将右兄弟中的最小值替换父节点中的第一个大于key的值
                        q1.keys[index1 + 1] = q1.childs[index1 + 1].keys[1];
                        //将右兄弟的key集体向左移动一格
                        for(int i = 2; i <= q1.childs[index1 + 1].keyNum ; i ++){
                            q1.childs[index1 + 1].keys[i - 1] = q1.childs[index1 + 1].keys[i];
                        }
                        //！！！！将右兄弟的child集体向左移动一格,需要考虑孩子
                        for(int i = 1; i <= q1.childs[index1 + 1].keyNum ; i ++){
                            q1.childs[index1 + 1].childs[i-1] = q1.childs[index1 + 1].childs[i];
                        }
                        q1.childs[index1 + 1].keys[q1.childs[index1 + 1].keyNum] = 0;
                        q1.childs[index1 + 1].keyNum--;
                        return;
                    }
                    //查询左兄弟并且左兄弟的关键字个数大于MIN_KEY_NUM - 1
                    //将父节点中最后一个小于key的值移下来
                    //将左兄弟中的最大值替换父节点中的最后一个小于key的值
                    if(index1 > 0 && q1.childs[index1 - 1].keyNum >= MIN_NUM){
                        System.out.println("-------------------------------");
                        //!!!!将q的key集体向右移动一格
                        for(int i = q.keyNum ; i >= 1  ; i --){
                            q.keys[i + 1] = q.keys[i];
                        }
                        //将父节点中最后一个小于key的值移到q的第一个关键字位置
                        q.keys[1] = q1.keys[index1];
                        q.keyNum ++;

                        //!!!!将q的child集体向右移动一格
                        for(int i = q.keyNum; i >= 0; i --){
                            q.childs[i + 1] = q.childs[i];
                        }
                        //将左兄弟的最右边的孩子移成q的最左边的孩子
                        q.childs[0] = q1.childs[index1 - 1].childs[q1.childs[index1 - 1].keyNum];

                        q1.keys[index1] = q1.childs[index1 - 1].keys[q1.childs[index1 - 1].keyNum];
                        q1.childs[index1 - 1].keys[q1.childs[index1 - 1].keyNum] = 0;
                        q1.childs[index1 - 1].keyNum--;
                        return;
                    }
                    ////////////////////////////////////////////////////////
                    if(index1 < q1.keyNum){
                        merge(q,true);
                    }else{
                        merge(q,false);
                    }
                }
            }

        } else{
            //将节点与左节点结合
            BTreeNode lr = q.childs[index - 1];
            merge(lr,true);
        }

    }

    /////////////////////////// 打印 //////////////////////////////////
    public void printBTree(){
        Queue<BTreeNode> queue = new LinkedList<BTreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            BTreeNode tmp = queue.poll();
            if(tmp != null ){
                System.out.print("keyNum:" + tmp.keyNum);
                System.out.print("\t\t" + Arrays.toString(tmp.keys));
                System.out.println("\t\t\t\t" + tmp.parent + "------" + tmp);
                for(int i = 0 ; i <= tmp.keyNum; i++){
                    queue.offer(tmp.childs[i]);
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        //这个例子说明原来的不对，如果注释掉我加的那段，根节点有6个关键字
        int[] arr = new int[]{5,10,12,15,21,24,26,32,36,38,39,40,42,47,54,55,57,61,62,73,78,80,85,86,92};

        BTree bt = new BTree();
        bt.createBTree(arr);
        bt.printBTree();

        System.out.println("============删除===============");
        int i = 0;
        int[] arr1 = new int[]{26};
        while(i < 1){
            System.out.println("删除：" + arr1[i]);
            bt.delete(arr1[i]);
            bt.printBTree();
            i++;
        }
    }



    //    public static void main(String[] args) {
//        System.out.println("Hello World!");
//
////        int count = new Random().nextInt(20);
////        while(count == 0){
////            count = new Random().nextInt(20);
////        }
//        int count = 10;
//        int[] arr = new int[count];
//        for(int i = 0; i < count ; i ++){
//            arr[i] = new Random().nextInt(100);
//        }
//        System.out.println(Arrays.toString(arr));
//        BTree bt = new BTree();
//        bt.createBTree(arr);
//        bt.printBTree();
//        System.out.println("============删除===============");
//        while(count > 0){
//            int index = new Random().nextInt(count);
//            System.out.println("删除：" + arr[index]);
//            bt.delete(arr[index]);
//            bt.printBTree();
//            for(int i = 0; i < count ; i ++){
//                if(i > index){
//                    arr[i - 1] = arr[i];
//                }
//            }
//            count--;
//        }
//    }
}
