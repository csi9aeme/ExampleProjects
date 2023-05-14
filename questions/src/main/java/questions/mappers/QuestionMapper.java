package questions.mappers;

import org.mapstruct.Mapper;
import questions.dtos.QuestionDto;
import questions.model.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto toDto(Question question);
    List<QuestionDto> toDto(List<Question> question);
}
