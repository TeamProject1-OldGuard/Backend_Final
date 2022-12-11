package com.oldguard.record.application;

import com.oldguard.auth.support.SecurityUtil;
import com.oldguard.member.domain.Member;
import com.oldguard.member.domain.repository.MemberRepository;
import com.oldguard.member.exception.MemberNotFoundException;
import com.oldguard.record.application.dto.DeleteRecordResponse;
import com.oldguard.record.application.dto.SendRecordResponse;
import com.oldguard.record.domain.Record;
import com.oldguard.record.presentation.dto.RecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final MemberRepository memberRepository;
    public void saveSendRecord(String name,String type){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        member.getSendRecord().add(Record.builder().time(LocalDateTime.now()).guestName(name).type(type).build());
    }
//    public List<DeleteRecordResponse> getDeleteRecords(RecordRequest request){
//        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
//                .orElseThrow(MemberNotFoundException::new);
//        List<DeleteRecordResponse> result=new ArrayList<>();
//        for(Record record: member.getDeleteRecord()){
//            result.add(DeleteRecordResponse.builder().name(record.getGuestName()).localDateTime(record.getTime()).build());
//        }
//        if(request.getSort().equals("최신순")) {
//            result.sort(Comparator.comparing(DeleteRecordResponse::getLocalDateTime));
//        }else{
//            result.sort((d1,d2)->d2.getLocalDateTime().compareTo(d1.getLocalDateTime()));
//        }
//        return result;
//    }

    public List<DeleteRecordResponse> getDeleteRecords(RecordRequest request){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        List<DeleteRecordResponse> result=new ArrayList<>();
        for(Record record: member.getDeleteRecord()){
            result.add(DeleteRecordResponse.builder().id(record.getId()).name(record.getGuestName()).localDateTime(record.getTime()).build());
        }
        if(request.getSort().equals("최신순")) {
            result.sort(Comparator.comparing(DeleteRecordResponse::getLocalDateTime));
        }else{
            result.sort((d1,d2)->d2.getLocalDateTime().compareTo(d1.getLocalDateTime()));
        }
        return result;
    }
    public List<SendRecordResponse> getSendRecords(RecordRequest request){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        List<SendRecordResponse> result=new ArrayList<>();
        for(Record record: member.getSendRecord()){
            result.add(SendRecordResponse.builder().type(record.getType()).name(record.getGuestName()).localDateTime(record.getTime()).build());
        }
        if(request.getSort().equals("최신순")) {
            result.sort(Comparator.comparing(SendRecordResponse::getLocalDateTime));
        }else{
            result.sort((d1,d2)->d2.getLocalDateTime().compareTo(d1.getLocalDateTime()));
        }
        return result;
    }
}
