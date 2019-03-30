package exam.toutiaoshixi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test3 {

    /**
     * 2
     * 2
     * 1 2
     * 4
     * 1 2 3 3
     *
     * output:
     * 3
     * 8
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for(int i =0; i < n; i++){
            int s = in.nextInt();
            int[] a = new int[s];
            for(int j = 0; j < s; j++){
                a[j] = in.nextInt();
            }
            list.add(helper(s, a));
        }
        for(Integer i: list){
            System.out.println(i);
        }
    }

    public static int helper(int s, int[] a){
        int result = 0;
        int[] Mcount = new int[s];
        Arrays.fill(Mcount, 1);
        if(s >2) {
            while(true){
                boolean flag = true;
                for(int i = 0; i < s; i++){
                    int pre = ( i - 1 + s) % s;
                    int next = (i +1) % s;
                    if(a[i] > a[pre] && a[i] > a[next]){
                        int t = Math.max(Mcount[pre], Mcount[next]) + 1;
                        if(Mcount[i] < t){
                            flag = false;
                            Mcount[i] = t;
                        }
                    } else if(a[i] > a[pre]){
                        if(Mcount[i] < Mcount[pre] + 1){
                            flag = false;
                            Mcount[i] = Mcount[pre] + 1;
                        }
                    } else if(a[i] > a[next]){
                        if(Mcount[i] < Mcount[next] + 1){
                            flag = false;
                            Mcount[i] = Mcount[next] + 1;
                        }
                    }
                }
                if(flag){
                    break;
                }
            }
        }
        else if(s==2){
            if(a[0]>a[1]) {
                Mcount[0]=Mcount[1]+1;
            }
            else if(a[0]<a[1]) {
                Mcount[1]=Mcount[0]+1;
            }
        }
        else if(s==1) {
            Mcount[0]=1;
        }
        System.out.println(Arrays.toString(Mcount));
        for(int i=0;i<s;i++) {
            result +=Mcount[i];
        }
        return result;
    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        List<Integer> list = new ArrayList<>();
//        for(int i =0; i < n; i++){
//            int s = in.nextInt();
//            int[] a = new int[s];
//            for(int j = 0; j < s; j++){
//                a[j] = in.nextInt();
//            }
//            list.add(helper(s, a));
//        }
//        for(Integer i: list){
//            System.out.println(i);
//        }
//    }
//
//    public static int helper(int n, int[] a){
//        if(n <= 0) return 0;
//        int[] left = new int[n];
//        boolean hasOne = false;
//        int count = 0;
//        for(int i = 0; i < n ; i++){
//            int pre = (i - 1 + n) % n;
//            int next = (i + 1) % n;
//            if(a[i] < a[pre] && a[i] < a[next]){
//                left[i] = 1;
//                hasOne = true;
//                count ++;
//            }
//        }
//        if( ! hasOne){
//            return n;
//        }
//        int begin = 0;
//        while(begin < n && left[begin] != 1){
//            begin ++;
//        }
//        begin ++;
////        System.out.println("begin=" + begin);
//        int index = begin;
//        while(count < n){
//            index = index % n;
//            if(left[index] != 0) continue;
//            int pre = (index - 1 + n) % n;
//            int next = (index + 1) % n;
//            if(a[index] > a[pre] && a[index] > a[next] ){
//                if(left[pre] != 0 && left[next] != 0){
//                    left[index] = Math.max(left[pre], left[next]) + 1;
//                    count ++;
//                } else if(left[pre] != 0){
//                    left[index] = left[pre] + 1;
//                    count ++;
//                } else if(left[next] != 0){
//                    left[index] = left[next] + 1;
//                    count ++;
//                }
//            } else if(a[index] >= a[pre] && a[index] <= a[next] && left[pre] != 0){
//                if(a[index] == a[pre]) {
//                    left[index] = left[pre];
//                } else{
//                    left[index] = left[pre] + 1;
//                }
//                count ++;
//            } else if(a[index] >= a[next] && a[index] <= a[pre] && left[next] != 0){
//                if(a[index] == a[next]) {
//                    left[index] = left[next];
//                } else{
//                    left[index] = left[next] + 1;
//                }
//                count ++;
//            }
//            index ++;
//        }
//
//        int sum = 0;
//        for(int i = 0; i < n; i++){
//            sum += left[i];
//        }
//        return sum;
//    }
}
