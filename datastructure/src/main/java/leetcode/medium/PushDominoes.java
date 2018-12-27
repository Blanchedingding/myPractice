package leetcode.medium;

/**
 * There are N dominoes in a line, and we place each domino vertically upright.
 *
 * In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left.
 * Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 *
 * When a vertical domino has dominoes falling on it from both sides,
 * it stays still due to the balance of the forces.
 *
 * For the purposes of this question,
 * we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 *
 * Given a string "S" representing the initial state.
 * S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R',
 * if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
 * Return a string representing the final state.
 *
 * Example 1:
 * Input: ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 *
 * Example 2:
 * Input: "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second domino.
 * Note:
 *
 * 0 <= N <= 10^5
 * String dominoes contains only 'L', 'R' and '.'
 */
public class PushDominoes {

    /**
     * Here is another idea that focus on 'L' and 'R'.
     * 'R......R' => 'RRRRRRRR'
     * 'R......L' => 'RRRRLLLL' or 'RRRR.LLLL'
     * 'L......R' => 'L......R'
     * 'L......L' => 'LLLLLLLL'
     * @param d
     * @return
     */
    public String pushDominoes2(String d) {
        d = 'L' + d + 'R';
        StringBuilder res = new StringBuilder();
        for (int i = 0, j = 1; j < d.length(); ++j) {
            if (d.charAt(j) == '.') continue;
            int middle = j - i - 1;
            if (i > 0) res.append(d.charAt(i));
            if (d.charAt(i) == d.charAt(j))
                for (int k = 0; k < middle; k++) res.append(d.charAt(i));
            else if (d.charAt(i) == 'L' && d.charAt(j) == 'R')
                for (int k = 0; k < middle; k++) res.append('.');
            else {
                for (int k = 0; k < middle / 2; k++) res.append('R');
                if (middle % 2 == 1) res.append('.');
                for (int k = 0; k < middle / 2; k++) res.append('L');
            }
            i = j;
        }
        return res.toString();
    }


    public String pushDominoes(String dominoes) {
        char[] a = dominoes.toCharArray();
        int n = a.length;
        while(true){
            boolean flag = true;//是否已经没有可以推动的牌了
            char[] b = a.clone();
            for(int i = 0; i < n; i++){
                if('.' == a[i]){
                    if(i - 1 >= 0 && a[i - 1] == 'R' && (i + 1 >= n || a[i + 1] != 'L')){
                        b[i] = 'R';
                        flag = false;
                    } else if(i + 1 < n && a[i + 1] == 'L' && (i - 1 < 0 || a[i - 1] != 'R')){
                        b[i] = 'L';
                        flag = false;
                    }
                }
            }
            a = b.clone();
            if ( flag ) break;
        }
        return String.valueOf(a);
    }

    public static void main(String[] args) {
        PushDominoes p =  new PushDominoes();
        System.out.println(p.pushDominoes(".L.R...LR..L.."));
    }
}
