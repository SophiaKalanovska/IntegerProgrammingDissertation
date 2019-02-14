package model.SCC;

import controller.GraphController;
import javafx.util.Pair;
import model.Inequalities.Inequality;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class LowerBoundList extends Observable implements java.io.Serializable {
    private ArrayList<Pair<Integer, Double>> sccLowerBoundContainer;
    private ArrayList<SCCCluster> SCCCluster;
    protected Map<Double, ImageIcon> imageMap ;



    /**
     * Creates a InequalitiesList object
     */
    public LowerBoundList(SCCClusterList SCCLowerBoundContainer) {
        this.sccLowerBoundContainer = new ArrayList<>();
        this.SCCCluster = SCCLowerBoundContainer.getProjectWallet();
    }


//    public InequalitiesList(ArrayList<Inequality> data) {
//        storeProject(data);
//        setChanged();
//        notifyObservers();
//    }


    public Map<Integer, ImageIcon> populate(){
//        tryUpdate();
        return createImageMap(SCCCluster);

    }



    private Map<Integer, ImageIcon> createImageMap(ArrayList<SCCCluster> SCC) {
        Map<Integer, ImageIcon> map = new HashMap<>();
        for(int i = 0; i < SCC.size() ; i ++){
            BufferedImage bImg = new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();

            graphics.setPaint(SCC.get(i).getColor());
            graphics.fillRect(0, 0, bImg.getWidth(), bImg.getHeight());

            ImageIcon imageIcon = new ImageIcon(bImg);

            Pair pair = new Pair<>(SCC.get(i).getId(), SCC.get(i).getLowerbound());
            sccLowerBoundContainer.add(i, pair);
            map.put(SCC.get(i).getId(), imageIcon);
        }
        return map;

    }



    /**
     * Deletes a project that has the same name as the one supplied
     *
     * @param x the name of the projects that has to be deleted
     */
    public void deleteInequality(Inequality x) {

    }


    public ArrayList<Pair<Integer,Double>> getProjectWallet() {
        return sccLowerBoundContainer;
    }


//    public void storeProject(ArrayList<Inequality> data) {
//        this.inequalitiesContainer = data;
//
//    }

    /**
     * Sends signal to the observers to update the view
     */
    public void tryUpdate() {
        setChanged();
        notifyObservers();
    }
}
