package chess;


import mathtools.Pair;

import java.util.HashSet;

/**
 * By Hrd at 17-1-24 下午10:19.
 */
public class RenjuRule extends Rule<Pair,BinaryPiece>{
    PlanarBoard chessboard;
    byte renju;
    Pair current;
    RenjuData data;
    public RenjuRule(PlanarBoard chessboard,byte renju){
        this.chessboard = chessboard;
        this.renju=renju;
        current = new Pair(1,1);
        data=new RenjuData();
    }
    @Override
    protected boolean put(Pair lo,BinaryPiece binaryPiece){
        if(chessboard.get(lo)==null){
            data.order.add(lo);
            current = lo;
            return true;
        } else return false;
    }
    @Override
    protected RenjuData end(){
        BinaryPiece p = chessboard.get(current);
        if(p==null)throw new IllegalArgumentException();
        if(search(1,0,p)|| search(0,1,p)|| search(1,1,p)|| search(1,-1,p)){
            return data;
        }
        data.winner = null;
        return data;
    }
    private boolean search(int dx,int dy,BinaryPiece p){
        HashSet<Pair> win = new HashSet<>();
        win.add(current);
        for(byte i=1;i<renju;i++){
            Pair m = current.move(dx*i,dy*i);
            if(chessboard.get(m)==p){
                win.add(m);
            } else break;
        }
        for(byte i=1;i<renju;i++){
            Pair m = current.move(-dx*i,-dy*i);
            if(chessboard.get(m)==p){
                win.add(m);
            } else break;
        }
        if(win.size()>=renju) {
            data.winner = p;
            data.pieceString = win;
            return true;
        }
        return false;
    }
}
