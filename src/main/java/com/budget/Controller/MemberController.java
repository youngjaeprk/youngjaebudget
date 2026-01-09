package com.budget.Controller;

import com.budget.Member.MemberService;
import com.budget.domain.Address;
import com.budget.domain.Member;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode(), form.getDetailAddress());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        member.setPassword(form.getPassword());

        memberService.memberJoin(member);
        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    //회원 로그인
    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "members/loginMemberForm";
    }

    @PostMapping("/members/login")
    public String login(@Valid LoginForm form, BindingResult result,
                        HttpSession session) {
        if(result.hasErrors()) {
            return "members/loginMemberForm";
        }

        // 로그인 로직
        Member member = memberService.login(form.getLoginId(), form.getPassword());

        if(member == null) {
            result.reject("loginFail", "이메일 또는 비밀번호가 틀렸습니다");
            return "Controller/loginForm";
        }

        // 세션에 회원 정보 저장
        session.setAttribute("memberId", member.getId());

        return "redirect:/";
    }
}
