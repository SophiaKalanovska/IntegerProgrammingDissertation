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


    /**
     * calls the generate() in RandomVariables and returns the decisionVariable
     */
    public DecisionVariable generateDecisionVariable() {
        DecisionVariable decisionVariable = new DecisionVariable();
        decisionVariable.setVariable(upperLower.generate());
        return decisionVariable;
    }

    /**
     * takes two random decision variables from the provided list and
     * generates a random weight for the first one (k / n )
     * assigns a string representation of the variable decisionVar <= decisionVar
     */
    public Inequality generateInequality(ArrayList<DecisionVariable> decisionVariables) {
        int max = 1;
        int min = 1;
        int variableIndexFirst = (int) (Math.random() * decisionVariables.size());
        int variableIndexSecond = (int) (Math.random() * decisionVariables.size());
        Inequality newIneqiality = new Inequality();
        DecisionVariable first = decisionVariables.get(variableIndexFirst);
        first.setWeight(random.nextInt(max - min + 1) + min);
        DecisionVariable second = decisionVariables.get(variableIndexSecond);
        second.setWeight(1);
        newIneqiality.setLeftDecisionVariable(first);
        newIneqiality.setRightDecisionVariable(second);
        newIneqiality.setExpression("" + first.getWeight() + first.toString() + "<=" + second.toString());
        return newIneqiality;
    }

    /**
     * takes a random decision variable from the provided list and
     * generates a random bound it then randomly chooses
     * whether to generate a upper or lower bound and assignes the bound to the variable
     */
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

    /**
     * takes a decision variables an generates a bound inequality,
     * all decision variables are bound to be more than or equal to 0
     * the string assignment is for the list presentation
     */
    public Inequality generateInequalityForNode(DecisionVariable decisionVariable) {
        Inequality newIneqiality = new Inequality();
        newIneqiality.setLeftDecisionVariable(decisionVariable);
        newIneqiality.setExpression("" + decisionVariable.toString() + ">=" + 0);
        return newIneqiality;
    }
}
