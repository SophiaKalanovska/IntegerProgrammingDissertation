
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;



public class Inequality  extends Observable implements Serializable {

//    private String rightSide;
    private String firstUnknownVariable;
    private int firstWeight;
    private String secondUnknownVariable;
    private int secondWeight;
    private String sign;
//    private String leftSide;
    private String expression;
    ArrayList<String> parsedExpression = new ArrayList<String>();

    public Inequality (){
        firstUnknownVariable = null;
        firstWeight = 1 ;
        secondUnknownVariable = null;
        secondWeight = 1 ;


    }

//    public Inequality (ArrayList<String> parsedExpression) {
//        this.parsedExpression = parsedExpression;
//        this.rightSide = parsedExpression.get(0);
//        this.sign = parsedExpression.get(1);
//        this.leftSide = parsedExpression.get(2);
//        this.expression = rightSide + sign + leftSide;
//    }
//
    public String getExpreission() {
        return this.expression;
    }

    public String setExpreission( String expression) {
        return this.expression = expression;
    }
//
    public ArrayList<String> getParsedExpression() {
        return this.parsedExpression;
    }
//
//    public void changeExpression(String expressionChanged){
//        expression = expressionChanged;
//    }

//
    public String toString(){
        return expression;
    }


    public void tryUpdate() {
        this.setChanged();
        this.notifyObservers();
    }

    public String getFirstUnknownVariable() {
        return firstUnknownVariable;
    }

    public void setFirstUnknownVariable(String firstUnknownVariable) {
        this.firstUnknownVariable = firstUnknownVariable;
    }

    public int getFirstWeight() {
        return firstWeight;
    }

    public String getSecondUnknownVariable() {
        return secondUnknownVariable;
    }

    public void setSecondUnknownVariable(String secondUnknownVariable) {
        this.secondUnknownVariable = secondUnknownVariable;
    }

    public int getSecondWeight() {
        return secondWeight;
    }

    public void setSecondWeight(int secondWeight) {
        this.secondWeight = secondWeight;
    }


    public void setFirstdWeight(int firstWeight) {
        this.firstWeight= firstWeight;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getSign() {
        return sign;
    }
}
