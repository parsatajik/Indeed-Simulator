package ProgramFiles;


// Parsa - I commented out the line below since it didnt allow the program to run
//import org.omg.CORBA.Current;

//import org.omg.CORBA.Current;

import java.util.ArrayList;
import java.time.LocalDate;

public class Posting implements java.io.Serializable {
    /**
     * Stores all the attributes of a Posting
     * <p>
     * Attributes:
     * <p>
     * ID: Unique string to identify posting
     * JobDescription: description of the job
     * Title: title of the job
     * CloseDAte: the date that no more applications an be submitted
     * SystemDate: current date of the system
     * FillStatus: whether the job has been filled
     * PostStatus: whether posting is still open for submission
     * AllApplicationSubmitted: An array list containing all the applications submitted to the job
     * RemainApplications: An array list containing all the applications that are still in the process of interview
     * CurrentRound: the current round of the interview
     * MaxRounds: Default is 4. Maximum rounds a posting can have
     * NumHire: Number of applications for hiring
     * HiredAPplication: the application that got chosen
     */
    private String ID;
    private String JobDescription;
    private String Title;
    private LocalDate CreationDate;
    private LocalDate CloseDate; //Should wait until the posting is closed to start interviewing everyone.
    private LocalDate SystemDate;

    private boolean FilledStatus = false;
    private boolean PostStatus = true; // Check if the posting is still open or not. Could be closed for many reasons.

    // All Applications Submitted will be stored in AllApplicationsSubmitted
    private ArrayList<UserApplication> AllApplicationsSubmitted = new ArrayList<>();
    // Each round, we delete from the RemainApplications;
    private ArrayList<UserApplication> RemainApplications = new ArrayList<>();

    private int CurrentRound = 0;
    private static final int MaxRounds = 4;
    private int NumHire = 1;
    private UserApplication HiredApplication;

    //maximum number of rounds is 4 according to the handout

    //Getters
    public String getID() {
        return ID;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public ArrayList<UserApplication> GetAllApplications() {
        return this.AllApplicationsSubmitted;
    }

    public ArrayList<UserApplication> GetRemainApplications() {
        return this.RemainApplications;
    }

    public boolean getPostStatus() {
        return this.PostStatus;
    }

    LocalDate getSystemDate() {
        return this.SystemDate;
    }

    LocalDate getCloseDate() {
        return this.CloseDate;
    } // No one can submit after getCLoseDate

    public String getTitle() {
        return this.Title;
    }

    int getCurrentRound() {
        return this.CurrentRound;
    }

    public Posting(String ID, String Title, String JobDescription, LocalDate CreationDate, LocalDate CloseDate, LocalDate systemdate) {
        this.ID = ID;
        this.Title = Title;
        this.JobDescription = JobDescription;
        this.CreationDate = CreationDate;
        this.CloseDate = CloseDate;
        this.SystemDate = systemdate;

    }


    public Posting(String ID, String Title, String JobDescription, LocalDate CreationDate, LocalDate CloseDate, LocalDate systemdate, int NumHire) {
        this.ID = ID;
        this.Title = Title;
        this.JobDescription = JobDescription;
        this.CreationDate = CreationDate;
        this.CloseDate = CloseDate;
        this.NumHire = NumHire;
        this.SystemDate = systemdate;
    }


    // Setters
    void ChangeFilledStatus(boolean Status) {
        this.FilledStatus = Status;
    }

    public void ChangePostingStatus(boolean Status) {
        this.PostStatus = Status;
    }

    /**
     * Submitt an application file and add it to AllApplicationSubmitted
     *
     * @param file an UserApplication object
     */
    void ApplicationSubmission(UserApplication file) {
        if (this.PostStatus) {
            this.AllApplicationsSubmitted.add(file);
            System.out.println("Submission Success.");
        } else {
            //POsting status is false if posting is closed. meaning interviewing or job is filled
            System.out.println("Submission Failure. Posting is closed or Job is filled.");
        }
    }


    public void SetHiredApplicant(UserApplication application) {
        this.HiredApplication = application;
    }


    /**
     * Update the corresponding attributes and statuses once someone is hired
     *
     * @param a an UserApplication object
     */
    public void Hiring_updates(UserApplication a) {
        //set closing date for application
        //add the round number for application
        //set the hired attribute, this is not the next round attribute.
        // check whether
        //

        // Helper function for whenever someone gets hired. ALl these attributes get updated.
        SetHiredApplicant(a);
        a.setLetter("Congratulations You have been hired as " + Title);
        a.setHirestatus(true);
        a.CloseApplication(SystemDate);
        ChangeFilledStatus(true);
        ChangePostingStatus(false);

    }

    /**
     * Update the corresponding attributes and statuses once someone is pushed to next round. Used as a helper function
     *
     * @param a an UserApplication object
     */

    public void next_round_update(UserApplication a) {
        a.setPushToNextRound(false); //Reset the next round status.
        a.setMatched(false); // not matched with interviewer
        a.setReviewstatus(false); //not reviewed by anyone
        a.setCurrentround(CurrentRound + 1); //survived one more round
    }

    /**
     * Start the interviewing process. Check relevant attributes to make sure everything is correct.
     */

    public void StartInterview() {
        //Posting must be false to start interview
        if (this.CurrentRound == 0 & !this.PostStatus) {
            this.RemainApplications = this.AllApplicationsSubmitted;
            this.CurrentRound = 1;
        } else if (this.CurrentRound > 0) {
            System.out.println("Interview is started");
        } else {
            System.out.println("Cant's start Interview. People still submitting resumes");
        }
    }


    /**
     * Push all the applications to the next round of the interview. Check recommendations given by interviewers.
     * Send out letters for congratulations and thank you letters for submissions.
     */
    void toNextRound() {
        // Move the applications from NextRoundApplications to CurrentRoundApplications. If round = 0.
        //Enter the interview period. Posting is closed.
        if (CheckMaxRound() & this.CurrentRound >= 1) {
            //Next Round keeps all the applications that passed.
            ArrayList<UserApplication> NextRound = new ArrayList<>();
            //LOOP through all the applications and push applications to next rounds.
            for (int i = 0; i < this.RemainApplications.size(); ++i) {
                UserApplication CurrentApplication = this.RemainApplications.get(i);
                if (CurrentApplication.isPushToNextRound()) {
                    NextRound.add(CurrentApplication);
                    next_round_update(CurrentApplication);

                } else {
                    CurrentApplication.CloseApplication(SystemDate);
                    CurrentApplication.setLetter("Sorry, you did not get the Job");
                    CurrentApplication.setCurrentround(CurrentRound);
                }
            }
            this.RemainApplications = NextRound;

        } else {
            System.out.println("There can be no more than 4 interviews");
            System.out.println("Human Resource Must choose a person");
        }

        this.CurrentRound = CurrentRound + 1;
        // If only one person is remained, that person is hired!
        if (this.RemainApplications.size() == 1) {
            // If only one person is remained, that person is hired!
            UserApplication SuccessApplication = this.RemainApplications.get(0);
            Hiring_updates(SuccessApplication);
        }


    }

    /**



     public void addApplication(UserApplication app){
     if (!FilledStatus){
     System.out.println("Job is filled");
     } else {
     AllApplicationsSubmitted.add(app);
     RemainApplications.add(app);
     }
     }
     */

    /**
     * Check if the current round of the posting is at 4th round
     *
     * @return boolean object
     */
    boolean CheckMaxRound() {
        if (CurrentRound <= 4) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return Title;
    }

}
