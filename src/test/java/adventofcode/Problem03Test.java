package adventofcode;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem03Test {

    @Test
    public void a_valid_triangle() throws Exception {
        boolean triangle = new Triangle(3, 4, 5).isValid();
        assertThat(triangle).isTrue();
    }

    @Test
    public void invalid_triangle() throws Exception {
        boolean triangle = new Triangle(5, 10, 25).isValid();
        assertThat(triangle).isFalse();
    }

    @Test
    public void input_1_part1() throws Exception {
        long count = Problem03.countTriangles_part1(Files.readAllLines(Paths.get("src", "test", "resources", "problem03.txt")));
        assertThat(count).isEqualTo(1050L);
    }

    @Test
    public void input_1_part2() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "test", "resources", "problem03.txt"));
        int count = Problem03.countTriangles_part2(lines);
        assertThat(count).isEqualTo(1921);
    }


    @Test
    public void input_1_part1_another() throws Exception {
        long count = Problem03.countTriangles_part1(Files.readAllLines(Paths.get("src", "test", "resources", "problem03_2.txt")));
        assertThat(count).isEqualTo(862L);
    }

    @Test
    public void part2_another() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "test", "resources", "problem03_2.txt"));
        int count = Problem03.countTriangles_part2(lines);
        assertThat(count).isEqualTo(1577);
    }
}