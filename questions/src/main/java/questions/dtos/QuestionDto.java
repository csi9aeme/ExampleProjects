package questions.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {

    private Long id;

    private String questionText;

    private String answerText;

    private boolean answered;

    private LocalDateTime answeredAt;

    private LocalDateTime createdAt;

    private int votes;
}
