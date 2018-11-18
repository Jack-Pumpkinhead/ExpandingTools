package mathtools;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BinaryOperator;

/**
 * Created by CowardlyLion on 2018/8/22 20:27
 */
public class BinaryBoolean {
    public static BinaryOperator<Boolean>[] array = new BinaryOperator[16];
    static {
        array[0] = (a, b) -> false;
        array[1] = (a, b) -> a && b;
        array[2] = (a, b) -> !(a || b);
        array[3] = (a, b) -> a == b;
        array[4] = (a, b) -> !a && b;
        array[5] = (a, b) -> b;
        array[6] = (a, b) -> !a;
        array[7] = (a, b) -> !a || b;
        array[8] = (a, b) -> a && !b;
        array[9] = (a, b) -> a;
        array[10] = (a, b) -> !b;
        array[11] = (a, b) -> a || !b;
        array[12] = (a, b) -> a != b;
        array[13] = (a, b) -> a || b;
        array[14] = (a, b) -> !(a && b);
        array[15] = (a, b) -> true;
    }

    public static boolean[][] arrayTable = new boolean[16][4];
    static {
        for (int i = 0; i < arrayTable.length; i++) {
            arrayTable[i][0] = array[i].apply(false, false);
            arrayTable[i][1] = array[i].apply(false, true);
            arrayTable[i][2] = array[i].apply(true, false);
            arrayTable[i][3] = array[i].apply(true, true);
//            System.out.println(Arrays.toString(arrayTable[i]));
        }
    }

    static byte naturalOrder(boolean a, boolean b, boolean c, boolean d) {
        return (byte) ((a ? 8 : 0) | (b ? 4 : 0) | (c ? 2 : 0) | (d ? 1 : 0));
    }

    static boolean[] booleanTable(byte naturalOrder) {
        boolean[] table = new boolean[4];
        table[3] = (naturalOrder % 2) != 0;
        naturalOrder /= 2;
        table[2] = (naturalOrder % 2) != 0;
        naturalOrder /= 2;
        table[1] = (naturalOrder % 2) != 0;
        naturalOrder /= 2;
        table[0] = (naturalOrder % 2) != 0;
        return table;
    }

    static boolean[][] naturalTable = new boolean[16][4];
    static {
        for (byte i = 0; i < naturalTable.length; i++) {
            naturalTable[i] = booleanTable(i);
        }

    }
    public static boolean calculate(byte naturalOrder, byte a, byte b) {
        return naturalTable[naturalOrder][(a == p_true ? 2 : 0) + (b == p_true ? 1 : 0)];
    }


    static byte[] stack;
    public static boolean resolver(byte[] exp, byte a, byte b) {
        if (exp.length == 1) {
            return resolve(exp[0], a, b) == p_true;
        }
        stack = new byte[exp.length];
        stack[0]=exp[0];
        stack[1]=exp[1];
        byte index = 2;
        for (int i = 2; i < exp.length; i++) {
            stack[index++] = exp[i];
            while (index >= 2 && stack[index - 2] < 0 && stack[index - 1] < 0) {
                stack[index - 3] = calculate(stack[index - 3], resolve(stack[index - 2], a, b), resolve(stack[index - 1], a, b)) ? p_true : p_false;
                index -= 2;
            }
        }
        if(index!=1){
            System.out.println("Error stack length");
        }
        return stack[0] == p_true;
    }

    static final byte p_a = -3;
    static final byte p_b = -4;
    static final byte p_false = -1;
    static final byte p_true = -2;
    static byte resolve(byte value, byte a, byte b) {
        switch (value) {
            case p_a: return a;
            case p_b: return b;
        }
        return value;
    }

    public static boolean equal(byte[] exp1, byte[] exp2) {
        return resolver(exp1, p_false, p_false) == resolver(exp2, p_false, p_false)
                && resolver(exp1, p_false, p_true) == resolver(exp2, p_false, p_true)
                && resolver(exp1, p_true, p_false) == resolver(exp2, p_true, p_false)
                && resolver(exp1, p_true, p_true) == resolver(exp2, p_true, p_true);
    }
    public static byte equal(byte[] exp) {
        return naturalOrder(resolver(exp, p_false, p_false),
                resolver(exp, p_false, p_true),
                resolver(exp, p_true, p_false),
                resolver(exp, p_true, p_true));
    }

