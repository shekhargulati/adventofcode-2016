package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static adventofcode.Utils.toInt;

public class Problem12 {

    public static void main(String[] args) throws Exception {
        List<String> instructions = Files.readAllLines(Paths.get("src", "test", "resources", "problem12.txt"));

        long startTime = System.currentTimeMillis();
        Map<String, Integer> state = new HashMap<>();
        state.put("a", 0);
        state.put("b", 0);
        state.put("c", 0);
        state.put("d", 0);

        problem(instructions, state);
        //318077
        System.out.println("The value of a is " + state.get("a"));
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken: " + (endTime - startTime) / 1000 + " seconds");

        state.clear();
        long startTime2 = System.currentTimeMillis();
        state.put("a", 0);
        state.put("b", 0);
        state.put("c", 1);
        state.put("d", 0);

        problem(instructions, state);
        //9227731
        System.out.println("The value of a is " + state.get("a"));
        long endTime2 = System.currentTimeMillis();
        System.out.println("Total time taken: " + (endTime2 - startTime2) / 1000 + " seconds");
    }

    private static void problem(List<String> instructions, Map<String, Integer> map) {
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            String[] parts = instruction.split("\\s");
            String cmd = parts[0];
            if (Objects.equals(cmd, "cpy")) {
                String x = parts[1];
                String y = parts[2];
                int first = isNumber(x) ? toInt(x) : map.get(x);
                map.put(y, first);
            } else if (Objects.equals(cmd, "inc")) {
                int x = map.get(parts[1]);
                map.put(parts[1], x + 1);
            } else if (Objects.equals(cmd, "dec")) {
                int x = map.get(parts[1]);
                map.put(parts[1], x - 1);
            } else if (Objects.equals(cmd, "jnz")) {
                String x = parts[1];
                String y = parts[2];
                int xValue = isNumber(x) ? toInt(x) : map.get(x);
                if (xValue != 0) {
                    int v = isNumber(y) ? toInt(y) : map.get(y);
                    i += (v - 1);
                }
            }
        }
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
