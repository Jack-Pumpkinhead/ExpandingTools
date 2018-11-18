package chess;


import mathtools.Pair;

/**
 * By Hrd at 17-1-24 下午9:22.
 */
public class PlanarBoard extends ChessBoard<Pair,BinaryPiece>{
    /*class Location{
        int x,y;
         Location(int x,int y){
            if(x<1||y<1||x>width||y>height) throw new IllegalArgumentException();
            this.x = x;
            this.y = y;
        }
        public Location move(int dx,int dy){
            Location result;
            try{
                result = new Location(x + dx,y + dy);
            } catch (Exception e){
                return null;
            }
            return result;
        }
        @Override
        public String toString(){
            return "(" + x + ", " + y + ")";
        }
    }
    public Location index(int x,int y){
        return new Location(x,y);
    }*/
    protected int width,height;
    BinaryPiece[][] board;
    public PlanarBoard(int width,int height){
        if(width<1||height<1) throw new IllegalArgumentException();
        this.width = width;
        this.height = height;
        board = new BinaryPiece[width][height];
    }
    @Override
    protected void put(Pair location,BinaryPiece p){
        board[location.x-1][location.y-1] = p;
    }
    @Override
    protected BinaryPiece get(Pair location){
        try{
            return board[location.x-1][location.y-1];
        } catch (Exception e){
            return null;
        }
    }
    @Override
    protected void clear(){
        board = new BinaryPiece[width][height];
    }
}
