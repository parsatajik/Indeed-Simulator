package ProgramFiles;
import java.util.ArrayList;

public class HR extends User {
    /** Stores all the attributes of a HR.
     *
     * Attributes:
     *
     * AllPosting: All postings in a company
     * username: username to log in
     * password: password to log in
     *
     *
     */


    private ArrayList<Posting> AllPosting;
    // Constructor: let's pass directly company's attribute posting into each HR.

    public HR(String userName, String password, ArrayList<Posting> CompanyPosting) {
        super(userName, password);
        this.AllPosting = CompanyPosting;
    }

    //Grab all applications for a specific posting
    /**
     * Returns an ArrayList object consisting of UserApplication objects.
     * Theses are all the applications submitted by the applicant
     * @param p a Posting object
     * @return  an array of UserApplication
     *
    */
    public ArrayList<UserApplication> GrabAllApplications(Posting p){

        //Grab all applications for a specific posting
        return p.GetAllApplications();
    }
    /**
     * Returns an ArrayList object consisting of UserApplication objects.
     * Theses are all the applications that are left in the interview process with a specific posting
     * @param p a Posting object
     * @return  an array of UserApplication
     *
     */
    private ArrayList<UserApplication> GrabRemainApplications(Posting p){
        //Grab all remaining applications for a specific posting
        return p.GetRemainApplications();
    }
    /**
     * Returns an int object counting number of remaining applications
     * @param p a Posting object
     * @return  int
     *
     */

    public int CountNumRemainApplicantions(Posting p){ return p.GetRemainApplications().size(); }

    /**
     * Returns a boolean object specifying whether HR can proceed to the next round.
     * All mathced applications must be reviewed in order to proceed
     * @param p a Posting object
     * @return  boolean
     *
     */

    private boolean canProceed(Posting p){
        for (UserApplication ua: GrabRemainApplications(p)){
            if ((ua.getMatched()) & (!ua.getReviewstatus())){
                return false;
            }
        }
        if (p.getCurrentRound() == 0) {
            System.out.println("can't push to next round. Has to start interview first by calling StartInterview()");
            return false;
            }

        return true;
    }
    /**
     * Returns an ArrayList object consisting of UserApplication objects.
     * Theses are all the applications that are left unmatched to an interviewer
     * @param p a Posting object
     * @return  an array of UserApplication
     *
     */

    public ArrayList<UserApplication> GetUnmatchedApplications(Posting p){
        ArrayList<UserApplication> Unmatched = new ArrayList<>();;
        for (UserApplication ua: GrabRemainApplications(p)){
            if (!(ua.getMatched())){
                Unmatched.add(ua);
            }}
        return Unmatched;
    }


    /**
     * Returns an int object counting number of unmatched applications
     * @param p a Posting object
     * @return  int
     *
     */

    public int CountUnmatchedApplications(Posting p){
        int NumUnmatched = 0;
        for (UserApplication ua: GrabRemainApplications(p)){
            if (!(ua.getMatched())){
                NumUnmatched = NumUnmatched + 1;
            }}
        return NumUnmatched;
        }


    /**
     * Returns an ArrayList object consisting of UserApplication objects.
     * Theses are all the applications that are not reviewed by an interviewer
     * @param p a Posting object
     * @return  an array of UserApplication
     *
     */
    public ArrayList<UserApplication> GetUnviewedApplications(Posting p){
        ArrayList<UserApplication> Unviewed = new ArrayList<>();;
        for (UserApplication ua: GrabRemainApplications(p)){
            if (!(ua.getReviewstatus())){
                Unviewed.add(ua);
            }}
        return Unviewed;
    }

    /**
     * Returns an int object counting number of applications not interviewed
     * @param p a Posting object
     * @return  int
     *
     */
    public int CountUnviewedApplications(Posting p){
        int NumUnviewed = 0;
        for (UserApplication ua: GrabRemainApplications(p)){
            if (!(ua.getReviewstatus())){
                NumUnviewed = NumUnviewed + 1;
            }}
        return NumUnviewed;
    }

    /**
     * Set the candidate to be hired for a posting
     * @param p a Posting object
     * @param a an UserApplication object
     *
     */

    public void SetHires(Posting p, UserApplication a){
        //Set the hire for a job.
        //Change the fill status
       p.Hiring_updates(a);
    }

    /**
     * Match an application to an interviewer
     * @param i a Interviewer object
     * @param a an UserApplication object
     *
     */

    public void MatchInterviewerApplication(Interviewer i, UserApplication a){
        i.AddApplication(a);
        a.setMatched(true);
    }


    /**
     * Start the interviewing process of a posting. Posting is now closed and no applications can be submitted
     * @param p a Posting object
     *
     */
    public void StartNextRountInterview(Posting p){
        if (p.getCurrentRound() > 0) {
            if (canProceed(p)) {
                p.toNextRound();
            } else {
                System.out.println("Cannot proceed");
            }
        }
        else {
            p.ChangePostingStatus(false);
            p.StartInterview();
        }
    }




}
