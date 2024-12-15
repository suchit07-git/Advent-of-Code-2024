import java.util.*;
import java.io.*;

public class Day10 {
    
    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    private static char[][] grid;
    private static boolean[][] visited;
    private static int[] cnt;
    private static int N, M;

    private static boolean check(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M && !visited[x][y];
    }

    private static void dfs(int v, int x, int y) {
        visited[x][y] = true;
        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { 1, 0, -1, 0 }; 
        cnt[v]++;
        for (int i = 0; i < 4; i++) {
            int _x = x + dx[i], _y = y + dy[i];
            if (check(_x, _y) && grid[_x][_y] - '0' == v + 1) {
                dfs(v + 1, _x, _y);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day10_input.txt"));
        ArrayList<String> tmp = new ArrayList<>();
        while (sc.hasNextLine()) {
            tmp.add(sc.nextLine());
        }
        N = tmp.size();
        M = tmp.get(0).length();
        grid = new char[N][];
        for (int i = 0; i < N; i++)
            grid[i] = tmp.get(i).toCharArray();
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '0') { 
                    visited = new boolean[N][M];
                    cnt = new int[10];
                    dfs(0, i, j);
                    res += cnt[9];
                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}
