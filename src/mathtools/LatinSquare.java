package mathtools;

import java.util.Random;

/**
 * Created by CowardlyLion on 2018/11/17 15:01
 */
public class LatinSquare {
    int order;
    int[][] square;

    public int getOrder() {
        return order;
    }

    public int[][] getSquare() {
        return square;
    }

    public LatinSquare(int order) {
        this.order = order;
        square = new int[order][order];
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                square[i][j] = (i + j) % order;
            }
        }
    }

    public void print() {
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                System.out.print(square[i][j] + "\t");
            }
            System.out.println();
        }
        if (!isLatinSquare()) {
            System.out.println("Wrong!");
        } else {
            System.out.println();
        }
    }

    public boolean isLatinSquare() {
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                int s = square[i][j];
                for (int J = j + 1; J < order; J++) {
                    if(square[i][J]==s) return false;
                }
                for (int I = i + 1; I < order; I++) {
                    if(square[I][j]==s) return false;
                }
            }
        }
        return true;
    }

    int i;
    int j;
    int a;
    int s;

    Random random = new Random();

    int count = 0;

    public int getCount() {
        return count;
    }

    public void randomMove() {
        i = random.nextInt(order);
        j = random.nextInt(order);
        a = (random.nextInt(order - 1) + 1 + square[i][j]) % order;
        boolean improper = moveProper();
        count++;
        while (improper) {
            improper = moveImproper();
            count++;
        }
    }

    public boolean moveProper() {
        int a = this.a;
        int b = square[i][j];
        int I = vf(j, a);
        int J = hf(i, a);
        square[i][j] = a;
        square[I][j] = b;
        square[i][J] = b;
        int c = square[I][J];
        if (c == b) {
            square[I][J] = a;
            return false;
        } else {
            this.i = I;
            this.j = J;
            this.a = b;
            this.s = a;
            return true;
        }
    }

    public boolean moveImproper() {
        int a = this.a;
        boolean boo = random.nextBoolean();
        int b = boo ? square[i][j] : s;
        int I = vf(j, a, random.nextBoolean());
        int J = hf(i, a, random.nextBoolean());
        square[i][j] = boo ? s : square[i][j];
        square[I][j] = b;
        square[i][J] = b;
        int c = square[I][J];
        if (c == b) {
            square[I][J] = a;
            return false;
        } else {
            this.i = I;
            this.j = J;
            this.a = b;
            this.s = a;
            return true;
        }
    }

    private int vf(int j, int b) {
        for (int i = 0; i < order; i++) {
            if (square[i][j] == b) {
                return i;
            }
        }
        return -1;
    }

    private int hf(int i, int b) {
        for (int j = 0; j < order; j++) {
            if (square[i][j] == b) {
                return j;
            }
        }
        return -1;
    }

    private int vf(int j, int b, boolean next) {
        for (int i = 0; i < order; i++) {
            if (square[i][j] == b) {
                if (next) {
                    next = false;
                    continue;
                }
                return i;
            }
        }
        return -1;
    }

    private int hf(int i, int b, boolean next) {

        for (int j = 0; j < order; j++) {
            if (square[i][j] == b) {
                if (next) {
                    next = false;
                    continue;
                }
                return j;
            }
        }
        return -1;
    }

}
