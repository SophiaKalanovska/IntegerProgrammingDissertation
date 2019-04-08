package Model.SCC;

import javafx.util.Pair;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class BoundsListRender extends DefaultListCellRenderer {

    private Map<Integer, ImageIcon> imageMap;

    /**
     * takes the map that was send from the CreateImageMap class
     * and maps the cluster ID provided of the elements list to
     * the Image Icon List
     * */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, ((Pair) value).getValue(), index, isSelected, cellHasFocus);
        label.setIcon(imageMap.get(((Pair) value).getKey()));
        label.setHorizontalTextPosition(JLabel.RIGHT);
        return label;
    }

    /**
     * setsTheImageMap
     * */
    public void setImageMap(Map<Integer,ImageIcon> map) {
        imageMap = map;
    }

}
