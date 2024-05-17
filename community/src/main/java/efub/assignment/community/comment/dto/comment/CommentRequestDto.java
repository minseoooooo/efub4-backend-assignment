package efub.assignment.community.comment.dto.comment;

import lombok.*;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String accountId;
    private String content;

    @Builder
    public CommentRequestDto(String accountId, String content) {
        this.accountId = accountId;
        this.content = content;
    }
}