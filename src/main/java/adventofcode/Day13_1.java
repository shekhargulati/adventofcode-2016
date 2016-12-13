package adventofcode;

import java.util.*;

public class Day13_1 {

    public static void main(String[] args) {
        Set<Point> traversed = new HashSet<>();
        traversed.add(new Point(1, 1));
        int steps = 0;
        Set<Point> newPlaces = new HashSet<>(traversed);

        String part1 = null;
        String part2 = null;

        while (part1 == null || part2 == null) {
            Set<Point> placesToCheck = new HashSet<>(newPlaces);
            newPlaces = new HashSet<>();
            for (Point entry : placesToCheck) {
                for (Point p : Arrays.asList(
                        new Point(entry.x + 1, entry.y),
                        new Point(entry.x - 1, entry.y),
                        new Point(entry.x, entry.y + 1),
                        new Point(entry.x, entry.y - 1))) {

                    if (p.x < 0 || p.y < 0 || traversed.contains(p) || isWall(p.x, p.y)) {
                        continue;
                    }

                    traversed.add(p);
                    newPlaces.add(p);
                }
            }
            steps += 1;
            if (newPlaces.contains(new Point(31, 39))) {
                part1 = String.valueOf(steps);
            }
            if (steps == 50) {
                part2 = String.valueOf(traversed.size());
            }
        }

        System.out.println("Part 1 " + part1);
        System.out.println("Part 2 " + part2);

    }

    private static boolean isWall(int x, int y) {
        int value = x * x + 3 * x + 2 * x * y + y + y * y + 1352;
        String binary = toBinary(value);
        int bits = Long.valueOf(Arrays.stream(binary.split("")).filter(b -> Objects.equals(b, "1")).count()).intValue();
        return bits % 2 == 1;

    }

    private static String toBinary(int number) {
        Stack<Integer> stack = new Stack<>();
        while (number != 0) {
            stack.push(number % 2);
            number /= 2;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
