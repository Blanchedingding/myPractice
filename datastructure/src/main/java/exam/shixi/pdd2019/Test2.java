package exam.shixi.pdd2019;

import java.util.*;

public class Test2 {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String s = in.nextLine();
//        char[] c = s.toCharArray();
//        int n = s.length();
//        for(int i = 0; i < n; i++){
//            if(c[i] < 97){
//                c[i] = (char)(c[i] + 32);
//            }
//        }
//        boolean[] isRepeat = new boolean[n];
//        Map<Character, Integer> map = new HashMap<>();
//        for(int i =0; i < n; i++){
//           if(map.get(c[i]) != null){
//               isRepeat[map.get(c[i])] = true;
//           } else {
//               map.put(c[i], i);
//           }
//        }
//        System.out.println(map);
//        System.out.println(Arrays.toString(isRepeat));
//        int result = (int)c[0];
//        for(int i = 0; i < n; i ++){
//            if(isRepeat[i] && i + 1 < n){
//                result = Math.min(result, (int)c[i + 1]);
//            } else {
//                break;
//            }
//        }
//        System.out.println((char)result);

        System.out.println('a');
        System.out.println((int)'a');
        Map<Integer, Integer> map = new HashMap<>();
        String s = "abduch";
        char[] c = s.toCharArray();
        System.out.println(map.get(c[0]));

    }

}
