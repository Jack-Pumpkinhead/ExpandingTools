package mathtools.binaryBoolean;

/**
 * By Hrd at 16-12-18 下午1:26.
 */
public class Boolean extends BooleanElement{
    public static final Boolean FALSE = new Boolean(false);
    public static final Boolean TRUE = new Boolean(true);
    final boolean value;
    private Boolean(boolean value){
        this.value = value;
    }
    public String toString(){
        return value ? "1" : "0";
    }
    public boolean value(){
        return value;
    }
    public BooleanElement simplify(){
        return this;
    }
    public BooleanElement and(BooleanElement e){
        if(e instanceof Boolean){
            return value&e.value() ? TRUE : FALSE;
        } else return super.and(e);
    }
    public BooleanElement or(BooleanElement e){
        if(e instanceof Boolean){
            return value|e.value() ? TRUE : FALSE;
        } else return super.and(e);
    }
    public BooleanElement not(){
        return value ? FALSE : TRUE;
    }
}
