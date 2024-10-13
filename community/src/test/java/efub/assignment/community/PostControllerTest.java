package efub.assignment.community;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import efub.assignment.community.account.AccountRepository;
import efub.assignment.community.account.dto.SignUpRequestDto;
import efub.assignment.community.post.dto.PostRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest  // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc
@Sql(scripts = "/data.sql")
@ActiveProfiles("test")
@ContextConfiguration(classes = CommunityApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class PostControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper; // 직렬화, 역질렬화를 위한 클래스

    @Autowired
    protected AccountRepository accountRepository;

    @BeforeEach // 테스트 실행 전 실행하는 메서드
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }


    @Test
    @DisplayName("createPost1 : 게시글 작성 성공")
    public void createPost1() throws Exception {
        /* given */
        final String url = "/posts";
        final String boardId = "1";
        final String writerNickname = "TestNickname";
        final String title = "제목";
        final String content = "내용";
        final String writerOpen = "Yes";
        final PostRequestDto requestDto = createDefaultSignUpRequestDto(
                boardId, writerNickname, title, content, writerOpen
        );

        /* when */
        final String requestBody = objectMapper.writeValueAsString(requestDto);

        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        /* then */
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.postId").isNotEmpty())
                .andExpect(jsonPath("$.boardId").value(boardId))
                .andExpect(jsonPath("$.writerNickname").value(writerNickname))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.writerOpen").value(writerOpen));
    }

    @Test
    @DisplayName("createPost2 : 게시글 작성 실패")
    public void createPost2() throws Exception {
        /* given */
        final String url = "/posts";
        final String boardId = "2";
        final String writerNickname = "TestNickname";
        final String title = "제목";
        final String content = "내용";
        final String writerOpen = " ";
        final PostRequestDto requestDto = createDefaultSignUpRequestDto(
                boardId, writerNickname, title, content, writerOpen
        );

        /* when */
        final String requestBody = objectMapper.writeValueAsString(requestDto);

        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        /* then */
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.postId").isNotEmpty())
                .andExpect(jsonPath("$.boardId").value(boardId))
                .andExpect(jsonPath("$.writerNickname").value(writerNickname))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.writerOpen").value(writerOpen));
    }


    private PostRequestDto createDefaultSignUpRequestDto(
            String boardId, String writerNickname, String title, String content, String writerOpen
    ) {
        return PostRequestDto.builder()
                .boardId(boardId)
                .writerNickname(writerNickname)
                .title(title)
                .content(content)
                .writerOpen(writerOpen)
                .build();
    }
}