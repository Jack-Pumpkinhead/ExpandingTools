package mathtools;

import mathtools.matrix.DoubleElement;
import mathtools.matrix.IntegerElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 16-11-12.
 */
public class VectorTest {
    Vector<IntegerElement> vector;
    Vector<IntegerElement> copy;
    @Before
    public void setUp() throws Exception {
        vector = Generater.toIntegerVector(new int[]{2,3,1,4});
        copy = vector.copy();
    }
    @Test
    public void add() throws Exception {
        Assert.assertTrue(vector.add(copy).equals(Generater.toIntegerVector(new int[]{4,6,2,8})));
    }
    @Test
    public void reduce() throws Exception {
        Assert.assertTrue(vector.subtract(copy).equals(vector.getZeroElement()));
    }
    @Test
    public void multiply() throws Exception {
        Assert.assertTrue(vector.multiply(copy).equals(Generater.toIntegerVector(new int[]{4,9,1,16})));
    }
    @Test
    public void scalarProduct() throws Exception {
        Assert.assertTrue(vector.scalarProduct(copy).equals(new IntegerElement(30)));
    }
    @Test
    public void isUnitVector() throws Exception {
        Assert.assertTrue(!vector.isUnitVector());
        Assert.assertTrue(Generater.toIntegerVector(new int[]{0,1,0,0,0}).isUnitVector());
    }

    @Test
    public void speedTest() {
        Vector<DoubleElement> temp = Generater.toDoubleVector(new double[]{2.3, 2.6, 3.7, 4.5});
        Vector<DoubleElement> temp2 = Generater.toDoubleVector(new double[]{2.3, 2.6, 3.7, 4.5});
        for (int i = 0; i < 10_000_000; i++) {
            temp = temp.add(temp2);
        }
        for (int i = 0; i < 4; i++) {
            System.out.print(""+temp.get(i)+"\t");

        }
        System.out.println();
    }
}