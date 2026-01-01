package com.budget.service;

import com.budget.Member.MemberService;
import com.budget.domain.Member;
import com.budget.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception{

        //given
        Member member = new Member();
        member.setName("park");

        //when
        Long saveId = memberService.memberJoin(member);
        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{

        Member member1 = new Member();
        member1.setName("park1");

        Member member2 = new Member();
        member2.setName("park1");

        //when
        memberService.memberJoin(member1);
        memberService.memberJoin(member2);

        //then (위에서 터져서 끝내야 해서 여기까지 오면 또 실패임)
        fail("예외가 발생해야 한다.");

    }
}