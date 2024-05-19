package efub.assignment.community.member.dto;


import efub.assignment.community.member.domain.Member;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.comment.CommentResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCommentResponseDto {
    private String writerNickname;
    private List<CommentResponseDto> accountCommentList;
    private Long count;

    public static MemberCommentResponseDto of(Member member, List<Comment> commentList){
        return MemberCommentResponseDto.builder()
                .writerNickname(member.getNickname())
                .accountCommentList(commentList.stream().map(CommentResponseDto::of).collect(Collectors.toList()))
                .count((long) commentList.size())
                .build();
    }
}
