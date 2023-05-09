package guinnessapp.repositories;

import guinnessapp.model.WorldRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorldRecordRepository extends JpaRepository<WorldRecord, Long> {
}
