public class DuplicateVotesException extends Exception{
    String name;

    public DuplicateVotesException(String name){
        this.name = name;
    }

}
