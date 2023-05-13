package training360.gardenservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        detail.setType(URI.create("gardenservices/not-valid"));
        detail.setDetail(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return detail;
    }

    @ExceptionHandler(GardenerWithIdNotExistException.class)
    public ProblemDetail handleRecorderWithIdNotExistException(GardenerWithIdNotExistException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        detail.setType(URI.create("gardenerservices/not-found"));
        return detail;
    }

    @ExceptionHandler(GardenWorkWithIdNotExistException.class)
    public ProblemDetail handleRecorderWithIdNotExistException(GardenWorkWithIdNotExistException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        detail.setType(URI.create("gardenerservices/not-found"));
        return detail;
    }



}
