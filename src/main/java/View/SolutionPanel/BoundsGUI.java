package View.SolutionPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BoundsGUI extends JPanel{

    private LowerBoundClusterGUI lowerBoundClusterGUI;
    private UpperBoundClusterGUI upperBoundClusterGUI;
    private IntegerAssignmentMinimizeGUI integerAssignmentMinimizeGUI;
    private InternalConstarinsClusterGUI internalConstarinsClusterGUI;
    private IntegerAssignmentMaximizeGUI integerAssignmentMaximizeGUI;
    private TitledBorder solution;
    private TitledBorder internalBound;
    private TitledBorder max;
    private TitledBorder min;

    private TitledBorder lowerBound ;
    private TitledBorder upperBound ;
    private TitledBorder constrain;

    /**
     * This class contains all JPanels that are contain a bound list.
     */
    public BoundsGUI(LowerBoundClusterGUI lowerBoundClusterGUI, UpperBoundClusterGUI upperBoundClusterGUI, IntegerAssignmentMinimizeGUI integerAssignmentMinimizeGUI, InternalConstarinsClusterGUI internalConstarinsClusterGUI, IntegerAssignmentMaximizeGUI integerAssignmentMaximizeGUI){
        this.lowerBoundClusterGUI = lowerBoundClusterGUI;
        this.upperBoundClusterGUI = upperBoundClusterGUI;
        this.integerAssignmentMinimizeGUI = integerAssignmentMinimizeGUI;
        this.internalConstarinsClusterGUI = internalConstarinsClusterGUI;
        this.integerAssignmentMaximizeGUI = integerAssignmentMaximizeGUI;

        Border topBorder = BorderFactory.createMatteBorder(1, 0, 0, 1, new Color(153, 218, 250));

        internalBound = new TitledBorder(topBorder, "<html><b> Internal Bounds:</html><b>");
        internalConstarinsClusterGUI.setBorder(internalBound);
        internalBound.setTitleJustification(TitledBorder.CENTER);

        lowerBound = new TitledBorder(topBorder, "<html><b> Lower Bounds:</html><b>");
        lowerBoundClusterGUI.setBorder(lowerBound);
        lowerBound.setTitleJustification(TitledBorder.CENTER);

        Border topBorder3 = BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(153, 218, 250));

        upperBound = new TitledBorder(topBorder3, "<html><b> Upper Bounds:</html><b>");
        upperBoundClusterGUI.setBorder(upperBound);
        upperBound.setTitleJustification(TitledBorder.CENTER);

        min = new TitledBorder(topBorder, "<html><b> When Minimizing:</html><b>");
        integerAssignmentMinimizeGUI.setBorder(min);
        min.setTitleJustification(TitledBorder.CENTER);

        max = new TitledBorder(topBorder3, "<html><b> When Maximizing:</html><b>");
        integerAssignmentMaximizeGUI.setBorder(max);
        max.setTitleJustification(TitledBorder.CENTER);


        JPanel leftPanel = new JPanel(new GridLayout(1,3));
        leftPanel.add(internalConstarinsClusterGUI);
        leftPanel.add(lowerBoundClusterGUI);
        leftPanel.add(upperBoundClusterGUI);
        leftPanel.setOpaque(false);

        JPanel rightPanel = new JPanel(new GridLayout(1,2));
        rightPanel.add(integerAssignmentMinimizeGUI);
        rightPanel.add(integerAssignmentMaximizeGUI);
        rightPanel.setOpaque(false);

        Border constrainBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(153, 218, 250));
        constrain = new TitledBorder(constrainBorder,"<html><b> Bounds in Clusters:</html><b>" );
        constrain.setTitleJustification(TitledBorder.CENTER);
        leftPanel.setBorder(constrain);

        Border topBorder2 = BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(153, 218, 250));
        solution = new TitledBorder(topBorder2,"<html><b> Optimal Integer Assignment:</html><b>" );
        solution.setTitleJustification(TitledBorder.CENTER);
        rightPanel.setBorder(solution);

        this.setLayout(new GridLayout(1,2));
        this.add(leftPanel);
        this.add(rightPanel);
        this.setOpaque(false);
    }


    /**
     * This method calls changeView in all JFrames and
     * changes the color scheme to dark mode and back
     */
    public void changeView(boolean dark){
        if (dark){
            solution.setTitleColor(Color.WHITE);
            lowerBound.setTitleColor(Color.WHITE);
            upperBound .setTitleColor(Color.WHITE);
            internalBound.setTitleColor(Color.WHITE);
            max.setTitleColor(Color.WHITE);
            min.setTitleColor(Color.WHITE);
            constrain.setTitleColor(Color.WHITE);
        }else{
            solution.setTitleColor(Color.BLACK);
            lowerBound.setTitleColor(Color.BLACK);
            upperBound .setTitleColor(Color.BLACK);
            internalBound.setTitleColor(Color.BLACK);
            max.setTitleColor(Color.BLACK);
            min.setTitleColor(Color.BLACK);
            constrain.setTitleColor(Color.BLACK);
        }
        lowerBoundClusterGUI.changeView(dark);
        upperBoundClusterGUI.changeView(dark);
        integerAssignmentMinimizeGUI.changeView(dark);
        internalConstarinsClusterGUI.changeView(dark);
        integerAssignmentMaximizeGUI.changeView(dark);
    }

}
