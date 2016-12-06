package adventofcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static adventofcode.Utils.zip;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

/**
 * Read problem statement in src/main/resources/problem-statements/problem06.txt
 */
public class Problem06 {

    private static Comparator<Map.Entry<String, Long>> maxComparator = (e1, e2) -> Long.valueOf(e1.getValue() - e2.getValue()).intValue();

    public static String part1(List<String> lines) {
        return findCode(lines, Problem06::max);
    }

    public static String part2(List<String> lines) {
        return findCode(lines, Problem06::min);
    }

    private static String findCode(List<String> lines, Function<String, String> extractor) {
        return zip(lines)
                .stream()
                .map(extractor)
                .collect(joining());
    }

    private static String max(String input) {
        return reduce(input, maxComparator);
    }

    private static String min(String input) {
        return reduce(input, maxComparator.reversed());
    }

    private static String reduce(String input, Comparator<Map.Entry<String, Long>> comparator) {
        Map<String, Long> counter = Arrays.stream(input.split("")).collect(groupingBy(identity(), counting()));
        return counter.entrySet()
                .stream()
                .max(comparator)
                .get()
                .getKey();
    }
}
