package adventofcode;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static adventofcode.Utils.md5;
import static java.util.stream.Collectors.joining;

public class Problem05 {

    public static void main(String[] args) {

        String doorId = "abbhdwsy";
        List<SimpleEntry<Integer, String>> result = LongStream
                .iterate(1L, i -> i + 1)
                .mapToObj(number -> md5(String.format("%s%d", doorId, number)))
                .filter(str -> str.startsWith("00000"))
                .map(str -> new SimpleEntry<>(String.valueOf(str.charAt(5)), str))
                .filter(entry -> {
                    try {
                        return Integer.parseInt(entry.getKey()) <= 7;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .map(entry -> new SimpleEntry<>(Integer.parseInt(entry.getKey()), entry.getValue()))
                .map(entry -> new SimpleEntry<>(entry.getKey(), String.valueOf(entry.getValue().charAt(6))))
                .limit(20)
                .collect(Collectors.toList());

        String[] r = new String[8];
        for (int i = 0; i < 8; i++) {
            for (SimpleEntry<Integer, String> entry : result) {
                if (Objects.equals(entry.getKey(), i)) {
                    r[i] = entry.getValue();
                    break;
                }
            }
        }
        System.out.println(Arrays.stream(r).collect(joining("")));

    }

    public static String part1(String doorId) {
        String result = LongStream
                .iterate(1L, i -> i + 1)
                .mapToObj(number -> md5(String.format("%s%d", doorId, number)))
                .filter(hash -> hash.startsWith("00000"))
                .limit(8)
                .map(str -> String.valueOf(str.charAt(5)))
                .collect(joining(""));
        return result;
    }
}
