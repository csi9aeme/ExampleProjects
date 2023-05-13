package training360.gardenservices.exception;

public class GardenWorkWithIdNotExistException extends RuntimeException{

    public GardenWorkWithIdNotExistException(long id) {
        super(String.format("Gardenwork with ID#%d not exist.", id));
    }
}
