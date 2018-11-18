package chess.database;

import chess.Tictactoe;
import mathtools.Pair;
import mathtools.graph.Link;
import mathtools.graph.Tree;

import java.util.ArrayList;

/**
 * By Hrd at 17-1-25 下午3:53.
 */
public class TictactoeData {
    Tree<Pair> data = new Tree<>(new Pair(0,0));
    public void filldata(){
        long theTime = System.currentTimeMillis();
        ArrayList<int[]> p ;//= Permutation.fullPermutation(new int[] {1,2,3,4,5,6,7,8,9});
        p = new ArrayList<>();
        p.add(new int[]{1,2,3,4,5,6,7,8,9});
        System.out.println("Permutation time used:" + (System.currentTimeMillis() - theTime) + "ms.");
        p.forEach(pe -> {
            Tictactoe t = new Tictactoe(null);
            Link<Pair> link = data.getRoot();
            Pair pair;
            for(int i = 0; i<9; i++){
                pair = Tictactoe.solve(pe[i]);
                link = link.sub(pair);
                t.put(pair.x,pair.y);
                if(t.isEnd()) break;
            }
            link.sign("win",t.getData().winner.toString());
        });
    }
    public void print(){
        long theTime = System.currentTimeMillis();
        data.load();
        System.out.println("Load time used:" + (System.currentTimeMillis() - theTime)+"ms.");
        data.getLeaves().forEach(le-> {
            System.out.print(le);
            System.out.println(le.sign("win"));
        });
    }
    public static void main(String[] args){
        /*long theTime = System.currentTimeMillis();
        TictactoeData td = new TictactoeData();
        long time1 = System.currentTimeMillis();
        td.filldata();
        System.out.println("Fill time used:" + (System.currentTimeMillis() - time1) + "ms.");
        long time2 = System.currentTimeMillis();
        td.print();
        System.out.println("Print time used:" + (System.currentTimeMillis() - time2) + "ms.");
        System.out.println("Total time used:" + (System.currentTimeMillis() - theTime) + "ms.");*/
        /*long time = System.currentTimeMillis();
        TictactoeData td = new TictactoeData();
        td.filldata();
        System.out.println("Fill time used:" + (System.currentTimeMillis() - time) + "ms.");
        
        long time2 = System.currentTimeMillis();
        td.data.write("TictactoeData.ser");
        System.out.println("Write time used:" + (System.currentTimeMillis() - time2) + "ms.");
    
        long theTime = System.currentTimeMillis();
        Tree<Pair> data = Tree.read("TictactoeData.ser");
        System.out.println("Read time used:" + (System.currentTimeMillis() - theTime) + "ms.");*/
        /*Tree<Pair> t = new Tree<>(new Pair(1,2));
        //t.sub(new Pair(2,3));
        t.write("aaa.ser");
        Tree<Pair> tp = Tree.read("aaa.ser");
        
        Link<Pair> li = new Link<Pair>(new Pair(1,1));
        li = li.sub(new Pair(2,2));
        li.write("li");
        Link<Pair> lim = Link.read("li");
        */
    
    }
}
