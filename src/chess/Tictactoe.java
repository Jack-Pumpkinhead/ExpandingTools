package chess;

import chess.ai.TI;
import chess.ai.TictactoeAI;
import mathtools.Pair;

import java.util.Collections;
import java.util.Random;

import static chess.BinaryPiece.Black;

/**
 * By Hrd at 17-1-24 下午8:41.
 */
public class Tictactoe {//井字棋
    private PlanarBoard chessBoard = new PlanarBoard(3,3);
    private RenjuRule rule = new RenjuRule(chessBoard,(byte)3);
    private RenjuData data = new RenjuData();
    public Tictactoe(){}
    private TictactoeAI ai;
    private TictactoeAI ai2;
    public Tictactoe(TictactoeAI ai){
        this.ai = ai;
    }
    public Tictactoe(TictactoeAI ai,TictactoeAI ai2){
        this.ai = ai;
        this.ai2 = ai2;
        for(int i=1;i<=9;i++){
            boolean a = isBlack ? put(ai2.compute(this,isBlack)):put(ai.compute(this,isBlack));
            if(isEnd()) break;
        }
    }
    public static Pair solve(int i){
        int x = (i-1)%3+1;
        int y = (i+2)/3;
        return new Pair(x,y);
    }
    
    public RenjuData getData(){
        return data;
    }
    private boolean isBlack = false;    //已下最后一步的棋子
    private boolean isBlack(){
        return isBlack = !isBlack;
    }
    public boolean isEnd(){
        return data.winner!=null||data.order.size()==9;
    }
    public boolean put(int i){  //0-8
        return put(solve(i+1));
    }
    public boolean put(Pair lo){
        if(isEnd()) return false;
        BinaryPiece p = isBlack()? Black :BinaryPiece.White;
        if(rule.put(lo,p)){
            chessBoard.put(lo,p);
            data = rule.end();
            return true;
        }
        isBlack();
        return false;
    }
    public boolean put(int x,int y){
        return put(new Pair(x,y));
    }
    public boolean AIput(){
        /*if(ai==null) return false;
        return put(ai.compute(this,isBlack));*/
        if(isEnd()) return false;
        return put(TI.compute(this));
    }
    Random rnd = new Random();
    public boolean randomput(){
        if(isEnd()) return false;
        boolean put = false;
        do{
            put = put(rnd.nextInt(9));
        } while(!put);
        return true;
    }
    public BinaryPiece get(Pair location){
        return chessBoard.get(location);
    }
    public BinaryPiece get(int x,int y){
        return chessBoard.get(new Pair(x,y));
    }
    public void print(){
        System.out.println("############# Winner:"+data.winner+"##############################");
        System.out.println(Collections.singletonList(data.pieceString));
        System.out.println(get(1,1)+"#"+get(2,1)+"#"+get(3,1));
        System.out.println(get(1,2)+"#"+get(2,2)+"#"+get(3,2));
        System.out.println(get(1,3)+"#"+get(2,3)+"#"+get(3,3));
        System.out.println("###########################################");
    }
    public static void main(String[] args){
        Tictactoe t;
        int blackwin = 0;
        int tie = 0;
        int whitewin = 0;
        int total = 10000;
        for(int i=0;i<total;i++){
            t = new Tictactoe();
            for(int n=1;n<=9;n++){
                boolean a = t.isBlack ? t.AIput():t.AIput();
                if(t.isEnd()) break;
            }
            if(t.data.winner==BinaryPiece.Black) blackwin++;
            else if(t.data.winner==BinaryPiece.White) whitewin++;
            else tie++;
        }
        System.out.println("Black win:"+blackwin);
        System.out.println("Tie:"+tie);
        System.out.println("White win:"+whitewin);
        System.out.println("Total:"+total);
        /*TictactoeAI main = new TictactoeAI(
                new int[][]{{-29, -37, 6, 33, 7, 31, -18, 10, 1},
                {38, -18, -11, -4, -15, -12, -44, -1, -19},
                {-7, 35, -1, 30, -5, -15, -12, -11, 15},
                {3, -19, 10, -12, -6, -10, -6, -18, 33},
                {-4, 10, 13, -23, -11, 11, 15, -28, -7},
                {-8, 13, -10, 24, 2, 52, 17, 2, -33},
                {-18, 11, -4, -3, -11, 0, -7, -28, -18},
                {7, 4, -1, 4, 12, 1, 3, 17, -5},
                {-41, -4, -26, -16, -23, -43, 34, 35, -6}
        },new int[][]{{-1, -52, 17, 12, -16, 18, -41, -9, -10},
            {34, -3, 0, 3, 2, 46, 17, -2, -55},
            {28, 6, -7, 70, 21, 12, -14, -12, 34},
            {23, 10, 12, -4, -33, -22, -9, 23, 6},
            {14, -11, 13, -3, -24, 13, 16, 46, 14},
            {-48, 13, 4, 19, 42, 3, 7, -24, -7},
            {45, -3, 4, -25, -18, 31, -2, -7, 17},
            {-15, -6, -1, -29, -18, 7, 21, 3, 34},
            {19, 15, -77, -21, -5, 5, -37, 0, -4}
        });
        for(int i=0;i<10;i++){
            main = train(main);
        }
        System.out.println("Goooo");
        Tictactoe t = new Tictactoe(main);
        Scanner s = new Scanner(System.in);
        while(true){
            byte input = s.nextByte();
            if(input==0) t.AIput();
            else t.put(solve(input));
            t.print();
        }*/
    }
    public static TictactoeAI train(TictactoeAI aaa){
        int count = 10;
        TictactoeAI[] ti = new TictactoeAI[count];
        int[] win = new int[count];
        for(int i=1;i<count;i++){
            ti[i] = new TictactoeAI();
        }
        ti[0] = aaa;
    
    
    
        Random random = new Random();
        int a,b;
        boolean first;
        long trainTime = System.currentTimeMillis();
        do{
            a = random.nextInt(count);
            first = random.nextBoolean();
            BinaryPiece p = first? new Tictactoe(ti[a],ti[0]).data.winner: new Tictactoe(ti[0],ti[a]).data.winner;
            if(p==null) continue;
            if(p==BinaryPiece.White^first){
                ti[0] = new TictactoeAI(ti[a],ti[0]);
                win[a]++;
                win[0] = 0;
            } else {
                ti[a] = new TictactoeAI(ti[0],ti[a]);
                win[a] = 0;
                win[0]++;
            }
        } while(win[0]<9);
        System.out.println("\nTrain time used:" + (System.currentTimeMillis() - trainTime) + "ms.");
        int biggest = 0;
        for(int i=0;i<count;i++){
            System.out.print(win[i]+"\t");
            if(win[i]>win[biggest]) biggest = i;
        }
        ti[biggest].print();
        int wincount = 0;
        int losecount = 0;
        for(int i=0;i<1000;i++){
            BinaryPiece p1 = new Tictactoe(ti[biggest],new TictactoeAI()).data.winner;
            BinaryPiece p2 = new Tictactoe(new TictactoeAI(),ti[biggest]).data.winner;
            if(p1==Black) wincount++;
            if(p2==BinaryPiece.White) wincount++;
            if(p1==BinaryPiece.White) losecount++;
            if(p2==Black) losecount++;
        }
        System.out.println(wincount+","+losecount);
        return ti[biggest];
    }
}
