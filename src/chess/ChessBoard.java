package chess;

/**
 * By Hrd at 17-1-24 下午7:59.
 */
public abstract class ChessBoard <L,P extends Piece>{
    protected abstract void put(L lo,P p);
    protected abstract P get(L lo);
    protected abstract void clear();
}
