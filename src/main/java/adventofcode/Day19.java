package adventofcode;

import java.util.ArrayDeque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day19 {

    public static void main(String[] args) throws Exception {
        int totalElves = 3012210;
        ArrayDeque<Integer> left = IntStream.rangeClosed(1, totalElves / 2).mapToObj(i -> i).collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Integer> right = IntStream.rangeClosed(totalElves / 2 + 1, totalElves).mapToObj(i -> i).collect(Collectors.toCollection(ArrayDeque::new));

        while (left.size() + right.size() > 1) {
            if (left.size() > right.size()) left.pollLast();
            else right.pollFirst();

            right.addLast(left.pollFirst());
            left.addLast(right.pollFirst());
        }

        System.out.println(left.size() == 0 ? right.getFirst() : left.getFirst());
    }

}




