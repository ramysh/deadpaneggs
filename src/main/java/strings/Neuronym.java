package strings;

/**
 * Created by rpurigella on 11/11/18.
 */
public class Neuronym {

    static void neuronyms(String s) {
        int x = s.length() - 2;
        while(x > 1) {
            print(s, x);
            x--;
        }
    }

    static void print(String s, int x) {
        for (int i = 1; i+x < s.length(); i++) {
            System.out.print(s.substring(0,i));
            System.out.print(x);
            System.out.println(s.substring(i+x));
        }
    }

    public static void main(String[] args) {
        neuronyms("nailed");
        neuronyms("batch");
        neuronyms("ab");
        neuronyms("abc");
    }
}
