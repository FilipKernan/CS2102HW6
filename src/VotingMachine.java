import java.util.LinkedList;
import java.util.Scanner;

public class VotingMachine {



    private Scanner keyboard = new Scanner(System.in);

    public void printBallot(LinkedList<String> ballot) {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }

    public void screen(ElectionData data) {
        printBallot(data.getBallot());
        System.out.println("Who is your first choice?");
        String candidate = keyboard.next();
        System.out.println("Who is your second choice?");
        String candidate2 = keyboard.next();
        System.out.println("Who is your third choice?");
        String candidate3 = keyboard.next();
        try {
            data.processVote(candidate, candidate2, candidate3);
        }catch (UnknownCandidateException e){
            System.out.println(e.name + " is not in the system, please enter their name as a write in");

        }catch(DuplicateVotesException e){

        }
        System.out.println("You voted for " + candidate);
    }

}
