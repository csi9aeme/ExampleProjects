package training360.gardenservices.mapper;

import org.mapstruct.Mapper;
import training360.gardenservices.dto.GardenerDto;
import training360.gardenservices.model.Gardener;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GardenerMapper {

    GardenerDto toDto(Gardener gardener);
    List<GardenerDto> toDto(List<Gardener> gardeners);
}
