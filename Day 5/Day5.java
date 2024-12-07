import java.util.*;
import java.io.*;

public class Day5 {

    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day5_input1.txt"));
        int N = 100;
        boolean[][] g = new boolean[N + 1][N + 1];
        while (sc.hasNextLine()) {
            String[] tmp = sc.nextLine().split("\\|");
            int u = Integer.parseInt(tmp[0]), v = Integer.parseInt(tmp[1]);
            g[u][v] = true;
        }
        sc = new Scanner(new File("./day5_input2.txt"));
        int res = 0;
        while (sc.hasNextLine()) {
            String[] tmp = sc.nextLine().split(",");
            ArrayList<Integer> updates = new ArrayList<>();
            for (String s : tmp)
                updates.add(Integer.parseInt(s));
            int c = 0;
            for (int i = 0; i < updates.size(); i++) {
                for (int j = i + 1; j < updates.size(); j++) {
                    int u = updates.get(i), v = updates.get(j);
                    if ((g[u][v] || !g[u][v]) && !g[v][u])
                        c++;
                }
            }
            int sz = updates.size();
            if (c == (sz * (sz - 1)) / 2)
                res += updates.get(updates.size() / 2);
        }
        System.out.println(res);
        sc.close();
    }
}
