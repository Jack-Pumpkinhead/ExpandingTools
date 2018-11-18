package mathtools.generator;

/**
 * Created by CowardlyLion on 2017/10/28 16:59
 */
public class IntegerSequences {
    public static int[] difference(int[] a) {
        int[] d = new int[a.length];
        d[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            d[i] = a[i] - a[i - 1];
        }
        return d;
    }
}
