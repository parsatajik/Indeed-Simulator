package ProgramFiles.GuiFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroPanel extends SuperPanel implements ActionListener{


    private JButton btnCompany;
    private JButton btnApplicant;

    private PanelSwitchListener panelSwitchListener;

    IntroPanel(){

        setBackground(new Color(0, 153, 255));

        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        btnCompany = new JButton("Company");
        btnApplicant = new JButton("Applicant");



        btnCompany.setPreferredSize(new Dimension(100, 50));
        btnApplicant.setPreferredSize(new Dimension(100, 50));

        btnApplicant.addActionListener(this);
        btnCompany.addActionListener(this);

        layout.putConstraint(SpringLayout.WEST, btnCompany, 270 , SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnCompany, 155, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, btnApplicant, 20, SpringLayout.SOUTH, btnCompany);
        layout.putConstraint(SpringLayout.WEST, btnApplicant, 0, SpringLayout.WEST, btnCompany);
        
        add(btnApplicant);
        add(btnCompany);

    }

    void setPanelSwitchListener(PanelSwitchListener listener){
        this.panelSwitchListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();
        if(clicked == btnCompany) {
            if (panelSwitchListener != null)
                panelSwitchListener.panelEmitted("CompanyIntroPanel");
        }
        else if(clicked == btnApplicant) {
            if (panelSwitchListener != null)
                panelSwitchListener.panelEmitted("ApplicantLoginPanel");
        }

    }
}
