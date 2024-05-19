package efub.assignment.community.member.service;

import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberUpdateRequestDto;
import efub.assignment.community.member.repository.MemberRepository;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long signUp(@Valid SignUpRequestDto requestDto) {
        if (existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 email입니다." + requestDto.getEmail()); // 이메일 이미 존재하면 띄우는 에러 메시지
        }
        Member member = memberRepository.save(requestDto.toEntity()); // 이미 존재하는 이메일이 아니면 이 부분을 실행 // save는 JPA 에서 기본적으로 제공하는 기능
        return member.getAccountId();
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public Member findAccountById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Account를 찾을 수 없습니다.id="+id));
    }

    public Long update(Long accountId, MemberUpdateRequestDto requestDto) {
        Member member = findAccountById(accountId);
        member.updateAccount(requestDto.getNickname());
        return member.getAccountId();
    }

    @Transactional
    public void withdraw(Long member_id) {
        Member member = findAccountById(member_id);
        member.withdrawAccount();

    }

    public void delete(Long accountId) {
        Member member = findAccountById(accountId);
        memberRepository.delete(member); // delete는 JPA에 이미 있음, 그걸 가져와서 사용하는거임니다.
    }
}