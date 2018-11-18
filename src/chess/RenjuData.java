package chess;

import mathtools.Pair;

import java.util.ArrayList;
import java.util.Set;

/**
 * By Hrd at 17-1-25 上午11:32.
 */
public class RenjuData extends Data {
    public BinaryPiece winner;
    public Set<Pair> pieceString;
    public ArrayList<Pair> order = new ArrayList<>();
    /*public RenjuData copy(){
        RenjuData result = new RenjuData();
        result.winner = this.winner;
        result.pieceString = this.pieceString;
        result.order = this.order;
        return result;
    }*/
}
