package com.oldguard.member.domain.repository;

import com.oldguard.member.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Hashtable;
import java.util.Optional;

@Slf4j
@Repository
public class MemberRepository {
    private static Long id=0L;
    //BPlusTree<Member> members=new BPlusTree<>();
    Hashtable<Long,Member> members=new Hashtable<>();

    public void save(Member member){
        log.info("회원가입 성공");
        member.updateId(id);
        members.put(id++,member);
    }
    public void delete(Member member){
        members.remove(member.getId());
    }
    public Optional<Member> findByMember(Member member){
        return members.values().stream()
                .filter(findMember -> findMember.equals(member)).findAny();
    }
    public Optional<Member> findByEmail(String email){
        return members.values().stream()
                .filter(member -> member.getUsername().equals(email)).findAny();
    }
}
