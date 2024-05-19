package efub.assignment.community.comment.domain;


import efub.assignment.community.member.domain.Member;
import efub.assignment.community.comment.dto.comment.CommentRequestDto;
import efub.assignment.community.entity.BaseTimeEntity;
import efub.assignment.community.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", updatable = false)
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", updatable = false)
    private Post post;



    @Builder
    public Comment(String content, Member writer, Post post) {
        this.content = content;
        this.writer = writer;
        this.post = post;

    }

    public void update(CommentRequestDto dto, Member writer){
        this.content = dto.getContent();
        this.writer = writer;
    }


}
