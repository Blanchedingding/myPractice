package matchstring;

/**
 * Rabin-Karp算法的思想：
 *
 * 1. 假设待匹配字符串的长度为M，目标字符串的长度为N（N>M）；
 * 2. 首先计算待匹配字符串的hash值，计算目标字符串前M个字符的hash值；
 * 3. 比较前面计算的两个hash值，比较次数N-M+1：
 *      a. 若hash值不相等，则继续计算目标字符串的下一个长度为M的字符子串的hash值
 *      b. 若hash值相同，则需要使用朴素算法再次判断是否为相同的字串；
 */
public class RabinKarp {

    public static void RabinKarpAlogrithm(char[] T,char[] P, int d,int q){
        int n=T.length;
        int m=P.length;
        if( n < m) return ;
        int h = 1;
        for(int i=1; i<=m-1; i++)   // 计算高度 h = d^(m-1) mod q
            h = h*d%q;//m-1个d相乘然后模q
        //预处理，计算p， t
        int p=0, t=0;
        for(int i=0; i<m; i++) {
            p = (( d*p + P[i]) % q);
            t = (( d*t + T[i]) % q);
        }
        //开始匹配
        for(int s = 0; s < n-m+1; s ++) {
            if( p == t ){
                int i=0;
                for(i=0; i<m; i++)// 进一步验证
                    if(P[i]!=T[s+i])
                        break;
                if(i==m) System.out.println("Pattern occurs with shift:"+s);
            }
            if( s < n-m )
                t= (d* (t - T[s]*h%q) + T[s+m])  % q;  // 计算ts+1
        }
        System.out.println("string matching ends");
    }

    public static void main(String[] args){
        String strT="2359023141526739921";
        String strP="31415";
        char[] T=strT.toCharArray();
        char[] P=strP.toCharArray();
        int d=10; //看成10进制数
        int q=13; //一个素数
        RabinKarp.RabinKarpAlogrithm(T,P,d,q);
    }

}
