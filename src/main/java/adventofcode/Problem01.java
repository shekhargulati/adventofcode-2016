package adventofcode;

import java.util.List;
import java.util.Objects;

public class Problem01 {

    public static int blocks(final List<Instruction> instructions) {
        Position current = new Position("north", 0, 0);
        for (Instruction instruction : instructions) {
            System.out.println(instruction);
            if (Objects.equals(current.direction, "north") && Objects.equals(instruction.dir, "R")) {
                current = new Position("east", current.x + instruction.blocks, current.y);
            } else if (Objects.equals(current.direction, "north") && Objects.equals(instruction.dir, "L")) {
                current = new Position("west", current.x - instruction.blocks, current.y);
            } else if (Objects.equals(current.direction, "east") && Objects.equals(instruction.dir, "R")) {
                current = new Position("south", current.x, current.y - instruction.blocks);
            } else if (Objects.equals(current.direction, "east") && Objects.equals(instruction.dir, "L")) {
                current = new Position("north", current.x, current.y + instruction.blocks);

            } else if (Objects.equals(current.direction, "west") && Objects.equals(instruction.dir, "R")) {
                current = new Position("north", current.x, current.y + instruction.blocks);

            } else if (Objects.equals(current.direction, "west") && Objects.equals(instruction.dir, "L")) {
                current = new Position("south", current.x, current.y - instruction.blocks);

            } else if (Objects.equals(current.direction, "south") && Objects.equals(instruction.dir, "L")) {
                current = new Position("east", current.x + instruction.blocks, current.y);

            } else if (Objects.equals(current.direction, "south") && Objects.equals(instruction.dir, "R")) {
                current = new Position("west", current.x - instruction.blocks, current.y);
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