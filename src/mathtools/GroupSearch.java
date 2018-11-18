package mathtools;

/**
 * Created by CowardlyLion on 2018/11/17 18:05
 */
public class GroupSearch {
    public static void main(String[] args) {
        LatinSquare ls = new LatinSquare(8);
        Group group = new Group(ls.getSquare());
        for (int i = 0; i < 1000000000; i++) {
            ls.randomMove();
            if (group.composition()) {
                ls.print();
            }
        }
    }
}
