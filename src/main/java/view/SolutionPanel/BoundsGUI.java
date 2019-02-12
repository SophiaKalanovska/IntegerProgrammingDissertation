package view.SolutionPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BoundsGUI extends JPanel{

    public BoundsGUI(LowerBoundClusterGUI lowerBoundClusterGUI, UpperBoundClusterGUI upperBoundClusterGUI){

        Border topBorder3 = BorderFactory.createMatteBorder(1, 1, 1, 0, new Color(153, 218, 250));
        Border topBorder4 = new TitledBorder(topBorder3,"<html><b> Lower Bounds in Clusters:</html><b>" );
        lowerBoundClusterGUI.setBorder(topBorder4);


        Border topBorder5 = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(153, 218, 250));
        Border topBorder6 = new TitledBorder(topBorder5,"<html><b> Upper Bounds in Clusters:</html><b>" );
        upperBoundClusterGUI.setBorder(topBorder6);


        this.setLayout(new GridLayout(1,2));
        this.add(lowerBoundClusterGUI);
        this.add(upperBoundClusterGUI);
        this.setBackground(Color.WHITE);
    }

}
