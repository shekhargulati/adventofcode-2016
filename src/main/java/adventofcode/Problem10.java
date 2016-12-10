package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static java.util.stream.Collectors.*;

public class Problem10 {

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "test", "resources", "problem10.txt"));

        int first = 17;
        int second = 61;
        Map<String, List<Integer>> initialValues = lines.stream()
                .filter(l -> l.startsWith("value"))
                .map(Problem10::getSplit)
                .collect(groupingBy(SimpleEntry::getKey, LinkedHashMap::new, mapping(SimpleEntry::getValue, toList())));


        Map.Entry<String, List<Integer>> start = initialValues.entrySet().stream().filter(e -> e.getValue().size() == 2).findAny().get();
        LinkedList<Map.Entry<String, List<Integer>>> queue = new LinkedList<>();
        queue.add(start);

        List<String> seen = new ArrayList<>();
        seen.add(start.getKey());
        Map<Integer, Integer> bins = new HashMap<>();

        while (!queue.isEmpty()) {
            String startingBot = queue.removeFirst().getKey();
            System.out.println("Bot: " + startingBot);
            lines.stream()
                    .filter(l -> {
                        String[] parts = l.split("\\s");
                        if (Objects.equals(parts[0] + " " + parts[1], startingBot)) {
                            return true;
                        } else {
                            return false;
                        }
                    })
//                    .filter(l -> !l.contains("output"))
                    .forEach(l -> {
                        System.out.println("Found line " + l);
                        String[] parts = l.split("\\s");
                        String giver = "bot " + parts[1];
                        boolean isFirstOutput = Objects.equals(parts[5], "output");
                        String takerLow = "bot " + parts[6];
                        boolean isSecondOutput = Objects.equals(parts[10], "output");
                        String takerHigh = "bot " + parts[11];
                        List<Integer> values = initialValues.get(giver);
                        int high = values.stream().max((i1, i2) -> i1 - i2).get();
                        int low = values.stream().min((i1, i2) -> i1 - i2).get();

//                        System.out.println("High " + high);
//                        System.out.println("Low " + low);

                        if (!isSecondOutput) {
                            if (initialValues.containsKey(takerHigh)) {
                                List<Integer> h = initialValues.get(takerHigh);
                                h.add(high);
                            } else {
                                List<Integer> list = new ArrayList<>();
                                list.add(high);
                                initialValues.put(takerHigh, list);
                            }
                        }


                        if (!isFirstOutput) {
                            if (initialValues.containsKey(takerLow)) {
                                List<Integer> h = initialValues.get(takerLow);
                                h.add(low);
                            } else {
                                List<Integer> list = new ArrayList<>();
                                list.add(low);
                                initialValues.put(takerLow, list);
                            }
                        }
                        if (isFirstOutput) {
                            bins.put(Utils.toInt(parts[6]), low);
                        }

                        if (isSecondOutput) {
                            bins.put(Utils.toInt(parts[11]), high);
                        }

                        if (isFirstOutput && isSecondOutput) {
                            return;
                        }
                        Map.Entry<String, List<Integer>> next = initialValues.entrySet().stream().filter(e -> !seen.contains(e.getKey()) && e.getValue().size() == 2).findFirst().get();
                        queue.add(next);
                        seen.add(next.getKey());

//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    });
        }

        System.out.println(initialValues);

        String result = initialValues.entrySet()
                .stream()
                .filter(e -> {
                    List<Integer> value = e.getValue();
                    if (value.contains(first) && value.contains(second)) {
                        return true;
                    }
                    return false;
                })
                .findFirst()
                .get()
                .getKey();

        System.out.println("Answer is " + result);

        System.out.println(bins.get(0) * bins.get(1) * bins.get(2));


    }

    private static SimpleEntry<String, Integer> getSplit(String l) {
        String[] parts = l.split("\\s");
        int v = Utils.toInt(parts[1]);
        String bot = String.format("bot %s", parts[5]);
        return new SimpleEntry<>(bot, v);
    }
}
