package api;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5 {
    public String encode(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] b = md.digest();
            System.out.println(Arrays.toString(b));
            System.out.println(new BigInteger(1, b));
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        String input = "2333333";
        MD5 md5 = new MD5();
        String output = md5.encode(input);
        System.out.println(output);
    }
}
