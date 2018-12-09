import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData {
    private LinkedList<String> ballot = new LinkedList<String>();
    private LinkedList<String> votes = new LinkedList<String>();
    private Scanner keyboard = new Scanner(System.in);

    private HashMap<Integer, LinkedList<String>> votersChoices;

    public ElectionData() {
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
            throw new CandidateExistsException(candidateName);
        } else {
            ballot.add(candidateName);
        }

    }

    public String findWinnerMostFirstVotes(HashMap<Integer, LinkedList<String>> submittedVotes) {
        HashMap<String, Integer> countedVotes = new HashMap<>();
        for(int k = 0; k < ballot.size(); k++) {
            countedVotes.put(ballot.get(k), 0);
        }
        for(LinkedList<String> votes: submittedVotes.values()) {
            countedVotes.put(votes.getFirst(), countedVotes.get(votes.getFirst()) + 1);
        }


        int totalVotes = 0;
        int max = 0;
        String maxName = "";
        for(String cand: countedVotes.keySet()) {
            totalVotes += countedVotes.get(cand);
            if(countedVotes.get(cand) > max) {
                max = countedVotes.get(cand);
                maxName = cand;
            }
        }
        if (totalVotes / 2 < max) {
            return maxName;
        } else {
            return "Runoff required";
        }
    }

    public String findWinnerMostPoints(HashMap<Integer, LinkedList<String>> submittedVotes) {
        HashMap<String, Integer> countedVotes = new HashMap<>();
        for(int i = 0; i < ballot.size(); i++) {
            countedVotes.put(ballot.get(i), 0);
        }
        for(LinkedList<String> votes: submittedVotes.values()) {
            countedVotes.put(votes.get(1), countedVotes.get(votes.get(1)) + 3);
            countedVotes.put(votes.get(2), countedVotes.get(votes.get(2)) + 2);
            countedVotes.put(votes.get(3), countedVotes.get(votes.get(3)) + 1);
        }

        int max = 0;
        String maxName = "";
        for(String cand: countedVotes.keySet()) {
            if(countedVotes.get(cand) > max) {
                max = countedVotes.get(cand);
                maxName = cand;
            }
        }
        return maxName;
    }
}