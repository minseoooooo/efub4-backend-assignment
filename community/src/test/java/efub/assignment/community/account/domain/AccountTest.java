package efub.assignment.community.account.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Account.class)
@MockBean(JpaMetamodelMappingContext.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void accountBuilderTest() throws Exception {
        // given
        Account account = Account.builder()
                .email("abcd@gmail.com")
                .password("abcd1")
                .nickname("aaaa")
                .university("abcd uni")
                .studentId("1234abcd")
                .build();

        // when & then
        assertNotNull(account);
        assertEquals("abcd@gmail.com", account.getEmail());
        assertEquals("abcd1", account.getPassword());
        assertEquals("aaaa", account.getNickname());
        assertEquals("abcd uni", account.getUniversity());
        assertEquals("1234abcd", account.getStudentId());
        assertEquals(AccountStatus.REGISTERED, account.getStatus());
    }

    @Test
    public void updateAccountTest() {
        // given
        Account account = Account.builder()
                .email("abcd@gmail.com")
                .password("abcd1")
                .nickname("aaaa")
                .university("abcd uni")
                .studentId("1234abcd")
                .build();

        // when
        account.updateAccount("new_email@gmail.com", "new_nickname", "new_password");

        // then
        assertEquals("newEmail@gmail.com", account.getEmail());
        assertEquals("newNickname", account.getNickname());
        assertEquals("newPassword", account.getPassword());
    }

    @Test
    public void withdrawAccountTest() {
        // given
        Account account = Account.builder()
                .email("abcd@gmail.com")
                .password("abcd1")
                .nickname("aaaa")
                .university("abcd uni")
                .studentId("1234abcd")
                .build();

        // when
        account.withdrawAccount();

        // then
        assertEquals(AccountStatus.UNREGISTERED, account.getStatus());
    }

}