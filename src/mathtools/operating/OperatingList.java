package mathtools.operating;

import java.util.ArrayList;

/**
 * Created by CowardlyLion on 2018/9/30 16:28
 */
public abstract class OperatingList<T extends OperatingObject> {
    ArrayList<T> list = new ArrayList<>();
    T current;
    ArrayList<Operation<T>> operations = new ArrayList<>();
    public void extendAll() {
        for (Operation<T> operation : operations) {
            list.add(operation.apply(current));
        }
    }



}
