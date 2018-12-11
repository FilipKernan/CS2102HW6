import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData {
    private LinkedList<String> ballot = new LinkedList<String>();



    private HashMap<Integer, LinkedList<String>> votersChoices;

    public ElectionData() {
        votersChoices = new HashMap<>();
    }


    public LinkedList<String> getBallot() {
        return ballot;
    }


    /**
     * This processes a users vote
     * @param firstChoice
     * @param secondChoice
     * @param thirdChoice
     * @throws DuplicateVotesException
     * @throws UnknownCandidateException
     */
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

    /**
     *
     * @param candidateName
     * @throws CandidateExistsException
     */
    public void addCandidate(String candidateName) throws CandidateExistsException {
        if (ballot.contains(candidateName)) {
            throw new CandidateExistsException(candidateName);
        } else {
            ballot.add(candidateName);
        }

    }

    /**
     *
     * @param candToAdd
     * @throws CandidateExistsException
     */
    public void addWriteIn(String candToAdd) throws CandidateExistsException {
        addCandidate(candToAdd);
    }

    /**
     *
     * @return
     */
    public String findWinnerMostFirstVotes() {
        HashMap<String, Integer> countedVotes = new HashMap<>();
        for(int k = 0; k < ballot.size(); k++) {
            countedVotes.put(ballot.get(k), 0);
        }
        for(LinkedList<String> votes: this.votersChoices.values()) {
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
        System.out.println(countedVotes);

        if (totalVotes / 2 < max) {
            return maxName;
        } else {
            return "Runoff required";
        }
    }

    /**
     *
     * @return
     */
    public String findWinnerMostPoints() {
        HashMap<String, Integer> countedVotes = new HashMap<>();
        for(int i = 0; i < ballot.size(); i++) {
            countedVotes.put(ballot.get(i), 0);
        }
        for(LinkedList<String> votes: this.votersChoices.values()) {
            countedVotes.put(votes.get(0), countedVotes.get(votes.get(0)) + 3);
            countedVotes.put(votes.get(1), countedVotes.get(votes.get(1)) + 2);
            countedVotes.put(votes.get(2), countedVotes.get(votes.get(2)) + 1);
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