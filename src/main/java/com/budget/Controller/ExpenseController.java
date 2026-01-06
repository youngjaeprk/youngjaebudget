package com.budget.Controller;

import com.budget.Expense.ExpenseService;
import com.budget.Income.IncomeService;
import com.budget.Member.MemberService;
import com.budget.domain.Address;
import com.budget.domain.Expense;
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
public class ExpenseController {

    private final MemberService memberService;
    private final ExpenseService expenseService;

    // ExpenseController.java
    @GetMapping("/expenses/new")
    public String createExpenseForm(Model model, HttpSession session){
        // 로그인 체크
        Long loginMemberId = (Long) session.getAttribute("memberId");
        if(loginMemberId == null) {
            model.addAttribute("alertMessage", "로그인 또는 회원가입 해주세요");
            model.addAttribute("redirectUrl", "/login");
            return "common/alert";
        }

        model.addAttribute("expenseForm", new ExpenseForm());
        return "expenses/createExpenseForm";
    }

    @PostMapping("/expenses/new")
    public String createExpenseForm(@Valid ExpenseForm form, BindingResult result,
                                    HttpSession session, Model model){
        // 로그인 체크
        Long loginMemberId = (Long) session.getAttribute("memberId");
        if(loginMemberId == null) {
            model.addAttribute("alertMessage", "로그인 또는 회원가입 해주세요");
            model.addAttribute("redirectUrl", "/login");
            return "common/alert";
        }

        if(result.hasErrors()){
            return "expenses/createExpenseForm";
        }

        Member loginMember = memberService.findMemberOne(loginMemberId);

        Expense expense = new Expense();
        expense.setMember(loginMember);
        expense.setCategory(form.getCategory());
        expense.setAmount(form.getAmount());

        expenseService.expenseSave(expense);

        return "redirect:/";
    }

}
