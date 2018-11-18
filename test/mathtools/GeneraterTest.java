package mathtools;

import mathtools.numberfield.Algebra;
import mathtools.numberfield.Divide;
import mathtools.numberfield.NumberElement;
import org.junit.Test;

/**
 * Created by Administrator on 16-12-11.
 */
public class GeneraterTest {
    @Test
    public void clear() throws Exception{
        System.out.println("x:\t"+Generater.resolve("x").simplify());
        NumberElement result = Generater.resolve("x*(x+3(x*x*x))^x+5").simplify();
        NumberElement result2 = Generater.resolve("(x+2)*(x-5)").simplify();
        System.out.println(result+":\t"+result.value());
        System.out.println(result2+":\t"+result.value());
        Algebra.create("x",new Divide(2));
        System.out.println(result+":\t"+result.value());
        System.out.println(result2+":\t"+result.value());
        System.out.println("variable:");
        Algebra.VARIABLE.forEach((i,j)-> System.out.println(i+":\t"+j));
        Generater.clear();
        System.out.println("After clear:");
        Algebra.VARIABLE.forEach((i,j)-> System.out.println(i+":\t"+j));
    }
    
}