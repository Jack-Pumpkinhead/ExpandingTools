package mathtools.graph;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashSet;

/**
 * Created by Administrator on 17-1-27.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeTest {
    Tree<Integer> t = new Tree<>(0);
    Tree<Integer> tr = new Tree<>(t.getRoot());
    Link<Integer> lin;
    @Before
    public void setUp() throws Exception{
        System.out.println("setUp.");
        Link<Integer> root = t.getRoot();
        root.sub(1).sub(3).sub(4);
        lin=root.sub(1).sub(3).sub(4).sub(5);
        root.sub(1).sub(3).sub(6);
    }
    @Test
    public void b_setTrunk() throws Exception{
        System.out.println("setTrunk.");
        tr.setTrunk(lin);
        tr.load();
        HashSet<Link<Integer>> a = tr.getNodes();
        a.forEach(i-> System.out.print(i.getObject()+" "));
        System.out.println("\n------trace------------");
        tr.getEnd().path().forEach(i-> System.out.print(i.getObject()+" "));
    }
    @Test
    public void a_sub() throws Exception{
        System.out.println("sub.");
        t.sub(10).sub(30).sub(40);
        t.sub(1).sub(3).sub(4).sub(5);
        t.load();
        HashSet<Link<Integer>> a = t.getNodes();
        a.forEach(i-> System.out.print(i.getObject()+" "));
        System.out.println("\n------trace------------");
        t.getEnd().path().forEach(i-> System.out.print(i.getObject()+" "));
    }
    
}