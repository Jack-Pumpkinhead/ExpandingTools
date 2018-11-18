package mathtools;

import mathtools.matrix.CountableMatrix;
import mathtools.matrix.CountableMatrixElement;

import java.util.ArrayList;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * By Hrd at 16-11-5 下午4:10.
 */
public class Vector <T extends CountableMatrixElement<T>> implements CountableMatrixElement<Vector<T>>{
    
    protected T zeroElement;
    protected T identityElement;
    protected ArrayList<T> vectorArray;
    protected int dimensions;
    
    
    public Vector(int dimensions,T initialElement) {
        this(dimensions,i->initialElement);
    }
    public Vector(int dimensions,Function<Integer, T> operator) {
        if(dimensions<1 || operator == null || operator.apply(0) == null) throw new IllegalArgumentException();
        zeroElement = operator.apply(0).getZeroElement();
        identityElement = operator.apply(0).getIdentityElement();
        vectorArray = new ArrayList<>(dimensions);
        for(int i = 0 ; i<dimensions ; i++){
            vectorArray.add(operator.apply(i) == null ? zeroElement : operator.apply(i).copy());
        }
        this.dimensions = dimensions;
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vector)) return false;
        Vector vector = ( Vector ) obj;
        if(this.dimensions != vector.dimensions) return false;
        for(int i = 0 ; i<dimensions ; i++){
            if(!this.getElement(i).equals(vector.getElement(i))) return false;
        }
        return true;
    }
    public Vector<T> copy() {
        return new Vector<T>(dimensions,this::getElement);
    }
    public Object getValue() {
        return this;
    }
    public void setValue(Vector<T> value) {
        zeroElement = value.zeroElement;
        identityElement = value.identityElement;
        vectorArray = value.vectorArray;
        dimensions = value.dimensions;
    }
    public Vector<T> getZeroElement() {
        return new Vector<T>(dimensions,zeroElement);
    }
    public Vector<T> getIdentityElement() {
        return null;
    }
    
    protected T getElement(int i) {
        return vectorArray.get(i);
    }
    public Object get(int i) {
        return getElement(i).getValue();
    }
    
    public Vector<T> add(Vector<T> vector) {
        return operateWith(vector,T::add);
    }
    public Vector<T> subtract(Vector<T> vector) {
        return operateWith(vector,T::subtract);
    }
    public Vector<T> multiply(Vector<T> vector) {
        return operateWith(vector,T::multiply);
    }
    public Vector<T> operateWith(Vector<T> vector,BinaryOperator<T> operator) {
        if(dimensions != vector.dimensions) throw new IllegalArgumentException();
        return new Vector<T>(dimensions,i -> operator.apply(this.getElement(i),vector.getElement(i)));
    }
    public T scalarProduct(Vector<T> vector) {
        if(this.dimensions != vector.dimensions) throw new IllegalArgumentException();
        T result = this.getElement(0).multiply(vector.getElement(0));
        for(int i = 1 ; i<dimensions ; i++){
            result = result.add(this.getElement(i).multiply(vector.getElement(i)));
        }
        return result;
    }
    public T modulusSquare() {    //模
        return scalarProduct(this);
    }   //模的平方
    public boolean isUnitVector() {
        return modulusSquare().equals(identityElement);
    }
    
    public CountableMatrix<T> toRowMatrix() {
        return new CountableMatrix<T>(1,dimensions,(i,j)->getElement(j));
    }
    public CountableMatrix<T> toColumnMatrix() {
        return new CountableMatrix<T>(dimensions,1,(i,j)->getElement(i));
    }
}
