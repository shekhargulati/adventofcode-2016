package adventofcode;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class Problem05Test {

    @Test
    public void part1_test() throws Exception {
        String password = Problem05.part1("abbhdwsy");
        assertThat(password).isEqualTo("801b56a7");
    }

    @Test
    public void part2_test() throws Exception {
        String password = Problem05.part2("abbhdwsy");
        assertThat(password).isEqualTo("424a0197");
    }
}