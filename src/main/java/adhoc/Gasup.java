package adhoc;

import java.util.*;

/**
 * @author rpurigella
 */
public class Gasup {

    private static final int MPG = 20;

    public int findAmpleCity(List<Integer> gallons,
                                    List<Integer> distances) {
        int remainingGallons = 0;
        CityAndRemainingGas min = new CityAndRemainingGas(0, 0);
        final int numCities = gallons.size();
        for (int i = 1; i < numCities; ++i) {
            remainingGallons += gallons.get(i - 1) - distances.get(i - 1) / MPG;
            if (remainingGallons < min.remainingGallons) {
                min = new CityAndRemainingGas(i, remainingGallons);
            }
        }
        return min.city;
    }

    private class CityAndRemainingGas {
        public Integer city;
        public Integer remainingGallons;

        public CityAndRemainingGas(Integer city, Integer remainingGallons) {
            this.city = city;
            this.remainingGallons = remainingGallons;
        }
    }

    public static void main(String[] args) {
        List<Integer> gallons = Arrays.asList(50, 20, 5, 30, 25, 10, 10);
        List<Integer> distances = Arrays.asList(900, 600, 200, 400, 600, 200, 100);
        Gasup gasup = new Gasup();
        System.out.println(gasup.findAmpleCity(gallons, distances));
    }
}

