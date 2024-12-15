import java.util.*;
import java.io.*;
import java.util.function.Predicate;

public class Day9 {
    
    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day9_input.txt"));
        char[] diskMap = sc.nextLine().toCharArray();
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> compactedForm = new ArrayList<>();
        for (int i = 0; i < diskMap.length; i++) {
            for (int j = 0; j < (diskMap[i] - '0'); j++) {
                if (i % 2 == 0)
                    compactedForm.add(Integer.toString(idx));
                else
                    compactedForm.add(".");
            }
            if (i % 2 == 1)
                idx++;
        }
        Predicate<String> isInteger = (str) -> {
            return str != null && str.matches("-?\\d+");    
        };
        int p = compactedForm.size() - 1;
        for (int i = 0; i < compactedForm.size(); i++) {
            if (i == p)
                break;
            if (isInteger.test(compactedForm.get(i)))
                continue;
            while (!isInteger.test(compactedForm.get(p)))
                p--;
            compactedForm.set(i, compactedForm.get(p));
            compactedForm.set(p, ".");
            p--;
        }
        long checksum = 0;
        for (int i = 0; i < compactedForm.size(); i++) {
            if (isInteger.test(compactedForm.get(i))) {
                checksum += i * 1L * Integer.valueOf(compactedForm.get(i));
            }
        }
        System.out.println(checksum);
        sc.close();
    }
}
