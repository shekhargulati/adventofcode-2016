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

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 0);
        map.put("b", 0);
        map.put("c", 1);
        map.put("d", 0);

        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            String[] parts = instruction.split("\\s");
            String cmd = parts[0];
            System.out.println(instruction);
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
                    if (v < 0) {
                        i += (v - 1);
                    } else {
                        i += (v - 1);
                    }
                }
            }
        }
        System.out.println(map.get("a"));
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
