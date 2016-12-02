package adventofcode;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem02Test {

    @Test
    public void should_be_1985() throws Exception {
        List<String> instructions = Arrays.asList("ULL", "RRDDD", "LURDL", "UUUUD");
        String code = Problem02.code_part1(instructions);
        assertThat(code).isEqualTo("1985");
    }

    @Test
    public void problem2_input() throws Exception {
        List<String> instructions = Files.readAllLines(Paths.get("src", "test", "resources", "problem02.txt"));
        String code = Problem02.code_part1(instructions);
        assertThat(code).isEqualTo("74921");
    }

    @Test
    public void should_be_5DB3_part2() throws Exception {
        List<String> instructions = Arrays.asList("ULL", "RRDDD", "LURDL", "UUUUD");
        String code = Problem02.code_part2(instructions);
        assertThat(code).isEqualTo("5DB3");
    }

    @Test
    public void problem2_input_part2() throws Exception {
        List<String> instructions = Files.readAllLines(Paths.get("src", "test", "resources", "problem02.txt"));
        String code = Problem02.code_part2(instructions);
        assertThat(code).isEqualTo("A6B35");
    }
}