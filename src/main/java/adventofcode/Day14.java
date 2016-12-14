package adventofcode;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.LongStream;

import static adventofcode.Utils.md5;

public class Day14 {

    public static void main(String[] args) {

        String salt = "ahsbgdzn";
        Map<Long, String> cache = new HashMap<>();
        LongStream.iterate(0, i -> i + 1)
                .mapToObj(i -> new SimpleEntry<>(md5(salt + i).toLowerCase(), i))
                .map(e -> {
                    if (cache.containsKey(e.getValue())) {
                        return new SimpleEntry<>(cache.get(e.getValue()), e.getValue());
                    }
                    String hash = e.getKey();
                    for (int i = 0; i < 2016; i++) {
                        hash = md5(hash).toLowerCase();
                    }
                    cache.putIfAbsent(e.getValue(), hash);
                    return new SimpleEntry<>(hash, e.getValue());
                })
                .filter(entry -> hasThreeSameCharacters(entry.getKey()) != null)
                .map(entry -> new SimpleEntry<>(hasThreeSameCharacters(entry.getKey()), entry))
                .filter(entryOfEntry -> {
                    Optional<SimpleEntry<String, Long>> first = LongStream
                            .rangeClosed(entryOfEntry.getValue().getValue() + 1, entryOfEntry.getValue().getValue() + 1000)
                            .mapToObj(i -> new SimpleEntry<>(md5(salt + i).toLowerCase(), i))
                            .map(e -> {
                                if (cache.containsKey(e.getValue())) {
                                    return new SimpleEntry<>(cache.get(e.getValue()), e.getValue());
                                }
                                String hash = e.getKey();
                                for (int i = 0; i < 2016; i++) {
                                    hash = md5(hash).toLowerCase();
                                }
                                cache.putIfAbsent(e.getValue(), hash);
                                return new SimpleEntry<>(hash, e.getValue());
                            })
                            .filter(e -> hasSameCharactersFiveTimes(e.getKey(), entryOfEntry.getKey()) != null)
                            .findFirst();
                    if (first.isPresent()) {
                        System.out.println(first.get());
                    }
                    return first.isPresent();
                })
                .limit(64)
                .forEach(e -> {
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
