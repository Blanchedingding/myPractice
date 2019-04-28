package commonProblem;

//求两个字符串的最长公共子串
//子串是连续的，子序列不是连续的
//https://blog.csdn.net/qq_25800311/article/details/81607168
public class LongestCommonSubstring {

    public static String helper(String x, String y){
        int[][] r = new int[x.length() + 1][y.length() + 1];
        int maxLength = -1, maxEnd = -1;
        for(int i = 1; i <= x.length(); i++){
            for(int j = 1; j <= y.length(); j++){
                if(x.charAt(i) == y.charAt(j)){
                    r[i][j] = r[i - 1][j - 1] + 1;
                } else {
                    r[i][j] = 0;
                }
                if(maxLength < r[i][j]){
                    maxLength = r[i][j];
                    maxEnd = j;
                }
            }
        }
        return y.subSequence(maxEnd - maxLength, maxEnd).toString();
    }

    public static void main(String[] args) {
        System.out.println(helper("123djer670sh", "4sh921er670cnhswg123"));
    }
}
