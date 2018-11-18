package mathtools;

import org.junit.Test;

import java.util.Arrays;

import static mathtools.BinaryBoolean.*;

/**
 * Created by CowardlyLion on 2018/8/24 20:59
 */
public class BinaryBooleanTest {
    @Test
    public void test1() {
        for (byte i = 0; i < 16; i++) {
            for (byte j = 0; j < 16; j++) {
                byte[] expression = new byte[]{j, i, -1, -2, -1};
                byte equal = equal(expression);
                System.out.println(i + " " + j + " = " + equal);
//                System.out.println(Arrays.toString(table(expression))+"\t"+Arrays.toString(arrayTable[equal]));
            }
        }
        byte[] expression = new byte[]{2, 3, -1, -2, -1};
        System.out.println(equal(expression));
    }

    @Test
    public void search() {
        for (int i = 1; i < 7; i++) {
            maxLength = (byte) (2 * i + 1);
            byte[] init = new byte[]{-1};
            searchTree(init, (byte) 0);
        }
        for (byte[] bytes : treeList) {
            System.out.println(Arrays.toString(bytes));
        }
    }

    @Test
    public void conposition() {
        byte[] twoOpTree = new byte[]{2, 1, -1, -2, -2};
        System.out.println(compositionLaw(twoOpTree));
        byte[] twoOpTree2 = new byte[]{2, 1, -1, -2, -1};
        System.out.println(compositionLaw(twoOpTree2));
    }

    @Test
    public void expand() {
        for (int i = 1; i < 7; i++) {
            maxLength = (byte) (2 * i + 1);
            byte[] init = new byte[]{-1};
            searchTree(init, (byte) 0);
        }
        for (byte[] tree : treeList) {
            expandTwo(tree, new byte[tree.length], 0);
//            System.out.println(Arrays.toString(tree));
//            System.out.println(composiTwoOpTree.size());
        }
//        for (byte[] twoOpTree : groupTwoOpTree) {
//            System.out.println(Arrays.toString(twoOpTree));
//        }
    }

    @Test
    public void naturalBooleanTable() {
            for (byte i = 0; i < 16; i++) {
                boolean[] table = booleanTable(i);
                System.out.println(Arrays.toString(table) + "\t" + naturalOrder(table[0], table[1], table[2], table[3]));
            }
    }
}