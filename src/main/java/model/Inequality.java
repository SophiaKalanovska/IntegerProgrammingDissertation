
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;



public class Inequality  extends Observable implements Serializable {

    private final String rightSide;
    private final String sign;
    private final String leftSide;
    private String expreission;
    ArrayList<String> parsedExpression;

    public Inequality (ArrayList<String> parsedExpression) {
        this.parsedExpression = parsedExpression;
        this.rightSide = parsedExpression.get(0);
        this.sign = parsedExpression.get(1);
        this.leftSide = parsedExpression.get(2);
        this.expreission = rightSide + sign + leftSide;
    }

    public String getexpreission() {
        return this.expreission;
    }

    public ArrayList<String> getParsedExpression() {
        return this.parsedExpression;
    }


    public void tryUpdate() {
        this.setChanged();
        this.notifyObservers();
    }
}
