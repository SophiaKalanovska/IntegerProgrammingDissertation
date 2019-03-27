package Model.Inequalities.RandomInequalities;

import Model.Inequalities.DecisionVariable;
import Model.Inequalities.Inequality;
import java.util.ArrayList;
import java.util.Random;

public class RandomInequalitiesGenerator {
    private RandomVariables upperLower;
    private Random random;

    public RandomInequalitiesGenerator() {
        this.upperLower = new RandomVariables();
        random = new Random();
    }

    public DecisionVariable generateNode() {
        DecisionVariable decisionVariable = new DecisionVariable();
        decisionVariable.setVariable(upperLower.getIndex((int)Math.random()*upperLower.getSize()));
        return  decisionVariable;
    }

    public Inequality generateInequalities(ArrayList<DecisionVariable> decisionVariables) {
        int max = 2;
        int min = 1;
        int variableIndexFirst = (int) (Math.random() * decisionVariables.size());
        int variableIndexSecond = (int) (Math.random() * decisionVariables.size());
        Inequality newIneqiality = new Inequality();
        DecisionVariable first = decisionVariables.get(variableIndexFirst);
        first.setWeight(random.nextInt(max - min + 1) + min);
        DecisionVariable second = decisionVariables.get(variableIndexSecond);
        second.setWeight(1);
        newIneqiality.setFirstDecisionVariable(first);
        newIneqiality.setSecondDecisionVariable(second);
        newIneqiality.setExpression("" + first.getWeight() + first.toString() + "<=" + second.toString());
        return newIneqiality;
    }

    public Inequality generateInequalityForNode(DecisionVariable decisionVariable) {
        Inequality newIneqiality = new Inequality();
        newIneqiality.setFirstDecisionVariable(decisionVariable);
        newIneqiality.setExpression("" + decisionVariable.toString() + ">=" + 0);
        return newIneqiality;
    }
}
