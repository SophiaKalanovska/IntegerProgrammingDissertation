package Model.Inequalities;
import java.io.Serializable;
import java.util.Observable;

public class Inequality  extends Observable implements Serializable {

    //create two decision variables
    private DecisionVariable leftDecisionVariable;
    private DecisionVariable rightDecisionVariable;
    private String expression;


    public Inequality() {
        leftDecisionVariable = new DecisionVariable();
        rightDecisionVariable = new DecisionVariable();
    }

    /**
     * set the string representation of the inequality
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String toString(){
        return expression;
    }

    /**
     * setters of the fields
     */
    public void setLeftDecisionVariable(DecisionVariable leftDecisionVariable) {
        this.leftDecisionVariable = leftDecisionVariable;
    }
    public void setRightDecisionVariable(DecisionVariable rightDecisionVariable) {
        this.rightDecisionVariable = rightDecisionVariable;
    }


    /**
     * getters of the fields
     */
    public String getFirstDecisionVariableValue() {

        return leftDecisionVariable.toString();
    }
    public String getSecondDecisionVariableValue() {
        return rightDecisionVariable.toString();
    }
    public DecisionVariable getLeftDecisionVariable() {
        return leftDecisionVariable;
    }
    public DecisionVariable getRightDecisionVariable() {
        return rightDecisionVariable;
    }

}
