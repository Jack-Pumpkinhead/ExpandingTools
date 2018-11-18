package mathtools.numberfield;

import mathtools.Constant;

import java.math.BigDecimal;

/**
 * By Hrd at 16-11-20 上午10:15.
 */
public class Power extends NumberElement {
    
    NumberElement base;
    NumberElement exponent;
    public NumberElement getBase() {
        return base;
    }
    public NumberElement getExponent() {
        return exponent;
    }
    
    /*public Power(int base,int exponent) {
        this(new Divide(base),new Divide(exponent));
    }
    public Power(Divide base,int exponent) {
        this(base,new Divide(exponent));
    }
    public Power(int base,Divide exponent) {
        this(new Divide(base),exponent);
    }
    public Power(Divide base,Divide exponent) {
        this.base = base;
        this.exponent = exponent;
        value = Math.pow(base.value(),exponent.value());
    }*/
    public Power(NumberElement base,NumberElement exponent) {
        this.base = base;
        this.exponent = exponent;
        /*Divide exp = new Divide(exponent.value());
        value = root(base.value().pow(exp.getDividend().value().intValue()),
                     exp.getDivisor().value().intValue(),
                     BigDecimal.ONE.divide(BigDecimal.TEN.pow(SCALE),BigDecimal.ROUND_DOWN));*/
        
    }
    public String toString() {
        return "("+base.toString()+"^"+exponent.toString()+")";
    }
    public BigDecimal value() {
        return BigDecimal.valueOf(Math.pow(base.value().doubleValue(),exponent.value().doubleValue()));
    }
    public boolean isNumber(){
        return base.isNumber()&&exponent.isNumber();
    }
    public NumberElement simplify() {
        base = base.simplify();
        exponent = exponent.simplify();
        if(exponent.isNumber()&&exponent.equals(Constant.ZERO)) {
            return Constant.ONE;
        }
        if(exponent.isNumber()&&exponent.equals(Constant.ONE)) {
            return this.base;
        }
        return base.power(exponent);
    }
    
    public static BigDecimal root(BigDecimal source,int root,BigDecimal scale) {
        if(source.signum()<0 && root%2 == 0 || scale.signum()<=0) throw new ArithmeticException();
        int sc = scale.scale()+1;
        BigDecimal a = BigDecimal.valueOf(root);
        BigDecimal b = BigDecimal.valueOf(root-1);
        BigDecimal guess = source.divide(a,sc,ROUNDING_MODE);
        BigDecimal pow;
        do{
            pow = guess.pow(root - 1);
            guess = guess.multiply(b).add(source.divide(pow,sc,ROUNDING_MODE)).divide(a,sc,ROUNDING_MODE);
        } while(pow.multiply(guess).subtract(source).abs().compareTo(scale)>0);
        return guess.setScale(sc-1,ROUNDING_MODE);
    }
    
    /*public NumberElement inverse() {
        return new Power(base,( Divide ) exponent.multiply(new Integer(-1)));
    }
    public static NumberElement simplify(Power source) {
        if(source.exponent.dividend.value==0) return new Integer(1);
        else {
            if(source.base.dividend.value == 0) return new Integer(0);
            if(source.exponent.divisor.value==1) {
                return source.base.power(new Integer(source.exponent.dividend));
            }
        }
        return source;
    }
    public NumberElement add(NumberElement numberElement) {
        return null;
    }
    public NumberElement subtract(NumberElement numberElement) {
        return null;
    }
    public NumberElement multiply(NumberElement numberElement) {
        if(numberElement instanceof Divide && this.base.equals(numberElement)) {
            return new Power(base,( Divide ) this.exponent.add(new Integer(1)));
        }
        if(numberElement instanceof Power && this.base == (( Power ) numberElement).base){
            return new Power(base,( Divide ) this.exponent.add((( Power ) numberElement).exponent));
        }
        return null;
    }
    public NumberElement divide(NumberElement numberElement) {
        if(numberElement instanceof Divide && this.base.equals(numberElement)) {
            return new Power(base,( Divide ) this.exponent.subtract(new Integer(1)));
        }
        if(numberElement instanceof Power && this.base == (( Power ) numberElement).base){
            return new Power(base,( Divide ) this.exponent.subtract((( Power ) numberElement).exponent));
        }
        return null;
    }
    public NumberElement power(NumberElement numberElement) {
        if(numberElement instanceof Integer) {
            return new Power(base,( Divide ) this.exponent.multiply(numberElement));
        }
        if(numberElement instanceof Divide) {
            return new Power(base,( Divide ) this.exponent.multiply(numberElement));
        }
        return null;
    }*/
}
