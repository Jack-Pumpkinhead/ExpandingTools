package mathtools;

import java.util.ArrayList;

/**
 * By Hrd at 17-2-4 下午10:08.
 */
public class TestGroup extends Group{
    public TestGroup(int[][] af){
        super(af);
    }
    public static ArrayList<int[][]> create(int order){
        ArrayList<int[][]> result = new ArrayList<>();
        Permutation.recursive(0,0,Permutation.naturalPermutation(order),new int[order][order],result,(i,j)->{
            int s = j[i.x][i.y];
            if(i.x==0)return s==i.y;
            else if(i.y==0) return s==i.x;
            else {
                for(int m=i.x-1;m>=0;m--){
                    if(j[m][i.y]==s)return false;
                }
                for(int n=i.y-1;n>=0;n--){
                    if(j[i.x][n]==s) return false;
                }
                if(i.x==order-1&&i.y==order-1) return new TestGroup(j).composition();
                return true;
            }
        });
        return result;
    }
    public static void main(String[] args){
        /*System.out.println("start:");
        ArrayList<int[]> p =Permutation.fullPermutation(4);
        int ps = p.size();
        ArrayList<int[]> pt = Permutation.combination(24,4);
        int pts = pt.size();
        int[][] a = new int[4][4];
        for(int i=0;i<pts;i++){
            int[] aa = pt.get(i);
            for(int j=0;j<4;j++){
                a[j] = p.get(aa[j]);
            }
            if(new TestGroup(a).jiehe()){
                System.out.println(Arrays.deepToString(a));
                System.out.println("____");
            }
        }*/
    
        for(int i = 1; i<11; i++){
            System.out.println(create(i).size());
        }
        /*int[] range = new int[order-1];
        for(int i = 1; i<order; i++){
            range[i-1] = i;
        }
        ArrayList<int[]> c = Permutation.combination(range,2);*/
        
        
    }
    public static int[][] change(int[][] source,int a,int b){
        int[][] s = Helper.clone(source);
        int o = s.length;
        for(int i=0;i<o;i++){
            for(int j = 0; j<o; j++){
                int k = s[i][j];
                s[i][j] = k==a?b: k==b?a: k;
            }
        }
        for(int i=0;i<o;i++){
            int j = s[a][i];
            s[a][i] = s[b][i];
            s[b][i] = j;
            j = s[i][a];
            s[i][a] = s[i][b];
            s[i][b] = j;
        }
        return s;
    }
}
