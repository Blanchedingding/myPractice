package commonProblem;

//求两个字符串的最长公共子序列
//子串是连续的，子序列不是连续的
public class LongestCommonSubsquence {

    public static String helper(String x, String y){
        x = "1" + x;
        y = "2" + y;
        int[][] r = new int[x.length()][y.length()];
        char[][] p = new char[x.length()][y.length()];
        for(int i = 1; i < x.length(); i++){
            for(int j = 1; j < y.length(); j++){
                if(x.charAt(i) == y.charAt(j)){
                    r[i][j] = r[i-1][j-1] + 1;
                    p[i][j] = '\\';
                } else if(r[i-1][j] > r[i][j-1]){
                    r[i][j] = r[i-1][j];
                    p[i][j] = '|';
                } else {
                    r[i][j] = r[i][j - 1];
                    p[i][j] = '-';
                }
            }
        }
        String s = "";
        int i = x.length() -1, j = y.length() - 1;
        while(i > 0 && j > 0){
            if(x.charAt(i) == y.charAt(j)){
                s = x.charAt(i) + s;
            }
            char c = p[i][j];
            if(c == '\\'){
                i --;
                j --;
            } else if(c == '|'){
                i --;
            } else {
                j --;
            }
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(helper("bdcaba", "abcbdab"));
    }
}
