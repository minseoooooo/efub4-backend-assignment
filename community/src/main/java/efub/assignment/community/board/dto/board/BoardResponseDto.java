package efub.assignment.community.board.dto.board;

import efub.assignment.community.board.domain.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardResponseDto { // 게시판 생성, 조회 시의 응답
    private long boardId;
    private String boardName;
    private String boardInfo;
    private String boardNotice;
    private String hostNickname;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static BoardResponseDto from(Board board){
        return new BoardResponseDto(
                board.getBoardId(),
                board.getBoardName(),
                board.getBoardInfo(),
                board.getBoardNotice(),
                board.getHostNickname(),
                board.getCreatedDate(),
                board.getModifiedDate()
        );
    }

}

