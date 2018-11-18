package mathtools.numberfield;

import mathtools.Constant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * By Hrd at 16-12-10 下午8:52.
 */
public class Algebra extends NumberElement {
    public static final Map<String,Algebra> VARIABLE = new HashMap<>();
    public static final String pattern = "[^a-zA-z_]";
    String name;
    NumberElement value;
    private Algebra(String name,NumberElement value){
        if(name.matches(pattern)){ throw new IllegalArgumentException();}
        this.name = name;
        this.value = value;
        VARIABLE.put(name,this);
    }
    public static Algebra create(String name){
        if(VARIABLE.containsKey(name)){
            return VARIABLE.get(name);
        } else return new Algebra(name,Constant.ZERO);
    }
    public static Algebra create(String name,long value){
        return create(name,new Integer(value));
    }
    public static Algebra create(String name,double value){
        return create(name,new Divide(value));
    }
    public static Algebra create(String name,BigInteger value){
        return create(name,new Integer(value));
    }
    public static Algebra create(String name,BigDecimal value){
        return create(name,new Divide(value));
    }
    public static Algebra create(String name,NumberElement value){
        if(VARIABLE.containsKey(name)){
            return VARIABLE.get(name).setValue(value);
        } else return new Algebra(name,value);
    }
    public Algebra setValue(NumberElement value){
        this.value = value;
        return this;
    }
    public NumberElement getValue(){
        return value;
    }
    public static NumberElement getValue(String name){
        return create(name).getValue();
    }
    public NumberElement remove(){
        return VARIABLE.remove(name);
    }
    public static NumberElement remove(String name){
        return VARIABLE.remove(name);
    }
    public static void clear(){
        VARIABLE.clear();
    }
    public String toString(){
        return this.name;
    }
    public BigDecimal value(){
        return this.value.value();
    }
    public boolean isNumber(){
        return false;
    }
    public NumberElement simplify(){
        if(name.matches("^_[a-zA-Z_]+_$")){
            return value.simplify();
        } else return this;
    }
}
