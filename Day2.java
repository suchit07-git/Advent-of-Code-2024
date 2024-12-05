import java.util.*;
import java.io.*;

public class Day2 {

    private static boolean is_increasing(ArrayList<Integer> A) {
        for (int i = 0; i < A.size() - 1; i++)
            if (A.get(i) > A.get(i + 1))
                return false;
        return true;
    }

    private static boolean is_decreasing(ArrayList<Integer> A) {
        for (int i = 0; i < A.size() - 1; i++)
            if (A.get(i) < A.get(i + 1))
                return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./day2_input.txt"));
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        int res = 0;
        while (sc.hasNextLine()) {
            String[] s = sc.nextLine().split(" ");
            ArrayList<Integer> tmp = new ArrayList<>();
            for (String _s : s)
                tmp.add(Integer.valueOf(_s));
            A.add(tmp);
        }
        for (int i = 0; i < A.size(); i++) {
            boolean ok = false;
            int c = 0;
            if (is_increasing(A.get(i))) {
                for (int j = 0; j < A.get(i).size() - 1; j++) {
                    if (A.get(i).get(j + 1) - A.get(i).get(j) >= 1 && A.get(i).get(j + 1) - A.get(i).get(j) <= 3)
                        c++;
                }
                if (c == A.get(i).size() - 1)
                    ok = true;
            } else if (is_decreasing(A.get(i))) {
                for (int j = 0; j < A.get(i).size() - 1; j++) {
                    if (A.get(i).get(j) - A.get(i).get(j + 1) >= 1 && A.get(i).get(j) - A.get(i).get(j + 1) <= 3)
                        c++;
                }
                if (c == A.get(i).size() - 1)
                    ok = true;
            }
            if (ok)
                res++;
        }
        System.out.println(res);
        sc.close();
    }
}
