package mathtools;

import org.junit.Test;

/**
 * Created by Administrator on 16-12-11.
 */
public class HelperTest {
    @Test
    public void intToChar() throws Exception{
        for(int i = 0; i<1000; i++){
            System.out.println(i+"\t"+Helper.intToChar(i)+"\t"+Helper.charToInt(String.valueOf(Helper.intToChar(i))));
        }
    }
    
}