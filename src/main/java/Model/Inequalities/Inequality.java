package Model.Inequalities;
import java.io.Serializable;
import java.util.Observable;

public class Inequality  extends Observable implements Serializable {

    private DecisionVariable firstDecisionVariable;
    private DecisionVariable secondDecisionVariable;
    private String expression;
    private int bound;

    public Inequality() {
        firstDecisionVariable = new DecisionVariable();
        secondDecisionVariable = new DecisionVariable();
    }

    public String getExpreission() {
        return this.expression;
    }

    public String setExpression(String expression) {
        return this.expression = expression;
    }


    public String toString(){
        return expression;
    }


    public String getFirstDecisionVariableValue() {
        return firstDecisionVariable.toString();
    }

    public void setFirstDecisionVariable(DecisionVariable firstDecisionVariable) {
        this.firstDecisionVariable = firstDecisionVariable;
    }


    public String getSecondDecisionVariableValue() {
        return secondDecisionVariable.toString();
    }

    public void setSecondDecisionVariable(DecisionVariable secondDecisionVariable) {
        this.secondDecisionVariable = secondDecisionVariable;
    }

    public DecisionVariable getFirstDecisionVariable() {
        return firstDecisionVariable;
    }


    public DecisionVariable getSecondDecisionVariable() {
        return secondDecisionVariable;
    }

}
