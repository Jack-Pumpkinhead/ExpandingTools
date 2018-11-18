package mathtools.number_theory;

import mathtools.PrimeNumber;

import java.util.ArrayList;

/**
 * Created by CowardlyLion on 2017/11/12 17:49
 */
public class PositiveInteger {
    public static int phi(int a) {  //Euler's totient function.
        ArrayList<Integer> prime = new ArrayList<>();
        int b=a;
        /*for (int i = 0, p = 2; p <= a; i++, p = PrimeNumber.get(i)) {
            if(a%p==0) {
                b /= p;
                prime.add(p);
            }
        }*/
        for (int i = 0; i < prime.size(); i++) {
            b *= (prime.get(i) - 1);
        }
        return b;
    }
}
