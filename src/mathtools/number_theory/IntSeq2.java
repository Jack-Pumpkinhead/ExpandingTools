package mathtools.number_theory;

import java.util.ArrayList;
import java.util.function.BiFunction;

/**
 * Created by CowardlyLion on 2018/4/10 14:14
 */
public class IntSeq2 {
    ArrayList<ArrayList<Integer>> rows;
    BiFunction<ArrayList<ArrayList<Integer>>,Integer, Integer> next;
    int bias;       //if bias=0 then each row will be calculated in the same size.

    public IntSeq2(ArrayList<ArrayList<Integer>> init,
                   BiFunction<ArrayList<ArrayList<Integer>>, Integer, Integer> calculateNext,
                   int bias) {
        this.rows = init;
        this.next = calculateNext;
        this.bias = bias;
    }
    public IntSeq2(int[][] init,
                   BiFunction<ArrayList<ArrayList<Integer>>, Integer, Integer> calculateNext,
                   int bias) {
        rows = new ArrayList<>();
        for (int i = 0; i < init.length; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < init[i].length; j++) {
                row.add(init[i][j]);
            }
            rows.add(row);
        }
        this.next = calculateNext;
        this.bias = bias;
    }

    public int get(int i, int j) {
        while (i >= rows.size()) {
            rows.add(new ArrayList<Integer>());
        }
        for (int k = 0; k <= i; k++) {
            int request = j + (bias * (i - k));
            while (rows.get(k).size() <= request) {
                rows.get(k).add(next.apply(rows, k));
            }
        }
        return rows.get(i).get(j);
    }

}
