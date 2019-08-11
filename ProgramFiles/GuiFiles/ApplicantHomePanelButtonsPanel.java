package ProgramFiles.GuiFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantHomePanelButtonsPanel extends SuperPanel implements ActionListener {


    private ApplicantHomePanelButton btnMyAccount;
    private ApplicantHomePanelButton btnPostings;
    private ApplicantHomePanelButton btnStatus;
    private CustomizedBackButton btnBack;

    private PanelSwitchListener panelSwitchListener;

    ApplicantHomePanelButtonsPanel(){
        init();
        createLayout();
        btnMyAccount.addActionListener(this);
        btnPostings.addActionListener(this);
        btnStatus.addActionListener(this);
        btnBack.addActionListener(this);
    }

    private void init(){
        // set up the size and the color of panel
        setPreferredSize(new Dimension(100, 480));
        setBackground(new Color(0, 153, 255));
        setOpaque(true);
        //
        btnMyAccount = new ApplicantHomePanelButton("My Account");
        btnPostings = new ApplicantHomePanelButton("Postings");
        btnStatus = new ApplicantHomePanelButton("Status");
        btnBack = new CustomizedBackButton();
    }


    private void createLayout(){
        // set the layout
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        // put constrains
        layout.putConstraint(SpringLayout.WEST, btnMyAccount, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnMyAccount, 0 , SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, btnPostings, 0, SpringLayout.WEST, btnMyAccount);
        layout.putConstraint(SpringLayout.NORTH, btnPostings, 0, SpringLayout.SOUTH, btnMyAccount);

        layout.putConstraint(SpringLayout.WEST, btnStatus, 0, SpringLayout.WEST, btnPostings);
        layout.putConstraint(SpringLayout.NORTH, btnStatus, 0, SpringLayout.SOUTH, btnPostings);

        layout.putConstraint(SpringLayout.WEST, btnBack, -3, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnBack, 344, SpringLayout.SOUTH, btnPostings);
        // add the components
        add(btnMyAccount);
        add(btnPostings);
        add(btnStatus);
        add(btnBack);
    }

    void setPanelSwitchListener(PanelSwitchListener listener){
        this.panelSwitchListener = listener;
    }

    public void actionPerformed(ActionEvent e){
        JButton clicked = (JButton)e.getSource();
        if(clicked == btnMyAccount){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("ApplicantHomeMyAccountPanel");
        }
        else if(clicked == btnPostings){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("ApplicantHomePostingsPanel");
        }
        else if(clicked == btnStatus){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("ApplicantHomeStatusPanel");
        }
        else if(clicked == btnBack){
            if(panelSwitchListener != null)
                panelSwitchListener.panelEmitted("ApplicantLoginPanel");
        }
    }




}
