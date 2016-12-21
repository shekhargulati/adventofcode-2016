package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static adventofcode.Utils.toInt;

public class Day21 {

    public static void main(String[] args) throws IOException {

        String str = "abcdefgh";
        char[] input = str.toCharArray();

        List<String> lines = Files.readAllLines(Paths.get("src", "main", "resources", "problem21.txt"));

        for (String line : lines) {
            try {
                System.out.println(input);
                String[] parts = line.split("\\s");
                if (line.startsWith("swap position")) {

                    input = swap(input, toInt(parts[2]), toInt(parts[5]));

                } else if (line.startsWith("swap letter")) {

                    input = swap(input, parts[2].charAt(0), parts[5].charAt(0));

                } else if (line.startsWith("reverse positions")) {

                    input = reverse(input, toInt(parts[2]), toInt(parts[4]));

                } else if (line.startsWith("rotate left")) {

                    input = rotateLeft(input, toInt(parts[2]));

                } else if (line.startsWith("rotate right")) {

                    input = rotateRight(input, toInt(parts[2]));

                } else if (line.startsWith("move position")) {

                    input = move(input, toInt(parts[2]), toInt(parts[5]));

                } else if (line.startsWith("rotate based")) {

                    input = rotateByCh(input, parts[6].charAt(0));

                } else {
                    System.out.println("Can't understand line: " + line);
                }

                System.out.println(line);
                System.out.println(input);

                System.out.println();
                System.out.println();
            } catch (Exception e) {
                throw new RuntimeException(line, e);
            }
        }

        System.out.println(input);


//        char[] input2 = swap(input, 4, 0);
//        System.out.println(input2);
//        char[] input3 = swap(input2, 'd', 'b');
//        System.out.println(input3);
//        char[] input4 = reverse(input3, 0, 4);
//        System.out.println(input4);
//
//        char[] input5 = rotateLeft(input4, 1);
//        System.out.println(input5);
//
//        char[] input6 = move(input5, 1, 4);
//        System.out.println(input6);
//
//        char[] input7 = move(input6, 3, 0);
//        System.out.println(input7);
//
//        char[] input8 = rotateByCh(input7, 'b');
//        System.out.println(input8);
//
//
//        System.out.println(rotateByCh(input8, 'd'));

    }


    private static char[] swap(char[] input, int x, int y) {
        char tmp = input[x];
        input[x] = input[y];
        input[y] = tmp;
        return input;
    }

    private static char[] swap(char[] input, char x, char y) {
        String s = String.valueOf(input);
        return swap(input, s.indexOf(x), s.indexOf(y));
    }

    private static char[] reverse(char[] input, int x, int y) {
        char[] reversed = new StringBuilder(String.valueOf(Arrays.copyOfRange(input, x, y + 1))).reverse().toString().toCharArray();
        char[] result = new char[input.length];
        for (int i = 0; i < x; i++) {
            result[i] = input[i];
        }

        int j = 0;
        for (int i = x; i <= y; i++) {
            result[i] = reversed[j++];
        }

        for (int i = y + 1; i < input.length; i++) {
            result[i] = input[i];
        }
        return result;
    }

    private static char[] rotateLeft(char[] input, int steps) {
        char[] start = Arrays.copyOfRange(input, steps, input.length);

        char[] rem = Arrays.copyOfRange(input, 0, steps);

        char[] result = new char[input.length];

        for (int i = 0; i < start.length; i++) {
            result[i] = start[i];
        }
        int j = 0;
        for (int i = input.length - steps; i < input.length; i++) {
            result[i] = rem[j++];
        }
        return result;
    }

    private static char[] rotateRight(char[] input, int steps) {
        char[] fst = Arrays.copyOfRange(input, 0, steps);
        char[] snd = Arrays.copyOfRange(input, steps, input.length);

        char[] result = new char[input.length];
        int j = steps;
        for (int i = 0; i < steps; i++) {
            if (j == result.length) {
                j = 0;
            }
            result[j++] = fst[i];
        }

        for (int i = 0; i < snd.length; i++) {
            if (j == result.length) {
                j = 0;
            }
            result[j++] = snd[i];
        }
        return result;
    }

    private static char[] move(char[] input, int from, int to) {
        if (to > from) {
            char ch = input[from];
            for (int i = from; i < to; i++) {
                input[i] = input[i + 1];
            }
            input[to] = ch;
        } else {
            char ch = input[from];
            for (int i = from; i > to; i--) {
                input[i] = input[i - 1];
            }
            input[to] = ch;
        }

        return input;
    }

    private static char[] rotateByCh(char[] input, char ch) {
        int index = String.valueOf(input).indexOf(ch);

        if (index < 4) {
            int numberOfRotations = index + 1;
            for (int i = 0; i < numberOfRotations; i++) {
                input = rotateRight(input, 1);
            }
        } else {
            int numberOfRotations = index + 1 + 1;
            for (int i = 0; i < numberOfRotations; i++) {
                input = rotateRight(input, 1);
            }
        }

        return input;
    }
}
