package adventofcode;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem07Test {

    @Test
    public void part1_test() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "test", "resources", "problem07.txt"));
        long count = Problem07.count_part1(lines);
        assertThat(count).isEqualTo(110L);
    }

    @Test
    public void part2_test() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "test", "resources", "problem07.txt"));
        long count = Problem07.count_part2(lines);
        assertThat(count).isEqualTo(242L);
    }
}