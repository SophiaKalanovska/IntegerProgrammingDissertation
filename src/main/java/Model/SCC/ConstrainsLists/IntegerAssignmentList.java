package Model.SCC.ConstrainsLists;

import Model.SCC.SCCCluster;
import Model.SCC.SCCClusterList;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class IntegerAssignmentList extends Observable implements java.io.Serializable {
    private ArrayList<Pair<Integer, Integer>> sccIntegerAssignmentContainer;
    private ArrayList<Model.SCC.SCCCluster> SCCCluster;
    protected Map<Double, ImageIcon> imageMap ;



    /**
     * Creates a InequalitiesList object
     */
    public IntegerAssignmentList(SCCClusterList SCCInternalConstarinsContainer) {
        this.sccIntegerAssignmentContainer = new ArrayList<>();
        this.SCCCluster = SCCInternalConstarinsContainer.getProjectWallet();
    }

    public Map<Integer, ImageIcon> populate(){
        return createImageMap(SCCCluster);

    }

    private Map<Integer, ImageIcon> createImageMap(ArrayList<SCCCluster> SCC) {
        Map<Integer, ImageIcon> map = new HashMap<>();
        for(int i = 0; i < SCC.size() ; i ++){
            BufferedImage bImg = new BufferedImage(40, 20, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();

            graphics.setPaint(SCC.get(i).getColor());
            graphics.fillRect(0, 0, bImg.getWidth(), bImg.getHeight());

            ImageIcon imageIcon = new ImageIcon(bImg);

            Pair pair = new Pair<>(SCC.get(i).getId(), SCC.get(i).getLambdaMinus());
            sccIntegerAssignmentContainer.add(i, pair);
            map.put(SCC.get(i).getId(), imageIcon);
        }
        return map;

    }

    public ArrayList<Pair<Integer,Integer>> getProjectWallet() {
        return sccIntegerAssignmentContainer;
    }



    /**
     * Sends signal to the observers to update the View
     */
    public void tryUpdate() {
        setChanged();
        notifyObservers();
    }
}
