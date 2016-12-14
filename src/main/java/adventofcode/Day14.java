package adventofcode;

import java.util.AbstractMap;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.LongStream;

public class Day14 {

    public static void main(String[] args) {

        String salt = "ahsbgdzn";
        LongStream.iterate(0, i -> i + 1)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(Utils.md5(salt + i).toLowerCase(), i))
                .filter(entry -> hasThreeSameCharacters(entry.getKey()) != null)
                .map(entry -> new AbstractMap.SimpleEntry<>(hasThreeSameCharacters(entry.getKey()), entry))
                .filter(entry -> {
                    Optional<AbstractMap.SimpleEntry<String, Long>> first = LongStream
                            .rangeClosed(entry.getValue().getValue() + 1, entry.getValue().getValue() + 1000)
                            .mapToObj(i -> new AbstractMap.SimpleEntry<>(Utils.md5(salt + i).toLowerCase(), i))
                            .filter(e -> hasSameCharactersFiveTimes(e.getKey(), entry.getKey()) != null)
                            .findFirst();
                    if(first.isPresent()){
                        System.out.println(first.get());
                    }
                    return first.isPresent();
                })
                .limit(64)
                .forEach(e ->{
                    System.out.println(e);
                    System.out.println();
                });


//        System.out.println(hasThreeSameCharacters("0034e0923cc38887a57bd7b1d4f953df"));

    }

    private static String hasSameCharactersFiveTimes(String hash, String needle) {
        String[] chs = hash.split("");
        int count = 0;
        String prev = null;
        for (String ch : chs) {
            if (Objects.equals(prev, needle)) {
                count++;
            } else {
                count = 1;
            }
            if (count == 6) {
                return ch;
            }
            prev = ch;
        }
        return null;
    }

    private static String hasThreeSameCharacters(String hash) {
        String[] chs = hash.split("");
        int count = 0;
        String prev = null;
        for (String ch : chs) {
            if (Objects.equals(ch, prev)) {
                count++;
            } else {
                count = 1;
            }
            if (count == 3) {
                return ch;
            }
            prev = ch;
        }
        return null;
    }
}
