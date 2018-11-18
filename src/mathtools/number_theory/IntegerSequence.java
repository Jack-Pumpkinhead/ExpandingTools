package mathtools.number_theory;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by CowardlyLion on 2018/4/10 13:49
 */
public class IntegerSequence {
    ArrayList<Integer> sequence;
    Function<ArrayList<Integer>, Integer> next;
    public IntegerSequence(ArrayList<Integer> init, Function<ArrayList<Integer>, Integer> calculateNext) {
        this.sequence = init;
        this.next = calculateNext;
    }

    public int get(int i) {
        while (i >= sequence.size()) {
            sequence.add(next.apply(sequence));
        }
        return sequence.get(i);
    }

}
