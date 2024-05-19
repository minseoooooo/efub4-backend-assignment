package efub.assignment.community.comment.service;


import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.comment.CommentRequestDto;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.exception.CustomDeleteException;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static efub.assignment.community.exception.ErrorCode.PERMISSION_REJECTED_USER;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final MemberService memberService;
    private final PostService postService;
    private final CommentRepository commentRepository;

    public Comment saveComment(Long postId, CommentRequestDto requestDto) {
        Member writer = memberService.findAccountById(Long.valueOf(requestDto.getAccountId()));
        Post post = postService.findPostById(postId);

        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .writer(writer)
                .post(post)
                .build();
        commentRepository.save(comment);

        return comment;
    }

    public List<Comment> findPostCommentList(Long postId) {
        Post post = postService.findPostById(postId);
        return commentRepository.findAllByPost(post);
    }

    public List<Comment> findAccountCommentList(Member writer) {
        return commentRepository.findAllByWriter(writer);
    }


    @Transactional(readOnly = true)
    public Comment findCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Comment를 찾을 수 없습니다.id=" + commentId));
        return comment;
    }

    public Long updateComment(Long commentId, CommentRequestDto dto, Long accountId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Comment를 찾을 수 없습니다.id=" + commentId));
        if (accountId != comment.getWriter().getAccountId()) {
            throw new CustomDeleteException(PERMISSION_REJECTED_USER);
        }
        Member member = memberService.findAccountById(Long.parseLong(dto.getAccountId()));
        comment.update(dto, member);
        return comment.getCommentId();
    }

    public void deleteComment(Long id, Long accountId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Comment를 찾을 수 없습니다.id=" + id));
        if (accountId != comment.getWriter().getAccountId()) {
            throw new CustomDeleteException(PERMISSION_REJECTED_USER);
        }
        commentRepository.delete(comment);
    }


}