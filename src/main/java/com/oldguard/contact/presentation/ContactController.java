package com.oldguard.contact.presentation;

import com.oldguard.contact.application.ContactService;
import com.oldguard.contact.application.dto.ContactResponse;
import com.oldguard.contact.presentation.dto.ContactCreateRequest;
import com.oldguard.contact.presentation.dto.ContactDeleteRequest;
import com.oldguard.contact.presentation.dto.ContactEditRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    @GetMapping
    @ApiOperation(value = "연락처 정보 조회")
    public ResponseEntity<List<ContactResponse>> getContacts(@RequestParam Long id){
        return ResponseEntity.status(200).body(contactService.getContacts(id));
    }
    @PostMapping
    @ApiOperation("연락처 등록")
    public ResponseEntity<String> addContact(@RequestBody @Validated ContactCreateRequest request){
        contactService.addContact(request);
        return ResponseEntity.status(200).body("연락처 등록 성공");
    }

    @PatchMapping
    @ApiOperation("연락처 수정")
    public ResponseEntity<String> editContact(@RequestBody @Validated ContactEditRequest request){
        contactService.editContact(request);
        return ResponseEntity.status(200).body("연락처 수정 성공");
    }
    @DeleteMapping
    @ApiOperation("연락처 삭제")
    public ResponseEntity<String> deleteContact(@RequestBody @Validated ContactDeleteRequest request){
        contactService.deleteContact(request);
        return ResponseEntity.status(200).body("연락처 삭제 성공");
    }
}
