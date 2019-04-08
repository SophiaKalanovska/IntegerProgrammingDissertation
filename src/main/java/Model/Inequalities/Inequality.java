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
     *  The equals method for comparing
     *  objects of this class
     */
    @Override
    public boolean equals(Object other) {
        boolean retVal = false;
        if (other == null) return false;
        if (other == this) return true;
        if (other instanceof Inequality){
            Inequality inequalitiy = (Inequality) other;
            retVal = (inequalitiy.getFirstDecisionVariableValue().equals(this.getFirstDecisionVariableValue()));
            retVal = retVal && (inequalitiy.getSecondDecisionVariableValue().equals(this.getSecondDecisionVariableValue()));
        }
        return retVal;
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
