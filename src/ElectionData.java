import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

class ElectionData {
    LinkedList<String> ballot = new LinkedList<String>();
    LinkedList<String> votes = new LinkedList<String>();
    Scanner keyboard = new Scanner(System.in);

    ElectionData() {
        this.ballot.add("Gompei");
        this.ballot.add("Husky");
    }

    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }

    public void screen() {
        this.printBallot();
        System.out.println("Who do you want to vote for?");
        String candidate = keyboard.next();
        votes.add(candidate);
        System.out.println("You voted for " + candidate);
    }

    public int countVotes(String forcand) {
        int numvotes = 0;
        for (String s : votes) {
            if (s.equals(forcand))
                numvotes = numvotes+1;
        }
        return numvotes;
    }

    public void addCandidate(String candidateName) throws CandidateExistsException {
        if (ballot.contains(candidateName)) {
            throw new CandidateExistsException();
        } else {
            ballot.add(candidateName);
        }
    }
}