    static ArrayList<byte[]> treeList = new ArrayList<>();
    static byte maxLength;
    static final byte bit_boolean = -1;
    static final byte bit_op = 1;
    public static void searchTree(byte[] tree, byte index) {
        if (tree.length == maxLength - 2) {
            for (byte i = index; i < tree.length; i++) {
                if (tree[i] == bit_boolean) {
                    treeList.add(insert(tree, i));
                }
            }
        } else {
            for (byte i = index; i < tree.length; i++) {
                if (tree[i] == bit_boolean) {
                    searchTree(insert(tree, i), i);
                }
            }
        }
    }
    static byte[] insert(byte[] tree, byte index) {
        byte[] result = new byte[tree.length + 2];
        System.arraycopy(tree, 0, result, 0, index);
        System.arraycopy(tree, index, result, index + 2, tree.length - index);
        result[index] = bit_op;
        result[index + 1] = bit_boolean;
        return result;
    }

//    static ArrayList<byte[]> composiTwoOpTree = new ArrayList<>();
    static ArrayList<byte[]> groupTwoOpTree = new ArrayList<>();

    static ArrayList<byte[]> twoOpTrees = new ArrayList<>();
    public static ArrayList<byte[][]> twoOpCaylayTables = new ArrayList<>();
    static {
        twoOpTrees.add(new byte[]{p_false});
        twoOpCaylayTables.add(genTwoOpCaylayTable(new byte[]{p_false}));
    }
    public static int findSame(byte[][] twoOpCaylayTable) {
        next:for (int i = 0; i < twoOpCaylayTables.size(); i++) {
            byte[][] table = twoOpCaylayTables.get(i);
            for (int i1 = 0; i1 < table.length; i1++) {
                for (int i2 = 0; i2 < table.length; i2++) {
                    if (table[i1][i2] != twoOpCaylayTable[i1][i2]) {
                        continue next;
                    }
                }
            }
            return i;   //Find duplicated.
        }
        return -1;  //No duplicated.
    }


    static void expandTwo(byte[] tree,byte[] twoOpTree,int index) { //Add copy of twoOpTree!
        if (index == tree.length) {
            byte[][] caylayTable = genTwoOpCaylayTable(twoOpTree);
            int indexInTable = findSame(caylayTable);
            if (indexInTable == -1) {
                byte[] copy = new byte[twoOpTree.length];
                System.arraycopy(twoOpTree, 0, copy, 0, twoOpTree.length);
                twoOpTrees.add(copy);
                twoOpCaylayTables.add(caylayTable);
            } else {
                if(indexInTable==0)
                System.out.println(infixFormat(twoOpTree) + " = " + infixFormat(twoOpTrees.get(indexInTable)));
                /*for (int i = 0; i < caylayTable.length; i++) {
                    for (int j = 0; j < caylayTable.length; j++) {
                        System.out.print(caylayTable[i][j]+"\t");
                    }
                    System.out.println();
                }
                System.out.println("-----------------------------------------");
//                byte[][] bytes = twoOpCaylayTables.get(indexInTable);
                byte[][] bytes = genTwoOpCaylayTable(twoOpTrees.get(indexInTable));
                for (int i = 0; i < caylayTable.length; i++) {
                    for (int j = 0; j < caylayTable.length; j++) {
                        System.out.print(bytes[i][j]+"\t");
                    }
                    System.out.println();
                }
                System.out.println();*/

            }
            if (twoOpCaylayTables.size() != twoOpTrees.size()) {
                System.out.println("Error!");
            }

            /*if (CaylayTable.isGroup(caylayTable)) {
                byte[] copy = new byte[twoOpTree.length];
                System.arraycopy(twoOpTree, 0, copy, 0, twoOpTree.length);
                groupTwoOpTree.add(copy);
                System.out.println(Arrays.toString(twoOpTree));
            }*/
//            if(compositionLaw(twoOpTree)){
//                byte[] copy = new byte[twoOpTree.length];
//                System.arraycopy(twoOpTree, 0, copy, 0, twoOpTree.length);
//                composiTwoOpTree.add(copy);
//            }
        } else {
            if (tree[index] < 0) {
//                twoOpTree[index] = p_false;expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = p_true;expandTwo(tree, twoOpTree, index+1);
                twoOpTree[index] = p_a;expandTwo(tree, twoOpTree, index+1);
                twoOpTree[index] = p_b;expandTwo(tree, twoOpTree, index+1);
            } else {
                twoOpTree[index] = op1Mask;expandTwo(tree, twoOpTree, index+1);
                twoOpTree[index] = op2Mask;expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 0; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 1; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 2; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 3; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 4; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 5; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 6; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 7; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 8; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 9; expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 10;expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 11;expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 12;expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 13;expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 14;expandTwo(tree, twoOpTree, index+1);
//                twoOpTree[index] = 15;expandTwo(tree, twoOpTree, index+1);
            }
        }

    }

