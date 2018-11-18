package mathtools.matrix;

import java.util.function.BinaryOperator;

/**
 * By Hrd at 16-10-30 下午3:47.
 */
public class DoubleElement implements CountableMatrixElement<DoubleElement> {
    
    double value;
    
    public DoubleElement(){this(0.0);}
    public DoubleElement(double value) {
        this.value = value;
    }
    
    public DoubleElement getZeroElement() {
        return new DoubleElement(0);
    }
    public DoubleElement getIdentityElement() {
        return new DoubleElement(1);
    }
    public DoubleElement copy() {
        return new DoubleElement(value);
    }
    public boolean equals(Object obj) {
        if(!(obj instanceof DoubleElement)) return false;
        return (( DoubleElement ) obj).value == value;
    }
    
    public Double getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = ( Double ) value;
    }
    public void setValue(DoubleElement value) {
        this.value = value.getValue();
    }
    public DoubleElement add(DoubleElement element) {
        return operateWith(element,(a,b) -> a + b);
    }
    
    public DoubleElement subtract(DoubleElement element) {
        return operateWith(element,(a,b) -> a - b);
    }
    
    public DoubleElement multiply(DoubleElement element) {
        return operateWith(element,(a,b) -> a*b);
    }
    
    private DoubleElement operateWith(DoubleElement element,BinaryOperator<Double> operator) {
        DoubleElement result = this.copy();
        result.setValue(operator.apply(this.getValue(),element.getValue()));
        return result;
    }
}
