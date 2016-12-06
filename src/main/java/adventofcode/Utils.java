package adventofcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

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
        int length = strings.get(0).length();
        return IntStream
                .range(0, length)
                .mapToObj(i -> {
                    StringBuilder sb = new StringBuilder();
                    for (String s : strings) {
                        sb.append(s.charAt(i));
                    }
                    return sb.toString();
                })
                .collect(toList());
    }

}