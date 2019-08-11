package ProgramFiles;

import ProgramFiles.GuiFiles.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });


    }


}

