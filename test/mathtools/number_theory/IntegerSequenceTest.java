package mathtools.number_theory;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by CowardlyLion on 2018/4/10 14:50
 */
public class IntegerSequenceTest {

    @Test
    public void get() {
        ArrayList<Integer> fi = new ArrayList<>();
        fi.add(0);
        fi.add(1);
        IntegerSequence a = new IntegerSequence(fi, integers -> integers.get(integers.size() - 2) + integers.get(integers.size() - 1));
        for (int i = 0; i < 100; i++) {
            System.out.print(a.get(i)+"\t");
        }
    }
}