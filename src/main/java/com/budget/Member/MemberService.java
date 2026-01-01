package com.budget.Member;

import com.budget.domain.Member;
import com.budget.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public Long memberJoin(Member member){
        //중복 id 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    @Transactional(readOnly=true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
    @Transactional(readOnly=true)
    public Member findMemberOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
