package leetcode;

import java.util.*;

public class TreeTest {

    public static void main(String[] args) {
        List<String> rawList = new ArrayList();

        Object[] strings = new String[2];
        strings[0] = "hi";   // OK
//        strings[1] = 100;

        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // true

        Deque<Integer> d = new LinkedList<>();
        int a = 0;

        List<Integer> l = Arrays.asList(new Integer[]{1,2,3});

     }
}
