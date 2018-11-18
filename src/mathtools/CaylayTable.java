package mathtools;

import java.util.ArrayList;

/**
 * Created by CowardlyLion on 2018/8/25 16:09
 */
public class CaylayTable {
    public static boolean composition(byte[][] c){
        for (byte j = 0; j < c.length; j++) {
            for (byte i = 0; i < c.length; i++) {
                for (byte k = 0; k < c.length; k++) {
                    if (c[c[i][j]][k] != c[i][c[j][k]]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static boolean communtative(byte[][] c){
        for(byte i=0;i<c.length;i++){
            for (byte j = (byte) (i + 1); j < c.length; j++) {
                if (c[i][j] != c[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static byte firstRightIdentity(byte[][] c) {
        for (byte i = 0; i < c.length; i++) {
            if (isRightIdentity(c, i)) {
                return i;
            }
        }
        return -1;
    }
    public static byte firstLeftIdentity(byte[][] c) {
        for (byte i = 0; i < c.length; i++) {
            if (isLeftIdentity(c, i)) {
                return i;
            }
        }
        return -1;
    }
    public static byte identity(byte[][] c) {
        for (byte i = 0; i < c.length; i++) {
            if (isLeftIdentity(c, i) && isRightIdentity(c, i)) {
                return i;
            }
        }
        return -1;
    }


    public static boolean isRightIdentity(byte[][] c, byte e) {
        for (byte i = 0; i < c.length; i++) {
            if (c[i][e] != i) {
                return false;
            }
        }
        return true;
    }
    public static boolean isLeftIdentity(byte[][] c, byte e) {
        for (byte i = 0; i < c.length; i++) {
            if (c[e][i] != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param c CaylayTable
     * @param b element
     * @param e element (may not be identity)
     * @return element {@code a} such that {@code ab = e}
     */
    public static byte firstLeftInverse(byte[][] c, byte b, byte e) {
        for (byte a = 0; a < c.length; a++) {
            if (c[a][b] == e) {
                return a;
            }
        }
        return -1;
    }
    /**
     * @param c CaylayTable
     * @param a element
     * @param e element (may not be identity)
     * @return element {@code b} such that {@code ab = e}
     */
    public static byte firstRightInverse(byte[][] c, byte a, byte e) {
        for (byte b = 0; b < c.length; b++) {
            if (c[a][b] == e) {
                return b;
            }
        }
        return -1;
    }

    public static boolean isGroup(byte[][] c) {
        if(!composition(c)) return false;
        byte e = firstLeftIdentity(c);
        if(e==-1) return false;
        for (byte b = 0; b < c.length; b++) {
            if (firstLeftInverse(c, b, e) == -1) {
                return false;
            }
        }
        return true;
    }


}
