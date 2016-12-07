package adventofcode;

import strman.Strman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Problem07 {

    public static boolean part1(String input) {
        /*
            1. split by [
            2.then split by ]
            3 take 0 then take 1
            4. find substring that is palindromic
         */

        String[] between1 = Strman.between(input, "[", "]");
        String[] parts = input.split("]");
        String start = "[";
        String[] supernetSequences = Arrays.stream(parts).map(subPart -> {
            if (!subPart.contains(start)) {
                return subPart;
            }
            return subPart.substring(0, subPart.indexOf(start));
        }).toArray(String[]::new);

        List<String> list = new ArrayList<>();
        for (String string : supernetSequences) {
            hasAba(string, list);
        }

        if (list.isEmpty()) {
            return false;
        }

        String[] hypernetSequences = Arrays.copyOfRange(between1, 0, between1.length - 1);
        boolean notBetween = false;
        for (String sequence : hypernetSequences) {
            for (String s1 : list) {
                if (hasBab(sequence, s1.charAt(0), s1.charAt(1))) {
                    return true;
                }
            }
        }

        return notBetween;
    }

    public static String hasAba(String str, List<String> list) {
        if (str.length() < 3) {
            return null;
        }
        String substring = str.substring(0, 3);
        if ((substring.charAt(0) == substring.charAt(2) && substring.charAt(0) != substring.charAt(1)) && Arrays.stream(substring.split("")).distinct().count() == 2) {
            list.add(substring);
        }
        return hasAba(str.substring(1), list);
    }

    public static boolean hasBab(String str, char a, char b) {
        if (str.length() < 3) {
            return false;
        }
        String substring = str.substring(0, 3);
        if ((substring.charAt(0) == b && substring.charAt(2) == b && substring.charAt(1) == a) && Arrays.stream(substring.split("")).distinct().count() == 2) {
            return true;
        }
        return hasBab(str.substring(1), a, b);
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
