package mathtools;

/**
 * By Hrd at 17-2-4 下午9:58.
 */
public class Group {
    int size;
    public int size(){
        return size;
    }
    int[][] caylayTable;
    
    public Group(int[][] caylayTable){
        size = caylayTable.length;
        this.caylayTable = caylayTable;
    }
    
    public int f(int a,int b){
        return caylayTable[a][b];
    }
    public boolean composition(){
        for(int i=0;i<size;i++){
            for(int j = 0; j<size; j++){
                for(int k = 0; k<size; k++){
                    if(f(f(i,j),k)!=f(i,f(j,k))){
                        //System.out.println(""+i+" "+j+" "+k);
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean communtative(){
        for(int i=0;i<size;i++){
            for(int j = i+1; j<size; j++){
                if(f(i,j)!=f(j,i)) return false;
            }
        }
        return true;
    }
}
