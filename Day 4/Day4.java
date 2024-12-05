import java.util.*;
import java.io.*;

public class Day4 {

    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day4_input.txt"));
        ArrayList<String> A = new ArrayList<>();
        while (sc.hasNextLine())
            A.add(sc.nextLine());
        int N = A.size(), M = A.get(0).length();
        char[][] input = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) { 
                input[i][j] = A.get(i).charAt(j);
            }
        }
        int[][] directions = {
            { -1, -1 }, 
            { -1, 0 },
            { -1, 1 },
            { 0, 1 },
            { 1, 1 },
            { 1, 0 },
            { 1, -1 },
            { 0, -1 } 
        };
        char[] xmas = { 'X', 'M', 'A', 'S' }; 
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (input[i][j] != 'X')
                    continue;
                for (int[] direction : directions) {
                    int x = i, y = j;
                    for (int k = 1; k < 4; k++) {
                        x += direction[0];
                        y += direction[1];
                        if (x >= 0 && x < N && y >= 0 && y < M && input[x][y] == xmas[k]) {
                            if (k == 3)
                                res++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}
