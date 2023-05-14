package questions.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import questions.dtos.*;
import questions.exception.MemberWithIdNotFoundException;
import questions.mappers.MemberMapper;
import questions.mappers.QuestionMapper;
import questions.model.Member;
import questions.model.Question;
import questions.repositories.MemberRepository;
import questions.repositories.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    public MemberDto saveMember(CreateMemberCommand command) {
        Member member = Member.builder()
                .name(command.getName())
                .build();

        memberRepository.save(member);

        return memberMapper.toDto(member);
    }

    public List<MemberDto> getMembers() {
        return memberMapper.toDto(memberRepository.findAll());
    }

    public QuestionDto saveQuestion(long id, CreateQuestionCommand command) {
        Member member = findMemberById(id);

        Question question = Question.builder()
                .id(member.getId())
                .questionText(command.getQuestionText())
                .createdAt(LocalDateTime.now())
                .answered(false)
                .answerText(null)
                .answeredAt(null)
                .votes(0)
                .build();
        member.addQuestion(question);
        questionRepository.save(question);
        return questionMapper.toDto(question);

    }

    public List<QuestionDto> getQuestions(long id) {
        findMemberById(id);
        List<Question> questions = findQuestionByMemberId(id);
        return questionMapper.toDto(questions);


    }
    private Member findMemberById(long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new MemberWithIdNotFoundException(id));

    }

    private List<Question> findQuestionByMemberId(long id) {
        return questionRepository.getQuestionsByMemberId(id);
    }

    @Transactional
    public QuestionDto answerQuestion(long id, long questionNumber, AnswerCommand command) {
        Question question = questionRepository.findQuestionByMemberAndId(id, questionNumber);
        question.setAnswered(true);
        question.setAnswerText(command.getAnswerText());
        question.setAnsweredAt(LocalDateTime.now());

        return questionMapper.toDto(question);
    }
}
