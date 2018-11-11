package strings.boggle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by rpurigella on 11/8/18.
 */
public class BoggleNode {
    int i;
    int j;

    public BoggleNode(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoggleNode boggleNode = (BoggleNode) o;
        return i == boggleNode.i &&
                j == boggleNode.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    public static List<BoggleNode> neighbors(BoggleNode v, char[][] b) {
        List<BoggleNode> list = new ArrayList<>();
        int[] n1 = {1, -1, 0, 0, -1, 1, 1, -1};
        int[] n2 = {0, 0, 1, -1, 1, 1, -1, -1};
        int rows = b.length;
        int cols = b[0].length;
        for (int i = 0; i < n1.length; i++) {
            int r = v.i + n1[i];
            int c = v.j + n2[i];
            if (r >= 0 && c >= 0 && r < rows && c < cols) {
                list.add(new BoggleNode(r, c));
            }
        }
        return list;
    }
}