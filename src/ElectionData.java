import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

class ElectionData {
    private LinkedList<String> ballot = new LinkedList<String>();
    private LinkedList<String> votes = new LinkedList<String>();
    Scanner keyboard = new Scanner(System.in);

    private HashMap<Integer, LinkedList<String>> votersChoices;

    ElectionData() {
        this.ballot.add("Gompei");
        this.ballot.add("Husky");
        votersChoices = new HashMap<>();
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



    public void processVote(String firstChoice, String secondChoice, String thirdChoice) throws DuplicateVotesException, UnknownCandidateException{
        LinkedList<String> choices = new LinkedList<>();
        choices.add(firstChoice);
        choices.add(secondChoice);
        choices.add(thirdChoice);
        int count = 0;
        for (String s: choices) {
            if (!this.ballot.contains(s)){
                throw new UnknownCandidateException(s);
            }
            if (choices.contains(s)){
                count++;
            }
            if (count > 1) throw new DuplicateVotesException(s);
        }

        int key = firstChoice.length() * 3 + secondChoice.length() * 11 + thirdChoice.length() * 17;
        votersChoices.put(key, choices);
    }

    public void addCandidate(String candidateName) throws CandidateExistsException {
        if (ballot.contains(candidateName)) {
            throw new CandidateExistsException();
        } else {
            ballot.add(candidateName);
        }

    }
}