package adventofcode;

import java.util.List;
import java.util.Objects;

public class Problem02 {

    private static final int[][] arr = {
            new int[]{1, 2, 3},
            new int[]{4, 5, 6},
            new int[]{7, 8, 9}
    };

    private static final String[][] arr2 = {
            new String[]{null, null, "1", null, null},
            new String[]{null, "2", "3", "4", null},
            new String[]{"5", "6", "7", "8", "9"},
            new String[]{null, "A", "B", "C", null},
            new String[]{null, null, "D", null, null},
    };

    public static String code(List<String> instructions) {
        StringBuilder codeBuilder = new StringBuilder();
        Point current = new Point(1, 1);
        for (String instruction : instructions) {
            String[] commands = instruction.split("");
            for (String cmd : commands) {
                if (Objects.equals(cmd, "U") && current.x > 0) {
                    current = new Point(current.x - 1, current.y);
                } else if (Objects.equals(cmd, "D") && current.x < 2) {
                    current = new Point(current.x + 1, current.y);
                } else if (Objects.equals(cmd, "L") && current.y > 0) {
                    current = new Point(current.x, current.y - 1);
                } else if (Objects.equals(cmd, "R") && current.y < 2) {
                    current = new Point(current.x, current.y + 1);
                } else {
                    // ignore
                }
            }
            codeBuilder.append(arr[current.x][current.y]);

        }
        return codeBuilder.toString();
    }

    public static String code2(List<String> instructions) {
        StringBuilder codeBuilder = new StringBuilder();
        Point current = new Point(2, 0);
        for (String instruction : instructions) {
            String[] commands = instruction.split("");
            for (String cmd : commands) {
                if (Objects.equals(cmd, "U") && (current.x > 0 && arr2[current.x - 1][current.y] != null)) {
                    current = new Point(current.x - 1, current.y);
                } else if (Objects.equals(cmd, "D") && (current.x < 4 && arr2[current.x + 1][current.y] != null)) {
                    current = new Point(current.x + 1, current.y);
                } else if (Objects.equals(cmd, "L") && (current.y > 0 && arr2[current.x][current.y - 1] != null)) {
                    current = new Point(current.x, current.y - 1);
                } else if (Objects.equals(cmd, "R") && (current.y < 4 && arr2[current.x][current.y + 1] != null)) {
                    current = new Point(current.x, current.y + 1);
                } else {
                    // ignore
                }
            }
            codeBuilder.append(arr2[current.x][current.y]);

        }
        return codeBuilder.toString();
    }

}

