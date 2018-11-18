package mathtools;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by CowardlyLion on 2018/11/17 15:55
 */
public class LatinSquareTest {

    @Test
    public void randomMove() {
        LatinSquare ls = new LatinSquare(256);
        ls.print();
        for (int i = 0; i < 2098000; i++) {
            ls.randomMove();
//            ls.print();
        }
        ls.print();
        System.out.println(ls.getCount());
    }
}