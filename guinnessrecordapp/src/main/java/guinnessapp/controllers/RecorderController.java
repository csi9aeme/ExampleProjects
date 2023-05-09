package guinnessapp.controllers;

import guinnessapp.dtos.RecorderCreateCommand;
import guinnessapp.dtos.RecorderDto;
import guinnessapp.dtos.RecorderShortDto;
import guinnessapp.services.RecorderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recorders")
@RequiredArgsConstructor
public class RecorderController {

    private final RecorderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecorderDto saveRecorder(@Valid @RequestBody RecorderCreateCommand command) {
        return service.saveRecorder(command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecorderShortDto> getRecorderList() {
        return service.getRecorderList();
    }
}
