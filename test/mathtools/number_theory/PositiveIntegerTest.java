package mathtools.number_theory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by CowardlyLion on 2017/11/19 18:51
 */
public class PositiveIntegerTest {

    @Test
    public void phi() throws Exception {
        for (int i = 1; i < 100; i++) {
            System.out.println(i + "\t" + PositiveInteger.phi(i));
        }
    }
}