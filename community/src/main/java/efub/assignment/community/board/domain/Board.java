package efub.assignment.community.board.domain;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.board.dto.board.BoardRequestDto;
import efub.assignment.community.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 테이블과의 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseTimeEntity {

    // board의 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", updatable = false)
    private Long boardId;

    // 외래키(member id) 가져오기
    @ManyToOne // board:account_id = many:one
    @JoinColumn(name = "account_id", updatable = false) // 외래키 사용 // updatable -> false???
    private Member member;

    // 게시판 이름
    @Column(nullable = false, length = 20)
    private String boardName;


    // 게시판 설명
    @Column(nullable = false, length = 50)
    private String boardInfo;

    // 게시판 공지
    @Column(nullable = false, length = 50)
    private String boardNotice;

    // 게시판 주인 닉네임 (외래키랑 연결?)
    @Column(nullable = false, length = 16)
    private String hostNickname;

    // 생성일 -> baseTimeEntity 받을꺼임

    // 수정일 -> baseTimeEntity 받을꺼임

    @Builder
    public Board(Member member, String hostNickname, String boardName, String boardInfo, String boardNotice) {
        this.member = member;
        this.hostNickname = hostNickname;
        this.boardName = boardName;
        this.boardInfo = boardInfo;
        this.boardNotice = boardNotice;
    }


    public void update(BoardRequestDto dto, Member member) {
        this.member = member;
        this.hostNickname = dto.getHostNickname();
        this.boardName = dto.getBoardName();
    }
}

