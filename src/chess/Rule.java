package chess;

/**
 * By Hrd at 17-1-24 下午8:08.
 */
public abstract class Rule<L,P extends Piece> {
    protected abstract boolean put(L lo,P p);
    protected abstract Data end();
}
