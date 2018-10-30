package strings;

/**
 * Created by rpurigella on 10/29/18.
 */
public class MoveLettersToLeft {

    public static void main(String[] args) {
        System.out.println(move("9874123"));
    }

    static String move(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int i = 0;
        for (int j = 0; j < n; j++) {
            if (!Character.isDigit(c[j])) {
                if (i != j) {
                    c[i] = c[j];
                }
                i++;
            }
        }

       return new String(c);
    }
}
