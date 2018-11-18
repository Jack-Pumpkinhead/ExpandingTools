package mathtools.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * By Hrd at 17-1-26 下午3:53.
 */
public class Tree<T extends Serializable> implements Serializable{
    private transient Link<T> root;
    private transient Link<T> end;
    private ArrayList<Link<T>> trunks = new ArrayList<>();
    private transient HashSet<Link<T>> nodes = new HashSet<>();
    private transient HashSet<Link<T>> leaves = new HashSet<>();
    public Link<T> getRoot(){
        return root;
    }
    public Link<T> getEnd(){
        return end;
    }
    public ArrayList<Link<T>> getTrunks(){
        return (ArrayList<Link<T>>)trunks.clone();
    }
    public HashSet<Link<T>> getNodes(){
        return (HashSet<Link<T>>)nodes.clone();
    }
    public HashSet<Link<T>> getLeaves(){
        return (HashSet<Link<T>>)leaves.clone();
    }
    
    public boolean write(String path){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(this);
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static <T extends Serializable> Tree<T> read(String path){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))){
            Tree<T> result = (Tree<T>)in.readObject();
            result.root = result.trunks.get(0);
            result.end = result.trunks.get(result.trunks.size() - 1);
            result.nodes = new HashSet<>();
            result.leaves = new HashSet<>();
            result.load();
            return result;
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Tree(T root){
        this.root = end = new Link<>(root);
        trunks.add(end);
        load();
    }
    public Tree(Link<T> end){
        this.end = end;
        this.trunks = end.path();
        this.root = trunks.get(0);
        load();
    }
    public Tree<T> setTrunk(Link<T> end){
        ArrayList<Link<T>> trunks = end.path();
        if(root != trunks.get(0)) throw new IllegalArgumentException();
        this.trunks = trunks;
        this.end = end;
        load();
        return this;
    }
    public Tree<T> sub(T object){
        end = end.sub(object);
        trunks.add(end);
        nodes.add(end);
        return this;
    }
    public void load(){
        nodes.clear();
        leaves.clear();
        nodes.add(root);
        load(root);
        leaves.addAll(nodes.stream().filter(Link::isEnd).collect(Collectors.toSet()));
    }
    private void load(Link<T> leaf){
        HashSet<Link<T>> links = leaf.getSub();
        nodes.addAll(links);
        links.forEach(this::load);
    }
    
    public void mark(HashSet<Link<T>> source,String key,Function<Link<T>,String> f){
        HashSet<Link<T>> links = new HashSet<>();
        source.forEach(a->{
            a.sign(key,f.apply(a));
            if(a.getParent()!=null) links.add(a.getParent());
        });
        if(!links.isEmpty()) mark(links,key,f);
    }
}
