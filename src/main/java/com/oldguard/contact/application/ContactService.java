package com.oldguard.contact.application;

import com.oldguard.auth.support.SecurityUtil;
import com.oldguard.contact.application.dto.ContactResponse;
import com.oldguard.contact.domain.Contact;
import com.oldguard.contact.domain.repository.ContactRepository;
import com.oldguard.contact.exception.ContactNotFoundException;
import com.oldguard.contact.presentation.dto.ContactCreateRequest;
import com.oldguard.contact.presentation.dto.ContactDeleteRequest;
import com.oldguard.contact.presentation.dto.ContactEditRequest;
import com.oldguard.guest.domain.Guest;
import com.oldguard.guest.domain.repository.GuestRepository;
import com.oldguard.member.domain.Member;
import com.oldguard.member.domain.repository.MemberRepository;
import com.oldguard.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final MemberRepository memberRepository;
    private final GuestRepository guestRepository;

    public List<ContactResponse> getContacts(Long id){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        Guest guest = guestRepository.findById(member,id);
        List<ContactResponse> result=new ArrayList<>();
        guest.getContacts().forEach(contact -> result.add(ContactResponse.builder()
                .contactId(contact.getId()).name(contact.getName()).contact(contact.getContact()).build()));
        return result;
    }
    public void addContact(ContactCreateRequest request){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        Guest guest = guestRepository.findById(member,request.getId());
        contactRepository.add(guest, request);
    }
    public void editContact(ContactEditRequest request){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        Guest guest = guestRepository.findById(member,request.getId());
        Optional<Contact> findContact= guest.getContacts().stream()
                .filter(contact -> contact.getContact().equals(request.getBeforeContact())).findAny();
        log.info(findContact.get().getContact(),findContact.get().getName());
        findContact.ifPresent(contact -> contact.updateContact(request.getContact(), request.getName()));
        log.info(findContact.get().getContact(),findContact.get().getName());
    }

    public void deleteContact(ContactDeleteRequest request){
        Member member=memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(MemberNotFoundException::new);
        Guest guest = guestRepository.findById(member,request.getId());
        List<Contact> contacts=guest.getContacts();
        Optional<Contact> findContact=contacts.stream()
                .filter(contact -> contact.getContact().equals(request.getContact())).findAny();
        if(findContact.isPresent()) {
            contactRepository.delete(guest,findContact.get());
        }else{
            throw new ContactNotFoundException();
        }
    }
}
