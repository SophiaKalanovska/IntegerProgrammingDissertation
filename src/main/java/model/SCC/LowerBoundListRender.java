package model.SCC;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LowerBoundListRender  extends DefaultListCellRenderer {
    Font font = new Font("helvitica", Font.BOLD, 24);
    private Map<Integer, ImageIcon> imageMap;



    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setIcon(imageMap.get((Integer) value));
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        return label;
    }


    public void setImageMap(Map<Integer,ImageIcon> map) {
        imageMap = map;
    }
}
