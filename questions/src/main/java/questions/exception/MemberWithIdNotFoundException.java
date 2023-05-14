package questions.exception;

public class MemberWithIdNotFoundException extends RuntimeException{

    public MemberWithIdNotFoundException(long id) {
        super(String.format("Member with ID #%d not found", id));
    }
}
