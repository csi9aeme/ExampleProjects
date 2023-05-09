package guinnessapp.mappers;

import guinnessapp.dtos.RecorderDto;
import guinnessapp.dtos.RecorderShortDto;
import guinnessapp.model.Recorder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecorderMapper {

    RecorderDto toDto(Recorder recorder);
    List<RecorderDto> toDto (List<Recorder> recorders);
    List<RecorderShortDto> toShortDto (List<Recorder> recorders);
}
