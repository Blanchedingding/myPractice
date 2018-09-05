package exam.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String a = in.nextLine();
        String b = in.nextLine();

        int l1 = a.length();
        int l2 = b.length();

        String longS, shortS;
        if(l1 >= l2) {
            longS = a;
            shortS = b;
        }else {
            longS = b;
            shortS = a;
        }

        int c1, c2, sum;
        char single;
        List<Character> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean plusOne = false;
        for(int i = 0; i < shortS.length(); i++) {
            c1 = shortS.charAt(shortS.length() - 1 - i) - 'a';
            c2 = longS.charAt(longS.length() - 1 - i) - 'a';
            sum = c1 + c2;
            if(plusOne)
                sum += 1;
            if(sum >= 26) {
                single = (char)(sum - 26 + 'a');
                plusOne = true;
            }else {
                single = (char)(sum + 'a');
                plusOne = false;
            }
            list.add(single);
        }
        for(int i = shortS.length(); i < longS.length(); i++) {
            c1 = longS.charAt(longS.length() - 1 - i) - 'a';
            if(plusOne) {
                sum = c1 + 1;
            }else {
                sum = c1;
            }
            if(sum >= 26) {
                single = (char)(sum - 26 + 'a');
                plusOne = true;
            }else {
                single = (char)(sum + 'a');
                plusOne = false;
            }
            list.add(single);
        }
        if(plusOne)
            list.add('a');

        for(int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i));
        }

        System.out.println(sb.toString());
    }

}