package questions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import questions.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {



}
