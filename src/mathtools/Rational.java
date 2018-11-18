package mathtools;

/**
 * Created by CowardlyLion on 2018/9/23 13:20
 */
public class Rational {
    int p;  //分子
    int q;   //分母

    public Rational() { }
    public Rational(int p, int q) {
        this.p = p;
        this.q = q;
    }

    public static void main(String[] args) {
        Rational a = new Rational();
        a.p = 1;
        a.p = 3;
        Rational b = new Rational(1, 3);
    }

    public int getP() {
        return p;
    }
    public void setP(int p) {
        this.p = p;
    }
    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public Rational add(Rational b) {
        p = p * b.q + q * b.p;
        q = q * b.q;
        return this;
    }
}
