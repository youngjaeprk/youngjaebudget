package com.budget.Income;

import com.budget.domain.Income;
import com.budget.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IncomeService {
    private final IncomeRepository incomeRepository;
    @Transactional
    public Long incomeSave(Income income){
        incomeRepository.incomeSave(income);
        return income.getId();
    }

    /**
     * 특정 수입 조회
     */
    public Income findOne(Long incomeId) {
        return incomeRepository.incomeFindOne(incomeId);
    }

    /**
     * 회원의 전체 수입 조회
     */
    public List<Income> findIncomesByMember(Long memberId) {
        return incomeRepository.findIncomeByMemberId(memberId);
    }

    /**
     * 기간별 수입 조회
     */
    public List<Income> findIncomesByDateRange(Long memberId, LocalDate startDate, LocalDate endDate) {
        return incomeRepository.findIncomeByDateRange(memberId, startDate, endDate);
    }

    /**
     * 카테고리별 수입 조회
     */
    public List<Income> findIncomesByCategory(Long memberId, String category) {
        return incomeRepository.findIncomeByCategory(memberId, category);
    }

    /**
     * 월별 총 수입 계산
     */
    public BigDecimal getMonthlyTotalIncome(Long memberId, int year, int month) {
        return incomeRepository.calculateMonthlyIncome(memberId, year, month);
    }

    /**
     * 수입 수정
     */
    @Transactional
    public void updateIncome(Long incomeId, String category, BigDecimal amount,
                             LocalDate date, String memo) {
        Income income = incomeRepository.incomeFindOne(incomeId);
        income.setCategory(category);
        income.setAmount(amount);
        income.setDate(date);
        income.setMemo(memo);
        // 변경 감지(dirty checking)로 자동 update
    }
}
