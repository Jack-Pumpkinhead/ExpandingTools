package mathtools.numberfield;

import mathtools.Constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * By Hrd at 16-11-26 下午12:16.
 */
public class Multiply extends NumberElement{
    ArrayList<NumberElement> monomial = new ArrayList<>();
    public Multiply(NumberElement a,NumberElement b,NumberElement... more) {
        monomial.add(a);
        monomial.add(b);
        Collections.addAll(monomial,more);
    }
    public String toString() {
        StringBuffer result = new StringBuffer("("+ monomial.get(0));
        for(int i = 1 ; i<monomial.size() ; i++){
            result.append("*").append(monomial.get(i));
        }
        return result.append(")").toString();
    }
    public BigDecimal value() {
        BigDecimal product = BigDecimal.ONE;
        for(NumberElement element : monomial){
            product = product.multiply(element.value());
        }
        return product.setScale(SCALE,ROUNDING_MODE);
    }
    public boolean isNumber(){
        return monomial.stream().allMatch(NumberElement::isNumber);
    }
    public NumberElement simplify() {
        ArrayList<NumberElement> m = new ArrayList<>();
        NumberElement n = Constant.ONE;
        boolean shouldSimplifyAgain = false;
        
        Iterator<NumberElement> iter = monomial.iterator();
        while(iter.hasNext()){
            NumberElement i = iter.next().simplify();
            if(i instanceof Integer){
                n = ((Integer)n).multiply(i);
                continue;
            }
            if(i instanceof Multiply) {
                shouldSimplifyAgain = true;
                m.addAll((( Multiply ) i).monomial);
                continue;
            }
            if(i.isNumber()&&i.equals(Constant.ZERO))return Constant.ZERO;
            m.add(i);
        }
        if(!n.equals(Constant.ONE)) m.add(n);
        int size = m.size();
        if(size == 0) {
            return Constant.ONE;
        }
        if(size == 1) {
            return m.get(0);
        }
        if(size == 2) {
            return m.get(0).multiply(m.get(1));
        }
        this.monomial = m;
        if(shouldSimplifyAgain) return this.simplify();
        return this;
    }
}
