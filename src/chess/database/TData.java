package chess.database;

import chess.Tictactoe;
import mathtools.Pair;
import mathtools.graph.Link;
import mathtools.graph.Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * By Hrd at 17-2-25 下午1:17.
 */
public class TData implements Serializable{
    public ArrayList<int[]> d = new ArrayList<>();
    public boolean write(String path){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(this);
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static <T extends Serializable> ArrayList<int[]> read(String path){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))){
            return ((TData)in.readObject()).d;
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args){
        /*long loadTime = System.currentTimeMillis();
        TData t = new TData();
        Permutation.recursive(0,Permutation.naturalPermutation(9),new int[9],(index,ints) -> {
            int k = ints[index];
            for(int i = 0; i<index; i++){
                if(ints[i]==k) return false;
            }
            
            Tictactoe tic = new Tictactoe(null);
            for(int i = 0; i<=index; i++){
                tic.put(Tictactoe.solve(ints[i] + 1));
            }
            if(tic.isEnd()){
                t.d.add(Arrays.copyOf(ints,index+1));
                return false;
            }
            return true;
        });
        
        t.write("TData1.ser");
        System.out.println("Load Time used:" + (System.currentTimeMillis() - loadTime) + "ms.");
        */
        /*ArrayList<int[]> a = TData.read("D:\\data\\TData1.ser");
        Link<Integer> t = new Link<>(-1);
        a.forEach(i->{
            Link<Integer> li = t.sub(i[0]);
            for(int n=1;n<i.length;n++){
                li = li.sub(i[n]);
            }
        });
        t.write("D:\\data\\TLink.ser");*/
        /*Link<Integer> tp = Link.read("D:\\data\\TLink.ser");
        Tree<Integer> trcp = new Tree<>(tp);
        trcp.mark(trcp.getLeaves(),"winner",a->{
            if(a.isEnd()){
                Tictactoe t = new Tictactoe(null);
                ArrayList<Link<Integer>> path = a.path();
                for(int i = 1; i<path.size(); i++){
                    t.put(path.get(i).getObject());
                }
                if(t.getData().winner==null) return "Tie";
                else return t.getData().winner.toString();
            } else {
                if(a.path().size()%2==0){
                    if(a.getSub().stream().anyMatch(i-> "White".equals(i.sign("winner")))) return "White";
                    else if(a.getSub().stream().anyMatch(i-> "Tie".equals(i.sign("winner")))) return "Tie";
                    else return "Black";
                } else {
                    if(a.getSub().stream().anyMatch(i-> "Black".equals(i.sign("winner")))) return "Black";
                    else if(a.getSub().stream().anyMatch(i-> "Tie".equals(i.sign("winner")))) return "Tie";
                    else return "White";
                }
            }
        });
        trcp.write("D:\\data\\TTreeMark.ser");*/
        Tree<Integer> trcpm = Tree.read("D:\\data\\TTreeMark.ser");
        Link<Integer> lin = trcpm.getRoot();
        Tictactoe t = new Tictactoe(null);
        Scanner s = new Scanner(System.in);
        while(true){
            System.out.println("Let's put.");
            t.put(s.nextInt());
            t.print();
            Link<Integer> li = lin;
            ArrayList<Pair> order = t.getData().order;
            for(int i = 0; i<order.size(); i++){
                li = li.sub(resolve(order.get(i)));
            }
            System.out.println(li.path().size()+"aa"+(li.path().size()%2));
            System.out.println(sss(li));
            System.out.println(li.sub(0).sign("winner")+"#"+li.sub(1).sign("winner")+"#"+li.sub(2).sign("winner"));
            System.out.println(li.sub(3).sign("winner")+"#"+li.sub(4).sign("winner")+"#"+li.sub(5).sign("winner"));
            System.out.println(li.sub(6).sign("winner")+"#"+li.sub(7).sign("winner")+"#"+li.sub(8).sign("winner"));
            System.out.println("###########################################");
        }
    }
    public static String sss(Link<Integer> li){
        if(li.path().size()%2==0){
            if(li.getSub().stream().anyMatch(i -> "White".equals(i.sign("winner")))) return "White";
            else if(li.getSub().stream().anyMatch(i -> "Tie".equals(i.sign("winner")))) return "Tie";
            else return "Black";
        } else {
            if(li.getSub().stream().anyMatch(i -> "Black".equals(i.sign("winner")))) return "Black";
            else if(li.getSub().stream().anyMatch(i -> "Tie".equals(i.sign("winner")))) return "Tie";
            else return "White";
        }
    }
    public static int resolve(Pair p){
        if(p.x==1){
            if(p.y==1){
                return 0;
            } else if(p.y==2){
                return 3;
            } else if(p.y==3){
                return 6;
            }
        } else if(p.x==2){
            if(p.y==1){
                return 1;
            } else if(p.y==2){
                return 4;
            } else if(p.y==3){
                return 7;
            }
        } else if(p.x==3){
            if(p.y==1){
                return 2;
            } else if(p.y==2){
                return 5;
            } else if(p.y==3){
                return 8;
            }
        }
        return -1;
    }
}
