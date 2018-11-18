package mathtools.matrix;

import mathtools.DoubleVector;

/**
 * By Hrd at 16-11-13 下午6:00.
 */
public class test {
    public static void main(String[] args) {
        int order = 10;
        
        DoubleVector[][] matrix = new DoubleVector[order+1][order+1];
        for(int j = 0 ; j<order+1 ; j++){
            int k=j;
            matrix[order][j] = new DoubleVector(order+1,a->Math.pow(k,a));
        }
        for(int i = order-1 ; i>=0 ; i--){
            for(int j = order-i ; j<order + 1 ; j++){
                matrix[i][j] = matrix[i+1][j].subtract(matrix[i+1][j-1]);
            }
        }
        for(int j = 0 ; j<order ; j++){
            matrix[0][j] = matrix[0][order].copy();
        }
        for(int i = 1 ; i<order ; i++){
            for(int j = order-i ; j>=0 ; j--){
                matrix[i][j] = matrix[i][j+1].subtract(matrix[i-1][j+1]);
            }
        }
        
        for(int i=0;i<order+1;i++){
            for(int j = 0 ; j<order+1 ; j++){
                matrix[i][j].print();
            }
            System.out.println();
        }
    }
}
