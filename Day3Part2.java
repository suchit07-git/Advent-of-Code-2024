import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Day3Part2 {

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
        String controlRegex = "\\b(do\\(\\)|don't\\(\\))";
        Pattern pattern = Pattern.compile(regex);
        Pattern controlPattern = Pattern.compile(controlRegex);
        Matcher matcher = pattern.matcher(text);
        Matcher controlMatcher = controlPattern.matcher(text); 
        long res = 0;
        int idx = 0;
        boolean isEnabled = true;
        while (matcher.find()) {
            while (controlMatcher.find(idx) && controlMatcher.start() < matcher.start()) {
                String instruction = controlMatcher.group();
                isEnabled = instruction.equals("do()");
                idx = controlMatcher.end();
            }
            if (isEnabled) {
                long n1 = Long.valueOf(matcher.group(1));
                long n2 = Long.valueOf(matcher.group(2));
                res += n1 * n2;
            }
        }
        System.out.println(res);
        sc.close();
    }
}


