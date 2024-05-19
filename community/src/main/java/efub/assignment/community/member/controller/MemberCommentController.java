package efub.assignment.community.member.controller;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberCommentResponseDto;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts/{accountId}/comments")
public class MemberCommentController {

    private final MemberService memberService;
    private final CommentService commentService;

    /* 작성자별 댓글 목록 조회 */
    @GetMapping
    public ResponseEntity<MemberCommentResponseDto> getAccountCommentList(@PathVariable("accountId") Long accountId){
        Member writer = memberService.findAccountById(accountId);
        List<Comment> commentList = commentService.findAccountCommentList(writer);

        return ResponseEntity.status(HttpStatus.OK)
                .body(MemberCommentResponseDto.of(writer, commentList));
    }
}







