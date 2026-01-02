package com.budget.Expense;

import com.budget.domain.Expense;
import com.budget.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Transactional
    public Long expenseSave(Expense expense){
        expenseRepository.expenseSave(expense);
        return expense.getId();
    }

    /**
     * 회원의 전체 수입 조회
     */
    public List<Expense> findExpenseByMemberId(Long memberId) {
        return expenseRepository.findExpenseByMemberId(memberId);
    }

    /**
     * 기간별 지출 조회
     */
    public List<Expense> findExpenseByDateRange(Long memberId, LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findExpenseByDateRange(memberId, startDate, endDate);
    }

    /**
     * 카테고리별 지출 조회
     */
    public List<Expense> findExpensesByCategory(Long memberId, String category) {
        return expenseRepository.findExpenseByCategory(memberId, category);
    }

    /**
     * 월별 총 지출 계산
     */
    public BigDecimal getMonthlyTotalExpense(Long memberId, int year, int month) {
        return expenseRepository.calculateMonthlyExpense(memberId, year, month);
    }

    /**
     * 지출 수정
     */
    @Transactional
    public void updateExpense(Long expenseId, String category, BigDecimal amount,
                             LocalDate date, String memo) {
        Expense expense = expenseRepository.expenseFindOne(expenseId);
        expense.setCategory(category);
        expense.setAmount(amount);
        expense.setDate(date);
        expense.setMemo(memo);
        // 변경 감지(dirty checking)로 자동 update
    }
}
