package adventofcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Problem02 {

    private static final String[][] keypadPart1 = {
            new String[]{"1", "2", "3"},
            new String[]{"4", "5", "6"},
            new String[]{"7", "8", "9"}
    };

    private static final String[][] keypad_part2 = {
            new String[]{null, null, "1", null, null},
            new String[]{null, "2", "3", "4", null},
            new String[]{"5", "6", "7", "8", "9"},
            new String[]{null, "A", "B", "C", null},
            new String[]{null, null, "D", null, null},
    };

    private static final Map<String, Function<Point, Point>> mappingsPart1 = new HashMap<String, Function<Point, Point>>() {
        {
            put("U", p -> p.x > 0 ? new Point(p.x - 1, p.y) : p);
            put("D", p -> p.x < keypadPart1.length - 1 ? new Point(p.x + 1, p.y) : p);
            put("L", p -> p.y > 0 ? new Point(p.x, p.y - 1) : p);
            put("R", p -> p.y < keypadPart1.length - 1 ? new Point(p.x, p.y + 1) : p);
        }
    };

    private static final Map<String, Function<Point, Point>> mappingsPart2 = new HashMap<String, Function<Point, Point>>() {
        {
            put("U", p -> (p.x > 0 && keypad_part2[p.x - 1][p.y] != null) ? new Point(p.x - 1, p.y) : p);
            put("D", p -> (p.x < keypad_part2.length - 1 && keypad_part2[p.x + 1][p.y] != null) ? new Point(p.x + 1, p.y) : p);
            put("L", p -> (p.y > 0 && keypad_part2[p.x][p.y - 1] != null) ? new Point(p.x, p.y - 1) : p);
            put("R", p -> (p.y < keypad_part2.length - 1 && keypad_part2[p.x][p.y + 1] != null) ? new Point(p.x, p.y + 1) : p);
        }
    };

    public static String code_part1(List<String> instructions) {
        return code(instructions, keypadPart1, mappingsPart1, new Point(1, 1));
    }

    public static String code_part2(List<String> instructions) {
        return code(instructions, keypad_part2, mappingsPart2, new Point(2, 0));
    }

    private static String code(List<String> instructions, String[][] keypad, Map<String, Function<Point, Point>> mappings, Point current) {
        StringBuilder codeBuilder = new StringBuilder();
        for (String instruction : instructions) {
            current = Arrays.stream(instruction.split("")).reduce(current, (acc, cmd) -> mappings.get(cmd).apply(acc), (p1, p2) -> p1);
            codeBuilder.append(keypad[current.x][current.y]);
        }
        return codeBuilder.toString();
    }
}

