package mathtools.numberfield;

import mathtools.Constant;

import java.math.BigDecimal;

/**
 * By Hrd at 16-11-20 下午12:32.
 */
public abstract class NumberElement implements Cloneable{
    protected static int SCALE = 16;
    protected static int ROUNDING_MODE = BigDecimal.ROUND_DOWN;
    public static void setSCALE(int SCALE) {
        NumberElement.SCALE = SCALE;
    }
    public static void setRoundingMode(int roundingMode) {
        ROUNDING_MODE = roundingMode;
    }
    public NumberElement clone() {
        try {
            return ( NumberElement ) super.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int hashCode() {
        return Double.hashCode(value().doubleValue());
    }
    public boolean equals(Object obj) {
        return super.equals(obj) || obj instanceof NumberElement && this.value().compareTo((( NumberElement ) obj).value()) == 0;
    }
    public abstract String toString();
    public abstract BigDecimal value();
    public abstract boolean isNumber();
    
    public static Divide parseDivide(double number) {
        return new Divide(number);
    }
    public static Divide[] parseDivide(double... number) {
        Divide[] result = new Divide[number.length];
        for(int i = 0 ; i<result.length ; i++){
            result[i] = new Divide(number[i]);
        }
        return result;
    }
    
    public NumberElement opposite() {
        return this.multiply(Constant.MINUSONE);
    }
    public NumberElement inverse() {
        return Constant.ONE.divide(this);
    }
    
    public NumberElement add(double numberElement,double... moreElement){
        return add(parseDivide(numberElement),( NumberElement[] ) parseDivide(moreElement));
    }
    public NumberElement subtract(double numberElement){
        return subtract(parseDivide(numberElement));
    }
    public NumberElement multiply(double numberElement,double... moreElement){
        return multiply(parseDivide(numberElement),( NumberElement[] ) parseDivide(moreElement));
    }
    public NumberElement divide(double numberElement){
        return divide(parseDivide(numberElement));
    }
    public NumberElement power(double numberElement){
        return power(parseDivide(numberElement));
    }
    
    public NumberElement add(NumberElement numberElement){
        return new Add(this,numberElement);
    }
    public NumberElement add(NumberElement numberElement,NumberElement... moreElement){
        return new Add(this,numberElement,moreElement);
    }
    public NumberElement subtract(NumberElement numberElement){
        return new Substract(this,numberElement);
    }
    public NumberElement multiply(NumberElement numberElement){
        return new Multiply(this,numberElement);
    }
    public NumberElement multiply(NumberElement numberElement,NumberElement... moreElement){
        return new Multiply(this,numberElement,moreElement);
    }
    public NumberElement divide(NumberElement numberElement){
        return new Divide(this,numberElement);
    }
    public NumberElement power(NumberElement numberElement){
        return new Power(this,numberElement);
    }
    
    public abstract NumberElement simplify();
}
