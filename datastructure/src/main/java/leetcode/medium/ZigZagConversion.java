package leetcode.medium;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class ZigZagConversion {

    public String convert(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }

    public String convert2(String s, int numRows) {
        if(numRows == 1) return s;
        int n = s.length();
        int row = 0, order = 1;//1:down;2:up
        StringBuffer[] t = new StringBuffer[numRows];
        for (int i = 0; i < t.length; i++) t[i] = new StringBuffer();
        for(int i = 0; i < n; i++){
            t[row].append(s.charAt(i));
            if(order == 1 && row == numRows - 1){
                order = 2;
                row --;
            } else if(order == 2 && row == 0){
                row ++;
                order = 1;
            } else if(order == 1){
                row ++;
            } else {
                row --;
            }
        }
        for(int i = 1; i < numRows; i++){
            t[0].append(t[i]);
        }
        return t[0].toString();
    }

    public static void main(String[] args) {
        ZigZagConversion z = new ZigZagConversion();
        System.out.println(z.convert("AB", 2));
        System.out.println(z.convert("PAYPALISHIRING", 4));
    }
}
