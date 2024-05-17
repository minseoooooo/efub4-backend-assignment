package efub.assignment.community.comment.controller;


import efub.assignment.community.account.service.AccountService;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.comment.AccountInfoRequestDto;
import efub.assignment.community.comment.dto.comment.CommentRequestDto;
import efub.assignment.community.comment.dto.comment.CommentResponseDto;
import efub.assignment.community.comment.service.CommentHeartService;
import efub.assignment.community.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final AccountService accountService;
    private final CommentHeartService commentHeartService;


    // 좋아요 등록
    @PostMapping("{commentId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createCommentLike(@PathVariable("commentId") final Long commentId, @RequestBody final AccountInfoRequestDto requestDto) {
        commentHeartService.create(commentId, requestDto);
        return "좋아요를 눌렀습니다.";
    }

    // 좋아요 삭제
    @DeleteMapping("{commentId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCommentLike(@PathVariable("commentId") final Long commentId, @RequestParam("accountId") final Long accountId) {
        commentHeartService.delete(commentId, accountId);
        return "좋아요가 취소되었습니다.";
    }



}