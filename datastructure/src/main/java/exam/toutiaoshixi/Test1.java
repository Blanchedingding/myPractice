package exam.toutiaoshixi;

import java.util.Scanner;

public class Test1 {

    //有1,4,14,64,元面值的纸币，给1024元买n元的东西，问找的钱张数最少是多少
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int left = 1024 - n;
        int a = left / 64;
        left -= a * 64;
        int b = left / 16;
        left -= b * 16;
        int c = left / 4;
        left -= c * 4;
        System.out.println(left + c + b + a);
    }
}
