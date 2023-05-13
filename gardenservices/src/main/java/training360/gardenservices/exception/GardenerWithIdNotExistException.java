package training360.gardenservices.exception;

public class GardenerWithIdNotExistException extends RuntimeException{
    public GardenerWithIdNotExistException(long id) {
        super(String.format("Gardener with ID#%d not exist.", id));
    }
}
