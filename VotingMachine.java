import java.util.Scanner;
/**
 * A class representing a voting booth.
 */
public class VotingMachine {
    ElectionData data = new ElectionData();
    Scanner cons = new Scanner(System.in);

    /**
     * Prints the ballot of available candidates.
     */
    public void printBallot() {
        System.out.println("The candidates are: ");

        for(String s : data.getBallot()) {
            System.out.println(s);
        }
    }

    /**
     * User interface for voting.
     */
    public void screen() {
        this.printBallot();

        System.out.println("Enter your first choice candidate: ");
        String candidate = cons.next();

        System.out.println("Enter your second choice candidate: ");
        String candidate2 = cons.next();

        System.out.println("Enter your third choice candidate: ");
        String candidate3 = cons.next();

        try {
            data.processVote(candidate, candidate2, candidate3);
        } catch (DuplicateVotesException e) {
            System.out.println("Cannot vote for " + e.getName() + " more than once!");

            screen();
        } catch (UnknownCandidateException e) {
            System.out.println("Cannot recognize " + e.getName() + ", would you like to add them to the ballot?");

            String answer = cons.next();

            if(answer.equalsIgnoreCase("Y"))
                addWriteIn(e.getName());
            screen();
        }
    }

    /**
     * Adds a candidate to the ballot.
     * @param name A String representing the candidate to be added.
     */
    public void addWriteIn(String name) {
        try {
            data.addCandidate(name);
            System.out.println("Candidate added successfully!");
        } catch (CandidateExistsException e) {
            System.out.println("This candidate already exists!");
        }
    }
}