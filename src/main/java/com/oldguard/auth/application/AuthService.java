package com.oldguard.auth.application;

import com.oldguard.auth.support.JwtTokenProvider;

import com.oldguard.code.domain.Code;
import com.oldguard.code.domain.repository.CodeRepository;
import com.oldguard.contact.domain.Contact;
import com.oldguard.guest.domain.Guest;
import com.oldguard.guest.domain.repository.GuestRepository;
import com.oldguard.member.domain.Member;
import com.oldguard.member.domain.Role;
import com.oldguard.member.domain.repository.MemberRepository;
import com.oldguard.auth.support.OAuth2Attribute;
import com.oldguard.record.domain.Record;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final GuestRepository guestRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final CodeRepository codeRepository;

    public String getKakaoUserInfo(String accessToken) {
        codeRepository.save(Code.builder().code("ABCDEF").id(0L).build());
        // 카카오에 요청 보내기 및 응답 받기
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kapi.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        JSONObject response = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/v2/user/me").build())
                .header("Authorization", "Bearer " + accessToken)
                .retrieve().bodyToMono(JSONObject.class).block();

        Map<String, Object> kakaoAccount  = (Map<String, Object>) response.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        // 받은 응답에서 원하는 정보 추출하기 (여기의 경우 회원 고유번호와 카카오 닉네임)
        OAuth2Attribute oAuth2Attribute=OAuth2Attribute.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .attributes(kakaoAccount)
                .attributeKey("email")
                .build();

        // 추출한 정보로 원하는 처리를 함
        Optional<Member> member= memberRepository.findByEmail(oAuth2Attribute.getEmail());
        if(member.isEmpty()){
            member= Optional.ofNullable(createMember(oAuth2Attribute));
            memberRepository.save(member.get());
        }
//        insertData(member.get());

        return jwtTokenProvider.createAccessToken(member.get());
    }

    private Member createMember(OAuth2Attribute oAuth2Attribute){
        return Member.builder()
                .userName(oAuth2Attribute.getEmail())
                .name(oAuth2Attribute.getName())
                .sendRecord(new ArrayList<>())
                .deleteRecord(new ArrayList<>())
                .role(Role.MEMBER)
                .build();
    }
    private void insertData(Member member){
        List<Contact> contacts=new ArrayList<>();
        List<Record> sendRecord= member.getSendRecord();
        List<Record> deleteRecord=member.getDeleteRecord();
        contacts.add(Contact.builder().contact("01041113131").name("김예린").build());
        contacts.add(Contact.builder().contact("01083263424").name("장호진").build());
        contacts.add(Contact.builder().contact("01044444444").name("정다현").build());
        List<Contact> contacts2=new ArrayList<>();
        contacts2.add(Contact.builder().contact("01013213131").name("신초은").build());
        contacts2.add(Contact.builder().contact("01012383183").name("권용재").build());
        contacts2.add(Contact.builder().contact("01051858181").name("김선욱").build());
        List<Contact> contacts3=new ArrayList<>();
        contacts3.add(Contact.builder().contact("01013913919").name("김광호").build());
        contacts3.add(Contact.builder().contact("01012382158").name("이서준").build());
        contacts3.add(Contact.builder().contact("01013913933").name("원현정").build());

        Guest guest1=Guest.builder().address("경기도 성남시 성남동").id(0L).name("윤혜민")
                .sleepStartTime(LocalTime.MIDNIGHT).sleepEndTime(LocalTime.NOON)
                .activateTime(LocalDateTime.now()).callTime(6L).messageTime(4L).emergencyTime(2L)
                .contacts(contacts3).build();
        Guest guest2=Guest.builder().address("경기도 용인시 역북동").id(1L).name("정민서").contacts(contacts2)
                .sleepStartTime(LocalTime.MIDNIGHT).sleepEndTime(LocalTime.NOON)
                .activateTime(LocalDateTime.now()).callTime(6L).messageTime(4L).emergencyTime(2L).build();
        Guest guest3=Guest.builder().address("경기도 용인시 풍덕천동").id(2L).name("박세진")
                .sleepStartTime(LocalTime.MIDNIGHT).sleepEndTime(LocalTime.NOON)
                .activateTime(LocalDateTime.now()).callTime(6L).messageTime(4L).emergencyTime(2L).contacts(contacts).build();
        sendRecord.add(Record.builder().type("문자").time(LocalDateTime.now().minusDays(12)).build());
        sendRecord.add(Record.builder().type("문자").time(LocalDateTime.now().minusDays(11)).build());
        sendRecord.add(Record.builder().type("문자").time(LocalDateTime.now().minusDays(10)).build());
        sendRecord.add(Record.builder().type("문자").time(LocalDateTime.now().minusDays(9)).build());
        sendRecord.add(Record.builder().type("문자").time(LocalDateTime.now().minusDays(8)).build());
        sendRecord.add(Record.builder().type("전화").time(LocalDateTime.now().minusDays(7)).build());
        sendRecord.add(Record.builder().type("문자").time(LocalDateTime.now().minusDays(6)).build());
        sendRecord.add(Record.builder().type("문자").time(LocalDateTime.now().minusDays(5)).build());
        sendRecord.add(Record.builder().type("전화").time(LocalDateTime.now().minusDays(4)).build());
        sendRecord.add(Record.builder().type("문자").time(LocalDateTime.now().minusDays(3)).build());
        sendRecord.add(Record.builder().type("문자").time(LocalDateTime.now().minusDays(2)).build());
        sendRecord.add(Record.builder().type("전화").time(LocalDateTime.now().minusDays(1)).build());

        guestRepository.joinGuest(member,guest1);
        guestRepository.joinGuest(member,guest2);
        guestRepository.joinGuest(member,guest3);
    }
}
