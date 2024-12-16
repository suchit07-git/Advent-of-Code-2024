import java.util.*;
import java.io.*;

public class Day11 {

    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day11_input.txt"));
        String[] S = sc.nextLine().split(" ");
        ArrayList<Long> stones = new ArrayList<>();
        for (String s : S)
            stones.add(Long.parseLong(s));
        for (int b = 0; b < 25; b++) { 
            for (int i = 0; i < stones.size(); i++) {
                if (stones.get(i) == 0) {
                    stones.set(i, 1L);
                } else if (Long.toString(stones.get(i)).length() % 2 == 0) {
                    String stone = Long.toString(stones.get(i));
                    long s1 = Long.parseLong(stone.substring(0, stone.length() / 2));
                    long s2 = Long.parseLong(stone.substring(stone.length() / 2, stone.length()));
                    stones.remove(i);
                    stones.add(i, s2);
                    stones.add(i, s1);
                    i++;
                } else {
                    stones.set(i, stones.get(i) * 2024);
                }
            }
        }
        System.out.println(stones.size());
        sc.close();
    }
}

