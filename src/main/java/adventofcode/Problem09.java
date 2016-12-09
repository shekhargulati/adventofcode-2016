package adventofcode;

import strman.Strman;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem09 {

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "test", "resources", "problem09.txt"));

//        String input = "A(1x5)BC";
//
//        int l = length(input);
//        System.out.println(l);
//
//        input = "(3x3)XYZ";
//        l = length(input);
//        System.out.println(l);
//
//        input = "A(2x2)BCD(2x2)EFG";
//        l = length(input);
//        System.out.println(l);
//
//        input = "(6x1)(1x3)A";
//        l = length(input);
//        System.out.println(l);
//
//        input = "X(8x2)(3x3)ABCY";
//        l = length(input);
//        System.out.println(l);

        System.out.println(length(lines.get(0)));

//        System.out.println(lines.stream().map(line -> length(line)).mapToLong(l -> l).sum());
    }

    private static int length(String input) {
        List<String> expressions = between(input);
        LinkedList<String> queue = expressions.stream().map(expression -> expression.replace("(", "").replace(")", "")).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(queue);
        StringBuilder result = new StringBuilder();
        boolean skip = false;
        int countOfQueues = 0;
        for (int i = 0; i < input.length(); i++) {
            String str = String.valueOf(input.charAt(i));
            if (skip) {
                if (Objects.equals(str, ")")) {
                    if (!queue.isEmpty()) {
                        String exp = queue.removeFirst();
                        String[] parts = exp.split("x");
                        int a = Utils.toInt(parts[0]);
                        int b = Utils.toInt(parts[1]);
                        String substring = input.substring(i + 1, i + 1 + a);
                        result.append(Strman.repeat(substring, b));
                        i = i + a;
                        skip = false;
                        IntStream.range(1, countOfQueues).forEach(l -> {
                            if (!queue.isEmpty()) {
                                queue.removeFirst();
                            }
                        });
                    }
                }
            } else if (Objects.equals(str, "(")) {
                skip = true;
                countOfQueues++;
            } else {
                result.append(str);
            }
        }
        System.out.println(result);
        String r = result.toString();
        int count = 0;
        for (int i = 0; i < r.length(); i++) {
            String str = String.valueOf(r.charAt(i));
            if(str == ")" || str == "(" || isNumber(str) || str.equals("x")){

            }else{
                count++;
            }
        }
        return count;
    }

    private static List<String> between(String in) {
//        String in = "A(2x2)BCD(2x2)EFG";

        Pattern p = Pattern.compile("\\((.*?)\\)");
        Matcher m = p.matcher(in);

        List<String> r = new ArrayList<>();
        while (m.find()) {
            r.add(m.group(1));
        }
        return r;
    }


    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
