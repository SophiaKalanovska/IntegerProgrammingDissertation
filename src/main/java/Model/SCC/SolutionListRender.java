package Model.SCC;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SolutionListRender extends DefaultListCellRenderer {

    private Map<Integer, ImageIcon> imageMap;



    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, ((Pair) value).getValue(), index, isSelected, cellHasFocus);
        label.setIcon(imageMap.get((Integer) ((Pair) value).getKey()));
        label.setHorizontalTextPosition(JLabel.RIGHT);
        return label;
    }


    public void setImageMap(Map<Integer,ImageIcon> map) {
        imageMap = map;
    }


}
