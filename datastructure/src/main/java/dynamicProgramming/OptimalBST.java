package dynamicProgramming;

/**
 * 每个关键字ki对应一个概率pi表示搜索概率
 * 每个伪关键字di(所有在ki和ki+1之间的值)对应一个概率qi表示搜索概率
 * sum(pi)+sum(qi) = 1
 * i    0    1      2     3     4     5
 * pi       0.15  0.10  0.05  0.10  0.20
 * qi 0.05  0.10  0.05  0.05  0.05  0.10
 *
 * 要对树中进行一次搜索的期望代价最小，假定搜索一次的代价等于访问的节点数
 *        k2
 *       /\
 *     k1  k5
 *    /\   /\
 *  d0 d1 k4  d5
 *        /\
 *      k3 d4
 *      /\
 *    d2 d3
 *
 *  e[i,j] = pr + (e[i,r-1] + w[i,r-1]) + (e[r+1,j] + w[r+1,j])
 *  因为 w[i,j] = w[i, r-1] + pr +w[r+1,j]
 *  所以 e[i,j] = e[i][r-1] + e[r+1][j] + w[i][j]
 */
public class OptimalBST {

    public double[][] optimalBST(double[] p, double[] q, int n){
        double[][] e = new double[n+2][n+1];//e[i,j]为包含ki到kj的子树进行一次搜索的期望代价
        double[][] w = new double[n+2][n+1];//w[i,j]为包含ki到kj的子树的所有概率之和
        double[][] root = new double[n+1][n+1];//root[i,j]记录包含ki到kj的子树的根
        for(int i = 1; i <= n+1; i++){
            e[i][i-1] = q[i-1];
            w[i][i-1] = q[i-1];
        }
        for(int l = 1; l <= n; l++){
            for(int i = 1; i <= n-l+1; i++){
                int j = i + l -1;
                e[i][j] = 10000;//正无穷
                w[i][j] = w[i][j-1] + p[j] + q[j];
                for(int r = i; r <= j; r++){
                    double t = e[i][r-1] + e[r+1][j] + w[i][j];
                    if(t < e[i][j]){
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
        System.out.println(e[1][n]);
        return e;
    }

    public static void main(String[] args) {
        double[] p = new double[]{ 0, 0.15 , 0.10,  0.05,  0.10,  0.20};
        double[] q = new double[]{0.05,  0.10,  0.05,  0.05,  0.05,  0.10};
        OptimalBST o = new OptimalBST();
        o.optimalBST(p, q, 5);
    }
}
