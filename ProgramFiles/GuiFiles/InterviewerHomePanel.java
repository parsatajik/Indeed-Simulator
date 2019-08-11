package ProgramFiles.GuiFiles;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterviewerHomePanel extends SuperPanel implements ActionListener {

    private CardLayout cd = new CardLayout();

    private JPanel workStation = new JPanel(cd);
    private InterviewerWorkPanel interviewerWorkPanel = new InterviewerWorkPanel();

    private JButton toWorkPanel = new JButton("Work Station");
    private BlackCustomizedBackButton back = new BlackCustomizedBackButton();

    private final String WORK_PANEL = "interviewerWorkPanel";

    InterviewerHomePanel(){

        setBackground(Color.white);
        setVisible(true);
        setBorder(new BevelBorder(BevelBorder.RAISED));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        TitledBorder border = new TitledBorder("Interviewer Home Page");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        setBorder(border);

        JToolBar homeToolBar = new JToolBar();

        homeToolBar.setOrientation(JToolBar.VERTICAL);
        homeToolBar.setFloatable(false);
        homeToolBar.setLayout(new GridLayout(2, 1));
        homeToolBar.add(toWorkPanel, 0);
        homeToolBar.add(back, 1);

        workStation.add(interviewerWorkPanel, WORK_PANEL);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;

        c.weightx = 0.2;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridwidth = 1;
        add(homeToolBar, c);

        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridwidth = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(workStation, c);
    }

    public void actionPerformed(ActionEvent e){
        JButton clicked = (JButton)e.getSource();
        if (clicked == toWorkPanel){
            interviewerWorkPanel.updateWorkPanel();
            cd.show(workStation, WORK_PANEL);
        } else if (clicked == back){
            getStorage().writeCompanies();
        }
    }
}
