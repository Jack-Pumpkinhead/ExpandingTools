package mathtools.tree;

import frege.run.Kind;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by CowardlyLion on 2018/8/21 17:55
 */
public class Tree <T> {
    ArrayList<Node<T>> headers = new ArrayList<>();
    Node<T> current;
    @Override
    public String toString() {
        String result = "[";
        int size = headers.size() - 1;
        for (int i = 0; i < size; i++) {
            result += headers.get(i).toString() + ",";
        }
        result += headers.get(size);
        result += "]";
        return result;
    }

    public Tree(Node<T>... headers) {
        this.headers.addAll(Arrays.asList(headers));
    }

    public Node<T> setCurrent(int i) {
        if(headers.size()==0) return null;
        return headers.get(i);
    }

    public Node<T> getCurrent() {
        return current;
    }

    public Tree<T> add(Node<T> header) {
        headers.add(header);
        return this;
    }


    public static void main(String[] args) {
        Tree<Integer> integerTree = new Tree<>(new Node<Integer>(1),new Node<Integer>(2));
        System.out.println(integerTree);

    }
}
