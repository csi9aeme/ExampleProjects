package guinnessapp.controllers;

import guinnessapp.dtos.BeatWorldRecordCommand;
import guinnessapp.dtos.BeatWorldRecordDto;
import guinnessapp.dtos.WorldRecordCreateCommand;
import guinnessapp.dtos.WorldRecordDto;
import guinnessapp.services.WorldRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/worldrecords")
@RequiredArgsConstructor
public class WorldRecordController {

    private final WorldRecordService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorldRecordDto saveWorldRecord(@Valid @RequestBody WorldRecordCreateCommand command) {
        return service.saveWorldRecord(command);
    }

    @PutMapping("/{id}/beatrecords")
    @ResponseStatus(HttpStatus.OK)
    public BeatWorldRecordDto beatWorldRecord(@PathVariable("id") long id, @RequestBody BeatWorldRecordCommand command) {
        return service.beatWorldRecord(id, command);
    }
}
