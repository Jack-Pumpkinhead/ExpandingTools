package mathtools.binaryBoolean;

import java.util.ArrayList;
import java.util.Collections;

/**
 * By Hrd at 16-12-18 下午1:24.
 */
public class And extends BooleanElement{
    ArrayList<BooleanElement> list = new ArrayList<>();
    public And(BooleanElement a,BooleanElement b,BooleanElement... e){
        list.add(a);
        list.add(b);
        Collections.addAll(list,e);
    }
    public String toString(){
        StringBuilder result = new StringBuilder("("+list.get(0));
        for(int i=1 ; i<list.size() ; i++){
            result.append("+").append(list.get(i));
        }
        return result.append(")").toString();
    }
    public boolean value(){
        return list.stream().allMatch(BooleanElement::value);
    }
    public BooleanElement simplify(){
        return this;
    }
}
