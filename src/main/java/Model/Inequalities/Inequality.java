package Model.Inequalities;
import java.io.Serializable;
import java.util.Observable;

public class Inequality  extends Observable implements Serializable {

    private DecisionVariable leftDecisionVariable;
    private DecisionVariable rightDecisionVariable;
    private String expression;


    public Inequality() {
        leftDecisionVariable = new DecisionVariable();
        rightDecisionVariable = new DecisionVariable();
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String toString(){
        return expression;
    }


    public String getFirstDecisionVariableValue() {
        return leftDecisionVariable.toString();
    }

    public void setLeftDecisionVariable(DecisionVariable leftDecisionVariable) {
        this.leftDecisionVariable = leftDecisionVariable;
    }


    public String getSecondDecisionVariableValue() {
        return rightDecisionVariable.toString();
    }

    public void setRightDecisionVariable(DecisionVariable rightDecisionVariable) {
        this.rightDecisionVariable = rightDecisionVariable;
    }

    public DecisionVariable getLeftDecisionVariable() {
        return leftDecisionVariable;
    }


    public DecisionVariable getRightDecisionVariable() {
        return rightDecisionVariable;
    }

}
