package guinnessapp.exception;

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
        detail.setType(URI.create("guinness/not-valid"));
        detail.setDetail(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return detail;
    }

    @ExceptionHandler(RecorderWithIdNotExistException.class)
    public ProblemDetail handleRecorderWithIdNotExistException(RecorderWithIdNotExistException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        detail.setType(URI.create("guinness/not-found"));
        return detail;
    }

    @ExceptionHandler(WorldRecordWithIdNotExistException.class)
    public ProblemDetail handleWorldRecordWithIdNotExistException(WorldRecordWithIdNotExistException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        detail.setType(URI.create("guinness/not-found"));
        return detail;
    }

    @ExceptionHandler(RecordCanNotBeatException.class)
    public ProblemDetail handleRecordCanNotBeatException(RecordCanNotBeatException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        detail.setType(URI.create("guinness/can-not-beat"));
        return detail;
    }
}
