package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author rpurigella
 */
public class NQueens {

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        find_all_arrangements(13);
    }

    static String[][] find_all_arrangements(int n) {
        List<List<String>> resultList = new ArrayList<>();
        solve(n, 0, new Stack<Pos>(), resultList);
        String[][] ret = new String[resultList.size()][];
        for (int i = 0; i < resultList.size(); i++) {
            List<String> row = resultList.get(i);
            ret[i] = row.toArray(new String[row.size()]);
        }
        return ret;
    }

    static void solve(int n, int col, Stack<Pos> stack, List<List<String>> list) {
        if (col == n) {
            list.add(getResult(stack, n));
            return;
        }
        for (int row = 0; row < n; row++) {
            Pos pos = new Pos(row, col);
            if (isSafeToPlace(pos, stack)) {
                stack.push(pos);
                solve(n, col+1, stack, list);
                stack.pop();
            }
        }
    }

    static List<String> getResult(Stack<Pos> stack, int n) {
        String[] result = new String[n];
        for (Pos pos : stack) {
            char[] tmp = new char[n];
            for (int i = 0; i < tmp.length; i++) {
                tmp[i] = '-';
            }
            tmp[pos.y] = 'q';
            result[pos.x] = new String(tmp);
        }
        return Arrays.asList(result);
    }

    static boolean isSafeToPlace(Pos pos, Stack<Pos> stack) {
        for (Pos inStack : stack) {
            if (pos.x == inStack.x) return false;
            if (diagonal(pos, inStack)) return false;
        }
        return true;
    }

    static boolean diagonal(Pos pos1, Pos pos2) {
        double y = Math.abs(pos2.y - pos1.y);
        double x = Math.abs(pos2.x - pos1.x);
        return (y/x == 1.0);
    }
}
