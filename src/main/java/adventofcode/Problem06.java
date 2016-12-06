package adventofcode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static adventofcode.Utils.zip;
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
        return zip(Files.readAllLines(inputFilePath))
                .stream()
                .map(extractor)
                .collect(joining());
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
