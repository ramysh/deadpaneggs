package recursion;

/**
 * @author rpurigella
 */
public class DoublePower {
    public static void main(String[] args) {
        System.out.println("Math: " + Math.pow(45.6, -4));
        System.out.println("Mine: " + pow(45.6f, -4));
    }

    static float pow(float dblbase, int ipower) {
        if (ipower == 0) return 1;
        float t = pow(dblbase, ipower/2);
        if (ipower % 2 == 0) {
            if (ipower > 0) return t * t;
            else return (t/dblbase);
        } else {
            if (ipower > 0) return t * t * dblbase;
            else return (t/dblbase)/dblbase;
        }
    }
}

