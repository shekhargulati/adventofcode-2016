package adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day17 {

    public static void main(String[] args) {
        /*

        1. Start from the initial position (0,0) on the grid (4,4)
        2. Calculate your next move by finding MD5 hash of input and path.
        3. Check which all positions are applicable
        4. When you pick a position, add it to the path and repeat until you reach end
         */

//        Arrays.asList("ihgpwlah", "kglvqrro", "ulqzkmiv").forEach(input -> {
        Arrays.asList("ihgpwlah").forEach(input -> {

            StringBuilder path = new StringBuilder(input);
            int x = 0, y = 0;

            Stack<Game> games = new Stack<>();
            games.add(new Game(0, 0, new ArrayList<>(), input));

            List<String> allPaths = new ArrayList<>();
            Map<String, List<Move>> cache = new HashMap<>();

            while (!games.isEmpty()) {
                String md5 = Utils.md5(path.toString());
                String move = md5.substring(0, 4);
                List<Move> possibleMoves = cache.containsKey(move) ? cache.get(move) : possibleMoves(x, y, move);

                if (possibleMoves.isEmpty()) {
                    if (games.stream().filter(g -> !g.moves.isEmpty()).count() == 0) {
                        break;
                    }
                    Game previousState = games.pop();
                    x = previousState.x;
                    y = previousState.y;
                    possibleMoves = previousState.moves;
                    path = new StringBuilder(previousState.path);
                }

                Move next = possibleMoves.get(0);
                List<Move> remaining = possibleMoves.stream().skip(1).collect(Collectors.toList());
                if (!remaining.isEmpty()) {
                    games.push(new Game(x, y, remaining, path.toString()));
                }
                cache.put(move, remaining);
                x += next.x;
                y += next.y;
                path.append(next.dir);
                if (x >= 3 && y >= 3) {
                    allPaths.add(path.toString());
                    Game previousState = games.pop();
                    x = previousState.x;
                    y = previousState.y;
                    path = new StringBuilder(previousState.path);
                }


            }

            String result = allPaths.stream().map(s -> s.substring(input.length())).max((s1, s2) -> s1.length() - s2.length()).get();
            System.out.println(result);
            System.out.println(result.length());
        });


    }

    private static List<Move> possibleMoves(int x, int y, String move) {
        List<Move> possibleMoves = new ArrayList<>();
        if (isOpen(move.charAt(0)) && y > 0) {
            possibleMoves.add(new Move(0, -1, "U"));
        }
        if (isOpen(move.charAt(1)) && y < 3) {
            possibleMoves.add(new Move(0, 1, "D"));
        }
        if (isOpen(move.charAt(2)) && x > 0) {
            possibleMoves.add(new Move(-1, 0, "L"));
        }
        if (isOpen(move.charAt(3)) && x < 3) {
            possibleMoves.add(new Move(1, 0, "R"));
        }
        return possibleMoves;
    }

    private static boolean isOpen(char ch) {
        return ch == 'b' || ch == 'c' || ch == 'd' || ch == 'e' || ch == 'f';
    }

}

class Move {
    int x;
    int y;
    String dir;

    public Move(int x, int y, String dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Move{" +
                "x=" + x +
                ", y=" + y +
                ", dir='" + dir + '\'' +
                '}';
    }

    public boolean isValid() {
        return this.x >= 0 && this.x < 4 && this.y >= 0 && this.y < 4;
    }
}

class Game {
    int x;
    int y;
    List<Move> moves;
    String path;

    public Game(int x, int y, List<Move> moves, String path) {
        this.x = x;
        this.y = y;
        this.moves = moves;
        this.path = path;
    }
}
