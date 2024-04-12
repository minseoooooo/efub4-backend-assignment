package efub.assignment.community.board.dto.board;

import efub.assignment.community.account.domain.Account;
import efub.assignment.community.board.domain.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardRequestDto { // 게시판 생성

    @NotBlank(message = "계정 id는 필수입니다.")
    private String accountId;

    // 게시판 이름, 게시판 설명, 게시판 공지, 게시판 주인 닉네임
    @NotBlank(message = "게시판 주인 닉네임은 필수입니다.")
    private String hostNickname;

    @NotBlank(message = "게시판 이름은 필수입니다.")
    private String boardName;

    //@NotBlank(message = "게시판 설명은 필수입니다.")
    private String boardInfo;

    //@NotBlank(message = "게시판 공지는 필수입니다.")
    private String boardNotice;

    public Board toEntity(Account account) {
        return Board.builder()
                .account(account)
                .hostNickname(hostNickname)
                .boardName(boardName)
                .boardInfo(boardInfo)
                .boardNotice(boardNotice)
                .build();
    }
}
