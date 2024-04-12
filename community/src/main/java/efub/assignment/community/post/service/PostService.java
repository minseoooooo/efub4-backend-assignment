package efub.assignment.community.post.service;



import efub.assignment.community.account.domain.Account;
import efub.assignment.community.account.service.AccountService;
import efub.assignment.community.exception.CustomDeleteException;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.post.PostRequestDto;
import efub.assignment.community.post.repository.PostRepository;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static efub.assignment.community.exception.ErrorCode.PERMISSION_REJECTED_USER;

@Service // 서비스 계층
@Transactional // 하나의 트랜잭션으로!! 처리
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AccountService accountService;

    public Post createNewPost(PostRequestDto dto){ // 엔티티가 아닌 dto 를 파라미터로~~~
        Account account = accountService.findAccountById(Long.parseLong(dto.getAccountId()));
        Post post = dto.toEntity(account);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    @Transactional(readOnly = true)
    public List<Post> findAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Transactional(readOnly = true)
    public long countAllPosts(){
        return postRepository.count();
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 Post를 찾을 수 없습니다.id="+postId)); // 에러시
        return post;
    }

    public Long updatePost(Long id, PostRequestDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 Post를 찾을 수 없습니다.id="+id));
        Account account = accountService.findAccountById(Long.parseLong(dto.getAccountId()));
        post.update(dto, account);
        return post.getPostId();
    }

    public void deletePost(Long id, Long accountId){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Post를 찾을 수 없습니다.id=" + id));
        if(accountId!=post.getAccount().getAccountId()){
            throw new CustomDeleteException(PERMISSION_REJECTED_USER);
        }
        postRepository.delete(post);
    }
}