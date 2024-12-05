import java.util.*;
import java.io.*;

public class Day2Part2 {

    static final FastReader sc = new FastReader();
    static final PrintWriter out = new PrintWriter(System.out, true);

    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    private static boolean is_increasing(ArrayList<Integer> A) {
        for (int i = 0; i < A.size() - 1; i++)
            if (A.get(i) > A.get(i + 1))
                return false;
        return true;
    }

    private static boolean is_decreasing(ArrayList<Integer> A) {
        for (int i = 0; i < A.size() - 1; i++)
            if (A.get(i) < A.get(i + 1))
                return false;
        return true;
    }

    private static boolean check(ArrayList<Integer> A) {
        boolean ok = false;
        int c = 0;
        if (is_increasing(A)) {
            for (int j = 0; j < A.size() - 1; j++) {
                if (A.get(j + 1) - A.get(j) >= 1 && A.get(j + 1) - A.get(j) <= 3)
                    c++;
            }
        } else if (is_decreasing(A)) {
            for (int j = 0; j < A.size() - 1; j++) {
                if (A.get(j) - A.get(j + 1) >= 1 && A.get(j) - A.get(j + 1) <= 3)
                    c++;
            }
        }
        if (c == A.size() - 1)
            ok = true;
        return ok;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day2_input.txt"));
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] s = sc.nextLine().split(" ");
            ArrayList<Integer> tmp = new ArrayList<>();
            for (String _s : s)
                tmp.add(Integer.valueOf(_s));
            A.add(tmp);
        }
        int res = 0;
        for (int i = 0; i < A.size(); i++) {
            boolean ok = false;
            for (int j = 0; j < A.get(i).size(); j++) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int k = 0; k < A.get(i).size(); k++) {
                    if (k == j)
                        continue;
                    tmp.add(A.get(i).get(k));
                }
                if (check(tmp) || check(A.get(i)))
                    ok = true;
            }
            if (ok)
                res++;
        }
        System.out.println(res);
        sc.close();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}


