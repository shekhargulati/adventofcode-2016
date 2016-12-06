package adventofcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {

    @Test
    public void should_zip_multiple_lists_together() throws Exception {
        List<String> zipped = Utils.zip(Arrays.asList("hello", "halao", "hatao"));
        assertThat(zipped).isEqualTo(Arrays.asList("hhh", "eaa", "llt", "laa", "ooo"));
    }
}