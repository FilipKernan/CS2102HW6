public class UnknownCandidateException extends  Exception{
    String name;

    public UnknownCandidateException(String name){this.name = name;}
}