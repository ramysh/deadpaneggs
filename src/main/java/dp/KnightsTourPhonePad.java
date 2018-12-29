package dp;

/**
 * @author rpurigella
 */
public class KnightsTourPhonePad {

    public static void main(String[] args) {
        System.out.println(numPhoneNumbersRecursive(1, 3));
        System.out.println(numPhoneNumbersDP(1, 3));
    }

    static int[][] a = {{4,6}, {8,6}, {7,9}, {4,8}, {3, 9, 0}, {}, {7, 1, 0}, {2, 6}, {1, 3}, {4, 2}};

    static long numPhoneNumbersRecursive(int startdigit, int phonenumberlength) {
        if (phonenumberlength == 1) return 1;
        long val = 0;
        for (int i : a[startdigit]) {
            val = val + numPhoneNumbersRecursive(i, phonenumberlength - 1);
        }
        return val;
    }

    static long numPhoneNumbersDP(int startdigit, int phonenumberlength) {
        long[][] table = new long[10][phonenumberlength];
        for (int i = 0; i < 10 ; i++) {
            table[i][0] = 1;
        }

        for (int j = 1; j < phonenumberlength; j++){
            for (int i = 0; i < 10; i++) {
                long val = 0;
                for (int num : a[i]) {
                    val += table[num][j-1];
                }
                table[i][j] = val;
            }
        }
        return table[startdigit][phonenumberlength-1];
    }

}

