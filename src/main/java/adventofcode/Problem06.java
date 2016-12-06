package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Problem06 {

    public static void main(String[] args) throws Exception {
        Path inputFilePath = Paths.get("src", "main", "resources", "problem06.txt");
        System.out.println(problem06(inputFilePath, Problem06::max));
        System.out.println(problem06(inputFilePath, Problem06::min));
    }

    private static String problem06(Path path, Function<String, String> fx) throws IOException {
        List<String> lines = Files.readAllLines(path);
        String[] columns = new String[8];
        for (String line : lines) {
            String[] chs = line.split("");
            int col = 0;
            for (String ch : chs) {
                columns[col] = (columns[col] == null ? "" : columns[col]) + ch;
                col++;
            }
        }

        return Arrays.stream(columns).map(fx).collect(Collectors.joining());
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
