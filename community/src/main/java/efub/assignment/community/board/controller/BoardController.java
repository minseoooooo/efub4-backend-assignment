package efub.assignment.community.board.controller;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.board.BoardRequestDto;
import efub.assignment.community.board.dto.board.BoardResponseDto;
import efub.assignment.community.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    /*게시판 생성*/
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoardResponseDto createNewBoard(@RequestBody @Valid final BoardRequestDto dto) {
        Board savedBoard = boardService.createNewBoard(dto);
        return BoardResponseDto.from(savedBoard);

    }

    /*게시판 조회*/
    @GetMapping("/{id}")
    public BoardResponseDto getOneBoard(@PathVariable Long id) {
        Board board = boardService.findBoardById(id);
        return BoardResponseDto.from(board);
    }

    /*게시판 수정*/
    @PutMapping("/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody @Valid final BoardRequestDto dto) {
        Long boardId = boardService.updateBoard(id, dto);
        Board board = boardService.findBoardById(boardId);
        return BoardResponseDto.from(board);
    }

    /*게시판 삭제*/
    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id,
                              @RequestParam(name = "accountId") Long account_id) {
        boardService.deleteBoard(id, account_id);
        return "성공적으로 삭제되었습니다.";
    }
}






