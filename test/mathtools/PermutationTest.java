package mathtools;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by CowardlyLion on 2017/10/2 16:28
 */
public class PermutationTest {
    @Test
    public void combination() throws Exception {
        Permutation.permutation(2, 3, (integer, ints) -> true);
        for (int i = 0; i < 10; i++) {
            testPermutationTime(i);
        }
    }

    private void testPermutationTime(int range) {
        long time = System.currentTimeMillis();
        ArrayList<int[]> permutation = Permutation.permutation(range,range, (integer, ints) -> true);
        System.out.println("Power at "+range+" time:"+(System.currentTimeMillis()-time)+"ms");
    }

}