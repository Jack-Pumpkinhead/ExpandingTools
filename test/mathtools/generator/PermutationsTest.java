package mathtools.generator;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by CowardlyLion on 2017/10/1 11:20
 */
public class PermutationsTest {
    IntStream a = IntStream.range(0, 6);
    IntStream b = IntStream.range(0, 10);

    @Test
    public void biCartesianProduct() throws Exception {
        print(Permutations.biCartesianProduct(3, 2));
        print(Permutations.biCartesianProduct(Permutations.biCartesianProduct(1, 3), 1));
        print(Permutations.biCartesianProduct(Permutations.biCartesianProduct(2, 3), Permutations.biCartesianProduct(1, 2)));
        print(Permutations.biCartesianProduct(2, Permutations.initialStream(3)));
        print(Permutations.biCartesianProduct(Permutations.initialStream(3), Stream.of(new int[0])));

    }

    private static void print(Stream<int[]> intStream) {
        intStream.forEachOrdered(i -> {
            System.out.println(Arrays.toString(i));
        });
    }

    @Test
    public void cartesianPower() throws Exception {
        Permutations.cartesianPower(2, 3).forEachOrdered(i -> System.out.println(Arrays.toString(i)));
        for (int i = 0; i < 10; i++) {
            testPowerTime(i);
        }

    }

    private void testPowerTime(int range) {
        long time = System.currentTimeMillis();
        System.out.println(Permutations.cartesianPower(range, range).count());
        System.out.println("Cartesian Power of " + range + " time:" + (System.currentTimeMillis() - time) + "ms");
    }

    @Test
    public void permutation() throws Exception {
        long time = System.currentTimeMillis();
        System.out.println(Permutations.permutation(8).count());
        System.out.println("Total time:"+(System.currentTimeMillis()-time)+"ms");
    }
}