package guinnessapp.exception;

public class RecorderWithIdNotExistException extends RuntimeException{

    public RecorderWithIdNotExistException(long id) {
        super("Recorder not found");
    }
}
