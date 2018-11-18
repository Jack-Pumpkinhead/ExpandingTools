package mathtools.optimization;

import mathtools.Constant;
import mathtools.numberfield.NumberElement;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * By Hrd at 17-1-12 下午8:10.
 */
public class SimpleMatrix {
    protected ArrayList<ArrayList<NumberElement>> matrixArray;
    protected int rows;
    protected int columns;
    public SimpleMatrix(int rows,int columns) {
        this(rows,columns,(i,j) -> Constant.ZERO);
    }
    public SimpleMatrix(int rows,int columns,NumberElement initialElement) {
        this(rows,columns,(i,j) -> initialElement);
    }
    public SimpleMatrix(int rows, int columns,NumberElement[][] initialElement) {
        this(rows,columns,(i,j) -> (
                i>=initialElement.length || j>=initialElement[i].length ? null : initialElement[i][j]
        ));
    }
    public SimpleMatrix(int rows,int columns,BiFunction<Integer, Integer, NumberElement> operator) {
        if(rows<1 || columns<1 || operator == null) throw new IllegalArgumentException();
        this.rows = rows;
        this.columns = columns;
        matrixArray = new ArrayList<>(rows);
        for(int i = 0 ; i<rows ; i++){
            ArrayList<NumberElement> array = new ArrayList<>(columns);
            for(int j = 0 ; j<columns ; j++){
                array.add(operator.apply(i,j) == null ? Constant.ZERO : operator.apply(i,j));
            }
            matrixArray.add(array);
        }
    }
    public String toString(){
        StringBuilder result = new StringBuilder("{"+getRowSimpleVectors(0));
        for(int i = 1; i<rows; i++){
            result.append("\n").append(getRowSimpleVectors(i));
        }
        return result.append("}").toString();
    }
    public NumberElement get(int i,int j) {
        return matrixArray.get(i).get(j);
    }
    public void set(int i,int j,Function<NumberElement,NumberElement> f) {
        matrixArray.get(i).set(j,f.apply(matrixArray.get(i).get(j)));
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public SimpleVector getColumnSimpleVectors(int index) {
        if(index<0 || index>=columns) throw new IllegalArgumentException();
        return new SimpleVector(rows,i -> get(i,index));
    }
    public SimpleVector getRowSimpleVectors(int index) {
        if(index<0 || index>=rows) throw new IllegalArgumentException();
        return new SimpleVector(columns,i -> get(index,i));
    }
    public SimpleMatrix add(SimpleMatrix matrix) {
        return operateWith(matrix,NumberElement::add);
    }
    public SimpleMatrix subtract(SimpleMatrix matrix) {
        return operateWith(matrix,NumberElement::subtract);
    }
    public SimpleMatrix multiply(SimpleMatrix matrix) {
        if(this.getColumns() != matrix.getRows())
            throw new IllegalArgumentException("NumberElementhe lines must equals columns of first matrixArray!");
        return new SimpleMatrix(rows,matrix.columns,(i,j) -> this.getRowSimpleVectors(i).scalarProduct(matrix.getColumnSimpleVectors(j)));
    }
    public SimpleMatrix simplify() {
        return operate(NumberElement::simplify);
    }
    public SimpleMatrix operate(Function<NumberElement,NumberElement> f){
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                NumberElement result = f.apply(get(i,j));
                matrixArray.get(i).set(j,result == null ? Constant.ZERO : result.simplify());
            }
        }
        return this;
    }
    public SimpleMatrix operateWith(SimpleMatrix matrix,BinaryOperator<NumberElement> operator) {
        if(getRows() != matrix.getRows() || getColumns() != matrix.getColumns())
            throw new IllegalArgumentException("Should have the same size!");
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                NumberElement result = operator.apply(get(i,j),matrix.get(i,j));
                matrixArray.get(i).set(j,result == null ? Constant.ZERO : result.simplify());
            }
        }
        return this;
    }
    public SimpleMatrix transpose() {
        return new SimpleMatrix(columns,rows,(i,j) -> get(j,i));
    }
}
