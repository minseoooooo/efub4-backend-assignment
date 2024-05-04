package efub.assignment.community.post.controller;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.comment.CommentRequestDto;
import efub.assignment.community.comment.dto.comment.CommentResponseDto;
import efub.assignment.community.comment.service.CommentService;
import efub.assignment.community.post.dto.post.PostCommentResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class PostCommentController {

    private final CommentService commentService;

    /* 게시글에 댓글 생성 */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("postId") Long postId,
                                                            @RequestBody CommentRequestDto requestDto) {
        Comment comment = commentService.saveComment(postId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommentResponseDto.of(comment));
    }

    /* 게시글의 댓글 목록 조회 */
    @GetMapping
    public ResponseEntity<PostCommentResponseDto> getPostCommentList(@PathVariable("postId") Long postId) {
        List<Comment> commentList = commentService.findPostCommentList(postId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(PostCommentResponseDto.of(postId, commentList));
    }

    /* 댓글 수정 */
    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable(name = "postId") Long postId,
                                            @PathVariable(name = "id") Long id,
                                            @RequestBody @Valid final CommentRequestDto dto,
                                            @RequestParam(name = "accountId")Long accountId) {
        Long commentId = commentService.updateComment(id, dto, accountId);
        Comment comment = commentService.findCommentById(commentId);
        return CommentResponseDto.from(comment, comment.getWriter().getNickname());

    }


    /* 댓글 삭제 */
    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable(name = "postId") Long postId,
                                @PathVariable(name = "id") Long commentId,
                                @RequestParam(name = "accountId") Long accountId) {

        commentService.deleteComment(commentId, accountId);

        return "성공적으로 삭제되었습니다.";

    }


}