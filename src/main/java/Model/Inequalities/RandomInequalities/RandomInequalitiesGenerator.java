package Model.Inequalities.RandomInequalities;

import Controller.GraphController;
import Model.Inequalities.DecisionVariable;
import Model.Inequalities.Inequality;
import Model.SCC.SCCAlgorithm;

import java.util.ArrayList;
import java.util.Random;

public class RandomInequalitiesGenerator {
    int numberOfNodesNeeded = 0;
//    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
////    private static final String ALPHA = "LED";
//    private StringBuilder output = new StringBuilder();
//    private static String notUsed = "abcdefghijklmnopqrstuvwxyz";
////    private static String notUsed = "cklm";
    private GraphController graphController;
    private RandomVariables upperLower;
    private Random random;
    private SCCAlgorithm algorithm;



    public RandomInequalitiesGenerator(GraphController graphController) {
        this.graphController = graphController;
        this.algorithm = new SCCAlgorithm(graphController.getGraph());
        this.upperLower = new RandomVariables();
        random = new Random();

    }

//
//    public String randomAlphaNumeric(int count, String alpha) {
//        StringBuilder builder = new StringBuilder();
//        while (count-- != 0) {
//            int character = (int) (Math.random() * alpha.length());
//            builder.append(alpha.charAt(character));
//            notUsed = notUsed.substring(0, character) + notUsed.substring(character+1);
//            alpha = alpha.substring(0, character) + alpha.substring(character+1);
//        }
//        return builder.toString();
//    }

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
            newIneqiality.setExpression(""  + decisionVariable.toString() + ">=" + 0 );
            return newIneqiality;

    }

}
