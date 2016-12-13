package adventofcode;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Day13 {

    public static void main(String[] args) throws Exception {
        String[][] layout = new String[50][50];

        int favNumber = 1352;

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {
                int number = number(j, i, favNumber);
//                System.out.println(number);
                String binary = toBinary(number);
//                System.out.println(binary);
                int bits = Long.valueOf(Arrays.stream(binary.split("")).filter(b -> Objects.equals(b, "1")).count()).intValue();
//                System.out.println(bits);
                if (bits % 2 == 0) {
                    layout[i][j] = ".";
                } else {
                    layout[i][j] = "#";
                }
            }
        }


        printLayout(layout);
        System.out.println();
        System.out.println();
        System.out.println();

        Point start = new Point(1, 1);
        Point result = new Point(31, 39);
        int steps = 0;

        PriorityQueue<Point> queue = new PriorityQueue<>((p1, p2) -> Double.valueOf(distanceBetween(p1, result) - distanceBetween(p2, result)).intValue());
        queue.add(start);
        Set<Point> visited = new HashSet<>();
        visited.add(start);
        while (!start.equals(result)) {
            start = queue.poll();
            layout[start.y][start.x] = "O";

            List<Point> availableLocations = availableLocations(layout, start);
            for (Point availableLocation : availableLocations) {
                if (!visited.contains(availableLocation)) {
                    queue.add(availableLocation);
                }
                visited.add(availableLocation);
            }
            steps++;
        }

        printLayout(layout);

        System.out.println("Total number of steps " + (steps));

    }

    private static boolean isClosed(String[][] layout, Point start) {
        System.out.println(start);
        return layout[start.y][start.x + 1] == "#" && layout[start.y][start.x - 1] == "#" && layout[start.y + 1][start.x] == "#";
    }

    private static List<Point> availableLocations(String[][] layout, Point p) {
        List<Point> points = new ArrayList<>();
        // add down point
        if (!Objects.equals(layout[p.y + 1][p.x], "#")) {
            points.add(new Point(p.x, p.y + 1));
        }
        // add up point
        if (!Objects.equals(layout[p.y - 1][p.x], "#")) {
            points.add(new Point(p.x, p.y - 1));
        }

        // add right pount
        if (!Objects.equals(layout[p.y][p.x + 1], "#")) {
            points.add(new Point(p.x + 1, p.y));
        }
        // add left point
        if (!Objects.equals(layout[p.y][p.x - 1], "#")) {
            points.add(new Point(p.x - 1, p.y));
        }
        return points;
    }

    private static void printLayout(String[][] layout) {
        int row = 0;
        for (String[] strings : layout) {
            System.out.println(Arrays.stream(strings).collect(joining(" ")));
            row++;
        }
    }

    private static int number(int x, int y, int favNumber) {
        return ((x * x) + (3 * x) + (2 * x * y) + y + (y * y)) + favNumber;
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
