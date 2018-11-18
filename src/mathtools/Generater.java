package mathtools;

import mathtools.matrix.CountableMatrix;
import mathtools.matrix.DoubleElement;
import mathtools.matrix.IntegerElement;
import mathtools.numberfield.Algebra;
import mathtools.numberfield.Divide;
import mathtools.numberfield.Integer;
import mathtools.numberfield.NumberElement;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * By Hrd at 16-11-12 下午8:02.
 */
public class Generater {
    private Generater() {}
    public static CountableMatrix<IntegerElement> toIntegerMatrix(int[][] value) {
        int rows = value.length;
        int columns = Arrays.stream(value).mapToInt(i->i.length).max().getAsInt();
        int[][] squareValue = new int[rows][columns];
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<value[i].length ; j++){
                squareValue[i][j] = value[i][j];
            }
        }
        return new CountableMatrix<IntegerElement>(rows,columns,(i,j)->new IntegerElement(squareValue[i][j]));
    }
    public static CountableMatrix<DoubleElement> toDoubleMatrix(double[][] value) {
        int rows = value.length;
        int columns = Arrays.stream(value).mapToInt(i->i.length).max().getAsInt();
        double[][] squareValue = new double[rows][columns];
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<value[i].length ; j++){
                squareValue[i][j] = value[i][j];
            }
        }
        return new CountableMatrix<DoubleElement>(rows,columns,(i,j)->new DoubleElement(squareValue[i][j]));
    }
    public static Vector<IntegerElement> toIntegerVector(int[] value) {
        return new Vector<IntegerElement>(value.length,i->new IntegerElement(value[i]));
    }
    public static Vector<DoubleElement> toDoubleVector(double[] value) {
        return new Vector<DoubleElement>(value.length,i->new DoubleElement(value[i]));
    }
    /*public static NumberElement resolveExpression(String expression) {
        String e = expression.trim();
        
        if(!e.contains("(") && !e.contains(")")) {
            return resolveSingleExpression(e);
        }
        String ss = e;
        StringBuffer s = new StringBuffer();
        int start = 0;
        int startFlag = 0;
        int startCount = 0;
        int endCount = 0;
        int bracketIndex = 0;
        for(int i = 0 ; i<e.length() ; i++){
            char c = e.charAt(i);
            if(c == '('){
                startFlag++;
                startCount++;
                if(startFlag == 1) {
                    start = i+1;
                }
            }
            if(c == ')'){
                startFlag--;
                endCount++;
                if(startFlag == 0) {
                    s.append(e.substring(start,i)).append(" ");
                    ss = ss.replace(e.substring(start-1,i+1),"#"+bracketIndex+"$");
                    bracketIndex++;
                }
                if(startFlag<0) throw new IllegalArgumentException();
            }
        }
        if(startCount != endCount) throw new IllegalArgumentException();
        String[] sub = s.toString().split(" ");
        NumberElement[] numbers = new NumberElement[sub.length];
        for(int i = 0 ; i<sub.length ; i++){
            numbers[i] = resolveExpression(sub[i]);
        }
        return resolveSingleExpression(ss,numbers);
    }
    public static NumberElement resolveSingleExpression(String expression,NumberElement... numbers) {
        String e = expression.trim();
        if(e.contains("(")) throw new IllegalArgumentException();
        String[] s = e.split("\\+|-|\\*|/|\\^");
        if(s.length == 1) {
            return replaceNumberToString(s[0],numbers);
        } else if(s.length == 2) {
            if(e.contains("+")) {
                return replaceNumberToString(s[0],numbers).add(replaceNumberToString(s[1],numbers));
            } else if(e.contains("-")) {
                return replaceNumberToString(s[0],numbers).subtract(replaceNumberToString(s[1],numbers));
            } else if(e.contains("*")) {
                return replaceNumberToString(s[0],numbers).multiply(replaceNumberToString(s[1],numbers));
            } else if(e.contains("/")) {
                return replaceNumberToString(s[0],numbers).divide(replaceNumberToString(s[1],numbers));
            } else {
                return replaceNumberToString(s[0],numbers).power(replaceNumberToString(s[1],numbers));
            }
        } else {
            if(e.contains("+")) {
                String[] strings = e.split("\\+",2);
                return resolveSingleExpression(strings[0],numbers).add(resolveSingleExpression(strings[1],numbers));
            } else if(e.contains("-")) {
                String[] strings = e.split("-",2);
                return resolveSingleExpression(strings[0],numbers).subtract(resolveSingleExpression(strings[1],numbers));
            } else if(e.contains("*")) {
                String[] strings = e.split("\\*",2);
                return resolveSingleExpression(strings[0],numbers).multiply(resolveSingleExpression(strings[1],numbers));
            } else if(e.contains("/")) {
                String[] strings = e.split("/",2);
                return resolveSingleExpression(strings[0],numbers).divide(resolveSingleExpression(strings[1],numbers));
            } else {
                String[] strings = e.split("^",2);
                return resolveSingleExpression(strings[0],numbers).power(resolveSingleExpression(strings[1],numbers));
            }
        }
    }
    private static NumberElement replaceNumberToString(String expression,NumberElement... numbers) {
        if(!expression.contains("#") && !expression.contains("$")||numbers.length==0) return new Divide(new BigDecimal(expression));
        if(expression.charAt(0)=='#'&&expression.charAt(expression.length()-1)=='$'){
            String index = expression.substring(1,expression.length()-1);
            if(!index.contains("#") && !index.contains("$")) {
                return numbers[Integer.valueOf(index)];
            }
        }
        StringBuffer exp = new StringBuffer().append(expression.charAt(0));
        for(int i = 1 ; i<expression.length() ; i++){
            if(expression.charAt(i) == '#') {
                if(expression.charAt(i - 1) == '$') {
                    exp.append("#");
                } else exp.append("*#");
            } else if(expression.charAt(i) == '$') {
                exp.append("$*");
            } else exp.append(expression.charAt(i));
        }
        if(expression.charAt(expression.length() - 1) == '$') {
            exp.deleteCharAt(exp.length()-1);
        }
        return resolveSingleExpression(exp.toString(),numbers);
    }*/
    private static int i = 0;
    private static Pattern resolvebracket = Pattern.compile("\\(([^()]*)\\)");
    private static Pattern resolveoperator1 = Pattern.compile("(.*)([+\\-])(.*)(?=[+\\-])?");
    private static Pattern resolveoperator2 = Pattern.compile("(.*)([*/])(.*)(?=[*/])?");
    private static Pattern resolveoperator3 = Pattern.compile("(.*)(\\^)(.*)(?=\\^)?");
    private static Pattern valueResolver = Pattern.compile("[a-zA-z_]+");
    public static NumberElement resolve(String expression) {
        Matcher bracket = resolvebracket.matcher(expression);
        if(bracket.find()){
            NumberElement number = resolve(bracket.group(1));
            String index = "_" + Helper.intToChar(i++) + "_";
            Algebra.create(index,number);
            return resolve(bracket.replaceFirst(index));
        } else {
            return resolve2(expression);
        }
    }
    public static NumberElement resolve2(String expression) {
        Matcher add = resolveoperator1.matcher(expression);
        if(add.find()) {
            NumberElement number = null;
            switch(add.group(2)){
                case "+":number = resolve2(add.group(1)).add(resolve2(add.group(3))); break;
                case "-":number = resolve2(add.group(1)).subtract(resolve2(add.group(3))); break;
            }
            String index = "_" + Helper.intToChar(i++) + "_";
            Algebra.create(index,number);
            return resolve2(add.replaceFirst(index));
        }
        Matcher multiply = resolveoperator2.matcher(expression);
        if(multiply.find()) {
            NumberElement number = null;
            switch(multiply.group(2)){
                case "*":number = resolve2(multiply.group(1)).multiply(resolve2(multiply.group(3))); break;
                case "/":number = resolve2(multiply.group(1)).divide(resolve2(multiply.group(3))); break;
            }
            String index = "_" + Helper.intToChar(i++) + "_";
            Algebra.create(index,number);
            return resolve2(multiply.replaceFirst(index));
        }
        Matcher pow = resolveoperator3.matcher(expression);
        if(pow.find()) {
            NumberElement number = resolve2(pow.group(1)).power(resolve2(pow.group(3)));
            String index = "_" + Helper.intToChar(i++) + "_";
            Algebra.create(index,number);
            return resolve2(pow.replaceFirst(index));
        }
        return resolve3(expression);
    }
    public static NumberElement resolve3(String expression) {
        Set<NumberElement> value = new HashSet<>();
        Matcher name = valueResolver.matcher(expression);
        while(name.find()) {
            value.add(Algebra.create(name.group()));
        }
        String[] number = expression.split(valueResolver.toString());
        
        NumberElement result = new Integer(1);
        for(String i : number){
            if(!i.equals("")) result = result.multiply(new Divide(new BigDecimal(i)));
        }
        for(NumberElement v : value){
            result = result.multiply(v);
        }
        return result;
    }
    public static void clear(){
        while(i>0){
            Algebra.remove("_" + Helper.intToChar(--i) + "_");
        }
    }
}
