package efub.assignment.community.account;

import static org.junit.jupiter.api.Assertions.*;

import efub.assignment.community.account.domain.Account;
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
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void existsByEmail_ShouldReturnTrue_WhenEmailExists() {
        // Given
        Account account = Account.builder()
                .email("abcd@gmail.com")
                .password("abcd1")
                .nickname("aaaa")
                .university("abcd uni")
                .studentId("1234abcd")
                .build();
        accountRepository.save(account);

        // When
        Boolean exists = accountRepository.existsByEmail("abcde@gmail.com");

        // Then
        assertTrue(exists);
    }

    @Test
    public void existsByEmail_ShouldReturnFalse_WhenEmailDoesNotExist() {
        // When
        Boolean exists = accountRepository.existsByEmail("abcde@gmail.com");

        // Then
        assertFalse(exists);
    }


}
