package mathtools.numberfield;

import mathtools.Helper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 16-11-20.
 */
public class DivideTest {
    Divide rational = new Divide(3,4);
    @Test
    public void getGreatestCommonFactor() throws Exception {
        Assert.assertTrue(Helper.getGreatestCommonFactor(-3,2)==1);
        Assert.assertTrue(Helper.getGreatestCommonFactor(-4,2)==2);
        Assert.assertTrue(Helper.getGreatestCommonFactor(4,2)==2);
        Assert.assertTrue(Helper.getGreatestCommonFactor(10,24)==2);
        Assert.assertTrue(Helper.getGreatestCommonFactor(0,24)==24);
        Assert.assertTrue(Helper.getGreatestCommonFactor(-3,0)==3);
        Assert.assertTrue(Helper.getGreatestCommonFactor(-19,-31)==1);
    }
    @Test
    public void equals() throws Exception {
        Assert.assertTrue(rational.equals(new Divide(12,16)));
        Assert.assertTrue(!rational.equals(new Divide(12,15)));
    }
    @Test
    public void add() throws Exception {
        Assert.assertTrue(rational.add(new Divide(4,3)).equals(new Divide(25,12)));
    }
    @Test
    public void subtract() throws Exception {
        Assert.assertTrue(rational.subtract(new Divide(4,3)).equals(new Divide(-7,12)));
    }
    @Test
    public void multiply() throws Exception {
        Assert.assertTrue(rational.multiply(new Divide(4,3)).equals(new Divide(12,12)));
    }
    @Test
    public void divide() throws Exception {
        Assert.assertTrue(rational.divide(new Divide(4,3)).equals(new Divide(9,16)));
    }
}