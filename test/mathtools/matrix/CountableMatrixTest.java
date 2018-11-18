package mathtools.matrix;

import mathtools.Generater;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 16-11-12.
 */
public class CountableMatrixTest {
    CountableMatrix<IntegerElement> matrix;
    CountableMatrix<IntegerElement> copy;
    
    @Before
    public void setUp() throws Exception {
        matrix = Generater.toIntegerMatrix(new int[][]{
                {1,2,-1,2},
                {3,1, 2,1},
                {2,1, 0,1}});
        copy = matrix.copy();
    }
    @Test
    public void equals() throws Exception {
        Assert.assertTrue(matrix.equals(copy));
        copy.set(0,1,new IntegerElement(0));
        Assert.assertTrue(!matrix.equals(copy));
    }
    @Test
    public void add() throws Exception {
        copy.set(0,1,new IntegerElement(0));
        matrix = matrix.add(copy);
        Assert.assertTrue(matrix.equals(Generater.toIntegerMatrix(new int[][]{
                {2,2,-2,4},
                {6,2, 4,2},
                {4,2, 0,2}})));
    }
    @Test
    public void subtract() throws Exception {
        copy.set(0,1,new IntegerElement(0));
        Assert.assertTrue(matrix.subtract(copy).equals(Generater.toIntegerMatrix(new int[][]{
                {0,2,0,0},
                {0,0,0,0},
                {0,0,0,0}})));
    }
    @Test
    public void multiply() throws Exception {
        copy = Generater.toIntegerMatrix(new int[][]{
                {1,2},
                {6,3},
                {0,-5},
                {1}});
        Assert.assertTrue(matrix.multiply(copy).equals(Generater.toIntegerMatrix(new int[][]{
                {15,13},
                {10,-1},
                { 9, 7}})));
    }
    @Test
    public void toTranspose() throws Exception {
        Assert.assertTrue(matrix.toTranspose().equals(Generater.toIntegerMatrix(new int[][]{
                {1,3,2},
                {2,1,1},
                {-1,2,0},
                {2,1,1}})));
    }
    
}