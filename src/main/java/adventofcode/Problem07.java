package adventofcode;

import strman.Strman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Problem07 {

    public static long count_part1(List<String> lines) {
        return lines.stream().filter(Problem07::isTls).count();
    }

    public static long count_part2(List<String> lines) {
        return lines.stream().filter(Problem07::isSsl).count();
    }

    private static boolean isTls(String ipAddress) {
        String[] hypernetSequences = hypernetSequences(ipAddress);
        if (Arrays.stream(hypernetSequences).filter(Problem07::hasPalindrome).findAny().isPresent()) {
            return false;
        }
        String[] supernetSequences = supernetSequences(ipAddress);
        return Arrays.stream(supernetSequences).filter(Problem07::hasPalindrome).findAny().isPresent();
    }

    private static boolean isSsl(String input) {
        String[] supernetSequences = supernetSequences(input);
        List<String> abaSequences = new ArrayList<>();
        for (String string : supernetSequences) {
            populateAllAbaSequences(string, abaSequences);
        }
        if (abaSequences.isEmpty()) {
            return false;
        }
        String[] hypernetSequences = hypernetSequences(input);
        return Arrays.stream(hypernetSequences).filter(sequence -> abaSequences.stream().filter(s1 -> hasBabSequence(sequence, s1.charAt(0), s1.charAt(1))).findAny().isPresent()).findAny().isPresent();
    }

    private static String[] hypernetSequences(String input) {
        String[] between1 = Strman.between(input, "[", "]");
        return Arrays.copyOfRange(between1, 0, between1.length - 1);
    }

    private static String[] supernetSequences(String input) {
        String[] parts = input.split("]");
        String start = "[";
        return Arrays.stream(parts).map(subPart -> {
            if (!subPart.contains(start)) {
                return subPart;
            }
            return subPart.substring(0, subPart.indexOf(start));
        }).toArray(String[]::new);
    }

    public static void populateAllAbaSequences(String str, List<String> list) {
        if (str.length() < 3) {
            return;
        }
        String substring = str.substring(0, 3);
        if ((substring.charAt(0) == substring.charAt(2) && substring.charAt(0) != substring.charAt(1)) && Arrays.stream(substring.split("")).distinct().count() == 2) {
            list.add(substring);
        }
        populateAllAbaSequences(str.substring(1), list);
    }

    public static boolean hasBabSequence(String str, char a, char b) {
        if (str.length() < 3) {
            return false;
        }
        String substring = str.substring(0, 3);
        if ((substring.charAt(0) == b && substring.charAt(2) == b && substring.charAt(1) == a) && Arrays.stream(substring.split("")).distinct().count() == 2) {
            return true;
        }
        return hasBabSequence(str.substring(1), a, b);
    }

    public static boolean hasPalindrome(String str) {
        if (str.length() < 4) {
            return false;
        }
        String substring = str.substring(0, 4);
        if (Objects.equals(substring, new StringBuilder(substring).reverse().toString()) && Arrays.stream(substring.split("")).distinct().count() == 2) {
            return true;
        }
        return hasPalindrome(str.substring(1));
    }


}
