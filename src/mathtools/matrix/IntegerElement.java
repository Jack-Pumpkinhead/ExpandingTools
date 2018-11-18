package mathtools.matrix;

import java.util.function.BinaryOperator;

/**
 * By Hrd at 16-10-30 下午1:52.
 */
public class IntegerElement implements CountableMatrixElement<IntegerElement> {
    
    int value;
    
    public IntegerElement(){this(0);}
    public IntegerElement(int value) {
        this.value = value;
    }
    public IntegerElement getZeroElement() {
        return new IntegerElement(0);
    }
    public IntegerElement getIdentityElement() {
        return new IntegerElement(1);
    }
    public Integer getValue() {
        return value;
    }
    private void setValue(int value) {
        this.value = value;
    }
    public void setValue(IntegerElement value) {
        this.value = value.getValue();
    }
    public IntegerElement copy() {
        return new IntegerElement(value);
    }
    public boolean equals(Object obj) {
        if(!(obj instanceof IntegerElement)) return false;
        return (( IntegerElement ) obj).value == value;
    }
    
    public IntegerElement add(IntegerElement element) {
        return operateWith(element,(a,b) -> a + b);
    }
    public IntegerElement subtract(IntegerElement element) {
        return operateWith(element,(a,b) -> a - b);
    }
    public IntegerElement multiply(IntegerElement element) {
        return operateWith(element,(a,b) -> a*b);
    }
    private IntegerElement operateWith(IntegerElement element,BinaryOperator<Integer> operator) {
        IntegerElement result = this.copy();
        result.setValue(operator.apply(this.getValue(),element.getValue()));
        return result;
    }
}
