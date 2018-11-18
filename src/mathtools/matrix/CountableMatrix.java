package mathtools.matrix;

import mathtools.Vector;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * By Hrd at 16-10-30 下午12:17.
 */
public class CountableMatrix <T extends CountableMatrixElement<T>> implements CountableMatrixElement<CountableMatrix<T>> {
    
    protected T zeroElement;
    protected T identityElement;
    protected ArrayList<ArrayList<T>> matrixArray;
    protected int rows;
    protected int columns;
    
    public CountableMatrix(int rows,int columns,T initialElement) {
        this(rows,columns,(i,j) -> initialElement);
    }
    public CountableMatrix(int rows, int columns,T[][] initialElement) {
        this(rows,columns,(i,j) -> (
                i>=initialElement.length || j>=initialElement[i].length ? null : initialElement[i][j]
        ));
    }
    public CountableMatrix(int rows,int columns,BiFunction<Integer, Integer, T> operator) {    //要求第一位元素非null，以获取单位元
        if(rows<1 || columns<1 || operator == null || operator.apply(0,0) == null) throw new IllegalArgumentException();
        zeroElement = operator.apply(0,0).getZeroElement();
        identityElement = operator.apply(0,0).getIdentityElement();
        this.rows = rows;
        this.columns = columns;
        matrixArray = new ArrayList<>(rows);
        for(int i = 0 ; i<rows ; i++){
            ArrayList<T> array = new ArrayList<>(columns);
            for(int j = 0 ; j<columns ; j++){
                array.add(operator.apply(i,j) == null ? zeroElement : operator.apply(i,j).copy());
            }
            matrixArray.add(array);
        }
    }
    
    public static void print(CountableMatrix<IntegerElement> m) {
        for(int i = 0 ; i<m.getRows() ; i++){
            for(int j = 0 ; j<m.getColumns() ; j++){
                System.out.print(m.get(i,j) + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public CountableMatrix<T> getZeroElement() {
        return new CountableMatrix<>(getColumns(),getColumns(),zeroElement);
    }
    public CountableMatrix<T> getIdentityElement() {
        return CountableSquareMatrix.getIdentityMatrix(getColumns(),identityElement);
    }
    public CountableMatrix<T> getZeroMatrix() {
        return new CountableMatrix<>(getRows(),getColumns(),zeroElement);
    }
    public boolean equals(Object o) {
        if(!(o instanceof CountableMatrix)) return false;
        CountableMatrix matrix = ( CountableMatrix ) o;
        return getRows() == matrix.getRows() && getColumns() == matrix.getColumns() && allMatch(MatrixElement::equals,matrix);
    }
    public CountableMatrix<T> copy() {
        return new CountableMatrix<T>(rows,columns,this::getElement);
    }
    public CountableMatrix<T> getValue() {
        return this;
    }
    public void setValue(CountableMatrix<T> value) {    //引用相同的行向量
        zeroElement = value.zeroElement;
        identityElement = value.identityElement;
        matrixArray = new ArrayList<>(value.matrixArray);
        rows = value.rows;
        columns = value.columns;
    }
    protected T getElement(int i,int j) {
        return matrixArray.get(i).get(j);
    }
    public Object get(int i,int j) {
        return getElement(i,j).getValue();
    }
    public void set(int i,int j,T value) {
        getElement(i,j).setValue(value);
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public Vector<T> getColumnVectors(int index) {
        if(index<0 || index>=columns) throw new IllegalArgumentException();
        return new Vector<T>(rows,i -> getElement(i,index));
    }
    public Vector<T> getRowVectors(int index) {
        if(index<0 || index>=rows) throw new IllegalArgumentException();
        return new Vector<T>(columns,i -> getElement(index,i));
    }
    public CountableMatrix<T> add(CountableMatrix<T> matrix) {
        return operateWith(matrix,T::add);
    }
    public CountableMatrix<T> subtract(CountableMatrix<T> matrix) {
        return operateWith(matrix,T::subtract);
    }
    public CountableMatrix<T> multiply(CountableMatrix<T> matrix) {
        if(this.getColumns() != matrix.getRows())
            throw new IllegalArgumentException("The lines must equals columns of first matrixArray!");
        return new CountableMatrix<T>(rows,matrix.columns,(i,j) -> this.getRowVectors(i).scalarProduct(matrix.getColumnVectors(j)));
    }
    public CountableMatrix<T> operateWith(CountableMatrix<T> matrix,BinaryOperator<T> operator) {
        if(getRows() != matrix.getRows() || getColumns() != matrix.getColumns())
            throw new IllegalArgumentException("Should have the same size!");
        return new CountableMatrix<T>(rows,columns,(i,j) -> operator.apply(this.getElement(i,j),matrix.getElement(i,j)));
    }
    
    public boolean allMatch(Predicate<T> predicate) {
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                if(!predicate.test(getElement(i,j))) return false;
            }
        }
        return true;
    }
    public boolean anyMatch(Predicate<T> predicate) {
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                if(predicate.test(getElement(i,j))) return true;
            }
        }
        return false;
    }
    public boolean noneMatch(Predicate<T> predicate) {
        return !anyMatch(predicate);
    }
    public boolean allMatch(BiPredicate<T,T> predicate,CountableMatrix<T> matrix) {
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                if(!predicate.test(this.getElement(i,j),matrix.getElement(i,j))) return false;
            }
        }
        return true;
    }
    public boolean anyMatch(BiPredicate<T,T> predicate,CountableMatrix<T> matrix) {
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                if(predicate.test(this.getElement(i,j),matrix.getElement(i,j))) return true;
            }
        }
        return false;
    }
    public boolean noneMatch(BiPredicate<T,T> predicate,CountableMatrix<T> matrix) {
        return !anyMatch(predicate,matrix);
    }
    
    public CountableSquareMatrix<T> toSquareMatrix(){
        if(columns!=rows) throw new IllegalArgumentException();
        return new CountableSquareMatrix<T>(columns,this::getElement);
    }
    public CountableMatrix<T> toTranspose() {
        return new CountableMatrix<T>(columns,rows,(i,j) -> getElement(j,i));
    }
    public boolean isSquare() {
        return getRows() == getColumns();
    }
    public boolean isVectors() {
        return getRows() == 1 || getColumns() == 1;
    }
}
