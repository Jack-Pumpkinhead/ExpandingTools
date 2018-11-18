package chess.ai;

import chess.BinaryPiece;
import chess.Tictactoe;
import mathtools.Pair;

import java.util.Random;

/**
 * By Hrd at 17-1-28 下午9:11.
 */
public class TictactoeAI {
    public Pair compute(Tictactoe tic,boolean isBlack){
        for(int i=1;i<=9;i++){
            BinaryPiece b = tic.get(Tictactoe.solve(i));
            if(b==null) input[i-1] = 0;
            else {
                if((b==BinaryPiece.Black)^isBlack) input[i - 1] = 1;
                if((b==BinaryPiece.White)^isBlack) input[i - 1] = -1;
            }
        }
        for(int i=0;i<9;i++){
            hide[i] = 0;
            for(int j=0;j<9;j++){
                hide[i] += Math.pow(weight1[i][j]*input[j],3);
            }
        }
        int result = 0;
        int biggest = Integer.MIN_VALUE;
        for(int i=0;i<9;i++){
            output[i] = 0;
            for(int j=0;j<9;j++){
                output[i] += Math.pow(weight2[i][j]*hide[i],3);
            }
            if(input[i]==0 && output[i]>biggest){
                result = i;
                biggest = output[i];
            }
        }
        return Tictactoe.solve(result + 1);
    }
    public void print(){
        System.out.println("1:");
        System.out.print("{");
        for(int i = 0; i<9;i++){
            System.out.print("{");
            for(int j = 0; j<8; j++){
                System.out.print(weight1[i][j]+", ");
            }
            System.out.println(weight1[i][8]+"},");
        }
        System.out.println("}");
        
        System.out.println("2:");
        System.out.print("{");
        for(int i = 0; i<9;i++){
            System.out.print("{");
            for(int j = 0; j<8; j++){
                System.out.print(weight2[i][j]+", ");
            }
            System.out.println(weight2[i][8]+"},");
        }
        System.out.println("}");
    }
    
    int[] input = new int[9];
    int[][] weight1 = new int[9][9];
    int[] hide = new int[9];
    int[][] weight2 = new int[9][9];
    int[] output = new int[9];
    
    Random random = new Random();
    int value = 7;
    int pro = 1;
    public TictactoeAI(){
        for(int i = 0; i<9;i++){
            for(int j = 0; j<9; j++){
                weight1[i][j] = (random.nextBoolean()?1:-1) * random.nextInt(value);
                weight2[i][j] = (random.nextBoolean()?1:-1) * random.nextInt(value);
            }
        }
    }
    int change = 3;
    public TictactoeAI(TictactoeAI a,TictactoeAI b){
        for(int i = 0; i<9;i++){
            for(int j = 0; j<9; j++){
                weight1[i][j] = random.nextInt(100)<change?b.weight1[i][j]:a.weight1[i][j];
                weight2[i][j] = random.nextInt(100)<change?b.weight2[i][j]:a.weight2[i][j];
                weight1[i][j] += random.nextInt(100)<pro? (random.nextBoolean()?1:-1) * random.nextInt(5):0;
                weight2[i][j] += random.nextInt(100)<pro? (random.nextBoolean()?1:-1) * random.nextInt(5):0;
            }
        }
    }
    public TictactoeAI(int[][] a,int[][] b){
        weight1 = a;
        weight2 = b;
    }
}
