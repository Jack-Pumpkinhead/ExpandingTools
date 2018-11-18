package mathtools.matrix;

import mathtools.Generater;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 16-11-13.
 */
public class CountableSquareMatrixTest {
    CountableSquareMatrix<IntegerElement> matrix;
    CountableSquareMatrix<IntegerElement> copy;
    
    @Before
    public void setUp() throws Exception {
        matrix = Generater.toIntegerMatrix(new int[][]{
                {1,3,2},
                {0,-1,1},
                {2,3,1}
        }).toSquareMatrix();
        copy = matrix.copy();
    }
    @Test
    public void isSymmetric() throws Exception {
        Assert.assertTrue(!matrix.isSymmetric());
        Assert.assertTrue(Generater.toIntegerMatrix(new int[][]{
                {1,2,0},
                {2,4,1},
                {0,1,-1}
        }).toSquareMatrix().isSymmetric());
    }
    @Test
    public void isSkewSymmetric() throws Exception {
        Assert.assertTrue(!matrix.isSkewSymmetric());
        Assert.assertTrue(Generater.toIntegerMatrix(new int[][]{
                {1,2,0},
                {0,4,1},
                {-1,2,-1}
        }).toSquareMatrix().isSkewSymmetric());
    }
    @Test
    public void isDiagonalMatrix() throws Exception {
        Assert.assertTrue(!matrix.isDiagonalMatrix());
        Assert.assertTrue(Generater.toIntegerMatrix(new int[][]{
                {1,0,0},
                {0,0,0},
                {0,0,-1}
        }).toSquareMatrix().isDiagonalMatrix());
    }
    @Test
    public void toTranspose() throws Exception {
        Assert.assertTrue(Generater.toIntegerMatrix(new int[][]{
                {1,0,2},
                {3,-1,3},
                {2,1,1}
        }).toSquareMatrix().equals(matrix.toTranspose()));
    }
    @Test
    public void determinant() throws Exception {
        Assert.assertTrue(matrix.determinant().equals(new IntegerElement(6)));
    }
    @Test
    public void isSingularMatrix() throws Exception {
        Assert.assertTrue(!matrix.isSingularMatrix());
        Assert.assertTrue(Generater.toIntegerMatrix(new int[][]{
                {1,0,2},
                {3,0,3},
                {2,0,1}
        }).toSquareMatrix().isSingularMatrix());
    }
}