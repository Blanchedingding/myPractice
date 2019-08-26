package exam.qiuzhao.toutiao;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给出一个分子式，比如： HMg2(H2O(N3Ag)2)3N2 计算这个分子式中每个原子出现了多少次，
 * 输出一个 map，比如上面的分子式中： map<string, int> {"H": 7, "Mg": 2,"Ag": 6, "N": 20，"O": 3}
 *
 *
 * 分子式的规则： 1. 都以大写字母开头，后面跟 0 个或者 1 个小写字母，比如 Mg, H 2.
 * 单个原子后面跟 0 个或者 1 个数字表示它出现的次数，比如 Mg2 表示 Mg 出现 2 次，数字范围 2-1000 3.
 * 分子式中可能有括号，括号后面可能跟 0 个或者 1 个数字表示整个括号内的原子出现的次数，
 * 比如 (N3Ag)2 表示 N出现 6 次，Ag 出现 2 次 4. 括号可以嵌套
 */
public class Test1 {

    public static void main(String[] args) {
        Test1 t = new Test1();
        System.out.println(t.getMap("HMg2(H2O(N3Ag)2)3N2"));

    }


    Map<String, Integer> result = new HashMap<>();
    Map<Integer, Map<String, Integer>> level = new HashMap<>();
    int curLevel = 0;

    public Map<String, Integer> getMap(String s){
        int n = s.length();
        for(int i = 0; i < n; i++){
//            for(Integer j: level.keySet()){
//                System.out.println(level.get(j));
//            }
            char c = s.charAt(i);
            if(Character.isUpperCase(c)){
                if(i + 1 < n){
                    char c2 = s.charAt(i + 1);
                    if(Character.isDigit(c2)){
                        i++;
                        add(String.valueOf(c), c2-'0');
                    } else if(Character.isLowerCase(c2)){
                        i++;
                        if(i + 1 < n){
                            char c3 = s.charAt(i + 1);
                            if(Character.isDigit(c3)){
                                i++;
                                add(String.valueOf(c) + String.valueOf(c2), c3-'0');
                            } else {
                                add(String.valueOf(c) + String.valueOf(c2), 1);
                            }
                        } else {
                            add(String.valueOf(c) + String.valueOf(c2), 1);
                        }
                    } else {
                        add(String.valueOf(c), 1);
                    }
                } else {
                    add(String.valueOf(c), 1);
                }
            } else if(c == '('){
                curLevel ++;
            } else if(c == ')'){
                int y = 1;
                if(i + 1 < n){
                    char c5 = s.charAt(i + 1);
                    if(Character.isDigit(c5)){
                        i++;
                        y = c5-'0';
                    }
                }
                pop(y);
                curLevel --;
            }
        }

        return level.get(0);
    }

    private void add(String s, int count){
//        System.out.println("s="+s+" count="+count);
        level.putIfAbsent(curLevel, new HashMap<>());
        Map<String, Integer> a = level.get(curLevel);
        a.putIfAbsent(s, 0);
        a.put(s, a.get(s) + count);
    }

    private void pop(int y){
        Map<String, Integer> a = level.get(curLevel);
        Map<String, Integer> b = level.get(curLevel - 1);
        for(String s: a.keySet()){
            b.putIfAbsent(s, 0);
            b.put(s, b.get(s) + a.get(s) * y);
        }
        level.remove(curLevel);
    }

    public int getCount(String func) {
        int cur = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < func.length()) {
            if (func.charAt(i) == '(') {
                stack.push(cur);
                cur = 0;
                i++;
            } else if (func.charAt(i) == ')') {
                int num = 0;
                while (++i < func.length() && Character.isDigit(func.charAt(i)))
                    num = num * 10 + (func.charAt(i) - '0');
                cur *= num;
                cur = cur + stack.pop();
            } else {
                if (i < func.length() - 1) {
                    String atom = func.substring(i, i + 2);
                    if (atom.equals("Ca"))
                        i += 2;
                    else
                        i++;
                }else
                    i++;
                if (i == func.length()) {
                    cur++;
                    break;
                }
                if (!Character.isDigit(func.charAt(i))) {
                    cur++;
                } else {
                    int num = 0;
                    while (i < func.length() && Character.isDigit(func.charAt(i)))
                        num = num * 10 + (func.charAt(i++) - '0');
                    cur = cur + num;
                }

            }
        }
        return cur;
    }

}
