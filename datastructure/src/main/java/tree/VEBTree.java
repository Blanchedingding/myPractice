package tree;

public class VEBTree {

    public Node root;

    public VEBTree(int length) {
        this.root = new Node(length);
    }

    private static class Node {
        private int u;
        private Integer min;
        private Integer max;
        private Node[] nodeArray;
        private Node summary;

        public Node(int u) {
            this.u = u;
            if (u != 2) {
                double pow = Math.log(u) / Math.log(2);
                int high = (int) Math.pow(2, (int) (Math.ceil(pow / 2)));
                int low = (int) Math.pow(2, (int) (Math.floor(pow / 2)));
                this.summary = new Node(high);
                this.nodeArray = new Node[low];
                for (int i = 0; i < low; i++)
                    this.nodeArray[i] = new Node(low);
            }
        }

        public boolean contain(int x) {
            if (min == x || max == x)
                return true;
            else if (u == 2)
                return false;
            else
                return nodeArray[x / nodeArray.length].contain(x % x / nodeArray.length);
        }

        public Integer successor(int x) {
            if (u == 2)
                if (x == 0 && max == 1)
                    return 1;
                else
                    return null;
            else if (min != null && x < min)
                return min;
            else {
                int highx = x / nodeArray.length;
                Integer max_low = nodeArray[highx].max;
                Integer offset = 0;
                if (max_low != null && x % nodeArray.length < max_low) {
                    offset = nodeArray[highx].successor(x % nodeArray.length);
                    return highx * nodeArray.length + offset;
                } else {
                    Integer successorClustor = summary.successor(highx);
                    if (successorClustor == null)
                        return null;
                    else {
                        offset = nodeArray[successorClustor].min;
                        return successorClustor * nodeArray.length + offset;
                    }
                }
            }
        }

        public Integer predecessor(int x) {
            if (u == 2)
                if (x == 1 && min == 0)
                    return 0;
                else
                    return null;
            else if (max != null && x > max)
                return max;
            else {
                int highx = x / nodeArray.length;
                int lowx = x % nodeArray.length;
                Integer min_low = nodeArray[highx].min;
                Integer offset = 0;
                if (min_low != null && lowx > min_low) {
                    offset = nodeArray[highx].predecessor(x);
                    return highx * nodeArray.length + offset;
                } else {
                    Integer predecessorClustor = summary.predecessor(highx);
                    if (predecessorClustor != null)
                        return predecessorClustor * nodeArray.length + nodeArray[predecessorClustor].predecessor(x);
                    else if (min != null && x > min)
                        return min;
                    else
                        return null;
                }
            }
        }

        private void emptyInsert(int x) {
            min = max = x;
        }

        public void insert(int x) {
            if (min == null) {
                emptyInsert(x);
            } else {
                if (x < min) {
                    int temp = x;
                    x = min;
                    min = temp;
                }
                if (u > 2) {
                    int highx = x / nodeArray.length;
                    int lowx = x % nodeArray.length;
                    if (nodeArray[highx].min == null) {
                        nodeArray[highx].emptyInsert(lowx);
                        summary.insert(highx);
                    } else
                        nodeArray[highx].insert(lowx);
                }
                if (x > max)
                    max = x;
            }
        }

        /**
         * del方法前提是x是树中一个元素
         */
        public void del(int x) {
            if (min == max)
                min = max = null;
            else if (u == 2) {
                if (x == 0)
                    min = 1;
                else
                    min = 0;
                min = max;
            } else {
                /**
                 *此处重点理解，先判断如果x==min，那么将当前node下的最小簇的最小节点用来顶替min，
                 * 并且将x置为最小簇的最小节点，
                 * 删除
                 * 如果删掉之后，x所在的簇为null，
                 *  那么删除summary中的对应节点，用现存最大节点顶替max
                 * 如果x所在簇不为null，但是x==max
                 *  那么用x所在簇的最大节点顶替max
                 */
                if (x == min) {
                    int firstCluster = summary.min;
                    x = firstCluster * nodeArray.length + nodeArray[firstCluster].min;
                    min = x;
                }
                int highx = x / nodeArray.length;
                int lowx = x % nodeArray.length;
                nodeArray[highx].del(lowx);
                if (nodeArray[highx].min == null) {
                    summary.del(highx);
                    if (x == max) {
                        Integer summary_max = summary.max;
                        if (summary_max == null)
                            max = min;
                        else
                            max = summary_max * nodeArray.length + nodeArray[summary_max].max;
                    }
                } else if (x == max) {
                    max = highx * nodeArray.length + nodeArray[highx].max;
                }
            }
        }
    }

    public Integer minNum() {
        return this.root.min;
    }

    public Integer maxNum() {
        return this.root.max;
    }

    public boolean contain(int x) {
        return root.contain(x);
    }

    public Integer successor(int x) {
        return root.successor(x);
    }

    public Integer predecessor(int x) {
        return root.predecessor(x);
    }

    public void insert(int x) {
        root.insert(x);
    }

    public void del(int x) {
        root.del(x);
    }

    public static void main(String[] args) {
        VEBTree t = new VEBTree(32);
        t.insert(2);
        t.insert(1);
        t.insert(15);
        System.out.println(t.successor(2));
        System.out.println(t.predecessor(2));

    }

}
