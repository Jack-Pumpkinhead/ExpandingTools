package mathtools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 16-11-13.
 */
public class DoubleVectorTest {
    DoubleVector vector;
    DoubleVector copy;
    @Before
    public void setUp() throws Exception {
        vector = new DoubleVector(new double[]{1.5,2.6,-3.7});
        copy = vector.copy();
    }
    @Test
    public void equals() throws Exception {
        Assert.assertTrue(vector.equals(copy));
        Assert.assertTrue(!vector.equals(new DoubleVector(new double[]{1.5,2.6,-3.69})));
    }
    @Test
    public void getZeroElement() throws Exception {
        Assert.assertTrue(vector.getZeroElement().equals(new DoubleVector(new double[]{0,0,0})));
    }
    @Test
    public void getIdentityElement() throws Exception {
        Assert.assertTrue(vector.getIdentityElement().equals(new DoubleVector(new double[]{1,0,0})));
    }
    @Test
    public void sum() throws Exception {
        Assert.assertTrue(Helper.approximatelyEqual(vector.sum() ,0.4));
    }
    @Test
    public void add() throws Exception {
        Assert.assertTrue(vector.add(copy).equals(new DoubleVector(new double[]{3,5.2,-7.4})));
    }
    @Test
    public void reduce() throws Exception {
        Assert.assertTrue(vector.subtract(copy).equals(vector.getZeroElement()));
    }
    @Test
    public void multiply() throws Exception {
        Assert.assertTrue(vector.multiply(copy).equals(new DoubleVector(new double[]{2.25,6.76,13.69})));
    }
    @Test
    public void scalarProduct() throws Exception {
        Assert.assertTrue(Helper.approximatelyEqual(vector.scalarProduct(copy),22.7));
    }
    @Test
    public void modulus() throws Exception {
        Assert.assertTrue(Helper.approximatelyEqual(vector.modulus(), Math.sqrt(22.7)));
    }
    @Test
    public void isUnitVector() throws Exception {
        Assert.assertTrue(!vector.isUnitVector());
        Assert.assertTrue(new DoubleVector(new double[]{Math.cos(3),Math.sin(3)}).isUnitVector());
    }
    
}