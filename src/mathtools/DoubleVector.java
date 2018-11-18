package mathtools;

import mathtools.matrix.CountableMatrixElement;
import mathtools.matrix.DoubleMatrix;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * By Hrd at 16-11-13 下午12:28.
 */
public class DoubleVector implements CountableMatrixElement<DoubleVector> {
    protected double[] vector;
    protected int dimensions;
    
    public DoubleVector(int dimensions) {
        this(dimensions,0.0);
    }
    public DoubleVector(int dimensions,double initialVector) {
        this(dimensions,i -> initialVector);
    }
    public DoubleVector(double[] initialVector) {
        this(initialVector.length,initialVector);
    }
    public DoubleVector(int dimensions,double[] initialVector) {
        this(dimensions,i -> i<initialVector.length ? initialVector[i] : null);
    }
    public DoubleVector(int dimensions,Function<Integer, Double> operator) {
        if(dimensions<1 || operator == null) throw new IllegalArgumentException();
        this.dimensions = dimensions;
        vector = new double[dimensions];
        setup(operator);
    }
    public DoubleVector setup(Function<Integer, Double> operator) {
        for(int i = 0 ; i<dimensions ; i++){
            Double value = operator.apply(i);
            if(value != null) vector[i] = value;
        }
        return this;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof DoubleVector)) return false;
        return allMatch(Helper::approximatelyEqual,(DoubleVector) obj);
    }
    
    public void print() {
        //System.out.print("---"+getClass().getName()+"---");
        for(int i = 0 ; i<dimensions ; i++){
            String element = BigDecimal.valueOf(vector[i]).setScale(0).toString();
            //element = element.substring(0,element.length()-2);
            System.out.print(element);
            if(element.length()<4) System.out.print("\t");
            if(element.length()<8) System.out.print("\t");
            if(element.length()<12) System.out.print("\t");
        }
        System.out.print("|\t");
    }
    @Override
    public DoubleVector copy() {
        return new DoubleVector(dimensions,vector);
    }
    @Override
    public DoubleVector getValue() {
        return this;
    }
    public double get(int index) {
        return vector[index];
    }
    public void setValue(DoubleVector value) {
        setup(i -> i<value.dimensions ? value.vector[i] : null);
    }
    public DoubleVector set(int index,double value) {
        vector[index] = value;
        return this;
    }
    @Override
    public DoubleVector getZeroElement() {
        return new DoubleVector(dimensions,0);
    }
    @Override
    public DoubleVector getIdentityElement() {
        return new DoubleVector(dimensions,0).set(0,1);
    }
    public double sum() {
        return Arrays.stream(vector).sum();
    }
    public DoubleVector add(DoubleVector anotherElement) {
        return operateWith(anotherElement,(i,j)->i+j);
    }
    public DoubleVector subtract(DoubleVector anotherElement) {
        return operateWith(anotherElement,(i,j)->i-j);
    }
    public DoubleVector multiply(DoubleVector anotherElement) {
        return operateWith(anotherElement,(i,j)->i*j);
    }
    public DoubleVector operateWith(DoubleVector doubleVector,BinaryOperator<Double> operator) {
        if(dimensions != doubleVector.dimensions) throw new IllegalArgumentException();
        return new DoubleVector(dimensions,i -> operator.apply(this.vector[i],doubleVector.vector[i]));
    }
    
    public boolean allMatch(Predicate<Double> predicate) {
        for(int i = 0 ; i<dimensions ; i++){
            if(!predicate.test(vector[i])) return false;
        }
        return true;
    }
    public boolean anyMatch(Predicate<Double> predicate) {
        for(int i = 0 ; i<dimensions ; i++){
            if(predicate.test(vector[i])) return true;
        }
        return false;
    }
    public boolean noneMatch(Predicate<Double> predicate) {
        return !anyMatch(predicate);
    }
    public boolean allMatch(BiPredicate<Double,Double> predicate,DoubleVector doubleVector) {
        for(int i = 0 ; i<dimensions ; i++){
            if(!predicate.test(this.vector[i],doubleVector.vector[i])) return false;
        }
        return true;
    }
    public boolean anyMatch(BiPredicate<Double,Double> predicate,DoubleVector doubleVector) {
        for(int i = 0 ; i<dimensions ; i++){
                if(predicate.test(this.vector[i],doubleVector.vector[i])) return true;
        }
        return false;
    }
    public boolean noneMatch(BiPredicate<Double,Double> predicate,DoubleVector doubleVector) {
        return !anyMatch(predicate,doubleVector);
    }
    public double scalarProduct(DoubleVector doubleVector) {
        if(this.dimensions != doubleVector.dimensions) throw new IllegalArgumentException();
        return this.multiply(doubleVector).sum();
    }
    public double modulus() {    //模
        return Math.sqrt(scalarProduct(this));
    }   //模
    public boolean isUnitVector() {
        return Helper.approximatelyEqual(modulus(),1);
    }
    
    public DoubleMatrix toRowMatrix() {
        return new DoubleMatrix(1,dimensions,(i,j)->vector[j]);
    }
    public DoubleMatrix toColumnMatrix() {
        return new DoubleMatrix(dimensions,1,(i,j)->vector[i]);
    }
}
