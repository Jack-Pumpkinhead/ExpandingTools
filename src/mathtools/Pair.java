package mathtools;

import java.io.Serializable;

/**
 * By Hrd at 17-1-27 下午2:31.
 */
public class Pair implements Serializable{
    public int x,y;
    public Pair(int x,int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        
        Pair pair = (Pair)o;
        
        if(x!=pair.x) return false;
        return y==pair.y;
    
    }
    @Override
    public int hashCode(){
        int result = x;
        result = 31*result + y;
        return result;
    }
    public Pair move(int dx,int dy){
        return new Pair(x + dx,y + dy);
    }
}
