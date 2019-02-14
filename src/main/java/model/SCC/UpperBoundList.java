package model.SCC;

import model.Inequalities.Inequality;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class UpperBoundList extends Observable implements java.io.Serializable{

    private ArrayList<Double> SCCLowerBoundContainer;
    private ArrayList<SCCCluster> SCCCluster;
    protected Map<Double, ImageIcon> imageMap ;



    /**
     * Creates a InequalitiesList object
     */
    public UpperBoundList(SCCClusterList SCCLowerBoundContainer) {
        this.SCCCluster = SCCLowerBoundContainer.getProjectWallet();
    }


//    public InequalitiesList(ArrayList<Inequality> data) {
//        storeProject(data);
//        setChanged();
//        notifyObservers();
//    }


    public Map<Double, ImageIcon> populate(){
        return createImageMap(SCCCluster);
    }



    private Map<Double, ImageIcon> createImageMap(ArrayList<SCCCluster> SCC) {
        Map<Double, ImageIcon> map = new HashMap<>();
        for(int i = 0; i < SCC.size() ; i ++){
            BufferedImage bImg = new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();

            graphics.setPaint(SCC.get(i).getColor());
            graphics.fillRect(0, 0, bImg.getWidth(), bImg.getHeight());

            ImageIcon imageIcon = new ImageIcon(bImg);
            map.put(SCC.get(i).getLowerbound(), imageIcon);
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


    public ArrayList<Double> getProjectWallet() {
        return SCCLowerBoundContainer;
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
