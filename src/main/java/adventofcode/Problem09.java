package adventofcode;

import strman.Strman;

import java.nio.file.Files;
import java.nio.file.Paths;

import static adventofcode.Utils.toInt;

public class Problem09 {

    public static void main(String[] args) throws Exception {
        String input = Files.readAllLines(Paths.get("src", "test", "resources", "problem09.txt")).get(0);
//        String input = "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN";

        System.out.println(doSth(input));
    }

    private static long doSth(String input) {
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        long length = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (!Character.isWhitespace(ch)) {
                if (ch == '(') {
                    StringBuilder markerBuilder = new StringBuilder();
                    while (ch != ')') {
                        ch = chars[++i];
                        if (ch != ')') {
                            markerBuilder.append(ch);
                        }
                    }
                    String[] parts = markerBuilder.toString().trim().split("x");
                    int a = toInt(parts[0]);
                    int b = toInt(parts[1]);
                    StringBuilder s = new StringBuilder();
                    for (int j = i + 1; j < i + 1 + a; j++) {
                        s.append(chars[j]);
                    }
                    String innerExpression = Strman.repeat(s.toString(), b);
                    length += doSth(innerExpression);
                    i += a;
                } else {
                    result.append(ch);
                    length++;
                }
            }
        }

        return length;
    }
}


class Marker {

    final int a;
    final int b;

    public Marker(String str) {
        String[] parts = str.trim().split("x");
        a = toInt(parts[0]);
        b = toInt(parts[1]);
    }

    @Override
    public String toString() {
        return "Marker{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}