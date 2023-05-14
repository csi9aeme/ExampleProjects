package questions.mappers;

import org.mapstruct.Mapper;
import questions.dtos.MemberDto;
import questions.model.Member;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDto toDto(Member member);
    List<MemberDto> toDto(List<Member> members);
}
