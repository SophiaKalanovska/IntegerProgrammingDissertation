package view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import controller.*;

/**
* Container is the class where the frame of the app is built.
* We then load panels to this frame.
*/
public class Container extends JFrame {

	public Container(){

		//ProjectWallet info = new ProjectWallet();
		// Home home = new Home(this);

        //class creation
		GraphGenerator graph = new GraphGenerator();
		ManualInequalitiesGUI manualInequalitiesGUI = new ManualInequalitiesGUI();
		RandomInequalitiesGUI randomInequalitiesGUI = new RandomInequalitiesGUI();
        GUILayout graphgen = new GUILayout(this, graph, manualInequalitiesGUI, randomInequalitiesGUI);

        //controller creation
		GUILayoutController controller = new GUILayoutController(randomInequalitiesGUI,this, graph);
        ManualInequalitiesController manualInequalitiesController = new ManualInequalitiesController(manualInequalitiesGUI, graph);
        setPanel(graphgen);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(900, 600));
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	/**
	* Loads a new panel to the frame
	*
	* @param  p  the panel to be loaded into the frame
	*/
	public void setPanel(JPanel p){
		this.getContentPane().invalidate();
		this.getContentPane().removeAll();
		this.getContentPane().add(p);
		this.revalidate();
		this.repaint();
	}

}
