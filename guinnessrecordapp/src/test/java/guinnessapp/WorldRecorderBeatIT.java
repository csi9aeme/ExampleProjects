package guinnessapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;


import guinnessapp.dtos.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from world_records", "delete from recorders"})
public class WorldRecorderBeatIT {

    @Autowired
    TestRestTemplate template;

    long benId;

    long glenId;

    long worldRecordId;

    @BeforeEach
    void saveTestRecorder() {
        RecorderCreateCommand inputCommand = new RecorderCreateCommand("Ben", LocalDate.of(1999, 9, 9));
        RecorderDto recorder = template.postForObject("/api/recorders", inputCommand, RecorderDto.class);
        benId = recorder.getId();

        inputCommand = new RecorderCreateCommand("Glen", LocalDate.of(1999, 9, 9));
        recorder = template.postForObject("/api/recorders", inputCommand, RecorderDto.class);
        glenId = recorder.getId();

        WorldRecordCreateCommand worldRecordCreateCommand =
                new WorldRecordCreateCommand("Largest pizza", 5.78, "meter", LocalDate.of(2001, 11, 11), benId);
        WorldRecordDto worldRecord = template.postForObject("/api/worldrecords", worldRecordCreateCommand, WorldRecordDto.class);
        worldRecordId = worldRecord.getId();
    }

    @Test
    void test_beatSuccessful() {
        BeatWorldRecordCommand inputCommand = new BeatWorldRecordCommand(glenId, 5.92);
        BeatWorldRecordDto result = template.exchange("/api/worldrecords/{id}/beatrecords",
                HttpMethod.PUT,
                new HttpEntity(inputCommand),
                BeatWorldRecordDto.class, worldRecordId).getBody();


        assertEquals("Largest pizza", result.getDescription());
        assertEquals("meter", result.getUnitOfMeasure());
        assertEquals("Ben", result.getOldRecorderName());
        assertEquals("Glen", result.getNewRecorderName());
        assertEquals(5.92, result.getNewRecordValue(), 0.005);
        assertEquals(0.14, result.getRecordDifference(), 0.005);
    }



    @Test
    void test_RecorderNotExists() {
        BeatWorldRecordCommand inputCommand = new BeatWorldRecordCommand(glenId + 1, 5.92);
        ProblemDetail problem = template.exchange("/api/worldrecords/{id}/beatrecords",
                HttpMethod.PUT,
                new HttpEntity(inputCommand),
                ProblemDetail.class, worldRecordId).getBody();

        assertEquals(HttpStatus.NOT_FOUND.value(), problem.getStatus());
        assertEquals("Recorder not found", problem.getDetail());
    }

    @Test
    void test_WorldRecordNotExists() {
        BeatWorldRecordCommand inputCommand = new BeatWorldRecordCommand(glenId, 5.92);
        ProblemDetail problem = template.exchange("/api/worldrecords/{id}/beatrecords",
                HttpMethod.PUT,
                new HttpEntity(inputCommand),
                ProblemDetail.class, worldRecordId + 1).getBody();

        assertEquals(HttpStatus.NOT_FOUND.value(), problem.getStatus());
        assertEquals("World record not found", problem.getDetail());
    }

    @Test
    void test_WorldRecordCanNotBeat() {
        BeatWorldRecordCommand inputCommand = new BeatWorldRecordCommand(glenId, 1.92);
        ProblemDetail problem = template.exchange("/api/worldrecords/{id}/beatrecords",
                HttpMethod.PUT,
                new HttpEntity(inputCommand),
                ProblemDetail.class, worldRecordId).getBody();

        assertEquals(HttpStatus.BAD_REQUEST.value(), problem.getStatus());
        assertEquals("Can not beat", problem.getDetail());
    }

}
