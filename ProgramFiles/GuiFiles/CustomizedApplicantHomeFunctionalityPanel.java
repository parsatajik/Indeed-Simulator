package ProgramFiles.GuiFiles;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

abstract class CustomizedApplicantHomeFunctionalityPanel extends SuperPanel {

    CustomizedApplicantHomeFunctionalityPanel(){
        setPreferredSize(new Dimension(530, 480));
    }

    void makeLabelBold(JLabel label){
        Font lblFont = label.getFont();
        label.setFont(lblFont.deriveFont(lblFont.getStyle() ^ Font.BOLD));
    }

    abstract void init();
    abstract void createLayout();

}
