// 우에.. 이제 제출하면 되는 상태! 오예

package efub.assignment.community.member.controller;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.*;
import efub.assignment.community.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController { // 클라이언트로부터 요청을 받아오는 (==컨트롤러)
    private final MemberService memberService;

    /* 계정 생성 기능 */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public SignUpResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto) { // 보내는거 받는거 전부 dto 타입임

        Long id = memberService.signUp(requestDto); // 여기서 로그인 과정을 거침
        Member findMember = memberService.findAccountById(id); // 있는지 조회(id로)
        return SignUpResponseDto.from(findMember); // 클라이언트에게 전달하는 response도 dto 타입으로 (이걸 클라이언트에게 보내는건 서비스가 함)

    }

    /* 계정 조회 기능 (나) */
    @GetMapping("/{me}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto getAccount(@PathVariable Long me) {
        Member findMember = memberService.findAccountById(me);
        return MemberResponseDto.from(findMember);
    }

    /* 계정 프로필 수정 */
    @PatchMapping("/profile/{member_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberUpdateResponseDto update(@PathVariable final Long member_id, @RequestBody @Valid final MemberUpdateRequestDto requestDto) {

        Long id = memberService.update(member_id, requestDto);
        Member findMember = memberService.findAccountById(id);
        return MemberUpdateResponseDto.from(findMember);

    }

    /* 계정 삭제 (휴면 계정으로) */
    @PatchMapping("/{member_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String withdraw(@PathVariable long member_id) {
        memberService.withdraw(member_id);
        return "성공적으로 탈퇴되었습니다.(휴면)";

    }

    /* 계정 삭제 (db에서도 삭제) */
//    @DeleteMapping("/{member_id}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public String delete(@PathVariable long accountId) {
//        accountService.delete(accountId);
//        return "성공적으로 탈퇴되었습니다 (DB삭제)";
//
//    }
}
