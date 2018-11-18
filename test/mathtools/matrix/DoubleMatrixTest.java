package mathtools.matrix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 16-11-13.
 */
public class DoubleMatrixTest {
    DoubleMatrix matrix ;
    DoubleMatrix copy ;
    @Before
    public void setUp() throws Exception {
        matrix = new DoubleMatrix(3,4,new double[][]{
                {1,2,-1,2},
                {3,1, 2,1},
                {2,1, 0,1}});
        copy = matrix.copy();
    }
    @Test
    public void equals() throws Exception {
        Assert.assertTrue(matrix.equals(copy));
        copy.set(0,1,0);
        Assert.assertTrue(!matrix.equals(copy));
    }
    @Test
    public void add() throws Exception {
        copy.set(0,1,0);
        matrix = matrix.add(copy);
        Assert.assertTrue(matrix.equals(new DoubleMatrix(3,4,new double[][]{
                {2,2,-2,4},
                {6,2, 4,2},
                {4,2, 0,2}})));
    }
    @Test
    public void subtract() throws Exception {
        copy.set(0,1,0);
        Assert.assertTrue(matrix.subtract(copy).equals(new DoubleMatrix(3,4,new double[][]{
                {0,2,0,0},
                {0,0,0,0},
                {0,0,0,0}})));
    }
    @Test
    public void multiply() throws Exception {
        copy = new DoubleMatrix(4,2,new double[][]{
                {1,2},
                {6,3},
                {0,-5},
                {1}});
        Assert.assertTrue(matrix.multiply(copy).equals(new DoubleMatrix(3,2,new double[][]{
                {15,13},
                {10,-1},
                { 9, 7}})));
    }
    @Test
    public void determinant() throws Exception {
        Assert.assertTrue(new DoubleMatrix(new double[][]{
                {1,3,2},
                {0,-1,1},
                {2,3,1}}).determinant() == 6);
    }
}