package adventofcode;

import strman.Strman;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static adventofcode.Utils.toInt;

public class Day22 {

    public static void main(String[] args) throws Exception {
        Stream<String> lines = Files.lines(Paths.get("src", "main", "resources", "problem22.txt")).skip(2);

        List<Node> data = lines.map(Node::new).collect(Collectors.toList());

        List<Pair> result = new ArrayList<>();

        for (Node fst : data) {
            for (Node snd : data) {
                if (fst.isViable(snd)) {
                    result.add(new Pair(fst, snd));
                }

            }
        }

        System.out.println("Total viable pairs: " + result.size());


    }
}

class Node {
    String name;
    int size;
    int avail;
    int used;
    int usePercent;

    public Node(String input) {
        input = Strman.collapseWhitespace(input);
        String[] parts = input.split("\\s");
        this.name = parts[0];
        this.size = toInt(parts[1].replace("T", ""));
        this.used = toInt(parts[2].replace("T", ""));
        this.avail = toInt(parts[3].replace("T", ""));
        this.usePercent = toInt(parts[4].replace("%", ""));
    }

    public boolean isViable(Node n2) {
        return !Objects.equals(this.name, n2.name)
                && this.used > 0
                && this.used <= n2.avail;
    }


}

class Pair {
    Node fst;
    Node snd;

    public Pair(Node fst, Node snd) {
        this.fst = fst;
        this.snd = snd;
    }


}