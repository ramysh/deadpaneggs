package recursion;

/**
 * @author rpurigella
 */
public class TOH {
    public static void main(String[] args) {
        toh('A', 'B', 'C', 11);
    }

    static void toh(char from, char to, char temp, int n) {
        if (n == 1) {
            System.out.println("Moving disk " + n + ": " + from + " -> " + to);
            return;
        }
        toh(from, temp, to, n-1);
        System.out.println("Moving disk " + n + ": " + from + " -> " + to);
        toh(temp, to, from, n-1);
    }
}

