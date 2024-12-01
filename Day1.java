import java.util.*;
import java.io.*;

public class Day1 {
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
        Collections.sort(A);
        Collections.sort(B);
        for (int i = 0; i < A.size(); i++) {
            res += Math.abs(A.get(i) - B.get(i));
        }
        System.out.println(res);
        sc.close();
    }
}
