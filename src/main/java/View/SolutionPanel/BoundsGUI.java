package View.SolutionPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BoundsGUI extends JPanel{

    public BoundsGUI(LowerBoundClusterGUI lowerBoundClusterGUI, UpperBoundClusterGUI upperBoundClusterGUI, IntegerAssignmentGUI integerAssignmentGUI, InternalConstarinsClusterGUI internalConstarinsClusterGUI){

        Border topBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(153, 218, 250));
        internalConstarinsClusterGUI.setBorder(new TitledBorder(topBorder,"<html><b> Internal ConstrainsLists in Clusters:</html><b>" ));


        Border topBorder1 = BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(153, 218, 250));
        lowerBoundClusterGUI.setBorder( new TitledBorder(topBorder1,"<html><b> Lower Bounds in Clusters:</html><b>" ));


        Border topBorder2 = BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(153, 218, 250));
        upperBoundClusterGUI.setBorder(new TitledBorder(topBorder2,"<html><b> Upper Bounds in Clusters:</html><b>" ));

        //border color
        Border topBorder3 = BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(153, 218, 250));
        integerAssignmentGUI.setBorder(new TitledBorder(topBorder3,"<html><b> Optimal Integer Assignment:</html><b>" ));


        this.setLayout(new GridLayout(1,4));
        this.add(internalConstarinsClusterGUI);
        this.add(lowerBoundClusterGUI);
        this.add(upperBoundClusterGUI);
        this.add(integerAssignmentGUI);
        this.setOpaque(false);
    }

}
