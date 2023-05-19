package training360.gardenservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import training360.gardenservices.dto.NameDescriptionPairDto;
import training360.gardenservices.model.Gardener;

import java.util.List;

public interface GardenerRepository extends JpaRepository<Gardener, Long> {

//    @Query("select  new training360.gardenservices.dto.NameDescriptionPairDto(gardener.name, gardenwork.description) " +
//            "from Gardener gardener inner join gardener.gardenWorks  gardenwork where gardenwork.done  = false order by gardener.name, gardenwork.description")
//    List<NameDescriptionPairDto> findWorkPairs();

    @Query("select  new training360.gardenservices.dto.NameDescriptionPairDto(gardenwork.gardener.name, gardenwork.description) from GardenWork gardenwork where gardenwork.done  = false order by gardenwork.gardener.name, gardenwork.description")
    List<NameDescriptionPairDto> findWorkPairs();
}
