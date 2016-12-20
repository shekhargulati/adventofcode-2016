package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static adventofcode.Utils.toLong;

public class Day20 {

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src", "main", "resources", "problem20.txt"));

        List<IpAddressRange> ranges = lines.stream()
                .map(IpAddressRange::new)
                .sorted((e1, e2) -> e1.start - e2.start < 0 ? -1 : 1)
                .collect(Collectors.toList());

        long current = 1;
        long allowed = 0;
        for (IpAddressRange range : ranges) {
            if (current < range.start) {
                allowed += (range.start - current);
            }

            if (current < range.end) {
                current = range.end + 1;
            }
        }

        System.out.println(allowed);

    }

}

class IpAddressRange {
    long start;
    long end;

    public IpAddressRange(String str) {
        String[] parts = str.split("-");
        this.start = toLong(parts[0]);
        this.end = toLong(parts[1]);
    }
}
