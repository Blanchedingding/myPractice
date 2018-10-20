package leetcode.medium;

/**
 * Given a non-empty string containing an out-of-order English representation of digits 0-9,
 * output the digits in ascending order.
 *
 * Note:
 * Input contains only lowercase English letters.
 * Input is guaranteed to be valid and can be transformed to its original digits.
 * That means invalid inputs such as "abc" or "zerone" are not permitted.
 * Input length is less than 50,000.
 *
 * Example 1:
 * Input: "owoztneoer"
 * Output: "012"
 *
 * Example 2:
 * Input: "fviefuro"
 * Output: "45"
 *
 * 0:zero
 * 1:one
 * 2:two
 * 3:three
 * 4:four
 * 5:five
 * 6:six
 * 7:seven
 * 8:eight
 * 9:nine
 */
public class OriginalDigits {

    public String originalDigits(String s) {
        int[] count = new int[10];
        for(int i = 0; i < s.length(); i++){
            switch(s.charAt(i)){
                case 'z':{
                    count[0] ++;
                    break;
                }
                case 'w':{
                    count[2] ++;
                    break;
                }
                case 'u':{
                    count[4] ++;
                    break;
                }
                case 'x':{
                    count[6] ++;
                    break;
                }
                case 'g':{
                    count[8] ++;
                    break;
                }
                case 's':{ //7+6
                    count[7] ++;
                    break;
                }
                case 'f':{ //5+4
                    count[5] ++;
                    break;
                }
                case 'i':{ //9+5+6+8
                    count[9] ++;
                    break;
                }
                case 't':{ //3+2+8
                    count[3] ++;
                    break;
                }
                case 'o':{ //1+0+2+4
                    count[1] ++;
                    break;
                }
            }
        }

        count[7] -= count[6];
        count[5] -= count[4];
        count[9] = count[9] - count[5] - count[6] - count[8] ;
        count[3] = count[3] - count[2] - count[8];
        count[1] = count[1] - count[0] - count[2] - count[4];
        StringBuilder result = new StringBuilder();
        for(int i = 0; i <= 9; i ++){
            for(int j = 0; j < count[i]; j++){
                result.append(i);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        OriginalDigits d = new OriginalDigits();
        System.out.println(d.originalDigits("owoztneoer"));
    }
}
