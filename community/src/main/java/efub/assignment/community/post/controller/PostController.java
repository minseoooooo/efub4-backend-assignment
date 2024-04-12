package efub.assignment.community.post.controller;

import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.post.AllPostsResponseDto;
import efub.assignment.community.post.dto.post.PostRequestDto;
import efub.assignment.community.post.dto.post.PostResponseDto;
import efub.assignment.community.post.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController // 객체 -> json
@RequiredArgsConstructor // final 이나 @notnull이 붙은 필드의 생성자를 자동생성
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    /*게시글 작성*/
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto createNewPost(@RequestBody @Valid final PostRequestDto dto) { // http 요청의 본문을 객체로 매핑
        Post savedPost = postService.createNewPost(dto);
        return PostResponseDto.from(savedPost, savedPost.getAccount().getNickname());
    }

    /*게시글 조회_전체*/
    @GetMapping
    public AllPostsResponseDto getAllPosts() {
        List<PostResponseDto> list = new ArrayList<>();
        List<Post> posts = postService.findAllPosts();
        for (Post post : posts) {
            PostResponseDto dto = PostResponseDto.from(post, post.getAccount().getNickname());
            list.add(dto);

        }
        long count = postService.countAllPosts();

        return new AllPostsResponseDto(list, count);

    }

    /*게시글 조회_1개*/
    @GetMapping("/{id}")
    public PostResponseDto getOnePost(@PathVariable(name = "id") Long id) {
        Post post = postService.findPostById(id);
        return PostResponseDto.from(post, post.getAccount().getNickname());
    }

    /*게시글 수정*/
    @PutMapping("/{id}") // patch는 일부 수정, put은 리소스 전체 수정
    public PostResponseDto updatePost(@PathVariable(name = "id") Long id, @RequestBody @Valid final PostRequestDto dto) {
        Long postId = postService.updatePost(id, dto);
        Post post = postService.findPostById(postId);
        return PostResponseDto.from(post, post.getAccount().getNickname());

    }



    /*게시글 삭제*/
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable(name = "id")Long id,
                             @RequestParam(name = "accountId")Long account_id){
        postService.deletePost(id, account_id);

        return "성공적으로 삭제되었습니다.";
    }

}