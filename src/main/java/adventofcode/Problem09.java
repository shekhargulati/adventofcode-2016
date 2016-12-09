package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;

import static adventofcode.Utils.toInt;
import static strman.Strman.repeat;

public class Problem09 {

    public static void main(String[] args) throws Exception {
        String input = Files.readAllLines(Paths.get("src", "test", "resources", "problem09.txt")).get(0);
        long startTime1 = System.currentTimeMillis();
        System.out.println(String.format("part 1 %d", decompressedLength(input, false))); // Answer is 98135
        long endTime1 = System.currentTimeMillis();
        System.out.println("Total time taken: " + (endTime1 - startTime1) / 1000 + " seconds");


        long startTime2 = System.currentTimeMillis();
        System.out.println(String.format("part 2 %d", decompressedLength(input, true))); // Answer is 10964557606
        long endTime2 = System.currentTimeMillis();
        System.out.println("Total time taken: " + (endTime2 - startTime2) / 1000 + " seconds");
    }

    private static long decompressedLength(String input, boolean part2) {
        long length = 0;
        char[] chars = input.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            char ch = chars[index];
            if (!Character.isWhitespace(ch)) {
                if (ch == '(') {
                    StringBuilder marker = new StringBuilder();
                    while (ch != ')') {
                        ch = chars[++index];
                        if (ch != ')') {
                            marker.append(ch);
                        }
                    }
                    String[] parts = marker.toString().trim().split("x");
                    int take = toInt(parts[0]);
                    int repetitions = toInt(parts[1]);
                    String expression = mkString(chars, index + 1, index + 1 + take);
                    length += part2
                            ? decompressedLength(expression, part2) * repetitions
                            : repeat(expression, repetitions).length();
                    index += take;
                } else {
                    length++;
                }
            }
        }
        return length;
    }

    private static String mkString(char[] chars, int start, int end) {
        StringBuilder builder = new StringBuilder();
        for (int j = start; j < end; j++) {
            builder.append(chars[j]);
        }
        return builder.toString();
    }
}