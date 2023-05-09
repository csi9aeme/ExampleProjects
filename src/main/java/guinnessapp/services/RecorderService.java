package guinnessapp.services;

import guinnessapp.dtos.RecorderCreateCommand;
import guinnessapp.dtos.RecorderDto;
import guinnessapp.dtos.RecorderShortDto;
import guinnessapp.mappers.RecorderMapper;
import guinnessapp.model.Recorder;
import guinnessapp.repositories.RecorderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecorderService {

    private final RecorderMapper mapper;
    private final RecorderRepository repository;


    public RecorderDto saveRecorder(RecorderCreateCommand command) {
        Recorder recorder = Recorder.builder()
                .name(command.getName())
                .dateOfBirth(command.getDateOfBirth())
                .build();
        repository.save(recorder);
        return mapper.toDto(recorder);
    }

    public List<RecorderShortDto> getRecorderList() {
        List<Recorder> recorders = repository.findRecordersByLetters();

        return mapper.toShortDto(recorders) ;
    }
}
