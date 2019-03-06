package graphs.problems;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by rpurigella on 10/16/18.
 */
public class ZombieCluster {

    static int zombieCluster(String[] zombies) {
        int n = zombies.length;
        int clusters = 0;
        Set<Integer> seen = new HashSet<>();
        for (int z = 0; z < n; z++) {
            if (!seen.contains(z)) {
                clusters++;
                explore(z, seen, zombies);
            }
        }
        return clusters;
    }

    static void explore(int curr, Set<Integer> seen, String[] zombies) {
        seen.add(curr);
        for (int i = 0; i < zombies.length; i ++) {
            if (curr == i) continue;
            if (zombies[curr].charAt(i) == '1' && !seen.contains(i)) {
                explore(i, seen, zombies);
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int zombiesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] zombies = new String[zombiesCount];

        for (int i = 0; i < zombiesCount; i++) {
            String zombiesItem = scanner.nextLine();
            zombies[i] = zombiesItem;
        }

        int res = zombieCluster(zombies);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
