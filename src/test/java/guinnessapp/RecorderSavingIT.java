package guinnessapp;

import guinnessapp.dtos.RecorderCreateCommand;
import guinnessapp.dtos.RecorderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.jdbc.Sql;


import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from world_records", "delete from recorders"})
public class RecorderSavingIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void test_saveSuccessful() {
        RecorderCreateCommand inputCommand = new RecorderCreateCommand("Ben", LocalDate.of(1999, 9, 9));
        RecorderDto recorder = template.postForObject("/api/recorders", inputCommand, RecorderDto.class);
        assertEquals("Ben", recorder.getName());
        assertEquals(LocalDate.of(1999, 9, 9), recorder.getDateOfBirth());
    }

    @Test
    void test_NameBlank() {
        RecorderCreateCommand inputCommand = new RecorderCreateCommand("", LocalDate.of(1999, 9, 9));
        ProblemDetail problem = template.postForObject("/api/recorders", inputCommand, ProblemDetail.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), problem.getStatus());
        assertEquals("must not be blank", problem.getDetail());
    }


    @Test
    void test_dateOfBirthInFuture() {
        RecorderCreateCommand inputCommand = new RecorderCreateCommand("K", LocalDate.of(2999, 9, 9));
        ProblemDetail problem = template.postForObject("/api/recorders", inputCommand, ProblemDetail.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), problem.getStatus());
        assertEquals("must be in the past", problem.getDetail());
    }
}