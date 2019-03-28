import Controller.GraphController;
import Controller.RandomInequalitiesController;
import Model.Inequalities.DecisionVariable;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import Model.Inequalities.RandomInequalities.RandomInequalitiesGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RandomInequalitiesControllerShould {


    @Test
    public void performAction(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.layoutGUI.getRandomNumberInequalities(), new GraphController(container.getLayoutGUI()));

        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("50");
        field.setName("randomNumberNodes");
        java.awt.event.ActionEvent e  = new ActionEvent(field, uniqueId,"");
        testContoller.actionPerformed(e);
    }


    @Test
    public void performActionOfVisualizing50Nodes(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.layoutGUI.getRandomNumberInequalities(), new GraphController(container.getLayoutGUI()));
        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
        ArrayList nodes = new ArrayList<>();
        final ArrayList<Inequality> inequalities = new ArrayList<>();
        for(int i = 0; i < 50; i ++) {
            DecisionVariable variable = generator.generateDecisionVariable();
            nodes.add(variable);
            inequalities.add(generator.generateInequalityForNode(variable));
        }
        testContoller.visualizeInequality(inequalities);
        int numberOfNodes = container.layoutGUI.getGraphGUI().getGraph().getNodeCount();
        Assert.assertEquals(numberOfNodes,50);
    }

    @Test
    public void performActionOfAdding50InequalitiesInTheList(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.layoutGUI.getRandomNumberInequalities(), new GraphController(container.getLayoutGUI()));
        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
        ArrayList nodes = new ArrayList<>();
        final ArrayList<Inequality> inequalities = new ArrayList<>();
        for(int i = 0; i < 50; i ++) {
            DecisionVariable variable = generator.generateDecisionVariable();
            nodes.add(variable);
            inequalities.add(generator.generateInequalityForNode(variable));
        }
        testContoller.addInequalities(inequalities);
        int numberOfNodes = testContoller.getInequalitiesList().getInequalitiesContainer().size();
        Assert.assertEquals(numberOfNodes,50);
    }
}
