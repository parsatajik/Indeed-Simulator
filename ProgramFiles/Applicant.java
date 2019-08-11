package ProgramFiles;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Applicant extends User implements java.io.Serializable {
    /**
     * Class storing all the information of an applicant
     *
     * Attributes:
     *
     * currentApplications: An ArrayList consisting of the unclosed applications of the applicant
     * previousApplications: An ArrayList consisting of the closed applications of the applicant
     * resume: A string containing the applicant's resume
     * coverLetter: A string containing the applicant's cover letter
     * creationDate: LocalDate containing the the date the account was created
     * JobsHired: An ArrayList containing the current Jobs of the User
     * Dat: Stores current Date of the system (need for updating).
     *
     */
    private ArrayList<UserApplication> applications;
    private String resume;
    private String coverLetter;
    private LocalDate creationDate;
    private ArrayList<Object> jobsHired;
    private LocalDate Dat;
    private boolean updated; // true if current applications are updated


    /**
     *  Constructor which intializes all the attributes.
     */
    Applicant(String userName, String password, String resume, String coverLetter, LocalDate dt) {

        super(userName, password);
        this.resume = resume;
        this.coverLetter = coverLetter;
        this.Dat = LocalDate.now();
        this.updated = true;
        this.jobsHired = new ArrayList<>();
        this.creationDate = dt;
        this.applications = new ArrayList<>();

    }

    /**
     * Move current applications to previousApplications or jobsHired if needed.
     */

    private void updateCurrentApps(){
        if (!updated) {
            for (UserApplication app : applications) {
                if (app.getHirestatus()) {
                    jobsHired.add(app.getJobHired());
                }
            }
        }
        updated = true;
    }

    /**
     * Add new Job info to jobsHired ("job" is an ArrayList <String>)
     */
    public void Hire(Object Job){
        jobsHired.add(Job);
    }

    /**
     * Remove a Job from Jobs hired ("job" is an ArrayList <String>)
     */
    public void Fire(Object Job){
        jobsHired.remove(Job);
    }

    /**
     * Access all the jobs.
     */
    public ArrayList<Object> getJobs(){
        updateCurrentApps();
        return jobsHired;
    }

    /**
     * Returns the String "type" of the user.
     */
    public String getType() {
        return "Applicant";
    }

    /**
     * @return : All the current unclosed applications
     */
    public ArrayList<UserApplication> getApplications() {
        updateCurrentApps();
        return applications;
    }

    /**
     * Test method for setting CurrentApplications
     */
    public void setApplications(ArrayList<UserApplication> applications) {
        this.applications = applications;
    }

    /**
     * Returns the date the last application closed
     */
    private LocalDate getLatestApplication(){
        updateCurrentApps();
        return calculateLatestDate(applications);
    }

    /**
     * Helper function for getLatest
     * Calculates latest Closing Date from a list of Applications
     * Returns null if no closing dates.
     */
    private LocalDate calculateLatestDate(ArrayList<UserApplication> apps){

        if (apps.size() > 0) {
            LocalDate latest = apps.get(0).getClosingDate();

            for (UserApplication app : apps) {
                if (app.getClosingDate() != null) {
                    if (latest == null) {
                        latest = app.getClosingDate();
                    } else if (latest.isBefore(app.getClosingDate())) {
                        latest = app.getClosingDate();
                    }
                }
            }
            if (latest != null)
                return latest;
        }
        return LocalDate.now();
    }

    /**
     * removes cv and resume if last application was closed 30 days prior to today
     */
    void deleteOldInfo(){
        LocalDate latest = getLatestApplication();
        LocalDate today = Dat;

        Period p = Period.between(latest, today);

        if (p.getDays() > 30){
            this.coverLetter = "";
            this.resume = "";
        }

    }

    /**
     * @return : resume
     */

    public String getResume() {
        return resume;
    }

    /**
     * @param cv : resume (String)
     */
    public void setResume(String cv) {
        this.resume = cv;
    }

    /**
     * @return : Cover Letter
     */
    public String getCoverLetter() {
        return coverLetter;
    }

    /**
     * @param coverLetter : CoverLetter (String)
     */
    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    /**
     * @return : Creation Date (LocalDate)
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate : Creation Date (LocalDate)
     */

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Applying to a Job
     */
    public void apply(Posting p){
        updateCurrentApps(); // update current applications if not already updated

        String name = p.getTitle();
        UserApplication app = new UserApplication(name, resume, coverLetter, Dat);
        if (resume == null || coverLetter == null){
            System.out.println("Resume or Cover Letter not found");
        }
        else {
            p.ApplicationSubmission(app); // send application
            applications.add(app);
        }
    }



}
