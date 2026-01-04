package com.budget.Controller;

import com.budget.Income.IncomeService;
import com.budget.Member.MemberService;
import com.budget.domain.Address;
import com.budget.domain.Income;
import com.budget.domain.Member;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class IncomeController {

    private final MemberService memberService;
    private final IncomeService incomeService;

    @GetMapping("/incomes/new")
    public String createIncomeForm(Model model){
        model.addAttribute("incomeForm",new IncomeForm());
        return "incomes/createIncomeForm";
    }

    @PostMapping("/incomes/new")
    public String createIncomeForm(@Valid IncomeForm form, BindingResult result,  HttpSession session){

        if(result.hasErrors()){
            return "incomes/createIncomeForm";
        }

        // 1. 로그인한 회원 정보 가져오기
        Long loginMemberId = (Long) session.getAttribute("memberId");  // 1
        Member loginMember = memberService.findMemberOne(loginMemberId);     // id=1인 회원

        // 2. Income 엔티티 생성
        Income income = new Income();
        income.setMember(loginMember);  // member_id = 1 (FK)
        income.setCategory(form.getCategory());
        income.setAmount(form.getAmount());
        // income의 id는 아직 null

        // 3. DB에 저장
        incomeService.incomeSave(income);
        // 이제 income_id = 100 (자동생성), member_id = 1 (FK)
        //memberService.memberJoin(member);
        return "redirect:/";
    }

}
