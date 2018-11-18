package mathtools.numberfield;

import mathtools.Constant;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * By Hrd at 16-11-20 上午10:14.
 */
public class Divide extends NumberElement {
    
    NumberElement dividend;
    NumberElement divisor;
    public NumberElement getDividend() {
        return dividend;
    }
    public NumberElement getDivisor() {
        return divisor;
    }
    
    public Divide(double value) {
        this(BigDecimal.valueOf(value));
    }
    public Divide(BigDecimal value) {
        this(value.unscaledValue(),BigInteger.TEN.pow(value.scale()));
    }
    public Divide(BigInteger dividend,BigInteger divisor) {
        this(new Integer(dividend),new Integer(divisor));
    }
    public Divide(long dividend) {
        this(dividend,1);
    }
    public Divide(long dividend,long divisor) {
        this(new Integer(dividend),new Integer(divisor));
    }
    public Divide(Integer dividend,long divisor) {
        this(dividend,new Integer(divisor));
    }
    public Divide(long dividend,Integer divisor) {
        this(new Integer(dividend),divisor);
    }
    public Divide(Integer dividend) {
        this(dividend,new Integer(1));
    }
    public Divide(Integer dividend,Integer divisor) {
        if(divisor.value().equals(BigDecimal.ZERO)) throw new ArithmeticException("/ by zero");
        BigInteger gcd = dividend.value.gcd(divisor.value);
        if(divisor.value().signum()>0) {
            this.dividend = new Integer(dividend.value.divide(gcd));
            this.divisor = new Integer(divisor.value.divide(gcd));
        } else {
            this.dividend = new Integer(dividend.value.divide(gcd).negate());
            this.divisor = new Integer(divisor.value.divide(gcd).negate());
        }
    }
    public Divide(NumberElement dividend,NumberElement divisor) {
        if(divisor.value().equals(BigDecimal.ZERO)) throw new ArithmeticException("/ by zero");
        this.dividend = dividend;
        this.divisor = divisor;
    }
    
    public String toString() {
        return "(" + dividend.toString() + "/" + divisor.toString() + ")";
    }
    public BigDecimal value() {
        return dividend.value().divide(divisor.value(),SCALE,ROUNDING_MODE);
    }
    public boolean isNumber(){
        return dividend.isNumber()&&divisor.isNumber();
    }
    public NumberElement simplify() {
        dividend = dividend.simplify();
        divisor = divisor.simplify();
        NumberElement result = dividend.divide(divisor);
        if(result.isNumber() && result instanceof Divide && ((Divide)result).divisor.equals(Constant.ONE)){
            return ((Divide)result).dividend;
        } else { return result;}
    }
    public NumberElement add(NumberElement numberElement) {
        if(numberElement instanceof Integer) {
            Divide result = new Divide(
                    (this.dividend.add(this.divisor.multiply(numberElement)))
                    ,this.divisor);
            return result;
        }
        if(numberElement instanceof Divide) {
            Divide ra = (Divide ) numberElement;
            Divide result = new Divide(
                    (this.dividend.multiply(ra.divisor).add(this.divisor.multiply(ra.dividend))),
                    (this.divisor.multiply(ra.divisor)));
            return result;
        }
        return super.add(numberElement);
    }
    public NumberElement subtract(NumberElement numberElement) {
        if(numberElement instanceof Integer) {
            Divide result = new Divide(
                    (this.dividend.subtract(this.divisor.multiply(numberElement)))
                    ,this.divisor);
            return result;
        }
        if(numberElement instanceof Divide) {
            Divide ra = (Divide ) numberElement;
            Divide result = new Divide(
                    (this.dividend.multiply(ra.divisor).subtract(this.divisor.multiply(ra.dividend))),
                    (this.divisor.multiply(ra.divisor)));
            return result;
        }
        return super.subtract(numberElement);
    }
    public NumberElement multiply(NumberElement numberElement) {
        if(numberElement instanceof Integer) {
            Divide result = new Divide(
                    (this.dividend.multiply(numberElement))
                    ,this.divisor);
            return result;
        }
        if(numberElement instanceof Divide) {
            Divide ra = (Divide ) numberElement;
            Divide result = new Divide(
                    (this.dividend.multiply(ra.dividend)),
                    (this.divisor.multiply(ra.divisor)));
            return result;
        }
        return super.multiply(numberElement);
    }
    public NumberElement divide(NumberElement numberElement) {
        if(numberElement instanceof Integer) {
            Divide result = new Divide(
                    this.dividend
                    ,(this.divisor.multiply(numberElement)));
            return result;
        }
        if(numberElement instanceof Divide) {
            Divide ra = (Divide ) numberElement;
            Divide result = new Divide(
                    (this.dividend.multiply(ra.divisor)),
                    (this.divisor.multiply(ra.dividend)));
            return result;
        }
        return super.divide(numberElement);
    }
    public NumberElement power(NumberElement numberElement) {
        if(numberElement instanceof Integer) {
            Divide result = new Divide(
                    (this.dividend.power(numberElement))
                    ,(this.divisor.power(numberElement)));
            return result;
        }
        return super.power(numberElement);
    }
}
