package adventofcode;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem04Test {

    @Test
    public void sum_of_sector_ids() throws Exception {
        int sum = Problem04.sumOfSectorIds(Arrays.asList("aaaaa-bbb-z-y-x-123[abxyz]", "a-b-c-d-e-f-g-h-987[abcde]", "not-a-real-room-404[oarel]", "totally-real-room-200[decoy]"));
        assertThat(sum).isEqualTo(1514);
    }

    @Test
    public void a_valid_room_name_when_checksum_is_first_5_common_characters() throws Exception {
        final String name = "aaaaa-bbb-z-y-x-123[abxyz]";
        boolean real = Problem04.isRealRoom(name);
        assertThat(real).isTrue();
    }

    @Test
    public void a_valid_room_name_when_checksum_is_first_5_common_characters_2() throws Exception {
        final String name = "a-b-c-d-e-f-g-h-987[abcde]";
        boolean real = Problem04.isRealRoom(name);
        assertThat(real).isTrue();
    }

    @Test
    public void a_valid_room_name_when_checksum_is_first_5_common_characters_3() throws Exception {
        final String name = "not-a-real-room-404[oarel]";
        boolean real = Problem04.isRealRoom(name);
        assertThat(real).isTrue();
    }

    @Test
    public void a_valid_room_name_when_checksum_is_first_5_common_characters_4() throws Exception {
        final String name = "totally-real-room-200[decoy]";
        boolean real = Problem04.isRealRoom(name);
        assertThat(real).isFalse();
    }

    @Test
    public void part1() throws Exception {
        List<String> names = Files.readAllLines(Paths.get("src", "test", "resources", "problem04.txt"));
        int sum = Problem04.sumOfSectorIds(names);
        assertThat(sum).isEqualTo(137896);
    }

    @Test
    public void decrypt_a_name() throws Exception {
        String realName = Problem04.toRealName("qzmt-zixmtkozy-ivhz-343[sdffr]");
        assertThat(realName).isEqualTo("very encrypted name");
    }

    @Test
    public void part2() throws Exception {
        List<String> names = Files.readAllLines(Paths.get("src", "test", "resources", "problem04.txt"));
        int sectorId = Problem04.sectorIdOf(names, "northpole object storage");
        assertThat(sectorId).isEqualTo(501);
    }
}