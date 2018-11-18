package mathtools;

import mathtools.generator.IntegerSequences;

import java.util.ArrayList;

public class PrimeNumber {

    private PrimeNumber() {}
    public static ArrayList<Integer> findWithin(int limit) {
        ArrayList<Integer> numberField = new ArrayList<>();
        ArrayList<Integer> delete = new ArrayList<>();
        int sqrt = (int) Math.sqrt(limit);

        for(int i = 2 ; i<=limit ; i++){
            numberField.add(i);
        }
        for (int i = 0, p; i < numberField.size() && (p = numberField.get(i)) <= sqrt; i++) {
            delete.clear();
            for (int j = i, product; (product = numberField.get(j) * p) <= limit; j++) {
                numberField.remove(Helper.integerBinarySearch(numberField, product));
                delete.add(product);
//                numberField.remove(Integer.valueOf(product));
            }
            for (int i1 = 0, product; i1 < delete.size() && (product = delete.get(i1) * p) <= limit; i1++) {
                numberField.remove(Helper.integerBinarySearch(numberField, product));
            }
        }
        return numberField;
    }

    //    public static ArrayList<Integer> list = new ArrayList<>();
//    private static int basePrime = 2;
//    private static int prime = 9;
//    static {
//        list.add(2);
//        list.add(3);
//        list.add(5);
//        list.add(7);
//        list.add(11);
//        list.add(13);
//        list.add(17);
//        list.add(19);
//        list.add(23);
//        list.add(29);
//    }
//
//    public static int get(int i) {
//        if(i<=prime) return list.get(i);
//        do {
//            extend();
//        } while (prime < i);
//        return list.get(i);
//    }
//
//    private static void extend() {
//        search:for (int i = 0; i < search.length; i++) {
//            index += ds[i];
//            for (int j = base; j < list.size(); j++) {
//                if (index % list.get(j) == 0) continue search;
//            }
//            list.add(index);
//        }
//    }
//    private static int base = 3;
//    private static int[] search = new int[]{1,7,11,13,17,19,23,29};
//    private static int[] ds = IntegerSequences.difference(search);
//    static {
//        ds[0] = 2;
//
//    }
//    private static int index = 29;
    public static ArrayList<Integer> list = new ArrayList<>();
    public static ArrayList<Integer> primeList = new ArrayList<>();
    public static ArrayList<Integer> fillList = new ArrayList<>();


    private static int basePrime = 2;
    private static int prime = 9;
    private static int product = 30;
    static {
        list.add(1);
        list.add(7);
        list.add(11);
        list.add(13);
        list.add(17);
        list.add(19);
        list.add(23);
        list.add(29);
        primeList.add(2);
        primeList.add(3);
        primeList.add(5);
    }
    public static void extend() {
        int p = list.get(1);
        int pro = p * product;
        int size = list.size();
        for (int i = product; i < pro; i += product) {
            for (int j = 0; j < size; j++) {
                list.add(list.get(j) + i);
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            list.remove(Helper.integerBinarySearch(list, list.get(i) * p));
//            System.out.println("remove " + list.get(i));
        }
        /*int j = size - 1;
        for (int i = list.size()-1; i >= 0; i--) {
            if (list.get(i) == list.get(j) * p) {
                list.remove(i);
                j--;
            }

        }*/
        product *= p;
        primeList.add(p);
    }

    private static void fill() {
        fillList = new ArrayList<>(primeList);
        fillList.addAll(list);
        fillList.remove(primeList.size());

    }

    public static void sieve(ArrayList<Integer> numberField,int sieved) {
        int limit = numberField.get(numberField.size()-1);
        int sqrt = (int) Math.sqrt(limit);

        for (int i = sieved, p; i < numberField.size() && (p = numberField.get(i)) <= sqrt; i++) {
            for (int j = i, product; (product = numberField.get(j) * p) <= limit; j++) {
                do {
                    numberField.remove(Helper.integerBinarySearch(numberField, product));
                    product *= p;
                } while (product <= limit);
            }
//            System.out.println("num " + numberField.get(i));
        }
    }
    private int i ;
    private int j ;
    private int startI ;
    private int sieved ;

}
