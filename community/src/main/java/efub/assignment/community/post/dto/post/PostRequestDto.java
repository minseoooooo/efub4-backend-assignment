package efub.assignment.community.post.dto.post;

import efub.assignment.community.account.domain.Account;
import efub.assignment.community.post.domain.Post;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostRequestDto {
    @NotBlank(message = "계정 id는 필수입니다.")
    private String accountId;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    public Post toEntity(Account account){
        return Post.builder()
                .account(account)
                .title(this.title)
                .content(this.content)
                .build();
    }


}
