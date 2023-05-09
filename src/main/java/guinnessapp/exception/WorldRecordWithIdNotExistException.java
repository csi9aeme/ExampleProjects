package guinnessapp.exception;

public class WorldRecordWithIdNotExistException extends RuntimeException {
    public WorldRecordWithIdNotExistException(long id) {
        super("World record not found");
    }
}
