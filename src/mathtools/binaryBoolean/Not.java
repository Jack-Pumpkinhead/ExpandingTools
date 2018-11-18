package mathtools.binaryBoolean;

/**
 * By Hrd at 16-12-18 下午1:25.
 */
public class Not extends BooleanElement{
    BooleanElement value;
    public Not(BooleanElement value){
        this.value = value;
    }
    public String toString(){
        return value + "'";
    }
    public boolean value(){
        return !value.value();
    }
    public BooleanElement simplify(){
        return this;
    }
}
