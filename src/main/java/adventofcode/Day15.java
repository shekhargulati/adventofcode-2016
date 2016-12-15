package adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day15 {

    public static void main(String[] args) {
//        List<String> input = Arrays.asList(
//                "Disc #1 has 5 positions; at time=0, it is at position 4.",
//                "Disc #2 has 2 positions; at time=0, it is at position 1.");

//        List<Disc> discs = Arrays.asList(
//                new Disc("#1", 17, 0, 5),
//                new Disc("#2", 19, 0, 8),
//                new Disc("#3", 7, 0, 1),
//                new Disc("#4", 13, 0, 7),
//                new Disc("#5", 5, 0, 1),
//                new Disc("#6", 3, 0, 0)
//        );

//        List<Disc> discs = Arrays.asList(
//                new Disc("#1", 5, 0, 4),
//                new Disc("#2", 2, 0, 1)
//        );


        IntStream.iterate(0, i -> i + 1)
                .filter(buttonPressTime -> {
                    List<Disc> discs = Arrays.asList(
                            new Disc("#1", 17, 0, 5),
                            new Disc("#2", 19, 0, 8),
                            new Disc("#3", 7, 0, 1),
                            new Disc("#4", 13, 0, 7),
                            new Disc("#5", 5, 0, 1),
                            new Disc("#6", 3, 0, 0)
                    );
                    int next = 1;
                    for (Disc disc : discs) {
                        disc.move(buttonPressTime + next);
                        if (disc.currentPosition != 0) {
                            return false;
                        }
                        next++;
                    }
                    return true;
                }).limit(1).forEach(System.out::println);
    }


    private static int time(Disc disc, int buttonPress) {
        while (disc.nextPosition() != 0) {
            buttonPress += 1;
        }
        return buttonPress;
    }
}

class Disc {
    String name;
    int positions;
    int startTime;
    int currentPosition;

    public Disc(String name, int positions, int startTime, int currentPosition) {
        this.name = name;
        this.positions = positions;
        this.startTime = startTime;
        this.currentPosition = currentPosition;
    }

    public int nextPosition() {
        this.currentPosition = (positions == currentPosition + 1) ? 0 : currentPosition + 1;
        return this.currentPosition;
    }

    public void move(int time) {
        for (int i = 0; i < time; i++) {
            nextPosition();
        }
    }

    @Override
    public String toString() {
        return "Disc{" +
                "name='" + name + '\'' +
                ", currentPosition=" + currentPosition +
                '}';
    }
}
