package ProgramFiles.GuiFiles;

import ProgramFiles.Storage;

import ProgramFiles.Applicant;
import ProgramFiles.Interviewer;
import ProgramFiles.HR;

import ProgramFiles.UserExistsException;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JLayeredPane mainPane;
    private IntroPanel introPanel;
    private ApplicantLoginPanel applicantLoginPanel;
    private ApplicantHomePanel applicantHomePanel;
    private CompanyIntroPanel companyIntroPanel;
    private HRLoginPanel hrLoginPanel;
    private HRHomePanel hrHomePanel;
    private InterviewerLoginPanel interviewerLoginPanel;
    private InterviewerHomePanel interviewerHomePanel;
    private Storage storage;

    private static final String INTRO = "IntroPanel";
    private static final String COMPANY_INTRO = "CompanyIntroPanel";
    private static final String APPLICANT_LOGIN = "ApplicantLoginPanel";
    private static final String APPLICANT_HOME = "ApplicantHomePanel";
    private static final String APPLICANT_SIGN_UP = "ApplicantSignUp";
    private static final String HR_LOGIN = "HRLoginPanel";
    private static final String HR_SIGN_UP = "HRSignUp";
    private static final String HR_HOME = "HRHomePanel";
    private static final String INTERVIEWER_LOGIN = "InterviewerLoginPanel";
    private static final String INTERVIEWER_HOME = "InterviewerHomePanel";
    private static final String INTERVIEWER_SIGN_UP = "InterviewerSignUp";

    public MainFrame(){

        super("Indeed Simulator");
        init();
        createLayout();

        introPanel.setPanelSwitchListener(new PanelSwitchListener() {
            @Override
            public void panelEmitted(String panel) {
                System.out.println(panel);
                switch (panel){
                    case APPLICANT_LOGIN: switchPanels(applicantLoginPanel, panel); break;
                    case COMPANY_INTRO: switchPanels(companyIntroPanel, panel); break;
                } }
        });

        applicantLoginPanel.setPanelSwitchListener(new PanelSwitchListener() {
            @Override
            public void panelEmitted(String panel) {
                System.out.println(panel);
                Applicant applicant;
                switch (panel){
                    case APPLICANT_SIGN_UP:
                        try {
                            applicant = storage.addApplicant(applicantLoginPanel.getUsername(), applicantLoginPanel.getPassword(), null, null);
                            storage.writeApplicants();
                            SuperPanel.setCurrentUser(applicant);
                            switchPanels(applicantHomePanel, panel);
                        }
                        catch (UserExistsException e){
                            System.out.println(e);
                        }
                        break;
                    case APPLICANT_HOME:
                        applicant = storage.loginApplicant(applicantLoginPanel.getUsername(), applicantLoginPanel.getPassword());
                        if (applicant != null) {
                            SuperPanel.setCurrentUser(applicant);
                            switchPanels(applicantHomePanel, panel);
                        }
                        break;
                    case INTRO: switchPanels(introPanel, panel); break;
                }
            }
        });


        companyIntroPanel.setPanelSwitchListener(new PanelSwitchListener() {
            @Override
            public void panelEmitted(String panel) {
                System.out.println(panel);
                switch (panel){
                    case INTERVIEWER_LOGIN: switchPanels(interviewerLoginPanel, panel); break;
                    case HR_LOGIN: switchPanels(hrLoginPanel, panel); break;
                    case INTRO: switchPanels(introPanel, panel); break;
                } }
        });

        interviewerLoginPanel.setPanelSwitchListener(new PanelSwitchListener() {
            @Override
            public void panelEmitted(String panel) {
                Interviewer interviewer;
                System.out.println(panel);
                switch (panel){
                    case INTERVIEWER_HOME:
                        interviewer = storage.loginInterviewer(interviewerLoginPanel.getUsername(), interviewerLoginPanel.getPassword());
                        if (interviewer != null) {
                            SuperPanel.setCurrentUser(interviewer);
                            switchPanels(interviewerHomePanel, panel);
                        }
                        break;
                    case INTERVIEWER_SIGN_UP:
                        try {
                            interviewer = storage.getCompanies().get(0).addInterviewer(new Interviewer(interviewerLoginPanel.getUsername(), interviewerLoginPanel.getPassword()));
                            storage.writeCompanies();
                            SuperPanel.setCurrentUser(interviewer);
                            switchPanels(interviewerHomePanel, panel);
                        } catch (UserExistsException e) {
                            System.out.println(e.toString());
                        }
                        break;
                    case COMPANY_INTRO: switchPanels(companyIntroPanel, panel);
                }
            }
        });

        hrLoginPanel.setPanelSwitchListener(new PanelSwitchListener() {
            @Override
            public void panelEmitted(String panel) {
                HR hr;
                System.out.println(panel);
                switch(panel){
                    case HR_HOME:
                        hr = storage.loginHR(hrLoginPanel.getUsername(), hrLoginPanel.getPassword());
                        if (hr != null) {
                            SuperPanel.setCurrentUser(hr);
                            switchPanels(hrHomePanel, panel);
                        }
                        break;
                    case HR_SIGN_UP:
                        hr = new HR(hrLoginPanel.getUsername(), hrLoginPanel.getPassword(), storage.getCompanies().get(0).getAllPostings());
                        try {
                            storage.getCompanies().get(0).addHR(hr);
                            SuperPanel.setCurrentUser(hr);
                            storage.writeCompanies();
                            switchPanels(hrHomePanel, panel);
                        } catch (UserExistsException e) {
                            System.out.println(e.toString());
                        }
                        break;
                    case COMPANY_INTRO: switchPanels(companyIntroPanel, panel); break;

                }
            }
        });





    }


    private void init(){
        // initialize the properties of MainFrame
        setSize(640, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // instantiation
        storage = new Storage();
        if (storage.getCompanies().size() == 0)
            storage.addCompany("Wahoo");
        SuperPanel.setStorage(storage);
        mainPane = new JLayeredPane(); mainPane.setLayout(new CardLayout());
        introPanel = new IntroPanel();
        applicantLoginPanel = new ApplicantLoginPanel();
        companyIntroPanel = new CompanyIntroPanel();
        applicantHomePanel = new ApplicantHomePanel();
        interviewerLoginPanel = new InterviewerLoginPanel();
        interviewerHomePanel = new InterviewerHomePanel();
        hrLoginPanel = new HRLoginPanel();
        hrHomePanel = new HRHomePanel();
    }

    private void createLayout(){
        add(mainPane);
        mainPane.add(introPanel, INTRO);
        mainPane.add(applicantLoginPanel, APPLICANT_LOGIN);
        mainPane.add(companyIntroPanel, COMPANY_INTRO);
        mainPane.add(applicantHomePanel, APPLICANT_HOME);
        mainPane.add(interviewerLoginPanel, INTERVIEWER_LOGIN);
        mainPane.add(interviewerHomePanel, INTERVIEWER_HOME);
        mainPane.add(hrLoginPanel, HR_LOGIN);
        mainPane.add(hrHomePanel, HR_HOME);
    }

    private void switchPanels(JPanel panel, String description){
        mainPane.removeAll();
        mainPane.add(panel, description);
        mainPane.repaint();
        mainPane.revalidate();
    }



}
