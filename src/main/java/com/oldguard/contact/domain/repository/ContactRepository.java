package com.oldguard.contact.domain.repository;

import com.oldguard.contact.domain.Contact;
import com.oldguard.contact.presentation.dto.ContactCreateRequest;
import com.oldguard.guest.domain.Guest;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    private static Long id=0L;
    public void add(Guest guest, ContactCreateRequest request){
        guest.updateContacts(Contact.builder()
                .id(id++).name(request.getName()).contact(request.getContact()).build());
    }
    public void delete(Guest guest,Contact contact){
        guest.getContacts().remove(contact);
    }
}
