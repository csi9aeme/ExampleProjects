package questions.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "answer_text")
    private String answerText;

    private boolean answered;

    @Column(name = "answered_at")
    private LocalDateTime answeredAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private int votes;

    @ManyToOne
    private Member member;



}
