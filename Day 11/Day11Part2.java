import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Day11Part2 {

    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    private static HashMap<String, BigInteger> memo;

    private static BigInteger solve(String num, int count) {
        String key = num + ":" + count;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (count == 0)
            return BigInteger.ONE;
        BigInteger c = BigInteger.ZERO;
        if (num.equals("0")) {
            c = solve("1", count - 1);
        } else if (num.length() % 2 == 0) {
            int mid = num.length() / 2;
            String n1 = Long.toString(Long.parseLong(num.substring(0, mid)));
            String n2 = Long.toString(Long.parseLong(num.substring(mid, num.length())));
            c = solve(n1, count - 1).add(solve(n2, count - 1));
        } else {
            long n = Long.parseLong(num);
            c = solve(String.valueOf(n * 2024L), count - 1);
        }
        memo.put(key, c);
        return c;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day11_input.txt"));
        String[] stones = sc.nextLine().split(" ");
        BigInteger res = BigInteger.ZERO;
        int count = new Scanner(System.in).nextInt();
        for (int i = 0; i < stones.length; i++) {
            memo = new HashMap<>();
            res = res.add(solve(stones[i], count));
        }
        System.out.println(res);
        sc.close();
    }
}

