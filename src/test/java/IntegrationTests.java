import Controller.GraphController;
import Controller.Listeners.ManualInequalitiesController;
import Controller.Listeners.RandomInequalitiesController;
import Model.Inequalities.DecisionVariable;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import Model.Inequalities.RandomInequalities.RandomInequalitiesGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class IntegrationTests {


    @Test
    public void performActionNumberOfRandomDecisionVaraibles(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.layoutGUI.getRandomInequalities(), new GraphController(container.getLayoutGUI()));

        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("50");
        field.setName("randomNumberNodes");
        java.awt.event.ActionEvent e  = new ActionEvent(field, uniqueId,"");
        testContoller.actionPerformed(e);
    }

    @Test
    public void performActionNumberOfRandomInequalities(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));
        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("50");
        field.setName("randomNumberNodes");
        java.awt.event.ActionEvent e  = new ActionEvent(field, uniqueId,"");
        testContoller.actionPerformed(e);
        int uniqueIdIneq = (int) System.currentTimeMillis();
        JTextField fieldIneq = new JTextField("50");
        fieldIneq.setName("randomNumberInequalities");
        java.awt.event.ActionEvent eIneq  = new ActionEvent(fieldIneq, uniqueIdIneq,"");
        testContoller.actionPerformed(eIneq);
    }

    @Test
    public void performActionManual(){
        Container container = new Container();
        ManualInequalitiesController testContoller = new ManualInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getManualInequalities(), new GraphController(container.getLayoutGUI()));
        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("3x<=y");
        java.awt.event.ActionEvent e  = new ActionEvent(field, uniqueId,"");
        testContoller.actionPerformed(e);
    }

    @Test
    public void performActionManualNegative(){
        Container container = new Container();
        ManualInequalitiesController testContoller = new ManualInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getManualInequalities(), new GraphController(container.getLayoutGUI()));
        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("3x<=-y");
        java.awt.event.ActionEvent e  = new ActionEvent(field, uniqueId,"");
        testContoller.actionPerformed(e);
    }

    @Test
    public void performActionManualOutOfScope(){
        Container container = new Container();
        ManualInequalitiesController testContoller = new ManualInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getManualInequalities(), new GraphController(container.getLayoutGUI()));
        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("3x<=6y");
        java.awt.event.ActionEvent e  = new ActionEvent(field, uniqueId,"");
        testContoller.actionPerformed(e);
    }

    @Test
    public void performActionManualNegativeNoConstrain(){
        Container container = new Container();
        ManualInequalitiesController testContoller = new ManualInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getManualInequalities(), new GraphController(container.getLayoutGUI()));
        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("-3x<=y");
        java.awt.event.ActionEvent e  = new ActionEvent(field, uniqueId,"");
        testContoller.actionPerformed(e);
    }

    @Test
    public void performActionOfVisualizing50Nodes(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));
        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
        ArrayList nodes = new ArrayList<>();
        final ArrayList<Inequality> inequalities = new ArrayList<>();
        for(int i = 0; i < 50; i ++) {
            DecisionVariable variable = generator.generateDecisionVariable();
            nodes.add(variable);
            inequalities.add(generator.generateInequalityForNode(variable));
        }
        testContoller.visualizeInequality(inequalities);
        int numberOfNodes = container.getLayoutGUI().getGraphGUI().getGraph().getNodeCount();
        Assert.assertEquals(numberOfNodes,50);
    }

    @Test
    public void performActionOfAdding50InequalitiesInTheList(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));
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

    @Test
    public void calculateSCCGGivenAGraph(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), container.getGraphController());
        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
        ArrayList nodes = new ArrayList<>();
        final ArrayList<Inequality> inequalities = new ArrayList<>();
        for(int i = 0; i < 50; i ++) {
            DecisionVariable variable = generator.generateDecisionVariable();
            nodes.add(variable);
            inequalities.add(generator.generateInequalityForNode(variable));
        }
        testContoller.addInequalities(inequalities);
        testContoller.visualizeInequality(inequalities);
        int numberOfNodes = testContoller.getInequalitiesList().getInequalitiesContainer().size();

        Assert.assertEquals(numberOfNodes,50);
        Assert.assertNotEquals(container.getGraphController().getSCCComponents().getProjectWallet().size() ,0);
    }


    @Test
    public void testMain() throws IOException {
        System.out.println("main");
        String[] args = null;
        Main.main(args);
    }

}
