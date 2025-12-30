package com.budget.repository;

import com.budget.domain.Income;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class IncomeRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 수입 저장
     */
    public void incomeSave(Income income) {
        em.persist(income);
    }

    /**
     * 특정 수입 조회
     */
    public Income incomeFindOne(Long id) {
        return em.find(Income.class, id);
    }

    /**
     * 회원의 전체 수입 조회
     */
    public List<Income> findIncomeByMemberId(Long memberId) {
        return em.createQuery(
                        "select i from Income i where i.member.id = :memberId order by i.date desc",
                        Income.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    /**
     * 기간별 수입 조회 (ex: 1월 1일 ~ 1월 31일)
     */
    public List<Income> findIncomeByDateRange(Long memberId, LocalDate startDate, LocalDate endDate) {
        return em.createQuery(
                        "select i from Income i where i.member.id = :memberId " +
                                "and i.date between :startDate and :endDate order by i.date desc",
                        Income.class)
                .setParameter("memberId", memberId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    /**
     * 카테고리별 수입 조회 (월급, 용돈 등)
     */
    public List<Income> findIncomeByCategory(Long memberId, String category) {
        return em.createQuery(
                        "select i from Income i where i.member.id = :memberId " +
                                "and i.category = :category order by i.date desc",
                        Income.class)
                .setParameter("memberId", memberId)
                .setParameter("category", category)
                .getResultList();
    }

    /**
     * 월별 총 수입 계산
     */
    public BigDecimal calculateMonthlyIncome(Long memberId, int year, int month) {
        BigDecimal result = em.createQuery(
                        "select sum(i.amount) from Income i where i.member.id = :memberId " +
                                "and year(i.date) = :year and month(i.date) = :month",
                        BigDecimal.class)
                .setParameter("memberId", memberId)
                .setParameter("year", year)
                .setParameter("month", month)
                .getSingleResult();

        return result != null ? result : BigDecimal.ZERO;
    }

    /**
     * 수입 삭제
     */
    public void incomeDelete(Long id) {
        Income income = incomeFindOne(id);
        if (income != null) {
            em.remove(income);
        }
    }
}