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
        long count = count(screen, Files.readAllLines(Paths.get("src", "test", "resources", "problem08.txt")));
        System.out.println(count);
        toString(screen);
    }


    private static long count(String[][] screen, List<String> cmds) {
        int rows = screen.length;
        int cols = screen[0].length;
        for (String cmd : cmds) {
            String[] parts = cmd.split("\\s");
            if (cmd.startsWith("rect")) {
                String[] dim = parts[1].split("x");
                fillRect(screen, toInt(dim[0]), toInt(dim[1]));
            } else {
                int side = toInt(parts[2].split("=")[1]);
                int by = toInt(parts[4]);
                if (cmd.startsWith("rotate column x") || cmd.startsWith("rotate row x")) {
                    rotateDown(screen, rows, by, side);
                } else {
                    rotateRight(screen, cols, by, side);
                }
            }
        }
        return Arrays.stream(screen).flatMap(Arrays::stream).filter(s -> Objects.equals(s, "#")).count();
    }

    private static void fillRect(String[][] screen, int a, int b) {
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                screen[i][j] = "#";
            }
        }
    }

    private static void rotateDown(String[][] screen, int rows, int by, int x) {
        String[] tmp = new String[rows];
        for (int i = 0; i < rows; i++) {
            for (int j = x; j <= x; j++) {
                tmp[i] = screen[i][j];
            }
        }
        for (int i = 0, v = i + by; i < rows; i++, v++) {
            for (int j = x; j <= x; j++) {
                if (v < rows) {
                    screen[v][j] = tmp[i];
                } else {
                    v = 0;
                    screen[v][j] = tmp[i];
                }
            }
        }
    }

    private static void rotateRight(String[][] screen, int cols, int by, int y) {
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
