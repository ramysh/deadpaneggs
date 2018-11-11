package strings;

import java.util.Arrays;

import static util.DPEUtil.pln;

/**
 * Created by rpurigella on 10/29/18.
 * Replace a with 2 d's and remove b's for up to given size and return new size and String back,
 */
public class ReplaceAndRemove {

    public static int rAndR(int x, char[] c) {
        int na = 0 , no = 0;
        for (int i = 0; i < x; i++) {
            if (c[i] == 'a') na++;
            else if (c[i] != 'b') no++;
        }
        int total = na * 2 + no;
        int n = total - 1;
        int j = n;

        while(j >= 0) {
            while (j >= 0 && c[j] == 'b') j--;
            if (c[j] == 'a') {
                c[n--] = 'd';
                c[n--] = 'd';
            } else if (c[j] != 'a') {
                c[n--] = c[j];
            }
            j--;
        }
        return total;
    }

    public static void main(String[] args) {
        String s = "bacdbbca";
        char[] c = s.toCharArray();
        pln(rAndR(5, c));
        pln(Arrays.toString(c));
    }
}