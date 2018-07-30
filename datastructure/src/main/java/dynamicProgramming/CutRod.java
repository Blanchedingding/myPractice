package dynamicProgramming;

/**
 * 给定一段长度为n英寸的钢条和一个价格表pi(i=1,2,…n)，求切割钢条方案，使得销售收益rn最大。
 * 注意，如果长度为n英寸的钢条的价格pn足够大，最优解可能就是完全不需要切割。
 * -------------------------------------------
 * 长度i   1  2  3  4   5   6   7   8   9  10
 * 价格pi  1  5  8  9  10  17  17  20  24  30
 *
 */
public class CutRod {

    //第一种
    //自顶向下的带备忘机制的dp
    public int memorizedCutRod(int[] p, int n){
        int[] r = new int[n+1];//存储之前计算过的子问题的解
        for(int i = 0; i <= n; i++){
            r[i] = Integer.MIN_VALUE;
        }
        return memorizedCutRodAux(p, n, r);
    }

    private int memorizedCutRodAux(int[] p, int n, int[] r){
        if(r[n] >= 0) return r[n];
        int q = Integer.MIN_VALUE;
        if(n == 0){
            q = 0;
        } else {
            for(int i = 1; i <= n; i++){
                q = Math.max(q, p[i] + memorizedCutRodAux(p, n - i, r));
            }
        }
        r[n] = q;
        return q;
    }

    //第二种
    //自底向上
    //保留最优解之一并打印
    public int bottomUpCutRod(int[] p, int n){
        int[] r = new int[n + 1];
        int[] s = new int[n + 1];
        r[0] = 0;
        for(int j = 1; j <= n; j++){
            int q = Integer.MIN_VALUE;
            for(int i = 1; i <= j; i++){
                if(q < p[i] + r[j - i]){
                    q =  p[i] + r[j - i];
                    s[j] = i;
                }
            }
            r[j] = q;
        }

        System.out.println(r[n]);
        //打印解决方案
        printCutRodSolution(r, s, n);
        return r[n];
    }

    private void printCutRodSolution(int[] r, int[] s, int n){
        System.out.println("切割方案为：");
        while(n > 0){
            System.out.print(s[n] + " ");
            n -= s[n];
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] p = new int[]{0,1,5,8,9,10,17,17,20,24,30};
        CutRod c = new CutRod();

        System.out.println("带备忘机制的自顶向下的方法：");
        System.out.println(c.memorizedCutRod(p, 7));
        System.out.println();

        System.out.println("自底向上的方法：");
        c.bottomUpCutRod(p, 7);


    }
}
