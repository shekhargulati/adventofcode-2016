package adventofcode;

import java.util.List;
import java.util.Objects;

public class Problem01 {

    public static int blocks(final List<Instruction> instructions) {
        Position current = new Position("north", 0, 0);
        for (Instruction instruction : instructions) {
            System.out.println(instruction);
            if (isNorth(current)) {
                if (isRight(instruction)) {
                    current = new Position("east", current.x + instruction.blocks, current.y);
                } else {
                    current = new Position("west", current.x - instruction.blocks, current.y);
                }
            } else if (isEast(current)) {
                if (isRight(instruction)) {
                    current = new Position("south", current.x, current.y - instruction.blocks);
                } else {
                    current = new Position("north", current.x, current.y + instruction.blocks);
                }
            } else if (isWest(current)) {
                if (isRight(instruction)) {
                    current = new Position("north", current.x, current.y + instruction.blocks);
                } else {
                    current = new Position("south", current.x, current.y - instruction.blocks);
                }
            } else if (isSouth(current)) {
                if (isRight(instruction)) {
                    current = new Position("west", current.x - instruction.blocks, current.y);
                } else {
                    current = new Position("east", current.x + instruction.blocks, current.y);
                }
            } else {
                throw new IllegalArgumentException("Undefined instruction" + instruction);
            }
            System.out.println(current);
        }
        return current.blocks();
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
    final int x;
    final int y;

    public Position(String direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public int blocks() {
        return Math.abs(this.x) + Math.abs(this.y);
    }

    @Override
    public String toString() {
        return String.format("%s:(%d,%d)", direction, x, y);
    }
}