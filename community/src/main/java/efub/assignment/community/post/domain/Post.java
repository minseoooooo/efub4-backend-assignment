package efub.assignment.community.post.domain;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.entity.BaseTimeEntity;
import efub.assignment.community.post.dto.post.PostRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private Member member;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHeart> postHeartList = new ArrayList<>();


    @Builder
    public Post(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;

    }

    public void update(PostRequestDto dto, Member member){
        this.member = member;
        this.title = dto.getTitle();
        this.content = dto.getContent();

    }


}