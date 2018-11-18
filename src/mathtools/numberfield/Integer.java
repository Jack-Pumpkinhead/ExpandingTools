package mathtools.numberfield;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * By Hrd at 16-11-20 下午2:43.
 */
@Deprecated//Unused excepted rename;
public class Integer extends NumberElement {

    final BigInteger value;

    public BigInteger getValue() {
        return value;
    }

    public Integer(long value) {
        this.value = BigInteger.valueOf(value);
    }

    public Integer(BigInteger value) {
        this.value = value;
    }

    public Integer(Integer integer) {
        this.value = integer.value;
    }

    public String toString() {
        if (value.signum() < 0) return "(" + value.toString() + ")";
        return value.toString();
    }

    public BigDecimal value() {
        return new BigDecimal(value);
    }

    public boolean isNumber() {
        return true;
    }

    public NumberElement simplify() {
        return this;
    }


    public NumberElement add(NumberElement numberElement) {
        if (numberElement instanceof Integer) {
            return new Integer(this.value.add(((Integer) numberElement).value));
        }
        return super.add(numberElement);
    }

    public NumberElement subtract(NumberElement numberElement) {
        if (numberElement instanceof Integer) {
            return new Integer(this.value.subtract(((Integer) numberElement).value));
        }
        return super.subtract(numberElement);
    }

    public NumberElement multiply(NumberElement numberElement) {
        if (numberElement instanceof Integer) {
            return new Integer(this.value.multiply(((Integer) numberElement).value));
        }
        return super.multiply(numberElement);
    }

    public NumberElement divide(NumberElement numberElement) {
        if (numberElement instanceof Integer) {
            return new Divide(this.value, ((Integer) numberElement).value);
        }
        return super.divide(numberElement);
    }

    public NumberElement power(NumberElement numberElement) {
        if (numberElement instanceof Integer) {
            return new Integer(this.value.pow(((Integer) numberElement).value.intValue()));
        }
        return super.power(numberElement);
    }
}
