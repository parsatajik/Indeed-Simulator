package ProgramFiles.GuiFiles;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import ProgramFiles.*;

public class HRHiringPanel extends SuperPanel implements ActionListener, ListSelectionListener {

    private DefaultListModel<UserApplication> recommendedApplicationsDLM = new DefaultListModel<>();
    private DefaultListModel<Posting> postingsDLM = new DefaultListModel<>();
    private JList<UserApplication> recommendedApplications = new JList<>(recommendedApplicationsDLM);
    private JList<Posting> postings = new JList<>(postingsDLM);
    private JScrollPane recommended = new JScrollPane(recommendedApplications);
    private JScrollPane postingsPane = new JScrollPane(postings);
    private JButton hire = new JButton("Hire selected");
    private JButton addPosting = new JButton("Add posting");
    private UserApplication currentApplication;
    private Posting currentPosting;

    HRHiringPanel(){

        init();
        createLayout();
        hire.addActionListener(this);
        addPosting.addActionListener(this);

    }

    private void init(){
        setBackground(Color.white);
        setVisible(true);
        TitledBorder border = new TitledBorder("Hiring for a position");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        setBorder(border);
        // add borders for recommended and postings pane
        recommended.setBorder(new TitledBorder("Recommended applications"));
        postingsPane.setBorder(new TitledBorder("All postings"));
        // set the selection mode of the JLists to single selection
        postings.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recommendedApplications.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void createLayout(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // add postingsPane
        c.gridx = 0; c.gridy = 0;
        c.weightx = 1; c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        add(postingsPane, c);
        // add recommended JList
        c.gridx = 1;
        add(recommended, c);
        // add addPosting
        c.gridx = 0; c.gridy = 1;
        c.weighty = 0.3;
        add(addPosting, c);
        // add hire
        c.gridx++;
        add(hire, c);
    }


    public void actionPerformed(ActionEvent e){
        JButton click = (JButton) e.getSource();
        if (click == addPosting){
            new HRPostingDialog(this);
        } else if (click == hire){
            if (currentPosting != null && currentApplication != null){
                hire(currentPosting, currentApplication);
            }
        }
        getStorage().writeCompanies();
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (lsm == recommendedApplications.getSelectionModel()){
            currentApplication = recommendedApplications.getSelectedValue();
        }
        if (lsm == postings.getSelectionModel()){
            currentPosting = postings.getSelectedValue();
            updateRecommended(currentPosting);
        }
    }

    void addPosting(Posting created){
        try {
            getCompany().addPosting(created);
            postingsDLM.addElement(created);
            SuperPanel.getStorage().writeCompanies();
        } catch (NullPointerException e){
            System.out.println("PArsing error");
        }
    }

    private Company getCompany(){
        Storage now = getStorage();
        return now.getCompanies().get(0);
    }

    private void updateRecommended(Posting posting) {

        recommendedApplicationsDLM.removeAllElements();
        for (UserApplication application : posting.GetRemainApplications()) {
            recommendedApplicationsDLM.addElement(application);
        }

    }

    private void hire(Posting posting, UserApplication application) {

        posting.SetHiredApplicant(application);
        posting.ChangePostingStatus(false);
        application.setHirestatus(true);
        application.CloseApplication(LocalDate.now());
        SuperPanel.getStorage().writeCompanies();

    }

}
