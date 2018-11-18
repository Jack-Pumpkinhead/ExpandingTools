package mathtools.numberfield;

import mathtools.Constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * By Hrd at 16-11-20 上午10:22.
 */
public class Add extends NumberElement{
    ArrayList<NumberElement> polynomial = new ArrayList<>();
    
    public Add(NumberElement a,NumberElement b,NumberElement... more) {
        polynomial.add(a);
        polynomial.add(b);
        Collections.addAll(polynomial,more);
    }
    
    public String toString() {
        StringBuffer result = new StringBuffer("("+polynomial.get(0));
        for(int i=1 ; i<polynomial.size() ; i++){
            result.append("+").append(polynomial.get(i));
        }
        return result.append(")").toString();
    }
    public BigDecimal value() {
        BigDecimal sum = BigDecimal.ZERO;
        for(NumberElement element : polynomial){
            sum = sum.add(element.value());
        }
        return sum.setScale(SCALE,ROUNDING_MODE);
    }
    public boolean isNumber(){
        return polynomial.stream().allMatch(NumberElement::isNumber);
    }
    public NumberElement simplify() {
        ArrayList<NumberElement> p = new ArrayList<>();
        NumberElement n = Constant.ZERO;
        boolean shouldSimplifyAgain = false;
        
        Iterator<NumberElement> iter = polynomial.iterator();
        while(iter.hasNext()){
            NumberElement i = iter.next().simplify();
            if(i instanceof Integer) {
                n = ((Integer)i).add(n);
                continue;
            }
            if(i instanceof Add) {
                shouldSimplifyAgain = true;
                p.addAll((( Add ) i).polynomial);
                continue;
            }
            p.add(i);
        }
        if(!n.equals(Constant.ZERO))p.add(n);
        int size = p.size();
        if(size == 0) {
            return Constant.ZERO;
        }
        if(size == 1) {
            return p.get(0);
        }
        if(size == 2) {
            return p.get(0).add(p.get(1));
        }
        this.polynomial = p;
        if(shouldSimplifyAgain) return this.simplify();
        return this;
    }
}
