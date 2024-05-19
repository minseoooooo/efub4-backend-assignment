package efub.assignment.community.board.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.board.BoardRequestDto;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.exception.CustomDeleteException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static efub.assignment.community.exception.ErrorCode.PERMISSION_REJECTED_USER;


@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberService memberService;

    public Board createNewBoard(BoardRequestDto dto) {
        Member member = memberService.findAccountById(Long.parseLong(dto.getAccountId()));
        Board board = dto.toEntity(member);
        Board savedBoard = boardRepository.save(board);
        return savedBoard;
    }


    @Transactional(readOnly = true)
    public Board findBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 board를 찾을 수 없습니다. id = " + boardId));
        return board;

    }

    //public Long updateBoard(Long id, BoardUpdateRequestDto dto) {
    public Long updateBoard(Long id, BoardRequestDto dto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 board를 찾을 수 없습니다. id = " + id));
        Member member = memberService.findAccountById(Long.parseLong(dto.getAccountId()));
        board.update(dto, member);
        return board.getBoardId();
    }

    public void deleteBoard(Long id, Long accountId) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 board를 찾을 수 없습니다. id = " + id));
        if (accountId != board.getMember().getAccountId()) {
            throw new CustomDeleteException(PERMISSION_REJECTED_USER);
        }
        boardRepository.delete(board);

    }
}
