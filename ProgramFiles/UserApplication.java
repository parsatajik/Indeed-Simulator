package ProgramFiles;

import java.time.LocalDate;
import java.util.ArrayList;

public class  UserApplication implements java.io.Serializable {
    /**
     * This class stores info on every application created by a user.
     *
     * Attributes:
     *
     * resume: A string containing the applicant's resume
     * coverLetter: A string containing the applicant's cover letter
     * nameOfApplicant: String containing the name of the user who created this application
     * creationDate: LocalDate containing the the date the account was created
     * closingDate: LocalDate containing the date the application was closed
     * pushToNextRound: boolean determining whether to push the application to next round
     * hireStatus: boolean determining whether application was accepted
     * reviewStatus: boolean determining whether application was reviewed
     * currentRound: int containing the round the application is in
     * Letter: String that contains rejection letter (if application is rejected)
     * JobHiredInfo: Arraylist that stores the info if application is accepted for a job
     */
    private String resume;
    private String coverLetter;
    private String nameOfApplicant;
    private LocalDate creationDate;
    private LocalDate closingDate = null;
    private boolean pushToNextRound = false;
    private boolean hireStatus = false;
    private boolean reviewStatus = false;
    private int currentRound = 0;
    private String Letter;
    private boolean matched;
    private ArrayList<String> JobHiredInfo;

    // METHODS

    public UserApplication(String nameOfApplicant, String resume, String coverLetter, LocalDate date) {
        this.nameOfApplicant = nameOfApplicant;
        this.resume = resume;
        this.coverLetter = coverLetter;
        this.creationDate = date;
    }

    /**
     * Returns the type of round the application is in as a String.
     *
     * @return The String translation of this application's round number.
     */
    public String roundNumberToString() {
        if (this.currentRound == 0) {
            return "This application has been recieved";
        } else if (this.currentRound == 1) {
            return "Phone interview";
        } else if (this.currentRound == 2) {
            return "Interview 1";
        } else if (this.currentRound == 3) {
            return "Interview 2";
        } else if (this.currentRound == 4) {
            return "Interview 3";
        } else {
            return "Invalid round";
        }
    }

    /**
     * Adds a closing date to the application.
     *
     * @param closingDate The date the application was closed.
     */
    public void CloseApplication(LocalDate closingDate){
        this.closingDate = closingDate;
    }

    /**
     * Returns the name of the applicant.
     *
     * @return The name of the applicant.
     */
    public String toString() {
        return this.nameOfApplicant + " - " + this.creationDate;
    }

    // GETTERS AND SETTERS

    public LocalDate getClosingDate(){
        return this.closingDate;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getNameOfApplicant() {
        return nameOfApplicant;
    }

    public void setNameOfApplicant(String nameOfApplicant) {
        this.nameOfApplicant = nameOfApplicant;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isPushToNextRound() {
        return pushToNextRound;
    }

    public void setPushToNextRound(boolean pushToNextRound) {
        this.pushToNextRound = pushToNextRound;
    }

    public boolean getHirestatus() {
        return hireStatus;
    }

    public void setHirestatus(boolean status) {
        this.hireStatus = status;
    }

    public boolean getReviewstatus() {
        return reviewStatus;
    }

    public void setJobHired(ArrayList p){
        JobHiredInfo = p;
    }

    public ArrayList getJobHired(){
        return JobHiredInfo;
    }

    public void setReviewstatus (boolean status) {
        this.reviewStatus = status;
    }
    public int getCurrentround() {
        return currentRound;
    }

    public void setCurrentround(int round) {
        this.currentRound = round;
    }
    public String getLetter() {
        return Letter;
    }
    public void setLetter (String Letter) {
        this.Letter = Letter;
    }
    public boolean getMatched(){
        return matched;
    }

    public void setMatched(Boolean m){
        this.matched = m;
    }
}
