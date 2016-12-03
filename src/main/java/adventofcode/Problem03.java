package adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Problem03 {


    public static int countTriangles_part1(List<String> lines) {
        return lines
                .stream()
                .map(Triangle::new)
                .filter(Triangle::isValid)
                .collect(toList())
                .size();
    }

    public static int countTriangles_part2(List<String> lines) {
        int count1 = countTrianglesInColumn(lines, 0);
        int count2 = countTrianglesInColumn(lines, 1);
        int count3 = countTrianglesInColumn(lines, 2);
        return count1 + count2 + count3;
    }

    private static int countTrianglesInColumn(List<String> lines, int column) {
        List<Integer> numbers = lines
                .stream()
                .map(l -> Integer.parseInt(sides(l).get(column).trim()))
                .collect(Collectors.toList());

        int count = 0;
        for (int i = 0; i < numbers.size() - 2; i = i + 3) {
            int a = numbers.get(i);
            int b = numbers.get(i + 1);
            int c = numbers.get(i + 2);
            if (new Triangle(a, b, c).isValid()) {
                count++;
            }

        }
        return count;
    }

    static List<String> sides(String line) {
        return Arrays.stream(line.trim().split("\\s+")).filter(s -> s.trim().length() > 0).collect(toList());
    }

}

class Triangle {
    final int a;
    final int b;
    final int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(String line) {
        List<String> sides = Problem03.sides(line);
        this.a = Integer.parseInt(sides.get(0).trim());
        this.b = Integer.parseInt(sides.get(1).trim());
        this.c = Integer.parseInt(sides.get(2).trim());
    }

    public boolean isValid() {
        if (this.a + this.b > this.c && this.a + this.c > this.b && this.b + this.c > this.a)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return String.format("a: %d, b: %d, c: %d", a, b, c);
    }

}
