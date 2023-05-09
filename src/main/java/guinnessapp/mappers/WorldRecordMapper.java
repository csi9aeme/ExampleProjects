package guinnessapp.mappers;

import guinnessapp.dtos.WorldRecordDto;
import guinnessapp.model.WorldRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorldRecordMapper {

    WorldRecordDto toDto(WorldRecord worldRecord);
    List<WorldRecordDto> toDto(List<WorldRecord> worldRecords);
}
