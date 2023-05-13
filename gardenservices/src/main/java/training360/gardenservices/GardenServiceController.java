package training360.gardenservices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.gardenservices.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GardenServiceController {

    private final GardenerService gardenersService;

    @PostMapping("/gardeners")
    @ResponseStatus(HttpStatus.CREATED)
    public GardenerDto createGardener(@Valid @RequestBody CreateGardenerCommand command) {
        return gardenersService.createGardener(command);
    }

    @GetMapping("/gardeners")
    @ResponseStatus(HttpStatus.CREATED)
    public List<GardenerDto> getGardeners() {
        return gardenersService.getGardeners();
    }

    @PostMapping("/gardeners/{gardenerId}/gardenworks")
    @ResponseStatus(HttpStatus.CREATED)
    public GardenWorkDto createGardenWork(@PathVariable("gardenerId") long id, @Valid @RequestBody CreateGardenWorkCommand command) {
        return gardenersService.createGardenWork(id, command);
    }
    @GetMapping("/gardeners/{gardenerId}/gardenworks")
    @ResponseStatus(HttpStatus.CREATED)
    public List<GardenWorkDto> getGardenWorks() {
        return gardenersService.getGardenWorks();
    }

    @PutMapping("/gardeners/{gardenerId}/gardenworks/{gardenWorkId}/answer")
    @ResponseStatus(HttpStatus.OK)
    public GardenWorkDto answerGardenWork(@PathVariable("gardenerId") long gardenerId, @PathVariable long gardenWorkId,
                                          @Valid @RequestBody AnswerCommand command) {
        return gardenersService.answerGardenWork(gardenerId, gardenWorkId, command);
    }

    @GetMapping("/pairs")
    @ResponseStatus(HttpStatus.OK)
    public List<NameDescriptionPairDto> getGardenerAndWorkPairs() {
        return gardenersService.getGardenerAndWorkPairs();
    }

}
