package mathtools.field;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by CowardlyLion on 2017/11/19 19:00
 */
public class FiniteFieldTest {
    @Test
    public void create() throws Exception {
        for (int i = 1; i < 100; i++) {
            System.out.println(i+"\tField:"+Arrays.toString(FiniteField.create(i).field));
            System.out.println(i+"\tUnit:"+Arrays.toString(FiniteField.create(i).unit));
        }
    }

}