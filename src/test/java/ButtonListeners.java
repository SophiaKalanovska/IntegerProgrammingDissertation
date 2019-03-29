import Controller.Constrains.IntegerAssignenmentMaxListController;
import Controller.GraphController;
import Controller.InequalitiesListController;
import Controller.ManualInequalitiesController;
import Controller.RandomInequalitiesController;
import Model.Inequalities.DecisionVariable;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import Model.Inequalities.RandomInequalities.RandomInequalitiesGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ButtonListeners {


    @Test
    public void InequalitiesListControllerDeleteElement(){
        Container container = new Container();

        InequalitiesListController controller = new InequalitiesListController(container.getInequalitiesList(), container.getLayoutGUI().getInequalitiesListGUI(), container.getGraphController(), container.getConstarinsController());
        JButton deleteGraph = new JButton("delete");
        deleteGraph.setName("delete");
        helperMethodAddNodes(container);
        controller.getInequalitiesListGUI().getListSelectedValue();
        int uniqueId2 = (int) System.currentTimeMillis();
        java.awt.event.MouseEvent e2  = new MouseEvent(deleteGraph, uniqueId2, System.currentTimeMillis(), 0, 0, 0, 1, false);
        controller.mouseClicked(e2);
    }



    @Test
    public void InequalitiesListControllerEvaluate(){
        Container container = new Container();
//        InequalitiesListController controller = new InequalitiesListController(container.getInequalitiesList(), container.getLayoutGUI().getInequalitiesListGUI(), container.getGraphController(), container.getConstarinsController());
        JButton deleteGraph = new JButton("evaluate");
        deleteGraph.setName("evaluate");
        helperMethodAddNodes(container);
        int uniqueId = (int) System.currentTimeMillis();
        java.awt.event.MouseEvent e  = new MouseEvent(deleteGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        container.getInequalitiesListController().mouseClicked(e);
    }

    @Test
    public void InequalitiesListControllerDeleteGraph(){
        Container container = new Container();
        InequalitiesListController controller = new InequalitiesListController(new InequalitiesList(), container.getLayoutGUI().getInequalitiesListGUI(), container.getGraphController(), container.getConstarinsController());
        JButton deleteGraph = new JButton("deleteGraph");
        deleteGraph.setName("deleteGraph");
        int uniqueId = (int) System.currentTimeMillis();
        java.awt.event.MouseEvent e  = new MouseEvent(deleteGraph, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        controller.mouseClicked(e);
    }




    @Test
    public void performActionPressedAndDeleteTheTextRandom(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));

        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mousePressed(e);
        Assert.assertEquals(field.getText(), "");

    }

    @Test
    public void performActionClickAndDeleteTheTextRandom(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));

        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mouseClicked(e);

    }

    @Test
    public void performActionReleasedeAndDeleteTheTextRandom(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));

        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mouseReleased(e);

    }

    @Test
    public void performActionEnteredAndDeleteTheTextRandom(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));

        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mouseEntered(e);

    }

    @Test
    public void performActionExitedAndDeleteTheTextRandom(){
        Container container = new Container();
        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));

        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mouseExited(e);

    }

    @Test
    public void performActionPressAndDeleteTheTextManual(){
        Container container = new Container();
        ManualInequalitiesController testContoller = new ManualInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getManualInequalities(), new GraphController(container.getLayoutGUI()));

        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mousePressed(e);
        Assert.assertEquals(field.getText(), "");

    }




    @Test
    public void performActionClickAndDeleteTheTextManual(){
        Container container = new Container();
        ManualInequalitiesController testContoller = new ManualInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getManualInequalities(), new GraphController(container.getLayoutGUI()));
        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mouseClicked(e);

    }

    @Test
    public void performActionReleasedeAndDeleteTheTextManual(){
        Container container = new Container();
        ManualInequalitiesController testContoller = new ManualInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getManualInequalities(), new GraphController(container.getLayoutGUI()));
        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mouseReleased(e);

    }

    @Test
    public void performActionEnteredAndDeleteTheTextManual(){
        Container container = new Container();
        ManualInequalitiesController testContoller = new ManualInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getManualInequalities(), new GraphController(container.getLayoutGUI()));
        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mouseEntered(e);

    }

    @Test
    public void performActionExitedAndDeleteTheTextManual(){
        Container container = new Container();
        ManualInequalitiesController testContoller = new ManualInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getManualInequalities(), new GraphController(container.getLayoutGUI()));
        int uniqueId = (int) System.currentTimeMillis();
        JTextField field = new JTextField("Click me");
        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
        testContoller.mouseExited(e);
    }



    public synchronized void helperMethodAddNodes( Container container ){

        RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator();
        ArrayList nodes = new ArrayList<>();
        final ArrayList<Inequality> inequalities = new ArrayList<>();
        for(int i = 0; i < 50; i ++) {
            DecisionVariable variable = generator.generateDecisionVariable();
            nodes.add(variable);
            inequalities.add(generator.generateInequalityForNode(variable));
        }
        container.getRandomInequalitiesController().addInequalities(inequalities);
        container.getRandomInequalitiesController().visualizeInequality(inequalities);

    }




//
//    @Test
//    public void performActionClickAndDeleteTheTextRandom(){
//        Container container = new Container();
//        IntegerAssignenmentMaxListController testContoller = new IntegerAssignenmentMaxListController(container.getIntegerAssignmentMaximizeGUI());
//
//        int uniqueId = (int) System.currentTimeMillis();
//        JTextField field = new JTextField("Click me");
//        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        testContoller.mouseClicked(e);
//
//    }
//
//    @Test
//    public void performActionReleasedeAndDeleteTheTextRandom(){
//        Container container = new Container();
//        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));
//
//        int uniqueId = (int) System.currentTimeMillis();
//        JTextField field = new JTextField("Click me");
//        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        testContoller.mouseReleased(e);
//
//    }
//
//    @Test
//    public void performActionEnteredAndDeleteTheTextRandom(){
//        Container container = new Container();
//        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));
//
//        int uniqueId = (int) System.currentTimeMillis();
//        JTextField field = new JTextField("Click me");
//        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        testContoller.mouseEntered(e);
//
//    }
//
//    @Test
//    public void performActionExitedAndDeleteTheTextRandom(){
//        Container container = new Container();
//        RandomInequalitiesController testContoller = new RandomInequalitiesController(new InequalitiesList(),container.getLayoutGUI().getRandomInequalities(), new GraphController(container.getLayoutGUI()));
//
//        int uniqueId = (int) System.currentTimeMillis();
//        JTextField field = new JTextField("Click me");
//        java.awt.event.MouseEvent e  = new MouseEvent(field, uniqueId, System.currentTimeMillis(), 0, 0, 0, 1, false);
//        testContoller.mouseExited(e);
//
//    }











}
