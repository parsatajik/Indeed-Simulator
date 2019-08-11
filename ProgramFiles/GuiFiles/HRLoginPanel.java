package ProgramFiles.GuiFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HRLoginPanel extends CustomizedLoginPanel implements ActionListener {

    private PanelSwitchListener panelSwitchListener;
    HRLoginPanel(){
        super();
        btnLogin.addActionListener(this);
        btnSignUp.addActionListener(this);
        btnBack.addActionListener(this);
    }


    void setPanelSwitchListener(PanelSwitchListener listener){
        this.panelSwitchListener = listener;
    }

    public void actionPerformed(ActionEvent e){

        JButton clicked = (JButton)e.getSource();
        if(clicked == btnLogin){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("HRHomePanel");
        }
        else if(clicked == btnSignUp){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("HRSignUp");
        }
        else if(clicked == btnBack){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("CompanyIntroPanel");
        }

    }
}
