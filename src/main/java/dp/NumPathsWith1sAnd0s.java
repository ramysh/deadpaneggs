package dp;

/**
¡

 * @author rpurigella
 */
public class NumPathsWith1sAnd0s {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 1},
        };
        System.out.println(numberOfPathsRecursive(grid));
        System.out.println(numberOfPaths(grid));
    }

    static int numberOfPathsRecursive(int[][] a) {
        return paths(a, 0, 0);
    }

    static int paths(int[][] a , int i, int j) {
        int n = a.length;
        int m = a[0].length;
        if (i == n-1 && j == m-1) return 1;
        if (i > n-1 || j > m-1) return 0;
        if (a[i][j] == 0) return 0;
        return paths(a, i+1, j) + paths(a, i, j+1);
    }

    static int numberOfPaths(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int[][] table = new int[n+1][m+1];
        table[n-1][m-1] = 1;
        for (int i = n-1; i >=0; i--) {
            for (int j = m-1; j >=0; j--) {
                if (i == n-1 && j == m-1) continue;
                if (a[i][j] == 0) {
                    table[i][j] = 0;
                } else {
                    table[i][j] = table[i+1][j] + table[i][j+1];
                }
            }
        }
        return table[0][0];
    }
}

