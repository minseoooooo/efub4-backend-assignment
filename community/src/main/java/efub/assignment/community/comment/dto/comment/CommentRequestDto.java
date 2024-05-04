package efub.assignment.community.comment.dto.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    private String accountId;
    private String content;
}