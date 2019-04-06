package Model.Inequalities.RandomInequalities;

import Model.Inequalities.DecisionVariable;
import Model.Inequalities.Inequality;
import java.util.ArrayList;
import java.util.Random;

public class RandomInequalitiesGenerator {
    private RandomVariables upperLower ;
    private Random random;

    public RandomInequalitiesGenerator() {
        upperLower = new RandomVariables();
        random = new Random();
    }

    public DecisionVariable generateDecisionVariable() {
        DecisionVariable decisionVariable = new DecisionVariable();
        decisionVariable.setVariable(upperLower.generate());
        return decisionVariable;
    }

    public Inequality generateInequalities(ArrayList<DecisionVariable> decisionVariables) {
        int max = 1;
        int min = 1;
        int variableIndexFirst = (int) (Math.random() * decisionVariables.size());
        int variableIndexSecond = (int) (Math.random() * decisionVariables.size());
        Inequality newIneqiality = new Inequality();
        DecisionVariable first = decisionVariables.get(variableIndexFirst);
        first.setWeight(random.nextInt(max - min + 1) + min);
        first.setWeight(1);
        DecisionVariable second = decisionVariables.get(variableIndexSecond);
        second.setWeight(1);
        newIneqiality.setLeftDecisionVariable(first);
        newIneqiality.setRightDecisionVariable(second);
        newIneqiality.setExpression("" + first.getWeight() + first.toString() + "<=" + second.toString());
        return newIneqiality;
    }


    public Inequality generateBound(ArrayList<DecisionVariable> decisionVariables) {
        int maxLower = 50;
        int minLower = 1;

        int maxUpper = 100;
        int minUpper = 51;
        int variableIndexFirst = (int) (Math.random() * decisionVariables.size());
        Inequality newIneqiality = new Inequality();

        DecisionVariable decisionVariable = decisionVariables.get(variableIndexFirst);
        newIneqiality.setLeftDecisionVariable(decisionVariable);
        if (random.nextBoolean()){
            int bound =  random.nextInt(maxLower - minLower + 1) + minLower;
            newIneqiality.setExpression("" + decisionVariable.toString() + ">=" + bound);
            decisionVariable.setLowerBound((double)bound);
        }else{
            int bound =  random.nextInt(maxUpper - minUpper + 1) + minUpper;
            newIneqiality.setExpression("" + decisionVariable.toString() + "<=" + bound);
            decisionVariable.setUpperBound((double)bound);
        }
        return newIneqiality;
    }

    public Inequality generateInequalityForNode(DecisionVariable decisionVariable) {
        Inequality newIneqiality = new Inequality();
        newIneqiality.setLeftDecisionVariable(decisionVariable);
        newIneqiality.setExpression("" + decisionVariable.toString() + ">=" + 0);
        return newIneqiality;
    }
}
