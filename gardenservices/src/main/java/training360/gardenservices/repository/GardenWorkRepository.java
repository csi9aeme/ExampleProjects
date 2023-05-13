package training360.gardenservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training360.gardenservices.model.GardenWork;

@Repository
public interface GardenWorkRepository extends JpaRepository<GardenWork, Long> {
}
