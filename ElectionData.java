import java.util.LinkedList;
import java.util.HashMap;
/**
 * A class representing election data.
 */
public class ElectionData {
    private LinkedList<String> ballot = new LinkedList<>();
    private HashMap<String, LinkedList<Integer>> votes = new HashMap<>();

    ElectionData() {}

    /**
     * Processes a given person's vote.
     * @param choice1 A String representing a voter's first choice.
     * @param choice2 A String representing a voter's second choice.
     * @param choice3 A String representing a voter's third choice.
     * @throws DuplicateVotesException Thrown when a candidate is voted for twice.
     * @throws UnknownCandidateException Thrown when a candidate not on the ballot is voted for.
     */
    public void processVote(String choice1, String choice2, String choice3) throws DuplicateVotesException, UnknownCandidateException{
        if(!votes.containsKey(choice1))
            throw new UnknownCandidateException(choice1);
        else if(!votes.containsKey(choice2))
            throw new UnknownCandidateException(choice2);
        else if(!votes.containsKey(choice3))
            throw new UnknownCandidateException(choice3);

        if(choice1.equals(choice2) || choice1.equals(choice3))
            throw new DuplicateVotesException(choice3);
        else if(choice2.equals(choice3))
            throw new DuplicateVotesException(choice2);

        votes.get(choice1).set(0, votes.get(choice1).get(0) + 1);
        votes.get(choice2).set(1, votes.get(choice2).get(1) + 1);
        votes.get(choice3).set(2, votes.get(choice3).get(2) + 1);
    }

    /**
     * Adds a candidate to the ballot.
     * @param candidate A String representing the candidate to be added.
     * @throws CandidateExistsException Thrown when candidate is already on the ballot.
     */
    public void addCandidate(String candidate) throws CandidateExistsException{
        LinkedList<Integer> newVote = new LinkedList<>();

        if(votes.containsKey(candidate)){
            throw new CandidateExistsException(candidate);
        }
        else {
            newVote.add(0);
            newVote.add(0);
            newVote.add(0);

            votes.put(candidate, newVote);
            ballot.add(candidate);
        }
    }

    /**
     * Calculates the winner based on the amount of first choice votes held.
     * @return The name of the candidate that has greater than 50% of the first choice votes, 'Runoff required' otherwise.
     */
    public String findWinnerMostFirstVotes(){
        double mostVotes = 0;

        for(String candidate : ballot)
            mostVotes += votes.get(candidate).get(0);

        if(mostVotes > 0) {
            for(String candidate : ballot) {
                double percent = votes.get(candidate).get(0) / mostVotes;

                if((percent * 100) > 50){
                    return candidate;
                }
            }
        }
        return "Runoff required";
    }

    /**
     * Calculates the winner based on the amount of points accumulated.
     * @return The name of the candidate (multiple candidates can be returned).
     */
    public String findWinnerMostPoints() {
        int mostPoints = 0;
        String winner = "";

        for(String candidate : ballot) {
            int candidatePoints = (votes.get(candidate).get(0) * 3) + (votes.get(candidate).get(1) * 2) + votes.get(candidate).get(2);

            if(candidatePoints > mostPoints) {
                mostPoints = candidatePoints;
                winner = candidate;
            }
        }
        return winner;
    }

    public LinkedList<String> getBallot(){ return this.ballot; }
}