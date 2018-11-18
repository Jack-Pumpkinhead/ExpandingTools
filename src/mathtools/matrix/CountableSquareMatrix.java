package mathtools.matrix;

import mathtools.Permutation;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * By Hrd at 16-11-5 上午10:33.
 */
public class CountableSquareMatrix <T extends CountableMatrixElement<T>> extends CountableMatrix<T> {
    
    protected int order;
    
    public CountableSquareMatrix(int order,T initialElement) {
        this(order,(i,j) -> initialElement);
    }
    public CountableSquareMatrix(int order,T[][] initialElement) {
        this(order,(i,j) -> (
                i>=initialElement.length || j>=initialElement[i].length ? null : initialElement[i][j]
        ));
    }
    protected CountableSquareMatrix(int order,BiFunction<Integer, Integer, T> operator) {
        super(order,order,operator);
        this.order = order;
    }
    
    public static <T extends CountableMatrixElement<T>> CountableMatrix<T> getIdentityMatrix(int order,T example) {
        CountableSquareMatrix<T> result = new CountableSquareMatrix<>(order,example.getZeroElement());
        for(int i = 0 ; i<order ; i++){
            result.set(i,i,example.getIdentityElement());
        }
        return result;
    }
    
    public CountableSquareMatrix<T> copy() {
        return new CountableSquareMatrix<T>(getOrder(),this::getElement);
    }
    
    public boolean isSquareMatrix() {
        return true;
    }
    
    public int getOrder() {
        return order;
    }
    
    public boolean isSymmetric() {  //对称
        return nonDiagonalAllMatch(MatrixElement::equals);
        //return nonDiagonalAllMatch(T::equals);
    }
    public boolean isSkewSymmetric() {
        return nonDiagonalAllMatch((a,b) -> !a.equals(b));
    }
    public boolean isDiagonalMatrix() {
        return nonDiagonalAllMatch((a,b) -> a.equals(zeroElement) && b.equals(zeroElement));
    }
    
    public boolean nonDiagonalAllMatch(BiPredicate<T, T> predicate) {
        for(int i = 0 ; i<order ; i++){
            for(int j = i + 1 ; j<order ; j++){
                if(!predicate.test(getElement(i,j),getElement(j,i))) return false;
            }
        }
        return true;
    }
    
    public CountableSquareMatrix<T> toTranspose() {
        return new CountableSquareMatrix<T>(order,(i,j) -> getElement(j,i));
    }
    
    public T determinant() {    //行列式
        ArrayList<int[]> fullPermutation = Permutation.fullPermutation(order);
        T resultElement = zeroElement;
        for(int i1 = 0 ; i1<fullPermutation.size() ; i1++){
            int[] permutations = fullPermutation.get(i1);
            T temp = getElement(0,permutations[0]);
            for(int i = 1 ; i<order ; i++){
                temp = temp.multiply(getElement(i,permutations[i]));
            }
            resultElement = Permutation.isNaturalEven(i1) ? resultElement.add(temp) : resultElement.subtract(temp);
        }
        return resultElement;
    }
    
    public boolean isSingularMatrix() {
        return zeroElement.equals(determinant());
    }
    
    public boolean isFullRank() {
        return !isSingularMatrix();
    }
    
    public CountableMatrix<T> toMatrix(){
        return new CountableMatrix<T>(columns,columns,this::getElement);
    }
}
