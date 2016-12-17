package adventofcode;

import java.util.*;

import static adventofcode.Utils.md5;

public class Day17_2 {

    public static void main(String[] args) {
        String input = "ioramepc";
        Stack<Pos> availableMovements = new Stack<>();
        availableMovements.push(new Pos(1, 1, input));

        Set<Pos> valid = new HashSet<>();
        allValidPositions(valid, availableMovements);
        System.out.println("Part 1: " + valid
                .stream()
                .min((p1, p2) -> p1.path.length() - p2.path.length())
                .map(p -> p.path.substring(input.length()))
                .get());


        System.out.println("Part 2: " + valid
                .stream()
                .max((p1, p2) -> p1.path.length() - p2.path.length())
                .map(p -> p.path.substring(input.length()))
                .get()
                .length());

    }

    private static void allValidPositions(Set<Pos> positions, Stack<Pos> availableMovements) {
        while (!availableMovements.isEmpty()) {
            Pos current = availableMovements.pop();
            List<Pos> nextPositions = findNextMoves(current);
            for (Pos nextPosition : nextPositions) {
                if (nextPosition.isDestination()) {
                    positions.add(nextPosition);
                } else {
                    availableMovements.push(nextPosition);
                }
            }
        }
    }

    private static List<Pos> findNextMoves(Pos pos) {
        String input = pos.path;
        String md5 = md5(input);
        String move = md5.substring(0, 4);
        List<Pos> positions = new ArrayList<>();
        if ("bcdef".contains(String.valueOf(move.charAt(0))) && pos.x > 1) {
            positions.add(new Pos(pos.x - 1, pos.y, input + "U"));
        }

        if ("bcdef".contains(String.valueOf(move.charAt(1))) && pos.x < 4) {
            positions.add(new Pos(pos.x + 1, pos.y, input + "D"));
        }

        if ("bcdef".contains(String.valueOf(move.charAt(2))) && pos.y > 1) {
            positions.add(new Pos(pos.x, pos.y - 1, input + "L"));
        }

        if ("bcdef".contains(String.valueOf(move.charAt(3))) && pos.y < 4) {
            positions.add(new Pos(pos.x, pos.y + 1, input + "R"));
        }
        return positions;
    }
}

class Pos {
    int x;
    int y;
    String path;

    public Pos(int x, int y, String path) {
        this.x = x;
        this.y = y;
        this.path = path;
    }


    public boolean isDestination() {
        return this.x == 4 && this.y == 4;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
