package questions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import questions.model.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question  q where q.member.id = :id")
    List<Question> getQuestionsByMemberId(@Param("id") long id);

    @Query("select q from Question q where q.member.id = :memberId AND q.id = :questionId")
    Question findQuestionByMemberAndId(@Param("memberId") long memberId, @Param("questionId") long questionId);
}
