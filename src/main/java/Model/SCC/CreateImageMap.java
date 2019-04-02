package Model.SCC;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class CreateImageMap extends Observable implements java.io.Serializable{
    private ArrayList lowerBoundContainer = new ArrayList();
    private ArrayList upperBoundContainer= new ArrayList();
    private ArrayList internalBoundContainer = new ArrayList();
    private ArrayList<Pair<Integer, SCCCluster>> integerAssignment= new ArrayList();
    private ArrayList<Pair<Integer, SCCCluster>> lambdaMinus = new ArrayList();
    private ArrayList<Model.SCC.SCCCluster> SCCCluster;
    protected Map<Double, ImageIcon> imageMap ;


    public CreateImageMap(SCCClusterList sccClusterList) {
        this.SCCCluster = sccClusterList.getProjectWallet();
    }

    public void clear() {
        lowerBoundContainer.clear();
        upperBoundContainer.clear();
        internalBoundContainer.clear();
    }

    public Map<Integer, ImageIcon> populate(){
        return createImageMap(SCCCluster);
    }

    private Map createImageMap(ArrayList<SCCCluster> SCC) {
        Map<Integer, ImageIcon> map = new HashMap<>();
        if (SCC.size() != 0) {
            for(int i = 0; i < SCC.size() ; i ++){
                BufferedImage bImg = new BufferedImage(40, 20, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = bImg.createGraphics();

                graphics.setPaint(SCC.get(i).getColor());
                graphics.fillRect(0, 0, bImg.getWidth(), bImg.getHeight());

                ImageIcon imageIcon = new ImageIcon(bImg);

                Pair pairInternal = new Pair<>(SCC.get(i).getId(), SCC.get(i).getInternalConstartins());
                Pair pairLower = new Pair<>(SCC.get(i).getId(), SCC.get(i).getLowerbound());
                Pair pairUpper = new Pair<>(SCC.get(i).getId(), SCC.get(i).getUpperbound());
                Pair pair = new Pair<>(SCC.get(i).getId(), SCC.get(i));
                integerAssignment.add(i, pair);
                lowerBoundContainer.add(i, pairLower);
                internalBoundContainer.add(i, pairInternal);
                upperBoundContainer.add(i, pairUpper);


                map.put(SCC.get(i).getId(), imageIcon);
            }
        } else {
            BufferedImage bImg = new BufferedImage(40, 20, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();

            graphics.setPaint(Color.BLACK);
            graphics.fillRect(0, 0, bImg.getWidth(), bImg.getHeight());

            ImageIcon imageIcon = new ImageIcon(bImg);

            map.put(0, imageIcon);
        }
        return map;
    }


    public ArrayList getLowerBoundContainer() {
        return lowerBoundContainer;
    }

    public ArrayList getUpperBoundContainer() {
        return upperBoundContainer;
    }

    public ArrayList getInternalBoundContainer() {
        return internalBoundContainer;
    }

    public ArrayList getProjectWalletCluster() {
        return integerAssignment;
    }

    public void tryUpdate() {
        setChanged();
        notifyObservers();
    }
}
