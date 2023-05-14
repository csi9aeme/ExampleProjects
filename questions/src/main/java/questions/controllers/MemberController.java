package questions.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import questions.dtos.*;
import questions.services.MemberService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto saveMember(@Valid @RequestBody CreateMemberCommand command) {
        return service.saveMember(command);
    }

    @GetMapping
    public List<MemberDto> getMembers() {
        return service.getMembers();
    }

    @PostMapping("/{id}/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto saveQuestion(@PathVariable("id") long id, @Valid @RequestBody CreateQuestionCommand command) {
        return service.saveQuestion(id, command);
    }

    @GetMapping("/{id}/questions")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionDto> getQuestions(@PathVariable("id") long id) {
        return service.getQuestions(id);
    }

    @PutMapping("/{id}/questions/{questionNumber}/answer")
    @ResponseStatus(HttpStatus.OK)
    public QuestionDto answerQuestion(@PathVariable("id") long id, @PathVariable("questionNumber") long questionNumber,
                                            @Valid @RequestBody AnswerCommand command) {
        return service.answerQuestion(id, questionNumber, command);
    }

}
