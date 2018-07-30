package dynamicProgramming;

/**
 * 以矩阵链<A1,A2,A3>为例，假设三个矩阵的规模分别为10X100，100X5和5X50。
 * ①以((A1*A2)*A3)方式划分，乘法执行次数为：10*100*5+10*5*50=5000+2500=7500次
 * ②以(A1*(A2*A3))方式划分，乘法执行次数为：100*5*50+10*100*50=25000+50000=75000次
 * 我们可以发现，对于同样的矩阵链<A1,A2,A3>相乘而言，不同的划分，乘法次数居然相差10倍。
 */
public class MatrixChain {

    //假设矩阵Ai的规模为pi-1 * pi
    public int[][] matrixChainOrder(int[] p){
        int n = p.length - 1;//有n个矩阵
        int[][] m = new int[n + 1][n + 1]; //m[i][j]表示Ai到Aj的最小乘法次数，只用到m[1..n, 1..n]
        int[][] s = new int[n + 1][n + 1]; //记录m[i][j]分割点，只用到s[1..n-1, 2..n]
        for(int i = 1; i <= n; i ++){
            m[i][i] = 0;
        }
        for(int l = 2; l <= n; l++){
            for(int i = 1; i <= n-l+1; i++){
                int j = i + l -1;
                m[i][j] = Integer.MAX_VALUE;
                for(int k = i; k <=j -1; k++){
                    int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if(q < m[i][j]){
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        System.out.println("总结果为：" + m[1][6]);
//        for(int i = 0; i < m.length; i++){
//            for(int j = 0; j < m[0].length; j++){
//                System.out.print(m[i][j] + "\t\t");
//            }
//            System.out.println();
//        }

        for(int i = 0; i < s.length; i++){
            for(int j = 0; j < s[0].length; j++){
                System.out.print(s[i][j] + " ");
            }
            System.out.println();
        }
        printOptimalParens(s, 1, n);

        return m;
    }

    private void printOptimalParens(int[][] s, int i, int j){
        if(i == j){
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(s,i,s[i][j]);
            printOptimalParens(s,s[i][j]+1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        int[] p = new int[]{30,35,15,5,10,20,25};
        MatrixChain mc = new MatrixChain();
        mc.matrixChainOrder(p);
    }
}
