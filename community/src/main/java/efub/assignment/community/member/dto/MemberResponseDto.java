package efub.assignment.community.member.dto;

import efub.assignment.community.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private Long accountId;
    private String email;
    private String nickname;
    private String university;
    private String studentId;

    public MemberResponseDto(Long accountId, String email, String nickname, String university, String studentId) {
        this.accountId = accountId;
        this.email = email;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
    }
    public static MemberResponseDto from(Member member) { //Member 엔티티 객체를 dto로 변환
        return new MemberResponseDto(
                member.getAccountId(),
                member.getEmail(),
                member.getNickname(),
                member.getUniversity(),
                member.getStudentId());
    }
}