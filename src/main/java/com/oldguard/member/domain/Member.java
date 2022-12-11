package com.oldguard.member.domain;

import com.oldguard.guest.domain.Guest;
import com.oldguard.record.domain.Record;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
public class Member implements UserDetails {
    private Long id;
    private String userName;
    private String name;
    private List<Guest> guests;
    private List<Record> deleteRecord;
    private List<Record> sendRecord;
    private Role role;

    public void updateId(Long id){
        this.id=id;
    }
    public void updateGuest(Guest guest){this.guests.add(guest);}
    public List<Guest> getGuests(){
        List<Guest> result=new ArrayList<>();
        for(Guest guest:guests){
            if(!guest.getDeleted()){
                result.add(guest);
            }
        }
        return result;
    }

    public List<Guest> getOriginGuests(){
        return guests;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Builder
    public Member(Long id, String userName, String name, List<Guest> guests, List<Record> deleteRecord, List<Record> sendRecord, Role role) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.guests = new ArrayList<>();
        this.deleteRecord = new ArrayList<>();
        this.sendRecord = new ArrayList<>();
        this.role = role;
    }
}
