package matchstring;

/**
 * 暴力枚举
 */
public class BruteForce {

    public static int match(String p, String t) {
        for (int s = 0; s <= t.length() - p.length(); s++) {
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == t.charAt(s+i) && i == p.length() - 1) {
                    return s;
                } else if(p.charAt(i) != t.charAt(s+i)){
                    break;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String p = "23";
        String t = "123";
        int i = match(p,t);
        System.out.println(i);
    }
}
