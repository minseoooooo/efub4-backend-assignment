package efub.assignment.community.post.domain;

import efub.assignment.community.account.domain.Account;
import efub.assignment.community.entity.BaseTimeEntity;
import efub.assignment.community.post.dto.post.PostRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 테이블과의 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long postId; // primary key

    @ManyToOne // 두 엔티티 사이의 다대일 관계
    @JoinColumn(name = "account_id", updatable = false) // 외래키 사용
    private Account account;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Builder
    public Post(Account account, String title, String content) {
        this.account = account;
        this.title = title;
        this.content = content;

    }

    public void update(PostRequestDto dto, Account account){
        this.account = account;
        this.title = dto.getTitle();
        this.content = dto.getContent();

    }


}