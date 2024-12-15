import java.util.*;
import java.io.*;
import java.util.function.Predicate;
import java.math.BigInteger;

public class Day9Part2 {

    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day9_input.txt"));
        char[] diskMap = sc.nextLine().toCharArray();
        Map<Integer, int[]> blocks = new HashMap<>();
        Map<Integer, int[]> freeSpace = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < diskMap.length; i++) {
            int d = diskMap[i] - '0';
            if (i % 2 == 0)
                blocks.put(i / 2, new int[]{idx, d});
            else
                freeSpace.put(i / 2, new int[]{idx, d});
            idx += diskMap[i] - '0';
        }
        for (int i = diskMap.length / 2; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                int size = blocks.get(i)[1], free = freeSpace.get(j)[1];
                if (free >= size) {
                    blocks.put(i, new int[]{freeSpace.get(j)[0], size}); 
                    freeSpace.put(j, new int[]{freeSpace.get(j)[0] + size, freeSpace.get(j)[1] - size}); 
                    break;
                }
            }
        }
        long checksum = 0;
        for (Map.Entry<Integer, int[]> e : blocks.entrySet()) {
            int[] v = e.getValue();
            for (int i = v[0]; i < v[0] + v[1]; i++)
                checksum += i * e.getKey();
        }
        System.out.println(checksum);
        sc.close();
    }
}
