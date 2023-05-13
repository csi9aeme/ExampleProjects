package training360.gardenservices.mapper;

import org.mapstruct.Mapper;
import training360.gardenservices.dto.GardenWorkDto;
import training360.gardenservices.dto.GardenerDto;
import training360.gardenservices.model.GardenWork;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GardenWorkMapper {

    GardenWorkDto toDto(GardenWork work);
    List<GardenWorkDto> toDto(List<GardenWork> works);
}
