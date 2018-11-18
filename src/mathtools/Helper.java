package mathtools;

import java.util.ArrayList;

/**
 * By Hrd at 16-11-13 下午2:14.
 */
public class Helper {
    public static boolean approximatelyEqual(double a,double b) {
        return Math.abs(a-b) <= 0.00000000000001;
    }
    public static boolean approximatelyEqual(double a,double b,double precise) {
        return Math.abs(a-b) <= precise;
    }
    public static int getGreatestCommonFactor(int a,int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if(a<b){
            int temp = a;
            a=b;
            b=temp;
        }
        if(b==0) return a;
        int r = a%b;
        while(r != 0){
            a = b;
            b = r;
            r = a%b;
        }
        return b;
    }
    public static int safeInteger(int source,int maximum) {
        return source>maximum ? maximum : source;
    }
    public static int safeInteger(int source,int minimum,int maximum) {
        return source>maximum ? maximum : source<minimum ? minimum : source;
    }
    public static StringBuilder intToChar(int source){
        if(source <0) throw new IllegalArgumentException();
        int mod = source%26;
        int divide = source/26;
        StringBuilder result = new StringBuilder().append((char)(mod + 97));
        if(divide==0){
            return result;
        } else return intToChar(divide).append(result);
    }
    public static int charToInt(String source){
        if(source.matches("[^a-z]")) throw new IllegalArgumentException();
        char[] split = source.toCharArray();
        int result = 0;
        for(int i=source.length()-1;i>=0;i--){
            result += (((int)split[i]) - 97)*Math.pow(26,source.length() - i-1);
        }
        return result;
    }
    public static void print(int[] a){
        System.out.print("{");
        int m = a.length-1;
        for(int i = 0; i<m; i++){
            System.out.print(a[i]+", ");
        }
        System.out.println(a[m]+"}");
    }
    public static void print(int[][] a){
        System.out.print("{");
        print(a[0]);
        System.out.println(",");
        int m = a.length-1;
        for(int i = 1; i<m;i++){
            System.out.print(" ");
            print(a[i]);
            System.out.println(",");
        }
        System.out.print(" ");
        print(a[m]);
        System.out.println("}");
    }
    public static int[][] clone(int[][] a){
        int[][] r = new int[a.length][];
        for(int i = 0; i<a.length; i++){
            r[i] = a[i].clone();
        }
        return r;
    }

    public static int integerBinarySearch(ArrayList<Integer> list, int a) { //In ascending order.
        int low = 0;
        int high = list.size()-1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int b = list.get(middle);
//            System.out.println("a:"+a+"  b:"+b+"  m:"+middle);
            if (b == a) {
                return middle;
            } else if (b > a) {
                high = middle - 1;
            } else low = middle + 1;
        }
//        System.out.println();
        return -1;
    }
}
