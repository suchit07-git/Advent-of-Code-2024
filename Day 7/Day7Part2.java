import java.util.*;
import java.io.*;

public class Day7Part2 {
    
    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    private static long solve(long target, long s, long[] operands, int idx) {
        if (target == s && idx == operands.length) {
            return target;
        } else if (idx == operands.length || s > target) {
            return 0;
        } else {
            long res = solve(target, s + operands[idx], operands, idx + 1);
            if (res == target)
                return target;
            res = solve(target, s * operands[idx], operands, idx + 1);
            if (res == target)
                return target;
            res = solve(target, Long.parseLong(s + "" + operands[idx]), operands, idx + 1);
            return res;
        }
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day7_input.txt"));
        ArrayList<Long> res = new ArrayList<>();
        ArrayList<ArrayList<Long>> operands = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] s = line.split(":");
            res.add(Long.parseLong(s[0]));
            s = s[1].strip().split(" ");
            ArrayList<Long> tmp = new ArrayList<>();
            for (String _s : s)
                tmp.add(Long.parseLong(_s));
            operands.add(tmp);
        }
        long ans = 0;
        for (int i = 0; i < res.size(); i++) {
            long[] tmp = new long[operands.get(i).size()];
            for (int j = 0; j < operands.get(i).size(); j++)
                tmp[j] = operands.get(i).get(j);
            ans += solve(res.get(i), 0, tmp, 0);
        }
        System.out.println(ans);
        sc.close();
    }
}
