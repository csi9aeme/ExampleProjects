package guinnessapp.repositories;

import guinnessapp.dtos.RecorderShortDto;
import guinnessapp.model.Recorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecorderRepository extends JpaRepository<Recorder, Long> {

    @Query("select recorder from Recorder recorder " +
            "where recorder.name like 'B%'" +
            "or recorder.name like '%e%'" +
            "order by  recorder.dateOfBirth desc")
    List<Recorder> findRecordersByLetters();

}
