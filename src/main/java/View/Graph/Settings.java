package View.Graph;

import Controller.CheckboxActionController;
import View.LayoutGUI;
import org.graphstream.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
     * Home is the class where the Home panel is built implements Observer
     */
    public class Settings extends JPanel {

        private JCheckBox darkMode;

        /**
         * Constructs a new Home panel
         *
         */
        public Settings(LayoutGUI layoutGUI){
            darkMode = new JCheckBox(new CheckboxActionController("Dark Mode", layoutGUI));
            darkMode.setMnemonic(KeyEvent.VK_C);
            darkMode.setSelected(false);

            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.add(darkMode);
            this.setOpaque(false);
        }

        public boolean isSelected(){
            if ( darkMode.isSelected()){
                return true;
            }else{
                return false;
            }
        }


        public JCheckBox getDarkMode(){
            return darkMode;
        }




    }

