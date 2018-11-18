package mathtools.operating;

import java.util.ArrayList;

/**
 * Created by CowardlyLion on 2018/9/30 16:41
 */
public abstract class Operation <T extends OperatingObject> {
    public abstract boolean isValid(T object);
    public abstract T apply(T object);
    public abstract ArrayList<Operation<T>> searchValid(T object);

    public abstract Operation<T> create(String instruction);


}
