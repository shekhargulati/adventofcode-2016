package adventofcode;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem06Test {

    @Test
    public void part1_test() throws Exception {
        String code = Problem06.part1(Files.readAllLines(Paths.get("src", "test", "resources", "problem06.txt")));
        assertThat(code).isEqualTo("ygjzvzib");
    }

    @Test
    public void part2_test() throws Exception {
        String code = Problem06.part2(Files.readAllLines(Paths.get("src", "test", "resources", "problem06.txt")));
        assertThat(code).isEqualTo("pdesmnoz");
    }
}
