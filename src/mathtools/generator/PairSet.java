package mathtools.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by CowardlyLion on 2017/11/12 15:57
 */
public class PairSet {
    Map<String,int[]> map = new HashMap<>();

    public int[] get(String name) {
        return map.get(name);
    }
    public int[] set(String name, int x, int y) {
        return map.put(name, new int[]{x, y});
    }

    public int[] remove(String name) {
        return map.remove(name);
    }

}