    public static String format(byte[] twoOpTree) {
        StringBuilder s = new StringBuilder("[ ");
        for (int i = 0; i < twoOpTree.length; i++) {
            switch (twoOpTree[i]) {
                case op1Mask:   s.append('·');  break;
                case op2Mask:   s.append('+');   break;
                case p_false:   s.append('F');   break;
                case p_true:    s.append('T');   break;
                case p_a:       s.append('a');   break;
                case p_b:       s.append('b');   break;
                default: s.append(twoOpTree[i]); break;
            }
            if (i != twoOpTree.length - 1) {
                s.append(", ");
            } else {
                s.append(" ]");
            }
        }
        return s.toString();
    }

    public static String infixFormat(byte[] twoOpTree) {
        return infixFormated(twoOpTree, (byte) 0, (byte) (twoOpTree.length - 1)).toString();
    }
    static StringBuilder infixFormated(byte[] twoOpTree,byte i,byte j) {
        byte middle = findEnd(twoOpTree, (byte) (i + 1));
        StringBuilder result = new StringBuilder();
        if (i + 1 == middle) {
            result.append(notationFormat(twoOpTree[middle]));
        } else {
            result.append("(").append(infixFormated(twoOpTree, (byte) (i + 1), middle)).append(")");
        }
        result.append(notationFormat(twoOpTree[i]));
        if (middle + 1 == j) {
            result.append(notationFormat(twoOpTree[j]));
        } else {
            result.append("(").append(infixFormated(twoOpTree, (byte) (middle + 1), j).append(")"));
        }
        return result;
    }

    public static byte findEnd(byte[] twoOpTree,byte opIndex) {
        if(twoOpTree[opIndex]<0) return opIndex;
        byte count = 2;
        for (byte i = (byte) (opIndex + 1); i < twoOpTree.length; i++) {
            if (twoOpTree[i] < 0) {
                count--;
            } else {
                count++;
            }
            if (count == 0) {
                return i;
            }
        }
        return -1;
    }
    static String notationFormat(byte notation) {
        switch (notation) {
            case op1Mask:   return "*";
            case op2Mask:   return "+";
            case p_false:   return "F";
            case p_true:    return "T";
            case p_a:       return "a";
            case p_b:       return "b";
            default: return String.valueOf(notation);
        }
    }

    static final byte op1Mask = 101;
    static final byte op2Mask = 102;
    public static byte[] construct(byte[] twoOpTree,byte op1,byte op2) {
        byte[] exp = new byte[twoOpTree.length];
        for (int i = 0; i < twoOpTree.length; i++) {
            if(twoOpTree[i]==op1Mask) exp[i] = op1;
            else if(twoOpTree[i]==op2Mask) exp[i] = op2;
            else exp[i] = twoOpTree[i];
        }
        return exp;
    }
    public static boolean compositionLaw(byte[] twoOpTree) {
//        return CaylayTable.composition(genTwoOpCaylayTable(twoOpTree));
        for (byte i = 0; i < 16; i++) {
            for (byte j = 0; j < 16; j++) {
                for (byte k = 0; k < 16; k++) {

                    long time = System.nanoTime();
                    byte[] ij = construct(twoOpTree, i, j);
                    byte[] ij_k = construct(twoOpTree, equal(ij), k);
                    byte[] jk = construct(twoOpTree, j, k);
                    byte[] i_jk = construct(twoOpTree, i, equal(jk));

//                    System.out.println("Total time: "+(System.nanoTime()-time)+" nanosecond");



                    if (!equal(ij_k, i_jk)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static byte[][] genTwoOpCaylayTable(byte[] twoOpTree) {
        byte[][] caylayTable = new byte[16][16];
        for (byte i = 0; i < 16; i++) {
            for (byte j = 0; j < 16; j++) {
                caylayTable[i][j] = equal(construct(twoOpTree, i, j));
            }
        }
        return caylayTable;
    }


}
