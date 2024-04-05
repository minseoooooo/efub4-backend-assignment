package efub.assignment.community.account.service;

import efub.assignment.community.account.dto.SignUpRequestDto;
import efub.assignment.community.account.domain.Account;
import efub.assignment.community.account.dto.AccountUpdateRequestDto;
import efub.assignment.community.account.dto.SignUpRequestDto;
import efub.assignment.community.account.repository.AccountRepository;
import efub.assignment.community.account.dto.AccountResponseDto;
import efub.assignment.community.account.service.AccountService;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Long signUp(@Valid SignUpRequestDto requestDto) {
        if (existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 email입니다." + requestDto.getEmail()); // 이메일 이미 존재하면 띄우는 에러 메시지
        }
        Account account = accountRepository.save(requestDto.toEntity()); // 이미 존재하는 이메일이 아니면 이 부분을 실행 // save는 JPA 에서 기본적으로 제공하는 기능
        return account.getAccountId();
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Account를 찾을 수 없습니다.id="+id));
    }

    public Long update(Long accountId, AccountUpdateRequestDto requestDto) {
        Account account = findAccountById(accountId);
        account.updateAccount(requestDto.getNickname());
        return account.getAccountId();
    }

    @Transactional
    public void withdraw(Long member_id) {
        Account account = findAccountById(member_id);
        account.withdrawAccount();

    }

    public void delete(Long accountId) {
        Account account = findAccountById(accountId);
        accountRepository.delete(account); // delete는 JPA에 이미 있음, 그걸 가져와서 사용하는거임니다.
    }
}