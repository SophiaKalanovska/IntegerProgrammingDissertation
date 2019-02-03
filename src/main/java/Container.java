import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import controller.*;
import model.InequalitiesList;
import view.GraphGUI;
import view.LayoutGUI;
import view.ManualInequalitiesGUI;
import view.RandomInequalitiesGUI;

/**
* Container is the class where the frame of the app is built.
* We then load panels to this frame.
*/
public class Container extends JFrame {

	public Container(){

        //class creation
        final InequalitiesList inequalitiesList = new InequalitiesList();
		GraphGUI graph = new GraphGUI();
		RandomInequalitiesGUI randomInequalitiesGUI = new RandomInequalitiesGUI();
        ManualInequalitiesGUI manualInequalitiesGUI = new ManualInequalitiesGUI(randomInequalitiesGUI);
        LayoutGUI graphgen = new LayoutGUI(this, graph, manualInequalitiesGUI, randomInequalitiesGUI);

        //controller creation
		GUILayoutController controller = new GUILayoutController(randomInequalitiesGUI,this, graph);
        ManualInequalitiesController manualInequalitiesController = new ManualInequalitiesController(inequalitiesList, manualInequalitiesGUI, graph);


        inequalitiesList.addObserver(manualInequalitiesGUI);
        inequalitiesList.tryUpdate();
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
