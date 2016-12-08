package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static adventofcode.Utils.toInt;
import static java.util.stream.Collectors.joining;

public class Problem08 {

    public static void main(String[] args) throws IOException {
        String[][] screen = new String[6][50];
        init(screen);
        int count = count(screen, Files.readAllLines(Paths.get("src", "test", "resources", "problem08.txt")));
        System.out.println(count);
        toString(screen);
    }


    private static int count(String[][] screen, List<String> cmds) {
        int rows = screen.length;
        int cols = screen[0].length;
        for (String cmd : cmds) {
            String[] parts = cmd.split("\\s");
            String type = parts[0];
            if (Objects.equals(type, "rect")) {
                String[] dim = parts[1].split("x");
                int a = toInt(dim[0]);
                int b = toInt(dim[1]);
                for (int i = 0; i < b; i++) {
                    for (int j = 0; j < a; j++) {
                        screen[i][j] = "#";
                    }
                }
            } else if (Objects.equals(type, "rotate") && Objects.equals(parts[1], "column")) {
                String[] strs = parts[2].split("=");
                if (strs[0].equals("x")) {
                    rotateDown(screen, rows, parts[4], strs[1]);
                } else {
                    rotateRight(screen, cols, parts[4], strs[1]);
                }
            } else if (Objects.equals(type, "rotate") && Objects.equals(parts[1], "row")) {
                String[] strs = parts[2].split("=");
                if (strs[0].equals("y")) {
                    rotateRight(screen, cols, parts[4], strs[1]);
                } else {
                    rotateDown(screen, rows, parts[4], strs[1]);
                }
            }

        }
        int count = 0;
        for (String[] ints : screen) {
            for (String anInt : ints) {
                if (Objects.equals(anInt, "#")) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void rotateDown(String[][] screen, int rows, String part, String str) {
        int x = Integer.parseInt(str);
        int by = Integer.parseInt(part);
        String[] tmp = new String[rows];
        for (int i = 0; i < screen.length; i++) {
            for (int j = x; j <= x; j++) {
                tmp[i] = screen[i][j];
            }
        }

        for (int i = 0, v = i + by; i < screen.length; i++, v++) {
            for (int j = x; j <= x; j++) {
                if (v < screen.length) {
                    screen[v][j] = tmp[i];
                } else {
                    v = 0;
                    screen[v][j] = tmp[i];
                }
            }
        }
    }

    private static void rotateRight(String[][] screen, int cols, String part, String str) {
        int y = Integer.parseInt(str);
        int by = Integer.parseInt(part);
        String[] tmp = new String[cols];
        for (int i = y; i <= y; i++) {
            for (int j = 0; j < cols; j++) {
                tmp[j] = screen[i][j];
            }
        }

        for (int i = y; i <= y; i++) {
            for (int j = 0, v = j + by; j < cols; j++, v++) {
                if (v < cols) {
                    screen[i][v] = tmp[j];
                } else {
                    v = 0;
                    screen[i][v] = tmp[j];
                }
            }
        }
    }

    public static void toString(String[][] screen) {
        for (String[] row : screen) {
            System.out.println(Arrays.stream(row).collect(joining(" ")));
        }
    }


    private static void init(String[][] screen) {
        int rows = screen.length;
        int cols = screen[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                screen[i][j] = ".";
            }
        }
    }
}
