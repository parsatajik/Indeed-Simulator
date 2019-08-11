package ProgramFiles.GuiFiles;

import ProgramFiles.UserApplication;
import ProgramFiles.Applicant;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ApplicantHomeStatusPanel extends CustomizedApplicantHomeFunctionalityPanel implements ActionListener, ListSelectionListener {


    private JLabel lblAppliedJobs;
    private JLabel lblStatus;
    private JTextField appliedJobStatus;
    private JList<UserApplication> appliedJobs;
    private JScrollPane appliedJobsScroller;
    private JButton btnShowStatus;
    private JButton btnRemoveApplication;
    private UserApplication currentApplication;
    private DefaultListModel<UserApplication> dlmApplications;

    ApplicantHomeStatusPanel(){
        super();
        init();
        createLayout();
        btnShowStatus.addActionListener(this);
        btnRemoveApplication.addActionListener(this);
        appliedJobs.getSelectionModel().addListSelectionListener(this);
    }

    void init(){
        lblAppliedJobs = new JLabel("Applied Jobs"); makeLabelBold(lblAppliedJobs);
        lblStatus = new JLabel("Status"); makeLabelBold(lblStatus);
        // create the list of applied jobs and add scrollbar
        dlmApplications = new DefaultListModel<>();
        appliedJobs = new JList<>(dlmApplications);
        appliedJobs.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        appliedJobsScroller = new JScrollPane(appliedJobs);
        //
        appliedJobStatus = new JTextField(); appliedJobStatus.setEditable(false);
        btnShowStatus = new JButton("Show Status");
        btnRemoveApplication = new JButton("Remove Application");
    }

    void createLayout(){
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        // add lblAppliedJobs
        gc.gridx = 0; gc.gridy = 0;
        gc.weighty = 0.1; gc.weightx = 0.7;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.fill = GridBagConstraints.BOTH;
        add(lblAppliedJobs, gc);
        // add appliedJobsScroller
        gc.gridy++;
        gc.weighty = 0.6;
        add(appliedJobsScroller, gc);
        // add btnShowStatus
        gc.gridy++;
        gc.weighty = 0.3;
        add(btnShowStatus, gc);
        // add lblStatus
        gc.gridx++; gc.gridy = 0;
        gc.weighty = 0.1; gc.weightx = 0.3;
        add(lblStatus, gc);
        // add appliedJobStatus
        gc.gridy++;
        gc.weighty = 0.5;
        add(appliedJobStatus, gc);
        // add btnRemoveApplication
        gc.gridy++;
        gc.weighty = 0.4;
        add(btnRemoveApplication, gc);
    }

    public void actionPerformed(ActionEvent e){
        JButton clicked = (JButton)e.getSource();
        if(clicked == btnRemoveApplication){
            if(currentApplication != null){
                dlmApplications.removeElement(currentApplication);
                ArrayList<UserApplication> newApplications = ((Applicant)getCurrentUser()).getApplications();
                newApplications.remove(currentApplication);
                ((Applicant)getCurrentUser()).setApplications(newApplications);
            }
        }
        else if(clicked == btnShowStatus){
            if(currentApplication != null){
                if (currentApplication.getHirestatus()) appliedJobStatus.setText("Hired!");
                else appliedJobStatus.setText("Not Hired!");
            }
        }
        getStorage().writeApplicants();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if(lsm == appliedJobs.getSelectionModel())
            currentApplication = appliedJobs.getSelectedValue();
    }


    void updateStatusPanel() {
        dlmApplications.clear();
        for (UserApplication application : ((Applicant) SuperPanel.getCurrentUser()).getApplications()) {
            if (application.getClosingDate() == null) {
                dlmApplications.addElement(application);
            }
        }
    }


}
