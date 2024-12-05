import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Day3 {

    private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    static void trace(Object... o) {
        if (debug) {
            System.err.println(Arrays.deepToString(o));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day3_input.txt"));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
        }
        String text = sb.toString();
        String regex = "\\bmul\\((\\d+),\\s*(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        long res = 0;
        while (matcher.find()) {
            long n1 = Long.valueOf(matcher.group(1));
            long n2 = Long.valueOf(matcher.group(2));
            res += n1 * n2;
        }
        System.out.println(res);
        sc.close();
    }
}


