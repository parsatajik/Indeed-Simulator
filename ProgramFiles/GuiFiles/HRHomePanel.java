package ProgramFiles.GuiFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HRHomePanel extends SuperPanel implements ActionListener {

    private CardLayout cd = new CardLayout();

    private JPanel workArea = new JPanel(cd);

    private final String[] PANELS = {"WORK STATION", "HIRING STATION"};

    private JButton hrWorkStation = new JButton("Work Station");
    private JButton hiringStation = new JButton("Hiring Station");
    private BlackCustomizedBackButton back = new BlackCustomizedBackButton();

    HRHomePanel(){
        JToolBar options = new JToolBar();
        options.setLayout(new GridLayout(3, 1));
        options.setOrientation(JToolBar.VERTICAL);
        options.add(hrWorkStation);
        options.add(hiringStation);
        options.add(back);
        options.setFloatable(false);

        HRWorkPanel hrWorkPanel = new HRWorkPanel();
        HRHiringPanel hrHiringPanel = new HRHiringPanel();

        workArea.add(hrWorkPanel, PANELS[0]);
        workArea.add(hrHiringPanel, PANELS[1]);
        cd.show(workArea, PANELS[0]);

        hrWorkStation.addActionListener(this);
        hiringStation.addActionListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.weighty = 1;
        gc.weightx = 0.5;

        gc.gridx = 0;
        gc.gridwidth = 1;
        add(options, gc);

        gc.gridx = 1;
        gc.gridwidth = 9;
        gc.weightx = 1;
        add(workArea, gc);
    }

    public void actionPerformed(ActionEvent e){
        JButton click = (JButton) e.getSource();
        if (hrWorkStation == click){
            cd.show(workArea, PANELS[0]);
        } else if (hiringStation == click) {
            cd.show(workArea, PANELS[1]);
        } else if (back == click){
            getStorage().writeCompanies();
        }
    }
}
