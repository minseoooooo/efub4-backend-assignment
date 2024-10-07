package efub.assignment.community.post;

import static org.junit.jupiter.api.Assertions.*;

import efub.assignment.community.account.AccountRepository;
import efub.assignment.community.account.domain.Account;
import efub.assignment.community.board.BoardRepository;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.post.domain.Post;
import java.util.Optional;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void existsByAccount_ShouldReturnTrue_WhenAccountExists() {
        // Given
        Account account = Account.builder()
                .email("abcd@gmail.com")
                .password("abcd1")
                .nickname("aaaa")
                .university("abcd uni")
                .studentId("1234abcd")
                .build();
        accountRepository.save(account);

        /*
            @Builder
    public Board(Account account, String boardName, String boardDescription, String boardNotice) {
        this.account = account;
        this.boardName = boardName;
        this.boardDescription = boardDescription;
        this.boardNotice = boardNotice;
    }
         */

        Board board = Board.builder()
                .account(account)
                .boardName("게시판1")
                .boardDescription("게시판1 설명")
                .boardNotice("사진필수")
                .build();
        boardRepository.save(board);

        Post post = Post.builder()
                .account(account)
                .board(board)
                .title("제목1")
                .content("내용1")
                .writerOpen("Yes")
                .build();
        postRepository.save(post);


        // When
        Boolean exists = postRepository.existsByAccountEmail("abcd@gmail.com");

        // Then
        assertTrue(exists);
    }

    @Test
    public void existsByAccount_ShouldReturnFalse_WhenAccountDoesNotExist() {


        // Given
        Account account = Account.builder()
                .email("abcd@gmail.com")
                .password("abcd1")
                .nickname("aaaa")
                .university("abcd uni")
                .studentId("1234abcd")
                .build();
        accountRepository.save(account);

        /*
            @Builder
    public Board(Account account, String boardName, String boardDescription, String boardNotice) {
        this.account = account;
        this.boardName = boardName;
        this.boardDescription = boardDescription;
        this.boardNotice = boardNotice;
    }
         */

        Board board = Board.builder()
                .account(account)
                .boardName("게시판1")
                .boardDescription("게시판1 설명")
                .boardNotice("사진필수")
                .build();
        boardRepository.save(board);

        Post post = Post.builder()
                .account(account)
                .board(board)
                .title("제목1")
                .content("내용1")
                .writerOpen("Yes")
                .build();
        postRepository.save(post);



        // When
        Boolean exists = postRepository.existsByAccountEmail("abcd@gmail.com");

        // Then
        assertFalse(exists);
    }


}