package guinnessapp.services;

import guinnessapp.dtos.*;
import guinnessapp.exception.RecordCanNotBeatException;
import guinnessapp.exception.RecorderWithIdNotExistException;
import guinnessapp.exception.WorldRecordWithIdNotExistException;
import guinnessapp.mappers.WorldRecordMapper;
import guinnessapp.model.Recorder;
import guinnessapp.model.WorldRecord;
import guinnessapp.repositories.RecorderRepository;
import guinnessapp.repositories.WorldRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorldRecordService {

    private final WorldRecordRepository worldRecordRepository;
    private final RecorderRepository recorderRepository;
    private final WorldRecordMapper mapper;

    public WorldRecordDto saveWorldRecord(WorldRecordCreateCommand command) {
        Recorder recorder = findRecorderById(command.getRecorderId());
        WorldRecord record = WorldRecord.builder()
                .description(command.getDescription())
                .value(command.getValue())
                .unitOfMeasure(command.getUnitOfMeasure())
                .dateOfRecord(command.getDateOfRecord())
                .recorder(recorder)
                .build();

        worldRecordRepository.save(record);
        WorldRecordDto dto = mapper.toDto(record);
        dto.setRecorderName(recorder.getName());

        return dto;
    }

    @Transactional
    public BeatWorldRecordDto beatWorldRecord(long id, BeatWorldRecordCommand command) {
        WorldRecord oldRecord = findWorldRecordById(id);
        Recorder newRecorder = findRecorderById(command.getNewRecorderId());

        checkIfNewRecordIsSmallerThanTheOldRecord(command.getNewRecordValue(), oldRecord.getValue());

        BeatWorldRecordDto newRecord = BeatWorldRecordDto.builder()
                .description(oldRecord.getDescription())
                .unitOfMeasure(oldRecord.getUnitOfMeasure())
                .oldRecorderName(oldRecord.getRecorder().getName())
                .oldRecordValue(oldRecord.getValue())
                .newRecorderName(newRecorder.getName())
                .newRecordValue(command.getNewRecordValue())
                .recordDifference(Math.abs(oldRecord.getValue() - command.getNewRecordValue()))
                .build();

        return newRecord;
    }

    private void checkIfNewRecordIsSmallerThanTheOldRecord(double newRecordValue, double value) {
        if (newRecordValue < value) {
            throw new RecordCanNotBeatException();
        }
    }

    private Recorder findRecorderById(long id) {
        return recorderRepository.findById(id).orElseThrow(
                () -> new RecorderWithIdNotExistException(id));
    }

    private WorldRecord findWorldRecordById(long id) {
        return worldRecordRepository.findById(id).orElseThrow(
                () -> new WorldRecordWithIdNotExistException(id));
    }

}
