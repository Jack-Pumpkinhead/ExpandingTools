package mathtools.matrix;

/**
 * By Hrd at 16-11-4 下午7:51.
 */
public interface CountableMatrixElement <T extends CountableMatrixElement> extends MatrixElement<T>{
    T getZeroElement();
    T getIdentityElement();
    T add(T anotherElement);
    T subtract(T anotherElement);
    T multiply(T anotherElement);
}
