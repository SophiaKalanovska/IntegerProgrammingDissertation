
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;



public class Inequality  extends Observable implements Serializable {

    private final String rightSide;
    private final String sign;
    private final String leftSide;
    private String expression;
    ArrayList<String> parsedExpression;

    public Inequality (ArrayList<String> parsedExpression) {
        this.parsedExpression = parsedExpression;
        this.rightSide = parsedExpression.get(0);
        this.sign = parsedExpression.get(1);
        this.leftSide = parsedExpression.get(2);
        this.expression = rightSide + sign + leftSide;
    }

    public String getExpreission() {
        return this.expression;
    }

    public ArrayList<String> getParsedExpression() {
        return this.parsedExpression;
    }

    public void changeExpression(String expressionChanged){
        expression = expressionChanged;
    }


    public String toString(){
        return expression;
    }


    public void tryUpdate() {
        this.setChanged();
        this.notifyObservers();
    }
}
