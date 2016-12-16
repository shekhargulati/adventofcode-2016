package adventofcode;

import java.util.Objects;

public class Day16 {

    public static void main(String[] args) {
        /*
        1. Generate string greater or equal to disc length
         2. Take chars equal to disc length
         3. Calculate checksum
         4 if even calculate till we have even
         5 else return checksum
         */

        int discLength = 35651584;
        String initial = "01111001100111011";
        String elongated = elongate(initial, discLength);
        System.out.println(elongated);
        String nextInput = elongated.substring(0, discLength);
        System.out.println(checksum(nextInput));
    }

    private static String checksum(String nextInput) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nextInput.length(); i += 2) {
            String pair = nextInput.substring(i, i + 2);
            if (Objects.equals(pair, "00") || Objects.equals(pair, "11")) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        if (sb.length() % 2 == 0) {
            return checksum(sb.toString());
        }
        return sb.toString();
    }

    private static String elongate(String initial, int discLength) {
        while (initial.length() < discLength) {
            StringBuilder resultBuilder = new StringBuilder();
            String a = initial;
            String b = new String(a);
            for (int i = b.length() - 1; i >= 0; i--) {
                if (b.charAt(i) == '0') {
                    resultBuilder.append("1");
                } else {
                    resultBuilder.append("0");
                }
            }

            initial = a + "0" + resultBuilder.toString();
        }
        return initial;

    }
}
