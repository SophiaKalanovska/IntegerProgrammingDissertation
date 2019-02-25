package View.SolutionPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BoundsGUI extends JPanel{

    private LowerBoundClusterGUI lowerBoundClusterGUI;
    private UpperBoundClusterGUI upperBoundClusterGUI;
    private IntegerAssignmentGUI integerAssignmentGUI;
    private InternalConstarinsClusterGUI internalConstarinsClusterGUI;
    private TitledBorder integerAssignment;
    private TitledBorder lowerBound ;
    private TitledBorder upperBound ;
    private TitledBorder internalConstarins;

    public BoundsGUI(LowerBoundClusterGUI lowerBoundClusterGUI, UpperBoundClusterGUI upperBoundClusterGUI, IntegerAssignmentGUI integerAssignmentGUI, InternalConstarinsClusterGUI internalConstarinsClusterGUI){
        this.lowerBoundClusterGUI = lowerBoundClusterGUI;
        this.upperBoundClusterGUI = upperBoundClusterGUI;
        this.integerAssignmentGUI = integerAssignmentGUI;
        this.internalConstarinsClusterGUI = internalConstarinsClusterGUI;

        Border topBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(153, 218, 250));
        internalConstarins = new TitledBorder(topBorder,"<html><b> Internal ConstrainsLists in Clusters:</html><b>" );
        internalConstarinsClusterGUI.setBorder(internalConstarins);


        Border topBorder1 = BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(153, 218, 250));
        lowerBound =  new TitledBorder(topBorder1,"<html><b> Lower Bounds in Clusters:</html><b>" );
        lowerBoundClusterGUI.setBorder(lowerBound);


        Border topBorder2 = BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(153, 218, 250));
        upperBound = new TitledBorder(topBorder2,"<html><b> Upper Bounds in Clusters:</html><b>" );
        upperBoundClusterGUI.setBorder(upperBound);

        //border color
        Border topBorder3 = BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(153, 218, 250));
        integerAssignment = new TitledBorder(topBorder3,"<html><b> Optimal Integer Assignment:</html><b>" );
        integerAssignmentGUI.setBorder(integerAssignment);


        this.setLayout(new GridLayout(1,4));
        this.add(internalConstarinsClusterGUI);
        this.add(lowerBoundClusterGUI);
        this.add(upperBoundClusterGUI);
        this.add(integerAssignmentGUI);
        this.setOpaque(false);
    }

    public void changeView(boolean dark){
        if (dark){
            integerAssignment.setTitleColor(Color.WHITE);
            lowerBound.setTitleColor(Color.WHITE);
            upperBound .setTitleColor(Color.WHITE);
            internalConstarins.setTitleColor(Color.WHITE);
        }else{
            integerAssignment.setTitleColor(Color.BLACK);
            lowerBound.setTitleColor(Color.BLACK);
            upperBound .setTitleColor(Color.BLACK);
            internalConstarins.setTitleColor(Color.BLACK);
        }
        lowerBoundClusterGUI.changeView(dark);
        upperBoundClusterGUI.changeView(dark);
        integerAssignmentGUI.changeView(dark);
        internalConstarinsClusterGUI.changeView(dark);
    }

}
