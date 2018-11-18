package mathtools.generator;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by CowardlyLion on 2017/10/1 10:06
 */
public class Permutations {
    public static Stream<int[]> cartesianPower(int range, int length) {
        return Stream.generate(() -> range).limit(length).reduce(Stream.of(new int[0]), Permutations::biCartesianProduct, Permutations::biCartesianProduct);
    }
    public static Stream<int[]> biCartesianProduct(Stream<int[]> a, Stream<int[]> b) {
        List<int[]> list = b.collect(Collectors.toList());
        return a.parallel().flatMap(i ->
            list.stream().parallel().map(j ->
                    IntStream.concat(Arrays.stream(i), Arrays.stream(j)).toArray())
            );
    }
    public static Stream<int[]> biCartesianProduct(Stream<int[]> a, int b) {
        return a.parallel().flatMap(i -> {
            int[] expand = Arrays.copyOf(i,i.length+1);
            return IntStream.range(0, b)/*.parallel()*/.mapToObj(j ->{
                expand[i.length] = j;
                return expand;
            });});
    }
    public static Stream<int[]> biCartesianProduct(int a, Stream<int[]> b) {
        return b.parallel().flatMap(j -> {
            int[] expand = new int[j.length+1];
            System.arraycopy(j,0,expand,1,j.length);
            return IntStream.range(0, a)/*.parallel()*/.mapToObj(i ->{
                expand[0] = i;
                return expand;
            });});
    }
    public static Stream<int[]> biCartesianProduct(int a, int b) {
        return IntStream.range(0, a).mapToObj(i ->
                IntStream.range(0, b).mapToObj(j ->
                        new int[]{i,j}))
                .flatMap(i -> i);
    }
    public static Stream<int[]> initialStream(int a) {
        return IntStream.range(0, a).mapToObj(i -> new int[]{i});
    }

    public static Stream<int[]> permutation(int range) {
        return cartesianPower(range, range).filter(Permutations::isDistinct);
    }
    public static boolean isDistinct(int[] nonNegativeArray) {
        BitSet set = new BitSet();
        return Arrays.stream(nonNegativeArray).allMatch(i->{
           if(set.get(i)) return false;
           set.set(i);
           return true;
        });
    }

    public static int[] changeTo(int[] permutation,int[] base) { //Three inputs must have SAME size;
        int[] result = new int[base.length];
        for(int i = 0 ; i<base.length ; i++){
            result[i] = base[permutation[i]];
        }
        return result;
    }
    public static <T> T[] changeTo(int[] permutation,T[] base,T[] result) { //Three inputs must have SAME size;
        for(int i = 0 ; i<result.length ; i++){
            result[i] = base[permutation[i]];
        }
        return result;
    }
}
