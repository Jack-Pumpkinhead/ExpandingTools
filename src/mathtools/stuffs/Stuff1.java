package mathtools.stuffs;

/**
 * Created by CowardlyLion on 2018/9/30 17:17
 */
public class Stuff1 {
    static int size = 8;

    public static int[] index = new int[size];
    static int[] list = new int[size];

    static int total = 0;
    static int count = 0;
    public static void indexing(int i) {
        for (int j = i == 0 ? 0 : index[i - 1] + 1; j < size; j++) {
            index[i] = j;
            indexing(i + 1);
        }

        int sum = 0;
        for (int i1 = 0; i1 < i; i1++) {
            sum += list[index[i1]];
        }
        if(sum%3==0&&sum%5!=0) {
            System.out.print("Sum: " + sum + "\t"+", [");
            for (int i1 = 0; i1 < i; i1++) {
                System.out.print(list[index[i1]]);
            }
            System.out.println("]");
            count++;
        }
        total++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < list.length; i++) {
            list[i] = i + 1;
        }
        indexing(0);
        System.out.println("Total count:" + total);
        System.out.println("count:" + count);
    }
}
