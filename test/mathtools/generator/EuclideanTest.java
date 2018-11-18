package mathtools.generator;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by CowardlyLion on 2017/11/12 16:38
 */
public class EuclideanTest {
    Random r = new Random();
    @Test
    public void execute() throws Exception {
        int bound = 998;
        int a;
        int b;
        int[] e;
        for (int i = 1; i < 10; i++) {
            a = r.nextInt(bound)+1;
            b = r.nextInt(bound)+1;
            e = Euclidean.execute(a, b);
            System.out.printf("(%3d,%-3d):\t%-3d = %3d*%-3d + %3d*%-3d = %-3d \t"+(e[1]*a+e[2]*b==e[0])+"\n",a,b,e[0],e[1],a,e[2],b,e[1]*a+e[2]*b);
        }
    }

}