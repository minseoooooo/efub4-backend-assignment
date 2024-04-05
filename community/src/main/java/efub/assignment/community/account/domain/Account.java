package efub.assignment.community.account.domain;


import efub.assignment.community.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static efub.assignment.community.account.domain.AccountStatus.REGISTERED;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account extends BaseTimeEntity {

    @Id // pk 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false)
    private long accountId; // 데베에 이 컬럼으로 들어감

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false)
    private String encondedPassword;

    @Column(nullable = false, updatable = true, length = 16) // 주의! 업데이터블 false인 상태임!!
    private String nickname;

    @Column(nullable = false, updatable = false, length = 20) // 주의! 업데이터블 false인 상태임!!
    private String university;

    @Column(nullable = false, updatable = false, length = 10) // 주의! 업데이터블 false인 상태임!!
    private String studentId;


    @Enumerated(EnumType.STRING) // enum 타입 -> id(0,1)로 인식함
    private AccountStatus status; // REGISTERED or UNREGISTERED

    @Builder
    public Account(String email, String password, String nickname, String university, String studentId, AccountStatus status) {
        this.email = email;
        this.encondedPassword = password;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
        this.status = REGISTERED;
    } // constructor로 생성함

    public void updateAccount(String nickname) {
        this.nickname = nickname;
    }

    public void withdrawAccount() {
        this.status = AccountStatus.UNREGISTERED;
    }


}
