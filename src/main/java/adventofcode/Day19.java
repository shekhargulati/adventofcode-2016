package adventofcode;

import java.util.Arrays;
import java.util.Iterator;

public class Day19 {

    public static void main(String[] args) throws Exception {
        CircularBuffer elfs = new CircularBuffer(3012210);
        for (int i = 0; i < elfs.size(); i++) {
            elfs.add(new Elf(i + 1, 1));
        }

        while (elfs.size() > 1) {
            for (int i = 0; i < elfs.size(); i++) {
                Elf current = elfs.get(i);
                if (current.presents > 0) {
                    Elf left = elfs.get(i + 1);
                    current.addPresents(left.presents);
                    left.zero();
                }
            }

            elfs = elfs.filterZero();
        }

        System.out.println(elfs.get(0));

    }
}

class Elf {
    int pos;
    int presents;

    public Elf(int pos, int presents) {
        this.pos = pos;
        this.presents = presents;
    }

    public void addPresents(int p) {
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


class CircularBuffer implements Iterable<Elf> {
    private final int n;
    private final Elf[] items;
    private int currentLocation;

    public CircularBuffer(int n) {
        this.n = n;
        this.items = new Elf[n];
    }

    public void add(Elf item) {
        items[currentLocation++] = item;
    }

    public Elf get(int i) {
        if (i == items.length) {
            i = 0;
        }
        return items[i];
    }

    public int size() {
        return this.n;
    }

    public CircularBuffer filterZero() {
        int nonZero = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).presents > 0) {
                nonZero++;
            }
        }

        CircularBuffer copy = new CircularBuffer(nonZero);
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).presents > 0) {
                copy.add(this.get(i));
            }
        }
        return copy;
    }

    @Override
    public Iterator<Elf> iterator() {
        return new CircularBufferIterator(items);
    }

    private class CircularBufferIterator implements Iterator<Elf> {

        private final Elf[] copy;
        int index;

        public CircularBufferIterator(Elf[] items) {
            this.copy = Arrays.copyOf(items, items.length);
        }

        @Override
        public boolean hasNext() {
            return index < items.length;
        }

        @Override
        public Elf next() {
            if (items[index].presents > 0) {
                return items[index++];
            }
            index++;
            return next();
        }
    }
}

