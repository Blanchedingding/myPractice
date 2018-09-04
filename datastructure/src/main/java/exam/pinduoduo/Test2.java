package exam.pinduoduo;

import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();
        char[][] s = new char[N][M];
        for(int i = 0; i < N; i++){
            String t = sc.nextLine();
            s[i] = t.toCharArray();
        }
//        for(int i = 0; i < N ; i++){
//            for(int j = 0; j < M; j++){
//                System.out.print(s[i][j] + " ");
//            }
//            System.out.println();
//        }

        for(int i = 0; i < M; i++){
            int hang = N -1;
            int time = 1;
            while(hang >= 0 && time <= N){
                if(s[hang][i] == '.'){
                    for(int k = hang; k > 0; k --){
                        if(s[k][i] == '.' && s[k-1][i] == 'o'){
                            s[k][i] = 'o';
                            s[k-1][i] = '.';
                        }
                    }
                } else if(s[hang][i] == 'o'){
                    if(hang == N -1){
                        s[hang][i] = '.';
                        for(int k = hang; k > 0; k --){
                            if(s[k][i] == '.' && s[k-1][i] == 'o'){
                                s[k][i] = 'o';
                                s[k-1][i] = '.';
                            }
                        }
                    } else {
                        hang --;
                    }
                } else {
                    hang --;
                }

                System.out.println();
                for(int p = 0; p < N ; p++){
                    for(int q = 0; q < M; q++){
                        System.out.print(s[p][q] + " ");
                    }
                    System.out.println();
                }
                time ++;
            }
        }

//        System.out.println();
//        for(int i = 0; i < N ; i++){
//            for(int j = 0; j < M; j++){
//                System.out.print(s[i][j] + " ");
//            }
//            System.out.println();
//        }

    }
}
