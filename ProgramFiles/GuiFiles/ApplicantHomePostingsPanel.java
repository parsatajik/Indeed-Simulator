package ProgramFiles.GuiFiles;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ProgramFiles.Posting;
import ProgramFiles.Applicant;

public class ApplicantHomePostingsPanel extends CustomizedApplicantHomeFunctionalityPanel implements ActionListener, ListSelectionListener {


    private JLabel lblJobs;
    private JLabel lblJobDescription;
    private JList<Posting> jobs;
    private JScrollPane jobsScroller;
    private JScrollPane jobDescriptionScroller;
    private JTextField jobDescription;
    private JButton btnApply;
    private JButton btnShowDescription;
    private Posting currentPosting;
    private DefaultListModel<Posting> dlmPostings;

    ApplicantHomePostingsPanel(){
        super();
        init();
        createLayout();
        btnApply.addActionListener(this);
        btnShowDescription.addActionListener(this);
        jobs.getSelectionModel().addListSelectionListener(this);
    }

    void init(){
        setLayout(new GridBagLayout());
        // create the list of current jobs and add a scroll bar
        dlmPostings = new DefaultListModel<>();
        jobs = new JList<>(dlmPostings);
        jobs.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jobsScroller = new JScrollPane(jobs);
        // add border to jobDescription
        jobDescription = new JTextField(); jobDescription.setEditable(false);
        jobDescriptionScroller = new JScrollPane(jobDescription);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); jobDescription.setBorder(border);
        //
        lblJobs = new JLabel("Jobs"); makeLabelBold(lblJobs);
        lblJobDescription = new JLabel("Description"); makeLabelBold(lblJobDescription);
        btnApply = new JButton("Apply");
        btnShowDescription = new JButton("Show Description");
    }

    void createLayout(){
        GridBagConstraints gc = new GridBagConstraints();
        // adding lblJobs
        gc.gridx = 0; gc.gridy = 0;
        gc.weighty = 0.05; gc.weightx = 0.3;
        gc.insets = new Insets(0, 0, 5, 0);
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lblJobs, gc);
        // adding jobsScroller
        gc.gridy++;
        gc.weighty = 0.85;
        add(jobsScroller, gc);
        // adding btnShowDescription
        gc.gridy++;
        gc.weighty = 0.1;
        gc.insets.bottom = 20;
        add(btnShowDescription, gc);
        // adding lblJobDescription
        gc.gridy = 0; gc.gridx++;
        gc.weightx = 0.7; gc.weighty = 0.05;
        gc.insets.left = 10; gc.insets.bottom = 5;
        add(lblJobDescription, gc);
        // adding jobDescription
        gc.gridy++;
        gc.weighty = 0.85;
        add(jobDescriptionScroller, gc);
        // adding btnApply
        gc.gridy++;
        gc.weighty = 0.1;
        gc.insets.bottom = 20;
        add(btnApply, gc);
    }


    void updatePostingsPanel() {

        dlmPostings.clear();
        for(Posting p: SuperPanel.getStorage().getCompanies().get(0).getAllPostings())
            dlmPostings.addElement(p);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton clicked = (JButton)e.getSource();
        if(currentPosting == null) return;
        if(clicked == btnShowDescription) showDescription(currentPosting);
        else if(clicked == btnApply) apply(currentPosting);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if(lsm == jobs.getSelectionModel()) {
            currentPosting = jobs.getSelectedValue();
            System.out.println(currentPosting);
        }
    }

    private void showDescription(Posting currentPosting){
        jobDescription.setText(currentPosting.getJobDescription());
    }

    private void apply(Posting posting) {
        ((Applicant) SuperPanel.getCurrentUser()).apply(posting);
        SuperPanel.getStorage().writeApplicants();
    }


}
