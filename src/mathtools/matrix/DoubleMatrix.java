package mathtools.matrix;

import mathtools.DoubleVector;
import mathtools.Helper;
import mathtools.Permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * By Hrd at 16-11-13 上午11:28.
 */
public class DoubleMatrix implements CountableMatrixElement<DoubleMatrix>{
    
    protected double[][] matrix;
    protected int rows;
    protected int columns;
    
    public DoubleMatrix(int rows,int columns) {
        this(rows,columns,0.0);
    }
    public DoubleMatrix(int rows,int columns,double initialElement) {
        this(rows,columns,(i,j) -> initialElement);
    }
    public DoubleMatrix(double[][] initialElement) {
        this(initialElement.length,Arrays.stream(initialElement).mapToInt(a->a.length).max().getAsInt(),initialElement);
    }
    public DoubleMatrix(int rows, int columns,double[][] initialMatrix) {
        this(rows,columns,(i,j) -> i<initialMatrix.length && j<initialMatrix[i].length ? initialMatrix[i][j] : null);
    }
    public DoubleMatrix(int rows,int columns,BiFunction<Integer, Integer, Double> operator) {
        if(rows<1 || columns<1 || operator == null) throw new IllegalArgumentException();
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
        setup(operator);
    }
    public DoubleMatrix setup(BiFunction<Integer, Integer, Double> operator) {
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                Double value = operator.apply(i,j);
                if(value != null) matrix[i][j] = value;
            }
        }
        return this;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof DoubleMatrix)) return false;
        return allMatch(Helper::approximatelyEqual,(DoubleMatrix) obj);
    }
    
    public void print() {
        System.out.println("---------------------"+getClass().getName()+"--------------------------");
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------");
    }
    
    public DoubleMatrix copy() {
        return new DoubleMatrix(rows,columns,matrix);
    }
    public DoubleMatrix getValue() {
        return this;
    }
    public double get(int row,int column) {
        return matrix[row][column];
    }
    public void setValue(DoubleMatrix value) {
        setup((a,b) -> a<value.rows && b<value.columns ? value.matrix[a][b] : this.matrix[a][b]);
    }
    public DoubleMatrix set(int row,int column,double value) {
        matrix[row][column] = value;
        return this;
    }
    public DoubleMatrix getZeroElement() {
        return new DoubleMatrix(columns,columns);
    }
    public DoubleMatrix getIdentityElement() {
        return new DoubleMatrix(columns,columns,(i,j) -> i == j ? 1.0 : 0.0);
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public DoubleVector getColumnVectors(int index) {
        if(index<0 || index>=columns) throw new IllegalArgumentException();
        return new DoubleVector(rows,i -> get(i,index));
    }
    public DoubleVector getRowVectors(int index) {
        if(index<0 || index>=rows) throw new IllegalArgumentException();
        return new DoubleVector(columns,i -> get(index,i));
    }
    @Override
    public DoubleMatrix add(DoubleMatrix anotherElement) {
        return operateWith(anotherElement,(i,j)->i+j);
    }
    @Override
    public DoubleMatrix subtract(DoubleMatrix anotherElement) {
        return operateWith(anotherElement,(i,j)->i-j);
    }
    @Override
    public DoubleMatrix multiply(DoubleMatrix anotherElement) {
        if(this.columns != anotherElement.rows) throw new IllegalArgumentException();
        return new DoubleMatrix(this.rows,anotherElement.columns,(i,j) -> this.getRowVectors(i).scalarProduct(anotherElement.getColumnVectors(j)));
    }
    
    public DoubleMatrix operateWith(DoubleMatrix doubleMatrix,BinaryOperator<Double> operator) {
        if(rows != doubleMatrix.rows || columns != doubleMatrix.columns) throw new IllegalArgumentException();
        return new DoubleMatrix(rows,columns,(i,j) -> operator.apply(this.matrix[i][j],doubleMatrix.matrix[i][j]));
    }
    
    public boolean allMatch(Predicate<Double> predicate) {
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                if(!predicate.test(matrix[i][j])) return false;
            }
        }
        return true;
    }
    public boolean anyMatch(Predicate<Double> predicate) {
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                if(predicate.test(matrix[i][j])) return true;
            }
        }
        return false;
    }
    public boolean noneMatch(Predicate<Double> predicate) {
        return !anyMatch(predicate);
    }
    public boolean allMatch(BiPredicate<Double,Double> predicate,DoubleMatrix doubleMatrix) {
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                if(!predicate.test(this.matrix[i][j],doubleMatrix.matrix[i][j])) return false;
            }
        }
        return true;
    }
    public boolean anyMatch(BiPredicate<Double,Double> predicate,DoubleMatrix doubleMatrix) {
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<columns ; j++){
                if(predicate.test(this.matrix[i][j],doubleMatrix.matrix[i][j])) return true;
            }
        }
        return false;
    }
    public boolean noneMatch(BiPredicate<Double,Double> predicate,DoubleMatrix doubleMatrix) {
        return !anyMatch(predicate,doubleMatrix);
    }
    
    public double determinant() {    //行列式
        if(rows != columns) throw new IllegalStateException();
        ArrayList<int[]> fullPermutation = Permutation.fullPermutation(rows);
        double result = 0;
        for(int i1 = 0 ; i1<fullPermutation.size() ; i1++){
            int[] per = fullPermutation.get(i1);
            double temp = matrix[0][per[0]];
            for(int i2 = 1 ; i2<rows ; i2++){
                temp *= matrix[i2][per[i2]];
            }
            result += Permutation.isNaturalEven(i1) ? temp : -temp;
        }
        return result;
    }
    
    public DoubleMatrix toTranspose() {
        return new DoubleMatrix(columns,rows,(i,j) -> matrix[j][i]);
    }
    
    public DoubleMatrix change(int m,int n,boolean isRow) {
        if(m == n) return this;
        if(isRow){
            double[] temp = matrix[m];
            matrix[m] = matrix[n];
            matrix[n] = temp;
        } else {
            for(int i = 0 ; i<rows ; i++){
                double temp = matrix[i][m];
                matrix[i][m] = matrix[i][n];
                matrix[i][n] = temp;
            }
        }
        return this;
    }
    public DoubleMatrix multiply(double value,int index,boolean isRow) {
        if(isRow){
            for(int i = 0 ; i<columns ; i++){
                matrix[index][i] *= value;
            }
        } else {
            for(int i = 0 ; i<rows ; i++){
                matrix[i][index] *= value;
            }
        }
        return this;
    }
    public DoubleMatrix multiply(double k,int from,int to,boolean isRow) {
        if(isRow){
            for(int i = 0 ; i<columns ; i++){
                matrix[to][i] += matrix[from][i] * k;
            }
        } else {
            for(int i = 0 ; i<rows ; i++){
                matrix[i][to] += matrix[from][i] * k;
            }
        }
        return this;
    }
}
