package ProgramFiles.GuiFiles;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ProgramFiles.Applicant;

public class ApplicantHomeMyAccountPanel extends CustomizedApplicantHomeFunctionalityPanel implements ActionListener {

    /**
     *
     */



    private JTextArea resume;
    private JScrollPane resumeScroller;
    private JTextArea coverLetter;
    private JScrollPane coverLetterScroller;
    private JLabel lblResume;
    private JLabel lblCoverLetter;
    private JButton btnSave;

    ApplicantHomeMyAccountPanel(){

        super();
        init();
        createLayout();
        btnSave.addActionListener(this);

    }

    /**
     *
     */
    void init(){
        setLayout(new GridBagLayout());
        // created resume box and added scroll bar
        resume = new JTextArea(10, 42);
        resumeScroller = new JScrollPane(resume);
        // created cover letter box and added scroll bar
        coverLetter = new JTextArea(10, 42);
        coverLetterScroller = new JScrollPane(coverLetter);
        //
        lblResume = new JLabel("Resume"); makeLabelBold(lblResume);
        lblCoverLetter = new JLabel("Cover Letter"); makeLabelBold(lblCoverLetter);
        btnSave = new JButton("Save");
    }

    /**
     *
     */

    void createLayout(){
        GridBagConstraints gc = new GridBagConstraints();
        // adding lblResume
        gc.gridx = 0; gc.gridy = 0;
        gc.weightx = 0.3; gc.weighty = 0.1;
        gc.insets = new Insets(5, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblResume, gc);
        // adding resumeScroller
        gc.gridy++;
        gc.weighty = 0.3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(resumeScroller, gc);
        // adding lblCoverLetter
        gc.gridy++;
        gc.weighty = 0.1;
        add(lblCoverLetter, gc);
        // adding coverLetterScroller
        gc.gridy++;
        gc.weighty = 0.3;
        add(coverLetterScroller, gc);
        // adding btnSave
        gc.weighty = 0.5;
        gc.gridy++;
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0, 0, 25, 0);
        add(btnSave, gc);

    }


    /**
     *
     */
    void updateFields() {
        resume.setText(((Applicant) SuperPanel.getCurrentUser()).getResume());
        coverLetter.setText(((Applicant) SuperPanel.getCurrentUser()).getCoverLetter());
    }

    /**
     *
     */
    private void saveInfo() {

        ((Applicant) SuperPanel.getCurrentUser()).setResume(resume.getText());
        ((Applicant) SuperPanel.getCurrentUser()).setCoverLetter(coverLetter.getText());
        SuperPanel.getStorage().writeApplicants();

    }

    /**
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        JButton clicked = (JButton)e.getSource();
        if(clicked == btnSave)
            saveInfo();
    }



}




