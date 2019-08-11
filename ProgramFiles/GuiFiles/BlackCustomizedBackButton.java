package ProgramFiles.GuiFiles;

import javax.imageio.ImageIO;
import javax.swing.*;

class BlackCustomizedBackButton extends JButton {

    BlackCustomizedBackButton(){
        super();
        try {
            ImageIcon img = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("Back-Button.png")));
            setIcon(img);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
