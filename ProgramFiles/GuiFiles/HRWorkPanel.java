package ProgramFiles.GuiFiles;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ProgramFiles.*;

public class HRWorkPanel extends SuperPanel implements ActionListener, ListSelectionListener {
    private DefaultListModel<Interviewer> interviewsDLM = new DefaultListModel<>();
    private DefaultListModel<UserApplication> applicationsDLM = new DefaultListModel<>();

    private JList<Interviewer> interviewers = new JList<>(interviewsDLM);
    private JList<UserApplication> applications = new JList<>(applicationsDLM);

    private JButton addPostingsToInterviewer = new JButton("Add postings to Interviewer");

    private Interviewer currentInterviewer;
    private UserApplication currentApplication;

    HRWorkPanel(){

        JScrollPane interviewerScroll = new JScrollPane(interviewers);
        JScrollPane applicationScroll = new JScrollPane(applications);

        interviewerScroll.setBorder(new TitledBorder("Interviewers"));
        applicationScroll.setBorder(new TitledBorder("Postings"));

        interviewers.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        applications.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        updateWorkPanel(getStorage().getCompanies().get(0));

        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Add applications for Interviewers"));
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridy = 0;

        gc.gridx = 0;
        add(interviewerScroll, gc);

        gc.gridx = 1;
        add(applicationScroll, gc);

        gc.gridy = 1;

        gc.gridx = 0;
        gc.weighty = 0.3;
        add(addPostingsToInterviewer, gc);

    }

    public void actionPerformed(ActionEvent e){
        JButton click = (JButton) e.getSource();
        if (click == addPostingsToInterviewer){
            if (currentInterviewer != null && currentApplication != null){
                submitForInterview(currentInterviewer, currentApplication);
                applicationsDLM.removeElement(currentApplication);
            }
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (lsm == applications.getSelectionModel()){
            currentApplication = applications.getSelectedValue();
        } if (lsm == interviewers.getSelectionModel()) {
            currentInterviewer = interviewers.getSelectedValue();
        }
    }

    private void updateWorkPanel(Company company) {

        interviewsDLM.clear();
        applicationsDLM.clear();

        for (Interviewer interviewer : company.getAllInterviewers()) {
            interviewsDLM.addElement(interviewer);
        }
        for (Posting posting : company.getAllPostings()) {
            if (posting.getPostStatus()) {
                for (UserApplication application : posting.GetRemainApplications()) {
                    if (application.getReviewstatus())
                        applicationsDLM.addElement(application);
                }
            }
        }

    }

    private void submitForInterview(Interviewer interviewer, UserApplication application) {
        interviewer.AddApplication(application);
        SuperPanel.getStorage().writeCompanies();
    }

}
