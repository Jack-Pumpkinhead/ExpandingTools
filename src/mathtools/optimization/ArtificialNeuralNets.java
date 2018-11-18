package mathtools.optimization;

import mathtools.Constant;
import mathtools.numberfield.NumberElement;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * By Hrd at 17-1-12 下午9:34.
 */
public class ArtificialNeuralNets {
    Function<SimpleMatrix,SimpleMatrix> f;
    Function<SimpleMatrix,SimpleMatrix> df;
    SimpleMatrix[] net;     //Column Vectors
    SimpleMatrix[] weight;
    SimpleMatrix[] delta;
    NumberElement learningRatio;
    public ArtificialNeuralNets(Function<SimpleMatrix,SimpleMatrix> f,int[] initialnet,NumberElement learningRatio,BiFunction<Integer, Integer, NumberElement> weightoperator){
        this.f = f;
        SimpleMatrix[] n = new SimpleMatrix[initialnet.length];
        for(int i=0;i<n.length;i++){
            n[i] = new SimpleMatrix(initialnet[i],1);
        }
        net = n;
        SimpleMatrix[] s = new SimpleMatrix[net.length-1];
        for(int i=0;i<s.length;i++){
            s[i] = new SimpleMatrix(net[i + 1].rows,net[i].rows,weightoperator);
        }
        weight = s;
        this.learningRatio = learningRatio;
    }
    public SimpleVector compute(SimpleVector input){
        if(net[0].rows != input.dimensions) throw new IllegalArgumentException();
        net[0] = input.toColumnMatrix().simplify();
        for(int i = 0; i<weight.length; i++){
            net[i+1] = f.apply(weight[i].multiply(net[i])).simplify();
        }
        return net[weight.length].getColumnSimpleVectors(0);
    }
    public static void main(String[] args){
        ArtificialNeuralNets a = new ArtificialNeuralNets(i->i,new int[]{3,3,4},Constant.ONE,(i,j)->Constant.ONE);
        NumberElement[] input = NumberElement.parseDivide(0,1,2);
        System.out.println(a.compute(new SimpleVector(3,i-> input[i])));
        for(int i = 0; i<a.net.length; i++){
            System.out.println(a.net[i].getColumnSimpleVectors(0));
        }
    }
}
