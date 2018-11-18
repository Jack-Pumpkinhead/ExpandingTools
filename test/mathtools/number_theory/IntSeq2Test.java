package mathtools.number_theory;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by CowardlyLion on 2018/4/10 14:55
 */
public class IntSeq2Test {

    @Test
    public void get() {
        IntSeq2 seq = new IntSeq2(new int[][]{
                {1},
                {2, 1}
        }, (rows, row) -> {
            if (row == 0 || row == 1) return 0;
            int index = rows.get(row).size();
            if (index == 0) {
                return 2 * rows.get(row - 1).get(0);
            }
            return 2 * rows.get(row - 1).get(index) + rows.get(row - 2).get(index - 1);
        }, 0
        );
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(seq.get(i,j)+"\t\t");
            }
            System.out.println();
        }
    }
}