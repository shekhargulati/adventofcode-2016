package adventofcode;

import java.util.*;

public class Problem01 {

    public static int blocks(final List<Instruction> instructions) {
        Set<Point> visited = new HashSet<>();
        Position current = new Position("north", new Point(0, 0));
        visited.add(new Point(0, 0));
        for (Instruction instruction : instructions) {
//            System.out.println(instruction);
            Point prev = current.point;
            int dis = instruction.blocks;
            List<Point> points;
            if (isNorth(current)) {
                if (isRight(instruction)) {
                    points = xPoints(current.point.x, current.point.y, dis);
                    current = new Position("east", new Point(current.point.x + dis, current.point.y));
                } else {
                    Point point = new Point(current.point.x - dis, current.point.y);
                    current = new Position("west", point);
                    points = xPoints(point.x, point.y, dis);
                }
            } else if (isEast(current)) {
                if (isRight(instruction)) {
                    Point point = new Point(current.point.x, current.point.y - dis);
                    current = new Position("south", point);
                    points = yPoints(point.x, point.y, dis);
                } else {
                    points = yPoints(current.point.x, current.point.y, dis);
                    current = new Position("north", new Point(current.point.x, current.point.y + dis));
                }
            } else if (isWest(current)) {
                if (isRight(instruction)) {
                    points = yPoints(current.point.x, current.point.y, dis);
                    current = new Position("north", new Point(current.point.x, current.point.y + dis));
                } else {
                    Point point = new Point(current.point.x, current.point.y - dis);
                    current = new Position("south", point);
                    points = yPoints(point.x, point.y, dis);
                }
            } else if (isSouth(current)) {
                if (isRight(instruction)) {
                    Point point = new Point(current.point.x - dis, current.point.y);
                    current = new Position("west", point);
                    points = xPoints(point.x, point.y, dis);
                } else {
                    points = xPoints(current.point.x, current.point.y, dis);
                    current = new Position("east", new Point(current.point.x + dis, current.point.y));
                }
            } else {
                throw new IllegalArgumentException("Undefined instruction" + instruction);
            }
            System.out.println(current);

            for (Point point : points) {
                if (visited.contains(point)) {
                    System.out.println("Found point visited twice >> " + point);
                    return 0;
                }else{
                    visited.add(point);
                }
            }

        }
        return current.blocks();
    }

    private static List<Point> yPoints(int x, int y, int dis) {
        List<Point> points = new ArrayList<>();
        for (int i = 1; i < dis; i++) {
            points.add(new Point(x, y + i));
        }
        return points;
    }

    private static List<Point> xPoints(int x, int y, int dis) {
        List<Point> points = new ArrayList<>();
        for (int i = 1; i < dis; i++) {
            points.add(new Point(x + i, y));
        }
        return points;
    }

    private static boolean isLeft(Instruction instruction) {
        return Objects.equals(instruction.dir, "L");
    }

    private static boolean isRight(Instruction instruction) {
        return Objects.equals(instruction.dir, "R");
    }

    private static boolean isSouth(Position current) {
        return Objects.equals(current.direction, "south");
    }

    private static boolean isWest(Position current) {
        return Objects.equals(current.direction, "west");
    }

    private static boolean isEast(Position current) {
        return Objects.equals(current.direction, "east");
    }

    private static boolean isNorth(Position current) {
        return Objects.equals(current.direction, "north");
    }
}


class Instruction {

    private final String instruction;
    String dir;
    int blocks;

    public Instruction(String instruction) {
        this.instruction = instruction;
        String[] strings = instruction.split("");
        this.dir = strings[0];
        this.blocks = Integer.parseInt(instruction.substring(1));
    }

    @Override
    public String toString() {
        return this.instruction;
    }
}

class Position {

    final String direction;
    final Point point;


    public Position(String direction, Point point) {
        this.direction = direction;
        this.point = point;
    }

    public int blocks() {
        return Math.abs(this.point.x) + Math.abs(this.point.y);
    }

    @Override
    public String toString() {
        return String.format("%s:(%d,%d)", direction, point.x, point.y);
    }
}

class Point {
    final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}