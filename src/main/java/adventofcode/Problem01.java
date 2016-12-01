package adventofcode;

import java.util.List;
import java.util.Objects;

public class Problem01 {

    public static int blocks(final List<Instruction> instructions) {
        int goingEast = 0;
        int goingNorth = 0;
        Position current = new Position("north", 0, 0);
        for (Instruction instruction : instructions) {
            System.out.println(instruction);
            if (Objects.equals(current.direction, "north") && Objects.equals(instruction.dir, "R")) {
                if (goingEast == 0 || goingEast == 1) {
                    current = new Position("east", current.x + instruction.blocks, current.y);
                } else {
                    current = new Position("east", current.x - instruction.blocks, current.y);
                }
                goingEast = 1;
            } else if (Objects.equals(current.direction, "north") && Objects.equals(instruction.dir, "L")) {
                if (goingEast == 0 || goingEast == 1) {
                    current = new Position("west", current.x - instruction.blocks, current.y);
                } else {
                    current = new Position("west", current.x + instruction.blocks, current.y);
                }
                goingEast = -1;

            } else if (Objects.equals(current.direction, "east") && Objects.equals(instruction.dir, "R")) {
                if (goingNorth == 0 || goingNorth == 1) {
                    current = new Position("south", current.x, current.y - instruction.blocks);
                } else {
                    current = new Position("south", current.x, current.y + instruction.blocks);
                }
                goingNorth = -1;
            } else if (Objects.equals(current.direction, "east") && Objects.equals(instruction.dir, "L")) {
                if (goingNorth == 0 || goingNorth == 1) {
                    current = new Position("north", current.x, current.y + instruction.blocks);
                } else {
                    current = new Position("north", current.x, current.y - instruction.blocks);
                }
                goingNorth = 1;

            } else if (Objects.equals(current.direction, "west") && Objects.equals(instruction.dir, "R")) {
                if (goingNorth == 0 || goingNorth == 1) {
                    current = new Position("north", current.x, current.y + instruction.blocks);
                } else {
                    current = new Position("north", current.x, current.y - instruction.blocks);
                }
                goingNorth = 1;

            } else if (Objects.equals(current.direction, "west") && Objects.equals(instruction.dir, "L")) {
                if (goingNorth == 0 || goingNorth == 1) {
                    current = new Position("south", current.x, current.y - instruction.blocks);
                } else {
                    current = new Position("south", current.x, current.y + instruction.blocks);
                }
                goingNorth = -1;

            } else if (Objects.equals(current.direction, "south") && Objects.equals(instruction.dir, "L")) {
                if (goingEast == 0 || goingEast == 1) {
                    current = new Position("east", current.x + instruction.blocks, current.y);
                } else {
                    current = new Position("east", current.x - instruction.blocks, current.y);
                }

                goingEast = 1;

            } else if (Objects.equals(current.direction, "south") && Objects.equals(instruction.dir, "R")) {
                if (goingEast == 0 || goingEast == 1) {
                    current = new Position("west", current.x - instruction.blocks, current.y);
                } else {
                    current = new Position("west", current.x + instruction.blocks, current.y);
                }
                goingEast = -1;
            } else {
                throw new IllegalArgumentException("Undefined instruction" + instruction);
            }
            System.out.println(current);
        }
        return current.blocks();
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