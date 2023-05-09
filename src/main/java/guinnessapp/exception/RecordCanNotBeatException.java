package guinnessapp.exception;

public class RecordCanNotBeatException extends RuntimeException{
    public RecordCanNotBeatException() {
        super("Can not beat");
    }
}
