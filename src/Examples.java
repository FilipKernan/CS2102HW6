import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

public class Examples {
    ElectionData test1 () throws CandidateExistsException {
        ElectionData ED = new ElectionData();
        ED.addCandidate("Bernie Sanders");
        ED.addCandidate("Joe Biden");
        ED.addCandidate("Jay Inslee");
        ED.addCandidate("Filip Kernan");
        ED.addCandidate("Eli Benevedes");
        try {
            ED.processVote("Bernie Sanders", "Joe Biden", "Eli Benevedes");
            ED.processVote("Bernie Sanders", "Joe Biden", "Filip Kernan");
            ED.processVote("Bernie Sandesr", "Joe Biden", "Jay Inslee");
            ED.processVote("Joe Biden", "Eli Benevedes", "Jay Inslee");
            ED.processVote("Eli Benevedes", "Joe Biden", "Bernie Sanders");
            ED.processVote("Jay Inslee", "Eli Benevedes", "Joe Biden");
        } catch (DuplicateVotesException e) {
            System.out.println(e);
        } catch (UnknownCandidateException e) {
            System.out.println(e);
        }
        return ED;
    }

    @Test
    public void firstPastThePost() {
        
    }
}