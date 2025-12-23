package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;

import javax.transaction.Transactional;
import java.util.List;

public class MemberService {

    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
    }
}
