package View;

import View.Graph.GraphGUI;
import View.Graph.Settings;
import View.OperationsOnInequalities.InequalitiesListGUI;
import View.OperationsOnInequalities.ManualInequalitiesGUI;
import View.OperationsOnInequalities.RandomInequalitiesGUI;
import View.SolutionPanel.BoundsGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/**
 * Home is the class where the Home panel is built implements Observer
 */
public class LayoutGUI extends JPanel  {

    private GraphGUI graph;
    private JLabel welcomeLabel;
    private TitledBorder enterInequalitiesBorder;
    private TitledBorder graphBorder;
    private TitledBorder randomBorder;
    private TitledBorder listBorder;
    private Settings settings;
    private BoundsGUI boundsGUI;
    private InequalitiesListGUI inequalitiesListGUI;
    private ManualInequalitiesGUI manualInequalitiesGUI;
    private RandomInequalitiesGUI randomInequalitiesGUI;

    /**
     * Constructs a new Home panel
     * @param  frame  the frame containing the panel
     */

    private RandomInequalitiesGUI randomInequalities;

    public LayoutGUI(JFrame frame, ManualInequalitiesGUI manualInequalitiesGUI, RandomInequalitiesGUI randomInequalities, InequalitiesListGUI inequalitiesListGUI, BoundsGUI boundGUI){

        //welcome label
        welcomeLabel = new JLabel("Integer Programming Solver");
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 34));
        //welcome panel creation
        JPanel welcomePanel = new JPanel(new FlowLayout());
        welcomePanel.add(welcomeLabel);
        welcomePanel.setOpaque(false);

        //border color
        Border mainBorder = BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(153, 218, 250));
        //create border for manual inequalities
        enterInequalitiesBorder = new TitledBorder(mainBorder,"<html><b> Enter Inequalities" );


        //create border for graph
        this.settings = new Settings(this);
        graph = new GraphGUI(settings);
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphPanel.add(graph.getView(),BorderLayout.CENTER);
        graphPanel.add(settings, BorderLayout.NORTH);
        Border graphBorderWalls = BorderFactory.createMatteBorder(1, 0, 0, 1, new Color(153, 218, 250));
        graphBorder = new TitledBorder(graphBorderWalls,"<html><b> Graph:</html><b>" );
        graphPanel.setBorder(graphBorder);
        graphPanel.setOpaque(false);

        //border color
        Border topBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(153, 218, 250));
        randomBorder = new TitledBorder(topBorder,"<html><b> Generate Random Inequalities:</html><b>" );
        this.randomInequalities = randomInequalities;
        randomInequalities.setBorder(randomBorder);

        listBorder = new TitledBorder(topBorder,"<html><b> List of Inequalities:</html><b>" );
        inequalitiesListGUI.setBorder(listBorder);


        //put border around inequalities
        this.boundsGUI = boundGUI;
        this.inequalitiesListGUI = inequalitiesListGUI;
        this.manualInequalitiesGUI = manualInequalitiesGUI;
        this.randomInequalitiesGUI = randomInequalities;

        JPanel leftPanel = new JPanel(new GridLayout(3,1));
        leftPanel.add(manualInequalitiesGUI);
        leftPanel.add(randomInequalities);
        leftPanel.add(inequalitiesListGUI);
        leftPanel.setOpaque(false);

        leftPanel.setBorder(enterInequalitiesBorder);


        // put the manual inequalities and the graph in a panel
        JPanel center = new JPanel(new GridLayout(1,2));
        center.add(leftPanel);
        center.add(graphPanel);
        center.setOpaque(false);



        this.setLayout(new BorderLayout());
        this.add(welcomePanel, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(boundGUI, BorderLayout.SOUTH);
        this.setBackground(Color.white);

        frame.add(this);

    }

    public void changeView(boolean dark){
        if (dark){
            this.setBackground(Color.black);
            graph.changeView(true);
            boundsGUI.changeView(true);
            inequalitiesListGUI.changeView(true);
            manualInequalitiesGUI.changeView(true);
            randomInequalitiesGUI.changeView(true);
            settings.getDarkMode().setForeground(Color.WHITE);
            welcomeLabel.setForeground(Color.WHITE);
            enterInequalitiesBorder.setTitleColor(Color.WHITE);
            graphBorder.setTitleColor(Color.WHITE);
            randomBorder.setTitleColor(Color.WHITE);
            listBorder.setTitleColor(Color.WHITE);
        }else{
            this.setBackground(Color.white);
            graph.changeView(false);
            boundsGUI.changeView(false);
            inequalitiesListGUI.changeView(false);
            manualInequalitiesGUI.changeView(false);
            randomInequalitiesGUI.changeView(false);
            settings.getDarkMode().setForeground(Color.BLACK);
            welcomeLabel.setForeground(Color.BLACK);
            enterInequalitiesBorder.setTitleColor(Color.BLACK);
            graphBorder.setTitleColor(Color.BLACK);
            randomBorder.setTitleColor(Color.BLACK);
            listBorder.setTitleColor(Color.BLACK);
        }
    }

//    public void createaPopUp()



    public GraphGUI getGraphGUI(){
        return graph;
    }

    public RandomInequalitiesGUI getRandomInequalities() {
        return randomInequalities;
    }
    public ManualInequalitiesGUI getManualInequalities() {
        return manualInequalitiesGUI;
    }

    public InequalitiesListGUI getInequalitiesListGUI() {
        return inequalitiesListGUI;
    }
}
