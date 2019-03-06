package strings;

/**
 * Created by rpurigella on 10/28/18.
 */
public class ReverseWords {

    public static void main(String[] args) {
        System.out.println(reverse_ordering_of_words("hello  world . "));
    }

    static String reverse_ordering_of_words(String s) {
        /*
         * Write your code here.
         */
        char[] c = s.toCharArray();
        reverse(c, 0, c.length-1);
        rw(c);
        return new String(c);
    }

    static void reverse(char[] c, int i, int j) {
        while (i <= j) {
            char tmp = c[i];
            c[i] = c[j];
            c[j] = tmp;
            i++;
            j--;
        }
    }

    static void rw(char[] c) {
        int i = 0;
        int j = 0;
        while (i < c.length) {
            while (i < c.length && c[i] == ' ') {
                i++;
            }
            j = i;
            while (j < c.length && c[j] != ' ') {
                j++;
            }
            reverse(c, i, j-1);
            i = j;
        }
    }

}
