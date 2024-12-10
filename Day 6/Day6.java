import java.util.*;
import java.util.function.*;
import java.io.*;

public class Day6 {

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
        int gx = -1, gy = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = tmp.get(i).charAt(j);
                if (grid[i][j] == '^') { 
                    gx = i;
                    gy = j;
                }
            }
        }
        boolean ok = false;
        BiPredicate<Integer, Integer> checkOutOfBounds = (x, y) -> {
            return x < N && x >= 0 && y < M && y >= 0;
        };
        BiPredicate<Integer, Integer> isObstacle = (x, y) -> {
            return checkOutOfBounds.test(x, y) && grid[x][y] == '#';
        };
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        int k = 0;
        while (true) {
            grid[gx][gy] = 'X';
            int x = gx + dx[k], y = gy + dy[k];
            if (!checkOutOfBounds.test(x, y))
                break;
            while (isObstacle.test(x, y)) {
                x -= dx[k];
                y -= dy[k];
                k++;
                k %= 4;
                x += dx[k];
                y += dy[k];
            }
            grid[x][y] = 'X';
            gx = x;
            gy = y;
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 'X')
                    res++;
            }
        }
        System.out.println(res);
        sc.close();
    }
}

