package adventofcode;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem07Test {


    @Test
    public void part1_small_input() throws Exception {
        boolean isTls = Problem07.part1("abba[mnop]qrst");
        assertThat(isTls).isTrue();

        isTls = Problem07.part1("abcd[bddb]xyyx");
        assertThat(isTls).isFalse();

        isTls = Problem07.part1("aaaa[qwer]tyui");
        assertThat(isTls).isFalse();

        isTls = Problem07.part1("ioxxoji[asdfgh]zxcvbn");
        assertThat(isTls).isTrue();
    }

    @Test
    public void part1_test() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "test", "resources", "problem07.txt"));
        long count = lines.stream().filter(line -> Problem07.part1(line)).count();
        assertThat(count).isEqualTo(110L);
    }

    @Test
    public void part2_test() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "test", "resources", "problem07.txt"));
        long count = lines.stream().filter(line -> Problem07.part1(line)).count();
        assertThat(count).isEqualTo(242L);
    }

    @Test
    public void name() throws Exception {
        String str = "gdlrknrmexvaypu[crqappbbcaplkkzb]vhvkjyadjsryysvj[nbvypeadikilcwg]jwxlimrgakadpxu[dgoanojvdvwfabtt]yqsalmulblolkgsheo";
        Problem07.part1(str);
    }

    @Test
    public void problem2_small_input() throws Exception {
        boolean isTls = Problem07.part1("aba[bab]xyz");
        assertThat(isTls).isTrue();

        isTls = Problem07.part1("xyx[xyx]xyx");
        assertThat(isTls).isFalse();

        isTls = Problem07.part1("aaa[kek]eke");
        assertThat(isTls).isTrue();

        isTls = Problem07.part1("zazbz[bzb]cdb");
        assertThat(isTls).isTrue();

    }
}