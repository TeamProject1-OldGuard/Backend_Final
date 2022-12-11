package com.oldguard.member.domain.repository;

import com.oldguard.member.domain.Member;
import com.oldguard.member.domain.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    private final MemberRepository memberRepository=new MemberRepository();
    @Test
    void add(){
        Member member=Member.builder().id(1L).name("호지니").userName("8528231@naver.com").role(Role.MEMBER).build();
        memberRepository.save(member);
        Assertions.assertEquals(member,memberRepository.findByMember(member).get());
    }
}