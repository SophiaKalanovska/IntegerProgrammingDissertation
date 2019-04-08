import Model.Inequalities.DecisionVariable;
import Model.Inequalities.Inequality;
import Model.Inequalities.RandomInequalities.RandomInequalitiesGenerator;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class EvaluationTests {

    @Test
    public void EvaluteAndCreate5000DecisionVariablesAnd5000InequalitiesAnd5000Bounds(){
        Container container = new Container();
        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
        JButton evaluateGraph = new JButton("evaluate");
        evaluateGraph.setName("evaluate");
        int uniqueId = (int) System.currentTimeMillis();
        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        long beginCreationAndEvaluation = System.currentTimeMillis();
        ArrayList nodes = helperMethodAddNodes(container, generator, 5000);
        helperMethodAddInequalities(container, generator, 5000, nodes);
        helperMethodAddBounds(container, generator, 5000, nodes);
        long evaluationBegin = System.currentTimeMillis();
        container.getInequalitiesListController().mouseClicked(e);
        long evaluationEnd = System.currentTimeMillis();
        long endCreationAndEvaluation = System.currentTimeMillis();
        System.out.println("CreationAndEvaluationBounds" + ((double)(endCreationAndEvaluation - beginCreationAndEvaluation))/1000);
        System.out.println("EvaluationBounds" + ((double)(evaluationEnd - evaluationBegin))/1000);
    }

    @Test
    public void EvaluteAndCreate5000DecisionVariablesAnd10000Inequalities(){
        Container container = new Container();
        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
        JButton evaluateGraph = new JButton("evaluate");
        evaluateGraph.setName("evaluate");
        int uniqueId = (int) System.currentTimeMillis();
        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        long beginCreationAndEvaluation = System.currentTimeMillis();
        ArrayList nodes = helperMethodAddNodes(container, generator, 5000);
        helperMethodAddInequalities(container, generator, 10000, nodes);
        long evaluationBegin = System.currentTimeMillis();
        container.getInequalitiesListController().mouseClicked(e);
        long evaluationEnd = System.currentTimeMillis();
        long endCreationAndEvaluation = System.currentTimeMillis();
        System.out.println("CreationAndEvaluation" + ((double)(endCreationAndEvaluation - beginCreationAndEvaluation))/1000);
        System.out.println("EvaluationTests" + ((double)(evaluationEnd - evaluationBegin))/1000);
    }

    @Test
    public void EvaluteAndCreate5000DecisionVariablesAnd15000InequalitiesAnd3000Bounds(){
        Container container = new Container();
        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
        JButton evaluateGraph = new JButton("evaluate");
        evaluateGraph.setName("evaluate");
        int uniqueId = (int) System.currentTimeMillis();
        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        long beginCreationAndEvaluation = System.currentTimeMillis();
        ArrayList nodes = helperMethodAddNodes(container, generator, 5000);
        helperMethodAddInequalities(container, generator, 15000, nodes);
        helperMethodAddBounds(container, generator, 3000, nodes);
        long evaluationBegin = System.currentTimeMillis();
        container.getInequalitiesListController().mouseClicked(e);
        long evaluationEnd = System.currentTimeMillis();
        long endCreationAndEvaluation = System.currentTimeMillis();
        System.out.print(Runtime.getRuntime().totalMemory());
        System.out.println("CreationAndEvaluationBounds" + ((double)(endCreationAndEvaluation - beginCreationAndEvaluation))/1000);
        System.out.println("EvaluationBounds" + ((double)(evaluationEnd - evaluationBegin))/1000);
    }


    public ArrayList helperMethodAddNodes( final Container container, final RandomInequalitiesGenerator generator, int amount ){
        ArrayList nodes = new ArrayList<>();
        final HashSet<Inequality> inequalities = new HashSet<>();
        for(int i = 0; i < amount; i ++) {
            DecisionVariable variable = generator.generateDecisionVariable();
            nodes.add(variable);
            inequalities.add(generator.generateInequalityForNode(variable));
        }
        container.getRandomInequalitiesController().addInequalities(inequalities);
        container.getRandomInequalitiesController().visualizeInequality(inequalities);
        return nodes;

    }

    public synchronized void helperMethodAddInequalities( final Container container, final RandomInequalitiesGenerator generator, int amount, ArrayList nodes ){
        HashSet<Inequality>  inequalities = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            inequalities.add(generator.generateInequality(nodes));
        }
        container.getRandomInequalitiesController().addInequalities(inequalities);
        container.getRandomInequalitiesController().visualizeInequality(inequalities);
    }

    public synchronized void helperMethodAddBounds( final Container container, final RandomInequalitiesGenerator generator, int amount, ArrayList nodes ){
        HashSet<Inequality>  inequalities = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            inequalities.add(generator.generateBound(nodes));
        }
        container.getRandomInequalitiesController().addInequalities(inequalities);
        container.getRandomInequalitiesController().visualizeInequality(inequalities);

    }
}
