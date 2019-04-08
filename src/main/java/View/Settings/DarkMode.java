package View.Settings;

import Controller.DarkModeController;
import View.LayoutGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
     * Home is the class where the Home panel is built implements Observer
     */
    public class DarkMode extends JPanel {
        private JCheckBox darkMode;

        /**
         * Creates a JCheckBox that has an DarkModeController attached.
         */
        public DarkMode(LayoutGUI layoutGUI){
            darkMode = new JCheckBox(new DarkModeController("Dark Mode", layoutGUI));
            darkMode.setMnemonic(KeyEvent.VK_C);
            darkMode.setSelected(false);
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.add(darkMode);
            this.setOpaque(false);
        }


        /**
         * This method gives information on whether the JCheckBox is selected or not.
         */
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

