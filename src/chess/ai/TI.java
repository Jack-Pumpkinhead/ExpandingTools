package chess.ai;

import chess.Tictactoe;
import mathtools.Pair;
import mathtools.graph.Link;
import mathtools.graph.Tree;

import java.util.ArrayList;
import java.util.Optional;

import static chess.database.TData.resolve;

/**
 * By Hrd at 17-2-12 下午5:49.
 */
public class TI {
    static Tree<Integer> trcpm = Tree.read("D:\\data\\TTreeMark.ser");
    static Link<Integer> lin = trcpm.getRoot();
    public static int compute(Tictactoe tic){
        Link<Integer> li = lin;
        ArrayList<Pair> order = tic.getData().order;
        for(int i = 0; i<order.size(); i++){
            li = li.sub(resolve(order.get(i)));
        }
        if(li.getSub().isEmpty()) return -1;
        boolean isBlack = li.path().size()%2==0;
        Optional<Link<Integer>> link = li.getSub().stream().filter(i -> (isBlack?"White":"Black").equals(i.sign("winner"))).unordered().findFirst();
        if(link.isPresent()) return link.get().getObject();
        link = li.getSub().stream().filter(i -> "Tie".equals(i.sign("winner"))).unordered().findFirst();
        if(link.isPresent()) return link.get().getObject();
        return li.getSub().stream().unordered().findFirst().get().getObject();
    }
}
