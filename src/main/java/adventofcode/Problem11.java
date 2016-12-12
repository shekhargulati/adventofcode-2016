package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Problem11 {

    public static void main(String[] args) throws Exception {
        List<String> input = Files.readAllLines(Paths.get("src", "test", "resources", "problem11.txt"));
        String[][] arrangement = toInitialArrangement(input);
        toString(arrangement);

        int currentFloor = 0;
        while (isFinish(arrangement)) {
            String[] current = arrangement[currentFloor];
            String[] microchips = microchips(current);
            String[] generators = generators(current);
            int nextFloor = currentFloor+1;
            if(microchips.length > 0){
                String[] next = arrangement[nextFloor];
                String[] nextGenerators = generators(next);
                for (String microchip : microchips) {
                    for (String nextGenerator : nextGenerators) {
                        if(microchip.startsWith(String.valueOf(nextGenerator.charAt(0)))){
                            
                        }


                    }
                }


            }

        }
    }

    private static String[] microchips(String[] current) {
        return Arrays.stream(current).filter(m -> m.endsWith("M")).toArray(String[]::new);
    }

    private static String[] generators(String[] current) {
        return Arrays.stream(current).filter(m -> m.endsWith("G")).toArray(String[]::new);
    }

    private static boolean isFinish(String[][] arrangement) {
        String[] fourth = arrangement[3];
        return Arrays.stream(fourth).allMatch(l -> l != ".");
    }

    private static String[][] toInitialArrangement(List<String> input) {
        int rows = 4;
        int columns = 6;
        String[][] arrangement = new String[rows][columns];

        int floor = 0;

        for (String instruction : input) {
            int generatorPos = 2;
            int microPos = 3;
            String[] parts = instruction.split("\\s");

            if (floor == 0) {
                arrangement[floor][1] = "E";
            }

            arrangement[floor][0] = "F" + (floor + 1);

            for (int i = 1; i < parts.length; i++) {
                if (parts[i].contains("microchip")) {
                    String microchip = parts[i - 1];
                    arrangement[floor][microPos] = toMicrochipName(microchip);
                    microPos += 2;
                } else if (parts[i].contains("generator")) {
                    String generator = parts[i - 1];
                    if (generator.startsWith("l")) {
                        generatorPos = generatorPos + 2;
                    }
                    arrangement[floor][generatorPos] = toGeneratorName(generator);
                    generatorPos += 2;
                }
            }

            floor++;
        }

        replaceNullWithDot(arrangement);

        return arrangement;
    }

    private static void replaceNullWithDot(String[][] arrangement) {
        for (int i = 0; i < arrangement.length; i++) {
            for (int j = 0; j < arrangement[0].length; j++) {
                if (arrangement[i][j] == null) {
                    arrangement[i][j] = ".";
                }
            }
        }
    }

    private static String toMicrochipName(String microchip) {
        return String.valueOf(microchip.charAt(0)).toUpperCase() + "M";
    }

    private static String toGeneratorName(String generator) {
        return String.valueOf(generator.charAt(0)).toUpperCase() + "G";
    }

    public static void toString(String[][] arrangement) {
        for (int i = arrangement.length - 1; i >= 0; i--) {
            String[] row = arrangement[i];
            System.out.println(Arrays.stream(row).collect(joining(" ")));
        }

    }
}
