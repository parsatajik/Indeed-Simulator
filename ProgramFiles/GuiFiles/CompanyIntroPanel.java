package ProgramFiles.GuiFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyIntroPanel extends JPanel implements ActionListener {

    private JButton btnHR;
    private JButton btnInterviewer;
    private CustomizedBackButton backButton;

    private PanelSwitchListener panelSwitchListener;


    CompanyIntroPanel(){


        setBackground(new Color(0, 153, 255));

        btnHR = new JButton("HR");
        btnHR.setPreferredSize(new Dimension(100, 50));

        btnInterviewer = new JButton("Interviewer");
        btnInterviewer.setPreferredSize(new Dimension(100 ,50));

        backButton = new CustomizedBackButton();

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.ipadx = 50;
        gc.insets = new Insets(10, 20, 10, 20);
        add(btnInterviewer, gc);

        gc.gridx++;
        add(btnHR, gc);

        gc.gridx++;
        add(backButton, gc);

        btnHR.addActionListener(this);
        btnInterviewer.addActionListener(this);
        backButton.addActionListener(this);


    }

    void setPanelSwitchListener(PanelSwitchListener listener){ panelSwitchListener = listener; }

    public void actionPerformed(ActionEvent e){
        JButton clicked = (JButton)e.getSource();
        if(clicked == btnHR){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("HRLoginPanel");
        }
        else if(clicked == btnInterviewer){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("InterviewerLoginPanel");
        }
        else if(clicked == backButton){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("IntroPanel");
        }
    }





}
