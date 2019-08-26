package exam.shixi.pinduoduo;

import java.util.*;

/**
 * 3 4
 *
 * CAKE
 * TORN
 * SHOW
 *
 * 每一位取几个单词相应位的一个字母，返回字典序第一个，没有就返回"-"
 */
public class Test4 {

//    public static String helper(ArrayList<String> t, int N, int L, String s, int index){
//        if(s.length() == L && !t.contains(s)) return s;
//        if(s.length() == L) return "";
//        ArrayList<String> k = new ArrayList<String>();
//        for(int i = 0; i < N; i++){
//            String p = helper(t, N, L, s + t.get(i).charAt(index), index + 1);
//            if(!"".equals(p)){
//                k.add(p);
//            }
//        }
//        if(k.isEmpty()) return "-";
//        else {
//           String[] y = new String[k.size()];
//           for(int u = 0; u < k.size(); u ++){
//               y[u] = k.get(u);
//           }
//           Arrays.sort(y);
//           return y[0];
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int L = sc.nextInt();
//        ArrayList<String> t = new ArrayList<>();
//        for(int i = 0; i < N; i++){
//            t.add(sc.next());
//        }
//        System.out.println(helper(t, N, L, "", 0));
//    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] words = input.split(" ");
        int N = Integer.parseInt(words[0]);
        int L = Integer.parseInt(words[1]);
        boolean[][] digits = new boolean[L][26];
        Map<String, Integer> map = new HashMap<>(N);
        for (int i = 0; i < N; i++) {
            String s = in.next();
            map.put(s, 1);
            for (int j = 0; j < L; j++)
                digits[j][s.charAt(j) - 'A'] = true;
        }
        String res = helper(digits, 0, L, new StringBuilder(""), map);
        if (res.equals(""))
            System.out.println("-");
        else
            System.out.println(res);
    }


    public static String helper(boolean[][] digits, int start, int target, StringBuilder sb, Map<String, Integer> map) {
        if (start < target) {
            for (int i = 0; i < 26; i++) {
                if (digits[start][i]) {
                    sb.append((char)('A' + i));
                    String res = helper(digits, start + 1, target, sb, map);
                    if (!res.equals(""))
                        return res;
                    sb.setLength(sb.length() - 1);
                }
            }
            return sb.toString();
        }
        else {
            if (!map.containsKey(sb.toString()))
                return sb.toString();
            else
                return "";
        }
    }

}
