package strings;

/**
 * Created by rpurigella on 10/29/18.
 * Look and say sequence : 1, 11, 21, 1211, 111221, 312211, 13112221, ....
 * ~ O(n * (2 pow n))
 */

public class LookAndSay {

    public static void main(String[] s) {
        System.out.println(compute(10));
    }

    static String compute(int n) {
        String s = "1";
        int count = 0;
        while(count < n) {
            s = next(s);
            System.out.println(s);
            count++;
        }
        System.out.println();
        return s;
    }

    static String next(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int n = s.length();
        while (i < n) {
            char c = s.charAt(i);
            i++;
            int count = 1;
            while (i < n && s.charAt(i) == c) {
                i++;
                count++;
            }
            sb.append(count);
            sb.append(c);
        }
        return sb.toString();
    }
}
