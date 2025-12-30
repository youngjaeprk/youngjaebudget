package com.budget.repository;

import com.budget.domain.Expense;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ExpenseRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 지출 저장
     */
    public void expenseSave(Expense expense) {
        em.persist(expense);
    }

    /**
     * 특정 지출 조회
     */
    public Expense expenseFindOne(Long id) {
        return em.find(Expense.class, id);
    }

    /**
     * 회원의 전체 지출 조회
     */
    public List<Expense> findExpenseByMemberId(Long memberId) {
        return em.createQuery(
                        "select e from Expense e where e.member.id = :memberId order by e.date desc",
                        Expense.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    /**
     * 기간별 지출 조회 (ex: 1월 1일 ~ 1월 31일)
     */
    public List<Expense> findExpenseByDateRange(Long memberId, LocalDate startDate, LocalDate endDate) {
        return em.createQuery(
                        "select e from Expense e where e.member.id = :memberId " +
                                "and e.date between :startDate and :endDate order by e.date desc",
                        Expense.class)
                .setParameter("memberId", memberId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    /**
     * 카테고리별 지출 조회
     * 카테고리: 미용, 식비, 생필품, 교육, 병의원, 경조사, 의류, 공과금, 통신비, 교통비, 세금, 보험
     */
    public List<Expense> findExpenseByCategory(Long memberId, String category) {
        return em.createQuery(
                        "select e from Expense e where e.member.id = :memberId " +
                                "and e.category = :category order by e.date desc",
                        Expense.class)
                .setParameter("memberId", memberId)
                .setParameter("category", category)
                .getResultList();
    }

    /**
     * 월별 총 지출 계산
     */
    public BigDecimal calculateMonthlyExpense(Long memberId, int year, int month) {
        BigDecimal result = em.createQuery(
                        "select sum(e.amount) from Expense e where e.member.id = :memberId " +
                                "and year(e.date) = :year and month(e.date) = :month",
                        BigDecimal.class)
                .setParameter("memberId", memberId)
                .setParameter("year", year)
                .setParameter("month", month)
                .getSingleResult();

        return result != null ? result : BigDecimal.ZERO;
    }

    /**
     * 월별 카테고리별 지출 합계
     */
    public BigDecimal calculateMonthlyCategoryExpense(Long memberId, int year, int month, String category) {
        BigDecimal result = em.createQuery(
                        "select sum(e.amount) from Expense e where e.member.id = :memberId " +
                                "and year(e.date) = :year and month(e.date) = :month " +
                                "and e.category = :category",
                        BigDecimal.class)
                .setParameter("memberId", memberId)
                .setParameter("year", year)
                .setParameter("month", month)
                .setParameter("category", category)
                .getSingleResult();

        return result != null ? result : BigDecimal.ZERO;
    }

    /**
     * 지출 삭제
     */
    public void expenseDelete(Long id) {
        Expense expense = expenseFindOne(id);
        if (expense != null) {
            em.remove(expense);
        }
    }
}