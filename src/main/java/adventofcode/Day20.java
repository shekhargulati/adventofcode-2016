package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

import static adventofcode.Utils.toLong;

public class Day20 {

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "main", "resources", "problem20.txt"));

        List<AbstractMap.SimpleEntry<Long, Long>> list = lines.stream()
                .map(s -> s.split("-"))
                .map(s -> new AbstractMap.SimpleEntry<>(toLong(s[0]), toLong(s[1])))
                .collect(Collectors.toList());


        long min = lines.stream()
                .map(s -> s.split("-"))
                .mapToLong(s -> toLong(s[0]))
                .min().getAsLong();

        long max = lines.stream()
                .map(s -> s.split("-"))
                .mapToLong(s -> toLong(s[1]))
                .max().getAsLong();


        while (min <= max) {

            boolean exists = false;
            for (AbstractMap.SimpleEntry<Long, Long> entry : list) {
                if (!exists && (min >= entry.getKey() && min <= entry.getValue())) {
                    exists = true;
                }
            }
            if(!exists){
                break;
            }
            min++;
        }

        System.out.println(min);


    }
}
