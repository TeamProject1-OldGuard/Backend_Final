package com.oldguard.guest.domain.repository;

import com.oldguard.contact.domain.Contact;
import com.oldguard.guest.domain.Guest;
import com.oldguard.member.domain.Member;
import com.oldguard.member.domain.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestRepositoryTest {
    private final MemberRepository memberRepository=new MemberRepository();

    private final GuestRepository guestRepository=new GuestRepository();
    @Test
    void test(){
        List<Contact> contacts=new ArrayList<>();
        Guest guest= Guest.builder().id(0L).activateTime(LocalDateTime.now().minusMinutes(3))
                .contacts(contacts).sleepStartTime(LocalTime.MIDNIGHT)
                .sleepEndTime(LocalTime.NOON).messageTime(1L).build();
        contacts.add(Contact.builder().contact("01077437539").id(0L).build());
        Member member=Member.builder().id(0L).build();
        guestRepository.joinGuest(member,guest);
        memberRepository.save(member);
        assertNull(guestRepository.findSleepGuests());
        Assertions.assertEquals(guestRepository.findSendSmsGuests().get(0),member.getGuests().get(0));
    }
}