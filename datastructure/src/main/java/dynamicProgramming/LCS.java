package dynamicProgramming;

/**
 * 最长公共子序列Largest Common subsequence
 * 一个给定的序列的子序列，就是将给定序列中零个或多个元素去掉后得到的结果
 *
 */
public class LCS {

    public int[][] lcs(String[] x, String[] y){
        //正式的字符串从1下标开始
        int m = x.length - 1;
        int n = y.length - 1;
        String[][] b = new String[m + 1][n + 1]; //b[i,j]指向的表项对应计算c[i,j]时所选的子问题最优解
        int[][] c = new int[m + 1][n + 1]; //c[m,n]保存了x和y的LAC长度
        for(int i = 0; i <= m; i++){
            c[i][0] = 0;
        }
        for(int i = 0; i <= n; i++){
            c[0][i] = 0;
        }
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(x[i] == y[j]){
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = "\\";
                } else if(c[i - 1][j] > c[i][j - 1]){
                    c[i][j] = c[i - 1][j];
                    b[i][j] = "|";
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = "—";
                }
            }
        }

        System.out.println("最长公共子序列长度：");
        System.out.println(c[x.length - 1][y.length - 1]);

        System.out.println("使用b辅助打印：");
        printLCS(b, x,x.length - 1, y.length - 1);

        System.out.println("\n不使用b辅助打印：");
        printLCS2(c, x, y, x.length - 1, y.length - 1);
        return c;
    }

    public void printLCS(String[][] b, String[] x, int i, int j){
        if(i == 0 || j == 0){
            return;
        }
        if(b[i][j] == "\\"){
            printLCS(b, x, i-1, j-1);
            System.out.print(x[i] + " ");
        } else if(b[i][j] == "|"){
            printLCS(b, x, i-1, j);
        } else {
            printLCS(b, x, i, j-1);
        }
    }

    //不用辅助数组b
    public void printLCS2(int[][] c, String[] x, String[] y, int i, int j){
       if(i == 0 || j == 0) return;
       if(x[i] == y[j]){
           printLCS2(c,x,y,i-1,j-1);
           System.out.print(x[i] + " ");
       } else if(c[i-1][j] > c[i][j-1]){
            printLCS2(c,x,y,i-1,j);
       } else {
           printLCS2(c,x,y,i,j -1);
       }
    }

    public static void main(String[] args) {
        //正式的字符串从1下标开始
        String[] x = new String[]{"-","B","D","C","A","B","A"};
        String[] y = new String[]{"-","A","B","C","B","D","A","B"};
        LCS l = new LCS();
        int[][] c = l.lcs(x,y);

    }
}
