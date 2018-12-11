import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;

public class Examples {
    ElectionData ED = new ElectionData();
    ElectionData ED2 = new ElectionData();
    @Before
    public void test1 () throws CandidateExistsException {

        ED.addCandidate("Bernie Sanders");
        ED.addCandidate("Joe Biden");
        ED.addCandidate("Jay Inslee");
        ED.addCandidate("Filip Kernan");
        ED.addCandidate("Eli Benevedes");
        try {
            ED.processVote("Bernie Sanders", "Joe Biden", "Eli Benevedes");
            ED.processVote("Bernie Sanders", "Joe Biden", "Filip Kernan");
            ED.processVote("Bernie Sanders", "Joe Biden", "Jay Inslee");
            ED.processVote("Joe Biden", "Eli Benevedes", "Jay Inslee");
            ED.processVote("Eli Benevedes", "Joe Biden", "Bernie Sanders");
        } catch (DuplicateVotesException e) {
            System.out.println(e);
        } catch (UnknownCandidateException e) {
            System.out.println(e);
        }
    }
//    @Before
//    public void test2 () throws CandidateExistsException {
//        ED2.addCandidate("Barrack Obama");
//        ED2.addCandidate("Patrick Eaton");
//        ED2.addCandidate("Weird Al");
//        ED2.addCandidate("Anyone but Trump");
//        ED2.addCandidate("Igor");
//        try {
//            ED2.processVote("Weird AL", "Barrack Obama", "Igor");
//            ED2.processVote("Weird AL", "Barrack Obama", "Patrick Eaton");
//            ED2.processVote();
//        } catch (DuplicateVotesException e) {
//            System.out.println(e);
//        } catch (UnknownCandidateException e) {
//            System.out.println(e);
//        }
//    }

    @Test
    public void firstPastThePost() {
        assertEquals(ED.findWinnerMostFirstVotes(), "Bernie Sanders");
    }
    @Test
    public void approvalBasedVotes() {
        assertEquals(ED.findWinnerMostPoints(), "Joe Biden");
    }
}