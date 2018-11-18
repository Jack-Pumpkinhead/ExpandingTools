package mathtools.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * By Hrd at 17-1-26 下午3:35.
 */
public class Link <T extends Serializable>  implements Serializable{
    private Link<T> parent;
    private T object;
    private transient HashSet<Link<T>> sub = new HashSet<>();
    private HashMap<String,String> signs = new HashMap<>();
    
    public Link<T> getParent(){
        return parent;
    }
    public HashSet<Link<T>> getSub(){
        return sub;
    }
    public T getObject(){
        return object;
    }
    public String sign(String key,String value){
        return signs.put(key,value);
    }
    public String sign(String key){
        return signs.get(key);
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
    public static <T extends Serializable> Link<T> read(String path){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))){
            return (Link<T>)in.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Link(T object){
        this(null,object);
    }
    private Link(Link<T> parent,T object){
        if(object==null) throw new IllegalArgumentException();
        this.parent = parent;
        this.object = object;
    }
    @Override
    public String toString(){
        if(parent==null) return sub.isEmpty() ? "[" + object + "]" : "[" + object;
        if(sub.isEmpty()) return parent+","+object+"]";
        return parent+","+object;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        
        Link<?> link = (Link<?>)o;
        
        if(parent!=null ? !parent.equals(link.parent) : link.parent!=null) return false;
        return object.equals(link.object);
    
    }
    @Override
    public int hashCode(){
        int result = parent!=null ? parent.hashCode() : 0;
        result = 31*result + (object!=null?object.hashCode():0);
        return result;
    }
    
    public boolean isEnd(){
        return sub.isEmpty();
    }
    public Link<T> sub(T object){
        Link<T> result = new Link<>(this,object);
        if(this.sub.add(result)) return result;
        return this.sub.stream().filter(result::equals).findFirst().get();
    }
    public ArrayList<Link<T>> path(){
        if(parent==null){
            ArrayList<Link<T>> result = new ArrayList<>();
            result.add(this);
            return result;
        }
        ArrayList<Link<T>> result = parent.path();
        result.add(this);
        return result;
    }
    
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(sub);
    }
    
    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        ois.defaultReadObject();
        sub = (HashSet<Link<T>>)ois.readObject();
    }
}
