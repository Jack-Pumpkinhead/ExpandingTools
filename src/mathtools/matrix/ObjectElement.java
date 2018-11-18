package mathtools.matrix;

/**
 * By Hrd at 16-10-30 下午1:32.
 */
public class ObjectElement <E> implements MatrixElement<ObjectElement<E>> {
    
    E value;
    
    public ObjectElement(){this(null);}
    public ObjectElement(E value) {
        this.value = value;
    }
    public ObjectElement<E> copy() {
        return new ObjectElement<>(value);
    }
    public E getValue() {
        return value;
    }
    public void setValue(ObjectElement<E> value) {
        this.value = value.getValue();
    }
    
}
