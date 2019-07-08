package matchstring;

/**
 * 时间复杂度O(m+n)。
 *
 * 如果用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量的时间，
 * 所以，kmp方法算法就利用之前判断过信息，通过一个next数组，保存模式串中前后最长公共子序列的长度，
 * 每次回溯时，通过next数组找到，前面匹配过的位置，省去了大量的计算时间。
 */
public class KMP {

    public static int kmp2(String str, String dest,int[] next){//str文本串  dest 模式串
        for(int i = 0, j = 0; i < str.length(); i++){
            while(j > 0 && str.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if(str.charAt(i) == dest.charAt(j)){
                j++;
            }
            if(j == dest.length()){
                return i-j+1;
            }
        }
        return 0;
    }

    public static int kmp(String str, String dest,int[] next){//str文本串  dest 模式串
        for(int i = 0, j = 0; i < str.length(); i++){
            while(j > 0 && str.charAt(i) != dest.charAt(j)){
                j = next[j - 1] + 1;
            }
            if(str.charAt(i) == dest.charAt(j)){
                j++;
            }
            if(j == dest.length()){
                return i-j+1;
            }
        }
        return -1;
    }


    public static int[] kmpnext2(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        for(int i = 1,j = 0; i < dest.length(); i++){
            while(j > 0 && dest.charAt(j) != dest.charAt(i)){
                j = next[j - 1];
            }
            if(dest.charAt(j) == dest.charAt(i)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int[] kmpnext(String dest){
        int[] next = new int[dest.length()];
        next[0] = -1;
        for(int i = 1,j = 0; i < dest.length(); i++){
            while(j > 0 && dest.charAt(j) != dest.charAt(i)){
                j = next[j - 1] + 1;
            }
            if(dest.charAt(j) == dest.charAt(i)){
                next[i] = j;
                j++;
            } else {
                next[i] = next[j];
            }
        }
        return next;
    }

    public static void main(String[] args){
        String a = "ababa";
        String b = "ssdfgasdbababa";
        int[] next = kmpnext2(a);
        int res = kmp2(b, a,next);
        System.out.println(res);
        for(int i = 0; i < next.length; i++){
            System.out.println(next[i]);
        }
        System.out.println(next.length);
    }

}
