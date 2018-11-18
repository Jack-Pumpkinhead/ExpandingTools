package mathtools.numberfield;

import org.junit.Test;

import java.math.BigDecimal;

import static mathtools.Generater.resolve;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 16-11-26.
 */
public class NumberElementTest {
    @Test
    public void equals() throws Exception {
        assertTrue(new Divide(3,4).equals(new Divide(6,8)));
        System.out.println(new Integer(3).value());
        System.out.println(new Divide(3).value());
        assertTrue(new Integer(3).equals(new Divide(3)));
        assertTrue(new Power(new Integer(3),new Divide(1,2)).equals(new Divide(Math.sqrt(3))));
        assertTrue(!new Power(new Integer(3),new Divide(1,2)).equals(new Divide(2)));
    }
    @Test
    public void ToString() throws Exception {
        System.out.println("4.0+3^2: \t"+resolve("4.0+3^2").simplify());
        System.out.println("2-1.2^3*3: \t"+resolve("2-1.2^3*3").simplify());
        System.out.println("1.0*3: \t"+resolve("1.0*3").simplify());
        System.out.println("1.3415453475772757364498547987/3: \t"+resolve("1.3415453475772757364498547987/3").simplify());
        System.out.println("1.32/3^2+0.7: \t"+resolve("1.32/3^2+0.7").simplify());
        System.out.println("sqrt(3): \t"+new Divide(Math.sqrt(3)).simplify());
        System.out.println("(1+3)/2: \t"+resolve("(1+3)/2").simplify());
        System.out.println("3^(1+3)*2: \t"+resolve("3^(1+3)*2").simplify());
        System.out.println("3+(2+(3+2)): \t"+resolve("3+(2+(3+2))").simplify());
        System.out.println("(2+(3+2)4(1+5)): \t"+resolve("(2+(3+2)4(1+5))").simplify());
        System.out.println("2-3^(2+6(3^2-4)-(32)): \t"+resolve("2-3^(2+6(3^2-4)-(32))").simplify());
        System.out.println("2-3^(2+6(1+1)*(3^2-4)-(32)): \t"+resolve("2-3^(2+6(1+1)*(3^2-4)-(32))").simplify());
    }
    @Test
    public void add() throws Exception {
        assertTrue(resolve("1.66+3").equals(new Divide(1.66+3)));
    }
    @Test
    public void subtract() throws Exception {
        assertTrue(resolve("1.66-3").equals(new Divide(1.66-3)));
    }
    @Test
    public void multiply() throws Exception {
        assertTrue(resolve("1.66*3").equals(new Divide(1.66).multiply(3)));
    }
    @Test
    public void divide() throws Exception {
        assertTrue(resolve("1.66/3").equals(new Divide(1.66/3)));
    }
    @Test
    public void power() throws Exception {
        assertTrue(resolve("1.66^3.1").equals(new Divide(Math.pow(1.66,3.1))));
    }
    @Test
    public void root() throws Exception {
        assertTrue(Power.root(BigDecimal.valueOf(2),2,new BigDecimal("0.001")).equals(BigDecimal.valueOf(1.414)));
        assertTrue(Power.root(BigDecimal.valueOf(3),2,new BigDecimal("0.001")).equals(BigDecimal.valueOf(1.732)));
    }
}