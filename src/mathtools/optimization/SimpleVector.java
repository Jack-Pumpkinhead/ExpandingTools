package mathtools.optimization;

import mathtools.Constant;
import mathtools.numberfield.NumberElement;

import java.util.ArrayList;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * By Hrd at 17-1-12 下午8:41.
 */
public class SimpleVector {
    protected ArrayList<NumberElement> vectorArray;
    protected int dimensions;
    public SimpleVector(int dimensions,NumberElement initialElement) {
        this(dimensions,i->initialElement);
    }
    public SimpleVector(int dimensions,Function<Integer, NumberElement> operator) {
        if(dimensions<1 || operator == null )throw new IllegalArgumentException();
        vectorArray = new ArrayList<>(dimensions);
        for(int i = 0 ; i<dimensions ; i++){
            vectorArray.add(operator.apply(i) == null ? Constant.ZERO : operator.apply(i));
        }
        this.dimensions = dimensions;
    }
    public String toString(){
        StringBuffer result = new StringBuffer("["+vectorArray.get(0));
        for(int i = 1; i<dimensions; i++){
            result.append(",").append(vectorArray.get(i));
        }
        return result.append("]").toString();
    }
    public NumberElement get(int i) {
        return vectorArray.get(i);
    }
    public void setValue(int i,Function<NumberElement, NumberElement> f) {
        vectorArray.set(i,f.apply(vectorArray.get(i)));
    }
    public SimpleVector add(SimpleVector vector) {
        return operateWith(vector,NumberElement::add);
    }
    public SimpleVector subtract(SimpleVector vector) {
        return operateWith(vector,NumberElement::subtract);
    }
    public SimpleVector multiply(SimpleVector vector) {
        return operateWith(vector,NumberElement::multiply);
    }
    public SimpleVector simplify() {
        return operate(NumberElement::simplify);
    }
    public SimpleVector operate(Function<NumberElement,NumberElement> f){
        for(int i=0;i<dimensions;i++){
            NumberElement result = f.apply(get(i));
            vectorArray.set(i,result == null ? Constant.ZERO : result.simplify());
        }
        return this;
    }
    public SimpleVector operateWith(SimpleVector vector,BinaryOperator<NumberElement> operator) {
        if(dimensions != vector.dimensions) throw new IllegalArgumentException();
        for(int i=0;i<dimensions;i++){
            NumberElement result = operator.apply(get(i),vector.get(i));
            vectorArray.set(i,result == null ? Constant.ZERO : result.simplify());
        }
        return this;
    }
    public NumberElement scalarProduct(SimpleVector vector) {
        if(this.dimensions != vector.dimensions) throw new IllegalArgumentException();
        NumberElement result = this.get(0).multiply(vector.get(0));
        for(int i = 1 ; i<dimensions ; i++){
            result = result.add(this.get(i).multiply(vector.get(i)));
        }
        return result;
    }
    public SimpleMatrix toRowMatrix() {
        return new SimpleMatrix(1,dimensions,(i,j)->get(j));
    }
    public SimpleMatrix toColumnMatrix() {
        return new SimpleMatrix(dimensions,1,(i,j)->get(i));
    }
}
