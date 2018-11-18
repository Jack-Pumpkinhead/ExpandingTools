package chess;

/**
 * By Hrd at 17-1-24 下午9:19.
 */
public class BinaryPiece extends Piece {
    public static BinaryPiece Black = new BinaryPiece(){
        public String toString(){
            return "Black";
        }
    };
    public static BinaryPiece White = new BinaryPiece(){
        public String toString(){
            return "White";
        }
    };
    private BinaryPiece(){}
}
