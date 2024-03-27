package ezenweb.controller;


import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired private MemberService memberService;

    @PostMapping("/signup/post.do") // 1. 회원가입
    public boolean doSignupPost(@RequestBody MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        return memberService.doSignupPost(memberDto);
    }

    @PostMapping("/login/post.do") // 2. 로그인
    public boolean doLoginPost( MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        return memberService.doLoginPost(memberDto);
    }

        @GetMapping("/logout/get.do")   // 3. 로그아웃
    public boolean doLogOutGet(){
        return memberService.doLogOutGet();
    }

    @GetMapping("/login/info/get.do")
    public MemberDto doLoginInfo(){
        return memberService.doLoginInfo();
    }

}
