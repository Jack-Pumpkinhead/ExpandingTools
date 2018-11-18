package mathtools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
@Deprecated
public class Permutation {
    
    private Permutation() {
    }
    
    public static <T> int inversionNumber(T[] base,T[] after) {  //逆序数
        if(Arrays.stream(base).count() != Arrays.stream(base).distinct().count())
            throw new IllegalArgumentException("Base must different!");
        if(base.length != after.length || !Arrays.stream(base).allMatch(A -> Arrays.stream(after).anyMatch(B -> A == B)))
            throw new IllegalArgumentException("Must full permutation!");
        ArrayList<T> first = new ArrayList<>();
        first.addAll(Arrays.asList(base));
        ArrayList<T> second = new ArrayList<>();
        second.addAll(Arrays.asList(after));
        int result = 0;
        for(T t : first){
            int index = second.indexOf(t);
            result += index;
            second.remove(index);
        }
        return result;
    }
    
    public static <T> boolean isOddPermutation(T[] base,T[] after) {    //奇排列
        return inversionNumber(base,after)%2 == 1;
    }
    
    public static <T> boolean isEvenPermutation(T[] base,T[] after) {
        return !isOddPermutation(base,after);
    }
    
    public static boolean isNaturalEven(int i) {    //自然全排列中的逆序数
        if(i<0) throw new IllegalArgumentException();
        if(i==0)return true;
        if(i==1)return false;
        int order = 1;
        int index = 1;
        while(order*++index<=i){
            order *= index;
        }
        return ((i/order)%2 == 0) == isNaturalEven(i % order);
    }
    public static int[] naturalPermutation(int n) { //0~n-1自然排列
        int[] result = new int[n];
        for(int i = 0 ; i<n ; i++){
            result[i] = i;
        }
        return result;
    }
    
    public static <T> T[] changeTo(int[] permutation,T[] base,T[] result) {
        for(int i = 0 ; i<result.length ; i++){
            result[i] = base[permutation[i]];
        }
        return result;
    }
    
    public static ArrayList<int[]> fullPermutation(int base) {  //全排列
        return combination(naturalPermutation(base),base);
    }
    public static ArrayList<int[]> fullPermutation(int[] base) {
        return combination(base,(int)Arrays.stream(base).distinct().count());
    }
    public static ArrayList<int[]> combination(int base,int length) {  //组合
        return combination(naturalPermutation(base),length);
    }
    public static ArrayList<int[]> combination(int[] base,int length) {
        return permutation(base,length,(a,b) -> {
            int k = b[a];
            for(int i=0;i<a;i++){
                if(b[i]==k)return false;
            }
            if(a>0) return k>b[a-1];
            return true;
        });
    }
    
    public static ArrayList<int[]> permutation(int base,int length,BiPredicate<Integer,int[]> filter) {
        return permutation(naturalPermutation(base),length,filter);
    }
    public static ArrayList<int[]> permutation(int[] base,int length,BiPredicate<Integer,int[]> filter) { //排列
        ArrayList<int[]> result = new ArrayList<>();
        recursive(0,base,new int[length],result,filter);
        return result;
    }
    /**
     *
     * @param index sub起始位置，一般是0
     * @param base 可重复集，sub每位遍历base中值
     * @param sub 长度决定遍历长度，若index为0，其中内容会被覆盖，仅起容器作用
     * @param result 符合filter的所有排列会添加至此
     * @param filter 每次更新sub都测试一次
     *               Integer: 正修改的位数
     *               int[]: 当前排列
     */
    public static void recursive(int index,int[] base,int[] sub,ArrayList<int[]> result,BiPredicate<Integer,int[]> filter) {
        for(int aBase : base){
            sub[index] = aBase;
            if(!filter.test(index,sub)) {
                result.add(sub.clone());
                continue;
            }
            if(index + 1 == sub.length) {
                result.add(sub.clone());
            } else {
                recursive(++index,base,sub,result,filter);
                index--;
            }
        }
    }
    public static void recursive(int index,int[] base,int[] sub,BiPredicate<Integer,int[]> filter) {
        for(int aBase : base){
            sub[index] = aBase;
            if(!filter.test(index,sub)) continue;
            if(index + 1 != sub.length){
                recursive(++index,base,sub,filter);
                index--;
            }
        }
    }
    
    public static void recursive(int x,int y,int[] base,int[][] sub,ArrayList<int[][]> result,BiPredicate<Pair,int[][]> filter) {
        for(int aBase : base){
            sub[x][y] = aBase;
            if(!filter.test(new Pair(x,y),sub)) continue;
            if(y+1!=sub[x].length){
                recursive(x,++y,base,sub,result,filter);
                y--;
            } else {
                if(x+1==sub.length) result.add(Helper.clone(sub));
                else {
                    recursive(++x,0,base,sub,result,filter);
                    x--;
                }
            }
        }
    }
}
