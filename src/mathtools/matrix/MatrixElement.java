package mathtools.matrix;

/**
 * By Hrd at 16-11-4 下午7:50.
 */
public interface MatrixElement <T extends MatrixElement>{
    boolean equals(Object o);
    T copy();
    Object getValue();
    void setValue(T value);
}
