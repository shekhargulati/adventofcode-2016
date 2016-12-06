package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

/**
 * Read problem statement in src/main/resources/problem-statements/problem06.txt
 */
public class Problem06 {

    public static String part1(Path inputFilePath) throws Exception {
        return findCode(inputFilePath, Problem06::max);
    }

    public static String part2(Path inputFilePath) throws Exception {
        return findCode(inputFilePath, Problem06::min);
    }

    private static String findCode(Path inputFilePath, Function<String, String> converter) throws IOException {
        Map<Integer, String> map = rowsToColumns(Files.readAllLines(inputFilePath));
        return map.entrySet()
                .stream()
                .map(entry -> new SimpleEntry<>(entry.getKey(), converter.apply(entry.getValue())))
                .sorted((e1, e2) -> e1.getKey() - e2.getKey())
                .map(SimpleEntry::getValue)
                .collect(joining());
    }

    private static Map<Integer, String> rowsToColumns(List<String> lines) {
        Map<Integer, String> columnsWithText = new HashMap<>();
        lines.forEach(line -> IntStream.range(0, line.length())
                .forEach(index -> columnsWithText.put(
                        index,
                        columnsWithText.compute(
                                index,
                                (k, v) -> v == null ?
                                        String.valueOf(line.charAt(k)) :
                                        v.concat(String.valueOf(line.charAt(k)))))));
        return columnsWithText;
    }

    private static String max(String input) {
        String[] chs = input.split("");
        Map<String, Integer> map = new HashMap<>();
        for (String ch : chs) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        Map.Entry<String, Integer> max = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (max == null || max.getValue() < entry.getValue()) {
                max = entry;
            }
        }

        return max.getKey();
    }

    private static String min(String input) {
        String[] chs = input.split("");
        Map<String, Integer> map = new HashMap<>();
        for (String ch : chs) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        Map.Entry<String, Integer> min = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (min == null || entry.getValue() < min.getValue()) {
                min = entry;
            }
        }
        return min.getKey();
    }
}
