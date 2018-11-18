package mathtools.generator;

/**
 * Created by CowardlyLion on 2017/11/12 16:04
 */
public class Euclidean {    //Used for POSITIVE integer only.
    private Euclidean(int a, int b) {
        this.a = a;
        this.b = b;
        this.r = b;
    }
    int a;
    int b;
    int r;
    int[] k1 = new int[]{1,0};
    int[] k2 = new int[]{0,1};  //k[0]a+k[1]b=r
    public static int[] execute(int a, int b) { //[r,m,n],ma+nb=r
        Euclidean e = new Euclidean(a,b);
        return e.algorithm(a,b);
    }

    private int[] algorithm(int a,int b) {   //a=bq+r
        int r = a % b;
//        System.out.println(r+"\t"+this.r);
        if (r == 0) return new int[]{this.r, k2[0], k2[1]};

        int q = a/b;
        int[] k = new int[]{k1[0] - q * k2[0], k1[1] - q * k2[1]};
        //System.out.println("%d*%d+%d*%d=%d,\t%d",k[0],this.a,k[1],this.b,k[0]*this.a+k[1]*this.b,);
        k1 = k2;
        k2 = k;
        this.r = r;
        return algorithm(b, r);
    }
}
