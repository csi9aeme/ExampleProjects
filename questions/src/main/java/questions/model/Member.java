package questions.model;

import jakarta.persistence.*;
import lombok.*;
import questions.dtos.QuestionDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Question> questions;

    public void addQuestion(Question question) {
        questions.add(question);
        question.setMember(this);
    }

}
