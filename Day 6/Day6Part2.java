import java.util.*;
import java.util.function.*;
import java.io.*;

public class Day6Part2 {

    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day6_input.txt"));
        List<String> tmp = new ArrayList<>();
        while (sc.hasNextLine()) {
            tmp.add(sc.nextLine());
        }
        int N = tmp.size(), M = tmp.get(0).length();
        char[][] grid = new char[N][M];
        int _gx = -1, _gy = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = tmp.get(i).charAt(j);
                if (grid[i][j] == '^') { 
                    _gx = i;
                    _gy = j;
                }
            }
        }
        int X = _gx, Y = _gy;
        BiPredicate<Integer, Integer> checkOutOfBounds = (x, y) -> {
            return x < N && x >= 0 && y < M && y >= 0;
        };
        BiPredicate<Integer, Integer> isObstacle = (x, y) -> {
            return checkOutOfBounds.test(x, y) && grid[x][y] == '#';
        };
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        BiPredicate<Integer, Integer> checkInfiniteLoop = (_x, _y) -> {
            int gx = X, gy = Y;
            boolean ok = false;
            int k = 0;
            HashMap<String, Boolean> used = new HashMap<>();
            while (true) {
                if (!checkOutOfBounds.test(gx, gy)) {
                    break;
                }
                int x = gx + dx[k], y = gy + dy[k];
                while (isObstacle.test(x, y)) {
                    x -= dx[k];
                    y -= dy[k];
                    k++;
                    k %= 4;
                    x += dx[k];
                    y += dy[k];
                }
                gx = x;
                gy = y;
                String s = gx + " " + dx[k] + " " + gy + " " + dy[k];
                if (used.containsKey(s)) {
                    ok = true;
                    break;
                }
                used.put(s, true);
            }
            return ok;
        };
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == X && j == Y) {
                    continue;
                }
                char ch = grid[i][j];
                grid[i][j] = '#';
                if (checkInfiniteLoop.test(i, j)) {
                    res++;
                }
                grid[i][j] = ch;
            }
        }
        System.out.println(res);
        sc.close();
    }
}
