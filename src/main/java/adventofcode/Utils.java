package adventofcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String md5(final String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                String hex = Integer.toHexString(0xFF & digest[i]);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> zip(List<String> strings) {
        List<String> result = new ArrayList<>();
        int length = strings.get(0).length();
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            for (String s : strings) {
                sb.append(s.charAt(i));
            }
            result.add(sb.toString());
        }
        return result;

    }

}