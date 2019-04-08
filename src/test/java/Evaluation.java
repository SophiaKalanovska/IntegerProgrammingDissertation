import Model.Inequalities.DecisionVariable;
import Model.Inequalities.Inequality;
import Model.Inequalities.RandomInequalities.RandomInequalitiesGenerator;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Evaluation {

//    @Test
//    public void Create1000DecisionVariables(){
//
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        long begin = System.currentTimeMillis();
//        helperMethodAddNodes(container, generator, 1000);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//    @Test
//    public void Create2000DecisionVariables(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        long begin = System.currentTimeMillis();
//        helperMethodAddNodes(container, generator, 2000);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//    @Test
//    public void Create3000DecisionVariables(){
//
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        long begin = System.currentTimeMillis();
//        helperMethodAddNodes(container, generator, 3000);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//    @Test
//    public void Create4000DecisionVariables(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        long begin = System.currentTimeMillis();
//        helperMethodAddNodes(container, generator, 4000);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//    @Test
//    public void Create5000DecisionVariables(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        long begin = System.currentTimeMillis();
//        helperMethodAddNodes(container, generator, 5000);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//    @Test
//    public void Create1000DecisionVariablesAnd2000Inequalities(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        long begin = System.currentTimeMillis();
//        ArrayList nodes = helperMethodAddNodes(container, generator, 1000);
//        helperMethodAddInequalities(container, generator, 2000, nodes);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//
//    @Test
//    public void Create2000DecisionVariablesAnd4000Inequalities(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        long begin = System.currentTimeMillis();
//        ArrayList nodes = helperMethodAddNodes(container, generator, 2000);
//        helperMethodAddInequalities(container, generator, 4000, nodes);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//
//    @Test
//    public void EvaluteAndCreate1000DecisionVariables(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        JButton evaluateGraph = new JButton("evaluate");
//        evaluateGraph.setName("evaluate");
//        int uniqueId = (int) System.currentTimeMillis();
//        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        long begin = System.currentTimeMillis();
//        helperMethodAddNodes(container, generator, 1000);
//        container.getInequalitiesListController().mouseClicked(e);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//
//    @Test
//    public void EvaluteAndCreate2000DecisionVariables(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        JButton evaluateGraph = new JButton("evaluate");
//        evaluateGraph.setName("evaluate");
//        int uniqueId = (int) System.currentTimeMillis();
//        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        long begin = System.currentTimeMillis();
//        helperMethodAddNodes(container, generator, 2000);
//        container.getInequalitiesListController().mouseClicked(e);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//
//
//    @Test
//    public void EvaluteAndCreate1000DecisionVariablesAnd2000Inequalities(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        JButton evaluateGraph = new JButton("evaluate");
//        evaluateGraph.setName("evaluate");
//        int uniqueId = (int) System.currentTimeMillis();
//        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        long begin = System.currentTimeMillis();
//        ArrayList nodes = helperMethodAddNodes(container, generator, 1000);
//        helperMethodAddInequalities(container, generator, 2000, nodes);
//        container.getInequalitiesListController().mouseClicked(e);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//
//    @Test
//    public void EvaluteAndCreate2000DecisionVariablesAnd4000Inequalities(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        JButton evaluateGraph = new JButton("evaluate");
//        evaluateGraph.setName("evaluate");
//        int uniqueId = (int) System.currentTimeMillis();
//        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        long begin = System.currentTimeMillis();
//        ArrayList nodes = helperMethodAddNodes(container, generator, 2000);
//        helperMethodAddInequalities(container, generator, 4000, nodes);
//        container.getInequalitiesListController().mouseClicked(e);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//
//    @Test
//    public void EvaluteAndCreate3000DecisionVariablesAnd6000Inequalities(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        JButton evaluateGraph = new JButton("evaluate");
//        evaluateGraph.setName("evaluate");
//        int uniqueId = (int) System.currentTimeMillis();
//        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        long begin = System.currentTimeMillis();
//        ArrayList nodes = helperMethodAddNodes(container, generator, 3000);
//        helperMethodAddInequalities(container, generator, 6000, nodes);
//        container.getInequalitiesListController().mouseClicked(e);
//        long end = System.currentTimeMillis();
//        System.out.println(((double)(end - begin))/1000);
//    }
//
//    @Test
//    public void EvaluteAndCreate4000DecisionVariablesAnd8000Inequalities(){
//        Container container = new Container();
//        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
//        JButton evaluateGraph = new JButton("evaluate");
//        evaluateGraph.setName("evaluate");
//        int uniqueId = (int) System.currentTimeMillis();
//        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        long begin = System.currentTimeMillis();
//        ArrayList nodes = helperMethodAddNodes(container, generator, 4000);
//        helperMethodAddInequalities(container, generator, 8000, nodes);
//        container.getInequalitiesListController().mouseClicked(e);
//        long end = System.currentTimeMillis();
//        System.out.print(((double)(end - begin))/1000);
//    }

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
        System.out.println("Evaluation" + ((double)(evaluationEnd - evaluationBegin))/1000);
    }


    @Test
    public void EvaluteAndCreate5000DecisionVariablesAnd15000InequalitiesAnd200Bounds(){
        Container container = new Container();
        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
        JButton evaluateGraph = new JButton("evaluate");
        evaluateGraph.setName("evaluate");
        int uniqueId = (int) System.currentTimeMillis();
        java.awt.event.MouseEvent e  = new MouseEvent(evaluateGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        long beginCreationAndEvaluation = System.currentTimeMillis();
        ArrayList nodes = helperMethodAddNodes(container, generator, 5000);
        helperMethodAddInequalities(container, generator, 18000, nodes);
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
        final ArrayList<Inequality> inequalities = new ArrayList<>();
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
        ArrayList<Inequality> inequalities = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            inequalities.add(generator.generateInequality(nodes));
        }
        container.getRandomInequalitiesController().addInequalities(inequalities);
        container.getRandomInequalitiesController().visualizeInequality(inequalities);
    }



    public synchronized void helperMethodAddBounds( final Container container, final RandomInequalitiesGenerator generator, int amount, ArrayList nodes ){
        ArrayList<Inequality> inequalities = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            inequalities.add(generator.generateBound(nodes));
        }
        container.getRandomInequalitiesController().addInequalities(inequalities);
        container.getRandomInequalitiesController().visualizeInequality(inequalities);

    }
}
