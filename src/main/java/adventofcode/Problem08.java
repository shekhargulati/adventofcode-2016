package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Problem08 {

    public static void main(String[] args) throws IOException {

//        String cmd = "rect 3x2";
//        String cmd1 = "rotate column x=1 by 1";
//        String cmd2 = "rotate row y=0 by 4";
//        String cmd3 = "rotate row x=1 by 1";
//        count(Arrays.asList(cmd, cmd1, cmd2, cmd3));

        int rows = 6;
        int cols = 50;
        int[][] screen = new int[rows][cols];

        List<String> cmds = Files.readAllLines(Paths.get("src", "test", "resources", "problem08.txt"));
        count(screen, cmds);
        toString(screen);
        int count = 0;
        for (int[] ints : screen) {
            for (int anInt : ints) {
                count += anInt;
            }
        }

        System.out.println(count);

        String[][] a = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(screen[i][j] == 1){
                    a[i][j] = "#";
                }else{
                    a[i][j] = " ";
                }
            }
        }

        toString1(a);
    }

    private static int count(int[][] screen, List<String> cmds) {

        int rows = 6;
        int cols = 50;

        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++) {
                screen[i][j] = 0;
            }
        }

        for (String cmd : cmds) {
            String[] parts = cmd.split("\\s");

            String type = parts[0];
            if (Objects.equals(type, "rect")) {
                String[] dim = parts[1].split("x");
                int a = Integer.parseInt(dim[0]);
                int b = Integer.parseInt(dim[1]);
                for (int i = 0; i < b; i++) {
                    for (int j = 0; j < a; j++) {
                        screen[i][j] = 1;
                    }
                }
            } else if (Objects.equals(type, "rotate") && Objects.equals(parts[1], "column")) {
                String[] strs = parts[2].split("=");
                if (strs[0].equals("x")) {
                    int x = Integer.parseInt(strs[1]);
                    int by = Integer.parseInt(parts[4]);
                    int[] tmp = new int[rows];
                    for (int i = 0; i < screen.length; i++) {
                        for (int j = x; j <= x; j++) {
                            tmp[i] = screen[i][j];
                        }
                    }

                    for (int i = 0, v = i + by; i < screen.length; i++, v++) {
                        for (int j = x; j <= x; j++) {
                            if (v < screen.length) {
                                screen[v][j] = tmp[i];
                            } else {
                                v = 0;
                                screen[v][j] = tmp[i];
                            }
                        }
                    }

                } else {

                    int y = Integer.parseInt(strs[1]);
                    int by = Integer.parseInt(parts[4]);
                    int[] tmp = new int[cols];
                    for (int i = y; i <= y; i++) {
                        for (int j = 0; j < cols; j++) {
                            tmp[j] = screen[i][j];
                        }
                    }

                    for (int i = y; i <= y; i++) {
                        for (int j = 0, v = j + by; j < cols; j++, v++) {
                            if (v < cols) {
                                screen[i][v] = tmp[j];
                            } else {
                                v = 0;
                                screen[i][v] = tmp[j];
                            }
                        }
                    }

                }


            } else if (Objects.equals(type, "rotate") && Objects.equals(parts[1], "row")) {
                String[] strs = parts[2].split("=");
                if (strs[0].equals("y")) {
                    int y = Integer.parseInt(strs[1]);
                    int by = Integer.parseInt(parts[4]);
                    int[] tmp = new int[cols];
                    for (int i = y; i <= y; i++) {
                        for (int j = 0; j < cols; j++) {
                            tmp[j] = screen[i][j];
                        }
                    }

                    for (int i = y; i <= y; i++) {
                        for (int j = 0, v = j + by; j < cols; j++, v++) {
                            if (v < cols) {
                                screen[i][v] = tmp[j];
                            } else {
                                v = 0;
                                screen[i][v] = tmp[j];
                            }
                        }
                    }

                } else {
                    int x = Integer.parseInt(strs[1]);
                    int by = Integer.parseInt(parts[4]);
                    int[] tmp = new int[rows];
                    for (int i = 0; i < screen.length; i++) {
                        for (int j = x; j < x + by; j++) {
                            tmp[i] = screen[i][j];
                        }
                    }

                    for (int i = 0, v = i + by; i < screen.length; i++, v++) {
                        for (int j = x; j <= x; j++) {
                            if (v < screen.length) {
                                screen[v][j] = tmp[i];
                            } else {
                                v = 0;
                                screen[v][j] = tmp[i];
                            }
                        }
                    }
                }
            }

//            System.out.println();
//            toString(screen);
//            System.out.println();
        }


        return 0;
    }

    public static void toString(int[][] screen) {
        for (int[] ints : screen) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void toString1(String[][] screen) {
        for (String[] ints : screen) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
