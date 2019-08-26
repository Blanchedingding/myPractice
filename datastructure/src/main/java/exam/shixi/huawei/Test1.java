package exam.shixi.huawei;

import java.util.*;

public class Test1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        char[] str = input.toCharArray();
        String[] items = input.split("\\d+");
        Pair[] pairs = new Pair[items.length];
        int start = 0;
        for (int i = 0; i < items.length; i++) {
            start += items[i].length();
            int end = start + 1;
            while (end < str.length && str[end] <= '9' && str[end] >= '0') {
                end++;
            }
            int count = Integer.parseInt(input.substring(start, end));
            start = end;
            Pair p = new Pair(count, items[i]);
            pairs[i] = p;
        }
        Arrays.sort(pairs);
        StringBuilder sb = new StringBuilder();
        for (Pair p : pairs)
            sb.append(p.toString());
        System.out.println(sb.toString());
    }
}


    class Pair implements Comparable<Pair>{
        int count;
        String value;

        public Pair(int count, String value){
            this.count =  count;
            this.value =  value;
        }
        @Override
        public int compareTo(Pair o) {
            if (this.count >  o.count) return this.count-o.count;
            else return value.compareTo(o.value);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<count; i++)
                sb.append(value);
            return sb.toString();
        }
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        char[] s= sc.nextLine().toCharArray();
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        String number = "";
//        String word = "";
//        int flag = 1; //1为字母，0为数字
//        for(int i = 0; i < s.length; i++){
//            if(flag == 1){
//                if(s[i] >= '0' && s[i] <= '9'){
//                    number = "" + s[i];
//                    flag = 0;
//                } else {
//                    word += s[i];
//                }
//            } else {
//                if(s[i] >= '0' && s[i] <= '9'){
//                    number += s[i];
//                } else {
//                    if(map.containsKey(word)){
//                        map.put(word, map.get(word) +Integer.valueOf(number));
//                    } else {
//                        map.put(word, Integer.valueOf(number));
//                    }
//                    word = "" + s[i];
//                    number = "";
//                    flag = 1;
//                }
//            }
//        }
//        map.put(word, Integer.valueOf(number));
////        System.out.println(map);
//        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                if(o1.getValue() == o2.getValue()){
//                    return o1.getKey().hashCode() - o2.getKey().hashCode();
////                    if(o1.getKey().length() == o2.getKey().length()){
////                        return o1.getKey().compareTo(o2.getKey());
////                    } else{
////                        return o1.getKey().length() - o2.getKey().length();
////                    }
//                } else {
//                    return o1.getValue().compareTo(o2.getValue());
//                }
//            }
//        });
//        for(Map.Entry<String,Integer> e: list){
//            for(int i = 0; i < e.getValue(); i++){
//                System.out.print(e.getKey());
//            }
//        }
//
//    }
//}
