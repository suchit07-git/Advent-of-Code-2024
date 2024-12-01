import java.util.*;
import java.io.*;

public class Day1Part2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day1_input.txt"));
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] s = sc.nextLine().split(" ");
            A.add(Integer.valueOf(s[0].trim()));
            B.add(Integer.valueOf(s[3].trim()));
        }
        long res = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < B.size(); i++) {
            cnt.put(B.get(i), cnt.getOrDefault(B.get(i), 0) + 1);
        }
        for (int i = 0; i < A.size(); i++) {
            res = res + A.get(i) * 1L * cnt.getOrDefault(A.get(i), 0);
        }
        System.out.println(res);
        sc.close();
    }
}
