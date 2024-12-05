import java.util.*;
import java.io.*;

public class Day4Part2 {

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
        int res = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (i + 1 < N && j + 1 < M && input[i + 1][j + 1] != 'A')
                    continue;
                boolean ok = true;
                char[] tmp = { input[i][j], input[i][j + 2], input[i + 2][j + 2], input[i + 2][j] }; 
                if (tmp[0] == tmp[1] && tmp[2] == tmp[3] && tmp[0] == (tmp[2] ^ 'M' ^ 'S'))
                    res++;
                else if (tmp[0] == tmp[3] && tmp[1] == tmp[2] && tmp[0] == (tmp[1] ^ 'M' ^ 'S'))
                    res++;
            }
        }
        System.out.println(res);
        sc.close();
    }
}
