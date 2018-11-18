package mathtools.tree;

import java.util.ArrayList;

/**
 * Created by CowardlyLion on 2018/8/21 17:54
 */
public class Node <T>{
    T object;
    Node<T> header;
    ArrayList<Node<T>> follower = new ArrayList<>();

    public Node(T object) {
        this.object = object;
    }
    public Node(T object, Node<T> header) {
        this.object = object;
        this.header = header;
    }

    @Override
    public String toString() {
        String result = object == null ? "" : object.toString();
        if(isPrimitive()) return result;

        result += "[";
        int size = follower.size() - 1;
        for (int i = 0; i < size; i++) {
            result += follower.get(i).toString() + ",";
        }
        result += follower.get(size);
        result += "]";
        return result;
    }

    public boolean isPrimitive() {
        return follower.size() == 0;
    }

    public boolean isHead() {
        return header == null;
    }

    public Node<T> up() {
        if(header==null) return this;
        return header;
    }

    public Node<T> down(int i) {
        return follower.get(i);
    }
}
