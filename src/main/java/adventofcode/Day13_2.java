package adventofcode;

import java.util.*;

public class Day13_2 {


    public static void main(String[] args) {

        Point start = new Point(1, 1);
        Point result = new Point(31, 39);
        int steps = 0;

        PriorityQueue<Point> queue = new PriorityQueue<>((p1, p2) ->
                Double.valueOf(distanceBetween(p1, result) - distanceBetween(p2, result)).intValue());
        queue.add(start);

        Set<Point> visited = new HashSet<>();
        visited.add(start);
        while (!start.equals(result)) {
            start = queue.poll();
            for (Point p : Arrays.asList(
                    new Point(start.x + 1, start.y),
                    new Point(start.x - 1, start.y),
                    new Point(start.x, start.y + 1),
                    new Point(start.x, start.y - 1))) {

                if (p.x < 0 || p.y < 0
                        || visited.contains(p)
                        || isWall(p.x, p.y)) {
                    continue;
                }

                visited.add(p);
                queue.add(p);
            }
            steps++;
        }

        System.out.println("Total steps " + steps);
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

    private static double distanceBetween(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
