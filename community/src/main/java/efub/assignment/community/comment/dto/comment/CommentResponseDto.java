package efub.assignment.community.comment.dto.comment;

import efub.assignment.community.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {
    private Long commentId;
    private Long postId;
    private String writerNickname;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .postId(comment.getPost().getPostId())
                .writerNickname(comment.getWriter().getNickname())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .build();
    }


    public static CommentResponseDto from(Comment comment, String writerNickname) {
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getPost().getPostId(),
                writerNickname,
                comment.getContent(),
                comment.getCreatedDate(),
                comment.getModifiedDate()
        );

    }


}