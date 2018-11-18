package mathtools.numberfield;

import java.math.BigDecimal;

/**
 * By Hrd at 16-11-26 下午12:11.
 */
public class Substract extends NumberElement{
    NumberElement minuend;
    NumberElement subtrahend;
    BigDecimal value;
    public Substract(NumberElement minuend,NumberElement subtrahend) {
        this.minuend = minuend;
        this.subtrahend = subtrahend;
        value = minuend.value().subtract(subtrahend.value());
    }
    public String toString() {
        return "(" + minuend + "-" + subtrahend + ")";
    }
    public BigDecimal value() {
        if(isNumber()){
            return value;
        } else {
            return value = minuend.value().subtract(subtrahend.value());
        }
    }
    public boolean isNumber(){
        return minuend.isNumber()&&subtrahend.isNumber();
    }
    public NumberElement simplify() {
        return minuend.add(subtrahend.opposite()).simplify();
    }
}
