import java.util.*;
import java.io.*;
import java.util.stream.*;
import java.util.function.*;
import static java.lang.Math.max;

public class Day8 {
    
    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day8_input.txt"));
        ArrayList<String> tmp = new ArrayList<>();
        while (sc.hasNextLine()) {
            tmp.add(sc.nextLine());
        }
        int N = tmp.size(), M = tmp.get(0).length();
        char[][] grid = new char[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                grid[i][j] = tmp.get(i).charAt(j);
        BiPredicate<Integer, Integer> checkOutOfBounds = (x, y) -> {
            return x >= 0 && x < N && y >= 0 && y < M;
        };
        Map<String, Boolean> used = new HashMap<>();
        int[][] cnt = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    for (int p = 0; p < M; p++) {
                        if ((i == k && j == p) || grid[i][j] != grid[k][p] || !Character.isLetterOrDigit(grid[i][j]))
                            continue;
                        String s1 = i + "" + j + "" + k + "" + p;
                        String s2 = k + "" + p + "" + i + "" + j;
                        if (used.containsKey(s1) || used.containsKey(s2))
                            continue;
                        used.put(s1, true);
                        used.put(s2, true);
                        int dx = k - i, dy = p - j;
                        int ndx = i - k, ndy = j - p;
                        int X1 = i + ndx, Y1 = j + ndy, X2 = k + dx, Y2 = p + dy;
                        if (checkOutOfBounds.test(X1, Y1)) {
                            cnt[X1][Y1] = max(cnt[X1][Y1], 1);
                        }
                        if (checkOutOfBounds.test(X2, Y2)) {
                            cnt[X2][Y2] = max(cnt[X2][Y2], 1);
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res += cnt[i][j];
            }
        }
        System.out.println(res);
        sc.close();
    }
}
