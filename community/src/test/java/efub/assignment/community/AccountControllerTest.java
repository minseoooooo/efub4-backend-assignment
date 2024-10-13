package efub.assignment.community;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import efub.assignment.community.account.AccountRepository;
import efub.assignment.community.account.dto.SignUpRequestDto;
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
public class AccountControllerTest {
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
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }


    /*  회원가입 : 테스트 통과 */
    @Test
    @DisplayName("createAccount1 : 회원가입 성공 케이스")
    public void createAccount1() throws Exception {
        /* given */
        final String url = "/accounts";
        final String email = "efub@ewhain.net";
        final String password = "!efub1234!";
        final String nickname = "efubBack";
        final String university = "ewha";
        final String studentId = "1234";

        final SignUpRequestDto requestDto = createDefaultSignUpRequestDto(email, password, nickname, university, studentId);

        /* when */
        final String requestBody = objectMapper.writeValueAsString(requestDto); // 객체 -> json
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        /* then */
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountId").isNotEmpty())
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.nickname").value(nickname))
                .andExpect(jsonPath("$.university").value(university))
                .andExpect(jsonPath("$.studentId").value(studentId));


    }


    @Test
    @DisplayName("createAccount2 : 회원가입 실패 케이스")
    public void createAccount2() throws Exception {
        /* given */
        final String url = "/accounts";
        final String email = "efub@ewhain.net";
        final String password = "!efub1234!";
        final String nickname = " ";
        final String university = "ewha";
        final String studentId = "1234";

        final SignUpRequestDto requestDto = createDefaultSignUpRequestDto(email, password, nickname, university, studentId);

        /* when */
        final String requestBody = objectMapper.writeValueAsString(requestDto); // 객체 -> json
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        /* then */
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountId").isNotEmpty())
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.nickname").value(nickname))
                .andExpect(jsonPath("$.university").value(university))
                .andExpect(jsonPath("$.studentId").value(studentId));


    }


    private SignUpRequestDto createDefaultSignUpRequestDto(String email, String password, String nickname,
                                                           String university, String studentId) {

        return SignUpRequestDto.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .university(university)
                .studentId(studentId)
                .build();
    }

}
