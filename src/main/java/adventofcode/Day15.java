package adventofcode;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day15 {

    public static void main(String[] args) {
        List<Disc> discs = Arrays.asList(
                new Disc("#1", 17, 0, 5),
                new Disc("#2", 19, 0, 8),
                new Disc("#3", 7, 0, 1),
                new Disc("#4", 13, 0, 7),
                new Disc("#5", 5, 0, 1),
                new Disc("#6", 3, 0, 0),
                new Disc("#7", 11, 0, 0)
        );

        IntStream.iterate(0, i -> i + 1)
                .filter(buttonPressTime ->
                        IntStream.range(0, discs.size())
                                .mapToObj(i -> new SimpleEntry<>(i + 1, discs.get(i)))
                                .allMatch(entry ->
                                        ((buttonPressTime + entry.getValue().currentPosition + entry.getKey()) % entry.getValue().positions) == 0))
                .limit(1)
                .forEach(System.out::println);
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

    @Override
    public String toString() {
        return "Disc{" +
                "name='" + name + '\'' +
                ", currentPosition=" + currentPosition +
                '}';
    }
}
