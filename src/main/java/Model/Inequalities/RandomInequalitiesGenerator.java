package Model.Inequalities;

import Controller.GraphController;
import Model.SCC.SCCAlgorithm;
import org.graphstream.graph.Node;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Random;

public class RandomInequalitiesGenerator {
    int numberOfNodesNeeded = 0;
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private String notUsed = "abcdefghijklmnopqrstuvwxyz";
    private GraphController graphController;
    private Random random;
    private SCCAlgorithm algorithm;



    public RandomInequalitiesGenerator(GraphController graphController) {
        this.graphController = graphController;
        this.algorithm = new SCCAlgorithm(graphController.getGraph());
        random = new Random();

    }


    public String randomAlphaNumeric(int count, String alpha) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * alpha.length());
            builder.append(alpha.charAt(character));
            notUsed = notUsed.substring(0, character) + notUsed.substring(character+1);
            alpha = alpha.substring(0, character) + alpha.substring(character+1);
        }
        return builder.toString();
    }

    public ArrayList<DecisionVariable> generateNodes(int numberOfNodes) {
        int max = 10;
        int min = 1;
        ArrayList<DecisionVariable> decisionVariables = new ArrayList<>();
        if (numberOfNodes < notUsed.length()) {
            String nodes = randomAlphaNumeric(numberOfNodes, notUsed);
            for (int i = 0; i < nodes.length() ; i++ ) {
                DecisionVariable decisionVariable = new DecisionVariable();
                decisionVariable.setVariable(Character.toString(nodes.charAt(i)));
                decisionVariable.setWeight(random.nextInt(max - min + 1) + min);
                decisionVariables.add(decisionVariable);
            }
        } else {
            numberOfNodes -= notUsed.length();
            String nodes = randomAlphaNumeric(notUsed.length(), notUsed);
            for (int i = 0; i < nodes.length() - 1; ) {
                DecisionVariable decisionVariable = new DecisionVariable();
                decisionVariable.setVariable(Character.toString(nodes.charAt(i)));
                decisionVariable.setWeight(random.nextInt(max - min + 1) + min);
                decisionVariables.add(decisionVariable);
            }
        }
            renderNodes(decisionVariables);
            return decisionVariables;
    }

    public ArrayList<Inequality> generateInequalities(ArrayList<DecisionVariable> decisionVariables, int numberOfInequalities) {
        ArrayList<Inequality> inequalities = new ArrayList<>();
        for (int i = 0; i < numberOfInequalities ; i++ ) {
            int variableIndexFirst = (int) (Math.random() * decisionVariables.size());
            int variableIndexSecond = (int) (Math.random() * decisionVariables.size());
            Inequality newIneqiality = new Inequality();
            DecisionVariable first = decisionVariables.get(variableIndexFirst);
            DecisionVariable second = decisionVariables.get(variableIndexSecond);
            newIneqiality.setFirstDecisionVariable(first);
            newIneqiality.setSecondDecisionVariable(second);
            newIneqiality.setExpression("" + first.getWeight() + first.toString() + "<=" + second.getWeight() + second.toString());
            inequalities.add(newIneqiality);
        }
        return inequalities;
    }



    public void renderNodes(ArrayList<DecisionVariable> decisionVariables){
        for (DecisionVariable decisionVariable : decisionVariables){
            graphController.addNode(decisionVariable);
            graphController.getPipeIn().pump();
            algorithm.clear();
            algorithm.calculateSCC();
            algorithm.cluster();
        }
//        return SCCComponents;
    }




}
