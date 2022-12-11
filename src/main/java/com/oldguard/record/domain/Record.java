package com.oldguard.record.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Record {
    private LocalDateTime time;
    private String guestName;
    private String type;
    private Long id;
    @Builder

    public Record(LocalDateTime time, String guestName, String type, Long id) {
        this.time = time;
        this.guestName = guestName;
        this.type = type;
        this.id = id;
    }


}
