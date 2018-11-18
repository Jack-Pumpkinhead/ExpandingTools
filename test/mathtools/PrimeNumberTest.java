package mathtools;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by CowardlyLion on 2017/11/18 20:00
 */
public class PrimeNumberTest {
    @Test
    public void findWithin() throws Exception {
        ArrayList<Integer> prime = PrimeNumber.findWithin(100
        );
        System.out.println(prime.size());
        for (int i = 1; i < prime.size(); i++) {
            for (int j = 0; j < prime.size(); j++) {
                System.out.print((int)Math.pow(prime.get(j),i)+"\t\t\t");
            }
            System.out.println();
        }

//        PrimeNumber.extend();

//        PrimeNumber.extend();
//        PrimeNumber.extend();
//        PrimeNumber.extend();
//        PrimeNumber.extend();
//        System.out.println(PrimeNumber.list);
//        System.out.println(PrimeNumber.list.size());
        /*
        System.out.println("extend finish");
        PrimeNumber.sieve(PrimeNumber.list, 1);
        System.out.println(PrimeNumber.list);
        System.out.println(PrimeNumber.list.size());*/

//        System.out.println(PrimeNumber.findWithin( 510482));

//        long time = System.currentTimeMillis();
//        ArrayList<Integer> list= PrimeNumber.findWithin(500000);
//        System.out.println(list);   //16864 in 500000
//        System.out.println(list.size());
//        System.out.println("Total time:"+(System.currentTimeMillis()-time)+"ms");   //6348ms
//
//        int a = 10;
//        int b = a + 500;
//        for (int i = a; i < b; i++) {
//            long times = System.currentTimeMillis();
//            System.out.print(i+"\t"+PrimeNumber.get(i));
//            System.out.println("\t" + (System.currentTimeMillis() - times) + "ms");
//        }
//        for (int i = 1; i <= 30; i++) {
//            if(i%2==0) continue;
//            if(i%3==0) continue;
//            if(i%5==0) continue;
//            if(i%7==0) continue;
//            System.out.print(i+",");
//        }

//        long time1 = System.currentTimeMillis();
//        for (int i = 0; i < 1000000000; i++) {
//            i++;
//            int a = (12645612 % i);
//        }
//        System.out.println("Total time:"+(System.currentTimeMillis()-time1)+"ms");
        
    }

}