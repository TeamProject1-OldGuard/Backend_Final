package com.oldguard.guest.application;

import com.oldguard.auth.support.JwtTokenProvider;
import com.oldguard.auth.support.SecurityUtil;
import com.oldguard.code.domain.repository.CodeRepository;
import com.oldguard.code.exception.CodeNotFoundException;
import com.oldguard.guest.application.dto.GuestCreateResponse;
import com.oldguard.guest.application.dto.GuestListResponse;
import com.oldguard.guest.application.dto.GuestResponse;
import com.oldguard.guest.domain.Guest;
import com.oldguard.guest.domain.repository.GuestRepository;
import com.oldguard.guest.presentation.dto.*;
import com.oldguard.member.domain.Member;
import com.oldguard.member.domain.repository.MemberRepository;
import com.oldguard.member.exception.MemberNotFoundException;
import com.oldguard.record.domain.Record;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuestService {
    private static Long id=0L;
    private final GuestRepository guestRepository;
    private final MemberRepository memberRepository;
    private final CodeRepository codeRepository;
    private final JwtTokenProvider jwtTokenProvider;
    public GuestCreateResponse  joinGuest(GuestCreateRequest request){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        codeRepository.findByCode(request.getCode())
                .orElseThrow(CodeNotFoundException::new);
        guestRepository.joinGuest(member,Guest.builder().id(id).deleted(false).address(request.getAddress()).name(request.getName()).contacts(new ArrayList<>()).build());
        return GuestCreateResponse.builder().id(id++).accessToken(jwtTokenProvider.createAccessToken(member)).build();
    }
    public void editGuest(GuestEditRequest request){
        Guest guest= findGuest(request.getId());
        guest.updateSleepTime(request.getSleepStartTime(),request.getSleepEndTime());
        guest.updateMessageTime(request.getEmergencyTime());
        guest.updateCallTime(request.getCallTime());
        guest.updateEmergencyTime(request.getEmergencyTime());
    }
    public List<GuestListResponse> getGuests(){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        List<GuestListResponse> result=new ArrayList<>();
        for(Guest guest: member.getGuests()){
            result.add(GuestListResponse.builder().id(guest.getId())
                    .name(guest.getName()).build());
        }
        return result;
    }

    public GuestResponse getGuest(Long id){
        Guest guest= findGuest(id);
        return GuestResponse.builder().guestName(guest.getName()).contacts(guest.getContacts()).messageTime(guest.getMessageTime())
                .callTime(guest.getCallTime()).emergencyTime(guest.getEmergencyTime()).sleepStartTime(guest.getSleepStartTime())
                .sleepEndTime(guest.getSleepEndTime()).build();
    }

    public void updateTimeLimit(TimeLimitRequest request){
        Guest guest= findGuest(request.getId());
        guest.updateMessageTime(request.getMessageTime());
        guest.updateCallTime(request.getCallTime());
        guest.updateEmergencyTime(request.getEmergencyTime());
        log.info("시간제한 설정 완료");
    }

//    public void deleteGuest(GuestDeleteRequest request){
//        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
//                .orElseThrow(MemberNotFoundException::new);
//        Guest guest=guestRepository.findById(member, request.getId());
//        member.getDeleteRecord().add(Record.builder().guestName(guest.getName()).time(LocalDateTime.now()).build());
//        System.out.println("삭제 전"+member.getGuests());
//        guest.delete();
//        System.out.println("삭제 후"+member.getGuests());
//    }

//    public Long deleteGuest(GuestDeleteRequest request){
//        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
//                .orElseThrow(MemberNotFoundException::new);
//        Guest guest=guestRepository.findById(member, request.getId());
//        member.getDeleteRecord().add(Record.builder().guestName(guest.getName()).time(LocalDateTime.now()).build());
//        guest.delete();
//        return guest.getId();
//    }

    public Long deleteGuest(GuestDeleteRequest request){
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        Guest guest=guestRepository.findById(member, request.getId());
        member.getDeleteRecord().add(Record.builder().id(guest.getId()).guestName(guest.getName()).time(LocalDateTime.now()).build());
        guest.delete();
        return guest.getId();
    }
//    public void restorationGuest(GuestRestorationRequest request){
//        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
//                .orElseThrow(MemberNotFoundException::new);
//        Guest guest=guestRepository.findById(member, request.getId());
//        guest.restore();
//        Record findRecord=member.getDeleteRecord().stream().filter(record -> record.getGuestName().equals(guest.getName())).findAny().orElseThrow();
//        member.getDeleteRecord().remove(findRecord);
//    }
    public void restorationGuest(GuestRestorationRequest request){
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        log.info("게스트 찾기 시작");
        Guest guest=guestRepository.findByOriginId(member, request.getId());
        log.info("게스트 찾기 성공");
        guest.restore();
        Record findRecord=member.getDeleteRecord().stream().filter(record -> record.getGuestName().equals(guest.getName())).findAny().orElseThrow();
        member.getDeleteRecord().remove(findRecord);
    }

    public void updateSleepTime(SleepTimeRequest request) {
        Guest guest= findGuest(request.getId());
        guest.updateSleepTime(request.getSleepStartTime(), request.getSleepEndTime());
        log.info("수면시간 설정 완료");
    }

//    private Guest findGuest(Long id){
//        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
//                .orElseThrow(MemberNotFoundException::new);
//        return guestRepository.findById(member, id);
//    }

    private Guest findGuest(Long id){
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        return guestRepository.findById(member, id);
    }
    private Guest findOriginGuest(Long id){
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        return guestRepository.findByOriginId(member, id);
    }
}
