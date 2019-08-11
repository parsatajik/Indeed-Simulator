package ProgramFiles.GuiFiles;

import javax.swing.*;
import java.awt.*;

class ApplicantHomePanelButton extends JButton {

    ApplicantHomePanelButton(String name){
        super(name);
        setPreferredSize(new Dimension(100, 33));
        setBackground(new Color(0, 153, 255));
        setOpaque(true);
    }

}

