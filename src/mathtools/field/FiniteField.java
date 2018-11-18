package mathtools.field;

import mathtools.generator.Euclidean;
import mathtools.number_theory.PositiveInteger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by CowardlyLion on 2017/11/12 17:36
 */
public class FiniteField {
    static Map<Integer, FiniteField> map = new HashMap<>();
    int[] field;
    int[] unit;
    private FiniteField(int a) {
        field = new int[a];
        for (int i = 0; i < a; i++) {
            field[i] = i;
        }
        unit = new int[PositiveInteger.phi(a)];
        int i=0;
        for (int j = 1; j < a; j++) {
            if(Euclidean.execute(j,a)[0]==1)unit[i++]=j;
        }
    }
    public static FiniteField create(int a) {   //a>0
        FiniteField result = map.get(a);
        if(result!=null) return result;
        else result = new FiniteField(a);
        map.put(a, result);
        return result;
    }
}
