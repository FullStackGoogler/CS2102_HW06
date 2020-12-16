import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Examples {
    //psvm + TAB
    public static void main(String[] args) {
        int num1 = 7;
        int num2 = addThree(num1);

        System.out.println(new ArrayList<Integer>());
    }

    public static int addThree(int num) {
        return num += 3;
    }

    public ElectionData election1() {
        ElectionData electionData = new ElectionData();

        try {
            electionData.addCandidate("Biden");
            electionData.addCandidate("Trump");
            electionData.addCandidate("Jorgensen");

            electionData.processVote("Biden", "Trump", "Jorgensen");
            electionData.processVote("Trump", "Biden", "Jorgensen");
            electionData.processVote("Biden", "Jorgensen", "Trump");
        } catch(Exception e) {
            System.out.println("Error1: " + e);
        }
        return electionData;
    }

    public ElectionData election2() {
        ElectionData electionData = new ElectionData();

        try {
            electionData.addCandidate("Ahri");
            electionData.addCandidate("Evelynn");
            electionData.addCandidate("Akali");
            electionData.addCandidate("Kai'Sa");

            electionData.processVote("Ahri", "Evelynn", "Kai'Sa");
            electionData.processVote("Kai'Sa", "Evelynn", "Akali");
            electionData.processVote("Ahri", "Akali", "Evelynn");
            electionData.processVote("Akali", "Ahri", "Kai'Sa");
            electionData.processVote("Ahri", "Kai'Sa", "Akali");
            electionData.processVote("Ahri", "Evelynn", "Akali");
            electionData.processVote("Akali", "Kai'Sa", "Ahri");
            electionData.processVote("Ahri", "Kai'Sa", "Akali");
        } catch(Exception e) {
            System.out.println("Error2: " + e);
        }
        return electionData;
    }

    public ElectionData election3() { //Bernardi does not have the >50% of 1st place votes to win
        ElectionData electionData = new ElectionData();

        try {
            electionData.addCandidate("Bernardi");
            electionData.addCandidate("Goulet");
            electionData.addCandidate("Sturm");

            electionData.processVote("Bernardi", "Goulet", "Sturm");
            electionData.processVote("Goulet", "Sturm", "Bernardi");
            electionData.processVote("Sturm", "Bernardi", "Goulet");
            electionData.processVote("Bernardi", "Sturm", "Goulet");
        } catch(Exception e) {
            System.out.println("Error3: " + e);
        }
        return electionData;
    }

    public ElectionData election4() {
        ElectionData electionData = new ElectionData();

        try {
            electionData.addCandidate("Mia");
            electionData.addCandidate("Lana");
            electionData.addCandidate("Riley");
            electionData.addCandidate("Asa");

            electionData.processVote("Mia", "Lana", "Riley");
            electionData.processVote("Lana", "Mia", "Asa");
            electionData.processVote("Asa", "Mia", "Lana");
            electionData.processVote("Riley", "Lana", "Mia");
        } catch(Exception e) {
            System.out.println("Error4: " + e);
        }
        return electionData;
    }

    @Test (expected = UnknownCandidateException.class)
    public void checkUnknownCandidateException() throws DuplicateVotesException, UnknownCandidateException {
        election1().processVote("Biden", "Trump", "Hawkins");
        election2().processVote("Aatrox", "Kassadin", "Darius");
    }

    @Test (expected = UnknownCandidateException.class)
    public void checkUnknownCandidateExceptionFirst() throws DuplicateVotesException, UnknownCandidateException {
        election1().processVote("Biden", "Biden", "Hawkins");
        election2().processVote("Aatrox", "Kai'Sa", "Ahri");
    }

    @Test (expected = DuplicateVotesException.class)
    public void checkDuplicateVotesException() throws DuplicateVotesException, UnknownCandidateException {
        election1().processVote("Trump", "Trump", "Biden");
        election2().processVote("Ahri", "Akali", "Ahri");
        election3().processVote("Bernardi", "Goulet", "Bernardi");
        election4().processVote("Asa", "Asa", "Mia");
    }

    @Test (expected = CandidateExistsException.class)
    public void checkCandidateExistsException() throws CandidateExistsException {
        election1().addCandidate("Biden");
        election2().addCandidate("Kai'Sa");
        election2().addCandidate("Evelynn");
        election3().addCandidate("Bernardi");
        election4().addCandidate("Lana");
    }

    @Test
    public void checkFindWinnerMostFirstVotes() {
        assertEquals("Biden", election1().findWinnerMostFirstVotes());
        assertEquals("Ahri", election2().findWinnerMostFirstVotes());
        assertEquals("Runoff required", election3().findWinnerMostFirstVotes());
    }

    @Test
    public void checkFindWinnerMostPoints() {
        assertEquals("Biden", election1().findWinnerMostPoints());
        assertEquals("Ahri", election2().findWinnerMostPoints());
        assertTrue(election4().findWinnerMostPoints().equals("Mia") || election4().findWinnerMostPoints().equals("Lana"));
    }
}
