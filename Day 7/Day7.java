import java.util.*;
import java.io.*;

public class Day7 {
    
    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day7_input.txt"));
        ArrayList<Long> res = new ArrayList<>();
        ArrayList<ArrayList<Integer>> operands = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] s = line.split(":");
            res.add(Long.parseLong(s[0]));
            s = s[1].strip().split(" ");
            ArrayList<Integer> tmp = new ArrayList<>();
            for (String _s : s)
                tmp.add(Integer.parseInt(_s));
            operands.add(tmp);
        }
        int N = res.size();
        long ans = 0;
        for (int i = 0; i < N; i++) {
            int M = operands.get(i).size();
            boolean ok = false;
            for (int j = 0; j < (1 << (M - 1)); j++) {
                ArrayList<Character> ops = new ArrayList<>();
                for (int k = 0; k < M - 1; k++) {
                    if ((j & (1 << k)) == (1 << k))
                        ops.add('+');
                    else
                        ops.add('*');
                }
                long s = operands.get(i).get(0);
                for (int k = 1; k < operands.get(i).size(); k++) {
                    if (ops.get(k - 1) == '+')
                        s += operands.get(i).get(k);
                    else
                        s *= operands.get(i).get(k);
                }
                if (s == res.get(i)) {
                    ok = true;
                    break;
                }                    
            }
            if (ok)
                ans += res.get(i);
        }
        System.out.println(ans);
        sc.close();
    }
}
