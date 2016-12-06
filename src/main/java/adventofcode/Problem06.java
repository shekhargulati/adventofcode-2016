package adventofcode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

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

    private static String findCode(Path inputFilePath, Function<String, String> extractor) throws Exception {
        Map<Integer, String> map = transpose(Files.readAllLines(inputFilePath));
        return map.entrySet()
                .stream()
                .map(entry -> new SimpleEntry<>(entry.getKey(), extractor.apply(entry.getValue())))
                .sorted((e1, e2) -> e1.getKey() - e2.getKey())
                .map(SimpleEntry::getValue)
                .collect(joining());
    }

    private static Map<Integer, String> transpose(List<String> lines) {
        Map<Integer, String> columns = new HashMap<>();
        lines.forEach(line ->
                IntStream
                        .range(0, line.length()).mapToObj(i -> i)
                        .reduce(columns, (acc, index) -> {
                            columns.put(
                                    index,
                                    columns.compute(
                                            index,
                                            (k, v) -> v == null
                                                    ? String.valueOf(line.charAt(k))
                                                    : v.concat(String.valueOf(line.charAt(k)))));
                            return columns;
                        }, (m1, m2) -> m1));
        return columns;
    }

    private static String max(String input) {
        Map<String, Long> charWithCount = Arrays.stream(input.split("")).collect(groupingBy(identity(), counting()));
        return charWithCount.entrySet()
                .stream()
                .max((e1, e2) -> Long.valueOf(e1.getValue() - e2.getValue()).intValue())
                .get()
                .getKey();
    }

    private static String min(String input) {
        Map<String, Long> charWithCount = Arrays.stream(input.split("")).collect(groupingBy(identity(), counting()));
        return charWithCount.entrySet()
                .stream()
                .min((e1, e2) -> Long.valueOf(e1.getValue() - e2.getValue()).intValue())
                .get()
                .getKey();
    }
}
