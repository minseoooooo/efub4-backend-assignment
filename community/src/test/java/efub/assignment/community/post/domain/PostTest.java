package efub.assignment.community.post.domain;

import static org.junit.jupiter.api.Assertions.*;

import efub.assignment.community.account.domain.Account;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.post.dto.PostUpdateDto;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(Post.class)
@MockBean(JpaMetamodelMappingContext.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PostTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void postBuilderTest() {
        // given
        Account account = Mockito.mock(Account.class);
        Board board = Mockito.mock(Board.class);

        // when
        Post post = Post.builder()
                .account(account)
                .board(board)
                .title("제목1")
                .content("내용1")
                .writerOpen("Yes")
                .build();

        // then
        assertNotNull(post);
        assertEquals(account, post.getAccount());
        assertEquals(board, post.getBoard());
        assertEquals("제목2", post.getTitle());
        assertEquals("내용2", post.getContent());
        assertEquals("Yes", post.getWriterOpen());
        assertNotNull(post.getCommentList());
        assertNotNull(post.getPostHeartList());
    }

    @Test
    public void updatePostTest() {
        // given
        Account account = Mockito.mock(Account.class);
        Board board = Mockito.mock(Board.class);
        Post post = Post.builder()
                .account(account)
                .board(board)
                .title("제목1")
                .content("내용1")
                .writerOpen("Yes")
                .build();

        // when
        PostUpdateDto updateDto = new PostUpdateDto("제목2", "내용2");
        post.update(updateDto);

        // then
        assertEquals("제목2", post.getTitle());
        assertEquals("내용2", post.getContent());
    }

}