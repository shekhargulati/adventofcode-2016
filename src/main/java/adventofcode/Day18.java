package adventofcode;

import java.util.Arrays;

public class Day18 {

    public static void main(String[] args) throws Exception {
        String input = "^^.^..^.....^..^..^^...^^.^....^^^.^.^^....^.^^^...^^^^.^^^^.^..^^^^.^^.^.^.^.^.^^...^^..^^^..^.^^^^";
        int rows = 400000;
        int cols = input.length();
        boolean[][] grid = new boolean[rows][cols];
        for (int i = 0; i < input.length(); i++) {
            grid[0][i] = input.charAt(i) == '.';
        }

        for (int i = 1; i < rows; i++) {
            boolean[] previous = grid[i - 1];
            for (int j = 0; j < cols; j++) {
                boolean left = j <= 0 || previous[j - 1];
                boolean center = previous[j];
                boolean right = j >= cols -1 || previous[j + 1];
                boolean v;
                if (!left && !center && right) {
                    v = false;
                } else if (!center && !right && left) {
                    v = false;
                } else if (!left && right && center) {
                    v = false;
                } else if (!right && left && center) {
                    v = false;
                } else {
                    v = true;
                }
                grid[i][j] = v;
            }
        }

        String[][] gridStr = new String[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gridStr[i][j] = grid[i][j] ? "." : "^";
                if (grid[i][j]) {
                    count++;
                }
            }
        }

        for (String[] strings : gridStr) {
            System.out.println(Arrays.toString(strings));
        }

        System.out.println();
        System.out.println();
        System.out.println("Count >> " + count);

    }


}
