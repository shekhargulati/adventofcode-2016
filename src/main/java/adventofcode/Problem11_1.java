package adventofcode;

import java.util.Arrays;

public class Problem11_1 {

    public static void main(String[] args) {
        int[] part1 = {4, 5, 1, 0};
        System.out.println(getMoves(part1));

        int[] part2 = {8, 5, 1, 0};
        System.out.println(getMoves(part2));
    }

    private static int getMoves(int[] items) {
        int moves = 0;
        while (items[3] != Arrays.stream(items).sum()) {
            int low = 0;
            while (items[low] == 0) {
                low += 1;
            }
            moves += 2 * (items[low] - 1) - 1;
            items[low + 1] += items[low];
            items[low] = 0;
        }
        return moves;
    }
}
