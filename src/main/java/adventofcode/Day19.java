package adventofcode;

import java.util.Arrays;

public class Day19 {

    public static void main(String[] args) throws Exception {

        Elf[] elfs = new Elf[3012210];
        for (int i = 0; i < elfs.length; i++) {
            elfs[i] = new Elf(i + 1, 1);
        }
        while (elfs.length > 1) {
            System.out.println(elfs.length);
            for (int i = 0; i < elfs.length; i++) {
                if(elfs[i].presents > 0){
                    int next = i + 1 < elfs.length ? i + 1 : 0;
                    elfs[i].addPresent(elfs[next].presents);
                    elfs[next].zero();
                }
            }
            elfs = Arrays.stream(elfs).filter(e -> e.presents > 0).toArray(Elf[]::new);
//            System.out.println(elfs.length);
        }

        System.out.println(Arrays.toString(elfs));

    }
}

class Elf {
    int pos;
    int presents;

    public Elf(int pos, int presents) {
        this.pos = pos;
        this.presents = presents;
    }

    public void addPresent(int p) {
        this.presents += p;
    }

    public void zero() {
        this.presents = 0;
    }

    @Override
    public String toString() {
        return "Elf{" +
                "pos=" + pos +
                ", presents=" + presents +
                '}';
    }
}
