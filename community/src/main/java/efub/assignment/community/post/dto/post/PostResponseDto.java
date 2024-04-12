package efub.assignment.community.post.dto.post;

import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String writerName;
    private String title;
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static PostResponseDto from(Post post, String writerName){
        return new PostResponseDto(
                post.getPostId(),
                writerName,
                post.getTitle(),
                post.getContent(),
                post.getCreatedDate(),
                post.getModifiedDate()
        );

    }

}
