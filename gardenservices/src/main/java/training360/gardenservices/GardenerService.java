package training360.gardenservices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training360.gardenservices.dto.*;
import training360.gardenservices.exception.GardenWorkWithIdNotExistException;
import training360.gardenservices.exception.GardenerWithIdNotExistException;
import training360.gardenservices.mapper.GardenWorkMapper;
import training360.gardenservices.mapper.GardenerMapper;
import training360.gardenservices.model.GardenWork;
import training360.gardenservices.model.Gardener;
import training360.gardenservices.repository.GardenWorkRepository;
import training360.gardenservices.repository.GardenerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GardenerService {

    private final GardenerMapper gardenerMapper;
    private final GardenWorkMapper gardenWorkMapper;
    private final GardenerRepository gardenerRepository;
    private final GardenWorkRepository gardenWorkRepository;

    public GardenerDto createGardener(CreateGardenerCommand command) {
        Gardener gardener = Gardener.builder()
                .name(command.getName())
                .build();
        gardenerRepository.save(gardener);
        return gardenerMapper.toDto(gardener);
    }

    public List<GardenerDto> getGardeners() {
        return gardenerMapper.toDto(gardenerRepository.findAll());
    }

    public GardenWorkDto createGardenWork(long id, CreateGardenWorkCommand command) {
        Gardener gardener = findGardenerById(id);

        GardenWork gardenWork = GardenWork.builder()
                .done(false)
                .description(command.getDescription())
                .createdAt(LocalDateTime.now())
                .answer(null)
                .answeredAt(null)
                .gardener(gardener)
                .build();
        gardener.addGardenWork(gardenWork);
        gardenWorkRepository.save(gardenWork);

        return gardenWorkMapper.toDto(gardenWork);
    }



    public List<GardenWorkDto> getGardenWorks() {
        return gardenWorkMapper.toDto((gardenWorkRepository.findAll()));
    }

    @Transactional
    public GardenWorkDto answerGardenWork(long gardenerId, long gardenWorkId, AnswerCommand command) {
        findGardenerById(gardenerId);

        GardenWork gardenWork = findGardenWorkById(gardenWorkId);

        gardenWork.setDone(true);
        gardenWork.setAnswer(command.getAnswer());
        gardenWork.setAnsweredAt(LocalDateTime.now());
        gardenWorkRepository.save(gardenWork);

        return gardenWorkMapper.toDto(gardenWork);
    }


    private Gardener findGardenerById(long id) {
        return gardenerRepository.findById(id).orElseThrow(
                () -> new GardenerWithIdNotExistException(id));
    }
    private GardenWork findGardenWorkById(long gardenWorkId) {
        return gardenWorkRepository.findById(gardenWorkId).orElseThrow(
                () -> new GardenWorkWithIdNotExistException(gardenWorkId));
    }

    public List<NameDescriptionPairDto> getGardenerAndWorkPairs() {
        return gardenerRepository.findWorkPairs();
    }
}
