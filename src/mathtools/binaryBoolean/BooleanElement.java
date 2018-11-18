package mathtools.binaryBoolean;

/**
 * By Hrd at 16-12-18 下午12:46.
 */
public abstract class BooleanElement {
    public int hashCode(){
        return value() ? 1 : 0;
    }
    public boolean equals(Object obj){
        return super.equals(obj) || obj instanceof BooleanElement && ((BooleanElement)obj).value()==this.value();
    }
    protected BooleanElement clone(){
        try {
            return ( BooleanElement ) super.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public abstract String toString();
    public abstract boolean value();
    public abstract BooleanElement simplify();
    public BooleanElement and(BooleanElement e){
        return new And(this,e);
    }
    public BooleanElement and(BooleanElement e,BooleanElement... m){
        return new And(this,e,m);
    }
    public BooleanElement or(BooleanElement e){
        return new Or(this,e);
    }
    public BooleanElement or(BooleanElement e,BooleanElement... m){
        return new Or(this,e,m);
    }
    public BooleanElement not(){
        return new Not(this);
    }
}
