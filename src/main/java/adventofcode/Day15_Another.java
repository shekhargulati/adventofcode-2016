package adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Day15_Another {

    public static void main(String[] args) {
        LongStream.iterate(0, i -> i + 1)
                .filter(buttonPressTime -> {
                    List<Disc> discs = Arrays.asList(
                            new Disc("#1", 17, 0, 5),
                            new Disc("#2", 19, 0, 8),
                            new Disc("#3", 7, 0, 1),
                            new Disc("#4", 13, 0, 7),
                            new Disc("#5", 5, 0, 1),
                            new Disc("#6", 3, 0, 0),
                            new Disc("#7", 11, 1, 0)
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
                })
                .limit(1).forEach(System.out::println);
    }
}

