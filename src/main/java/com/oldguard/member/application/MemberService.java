package com.oldguard.member.application;

import com.oldguard.auth.support.SecurityUtil;
import com.oldguard.member.domain.Member;
import com.oldguard.member.domain.repository.MemberRepository;
import com.oldguard.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void deleteMember(){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                        .orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
    }
}
