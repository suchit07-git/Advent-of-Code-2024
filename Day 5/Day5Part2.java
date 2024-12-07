import java.util.*;
import java.io.*;

public class Day5Part2 {

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
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++)
            adj[i] = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] tmp = sc.nextLine().split("\\|");
            int u = Integer.parseInt(tmp[0]), v = Integer.parseInt(tmp[1]);
            adj[u].add(v);
        }
        sc = new Scanner(new File("./day5_input2.txt"));
        int res = 0;
        while (sc.hasNextLine()) {
            String[] tmp = sc.nextLine().split(",");
            ArrayList<Integer> updates = new ArrayList<>();
            for (String s : tmp)
                updates.add(Integer.parseInt(s));
            List<Integer> orderedPages = updates.stream().sorted((o1, o2) -> {
                if (adj[o1].contains(o2)) {
                    return -1;
                } else if (adj[o2].contains(o1)) {
                    return 1;
                } else {
                    return 0;
                }
            }).toList();
            if (!updates.equals(orderedPages))
                res += orderedPages.get(orderedPages.size() / 2);
        }
        System.out.println(res);
        sc.close();
    }
}
