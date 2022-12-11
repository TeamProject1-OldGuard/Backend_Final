package com.oldguard.guest.domain.repository;

import com.oldguard.guest.domain.Guest;
import com.oldguard.guest.exception.GuestNotFoundException;
import com.oldguard.member.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class GuestRepository {
    private static Long id=0L;
    //BPlusTree<Guest> guests=new BPlusTree<>();
    Hashtable<Long,Guest> guests=new Hashtable<>();
    
    public void joinGuest(Member member, Guest guest){
        member.updateGuest(guest);
        guests.put(id++,guest);
    }

//    public Guest findById(Member member,Long id){
//        Optional<Guest> guest=member.getGuests().stream().filter(findGuest -> !findGuest.getDeleted()&&findGuest.getId().equals(id)).findAny();
//        guest.orElseThrow(GuestNotFoundException::new);
//        return guest.get();
//    }

    public Guest findById(Member member,Long guestId){
        System.out.println(member.getGuests());
        log.info(member.getGuests().toString());
        log.info(guestId.toString());
        Optional<Guest> guest=member.getGuests().stream().filter(findGuest -> !findGuest.getDeleted() &&findGuest.getId().equals(guestId)).findAny();
        guest.orElseThrow(GuestNotFoundException::new);
        return guest.get();
    }

    public Guest findByOriginId(Member member,Long guestId){
        Optional<Guest> guest=member.getOriginGuests().stream().filter(findGuest -> findGuest.getDeleted() &&findGuest.getId().equals(guestId)).findAny();
        System.out.println(guest.get().getId());
        guest.orElseThrow(GuestNotFoundException::new);
        return guest.get();
    }

    public List<Guest> findSendSmsGuests(){
        List<Guest> result=new ArrayList<>();
        try {
            result=guests.values().stream().filter(guest -> (Duration.between(guest.getActivateTime(), LocalDateTime.now()).getSeconds()
                            > (guest.getMessageTime() * 10))).collect(Collectors.toList());
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return result;
    }
    public List<Guest> findSendCallGuests(){
        List<Guest> result=new ArrayList<>();
        try {
            result=guests.values().stream().filter(guest -> (Duration.between(guest.getActivateTime(), LocalDateTime.now()).getSeconds()
                    > (guest.getCallTime() * 10))).collect(Collectors.toList());
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return result;
    }
    public List<Guest> findSleepGuests(){
        LocalTime now=LocalTime.now();
        List<Guest> result=new ArrayList<>();
        try {
            result= guests.values().stream()
                    .filter(guest -> guest.getSleepStartTime().isAfter(now) &&
                            guest.getSleepEndTime().isBefore(now)).collect(Collectors.toList());
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return result;
    }
}
