package efub.assignment.community.account.dto;

import efub.assignment.community.account.domain.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountUpdateResponseDto {
    //private Long accountId;
    private String email;
    private String nickname;
    private String university;
    private String studentId;

    public AccountUpdateResponseDto(String email, String nickname, String university, String studentId) {
        //this.accountId = accountId;
        this.email = email;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
    }
    public static AccountUpdateResponseDto from(Account account) { //Account 엔티티 객체를 dto로 변환
        return new AccountUpdateResponseDto(
                account.getEmail(),
                account.getNickname(),
                account.getUniversity(),
                account.getStudentId());
    }
}